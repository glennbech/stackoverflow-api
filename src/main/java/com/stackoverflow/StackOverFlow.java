/*
 * "I, the copyright holder of this work, hereby release it into the public domain. This applies worldwide.
 *
 * In case this is not legally possible, I grant any entity the right to use this work for any purpose,
 * without any conditions, unless such conditions are required by law."
 */

package com.stackoverflow;


import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;

/**
 * Class for interfacing with the StackOverflow Rest based API.
 * Some methods take Vectorized Ids as arguments. These are multiple ids with semicolon
 * delimited ( 1212;323;3434;2323)
 * <p/>
 * All pages are based on page 1 as the first page not 0.
 *
 * @author Glenn Bech
 */

public class StackOverFlow {

    /**
     * Some of the methods are page based. This constants decides how many items to read at once.
     *
     * @todo move into constructor
     */
    static final int DEFAULT_PAGESIZE = 5;
    /**
     * What site fetch data from.
     *
     * @todo move into constructor
     */
    private static final String STACK_OVERFLOW_API_DOMAIN = "api.stackoverflow.com";

    private static String URL_TEMPLATE_QUESTIONS_BY_USER = "http://" + STACK_OVERFLOW_API_DOMAIN + "/1.1/users/{0}/questions?pagesize={1}&page={2}&min={3}";
    private static String URL_TEMPLATE_QUESTION = "http://" + STACK_OVERFLOW_API_DOMAIN + "/1.1/questions/{0}?pagesize={1}&page={2}&min={3}";
    private static String URL_TEMPLATE_ANSWERS = "http://" + STACK_OVERFLOW_API_DOMAIN + "/1.1/users/{0}/answers?pagesize={1}&page={2}&min={3}";

    private static String URL_TEMPLATE_QUESTION_TIMELINE = "http://" + STACK_OVERFLOW_API_DOMAIN + "/1.1/questions/{0}/timeline?pagesize={1}&page={2}";
    private static String URL_TEMPLATE_USERS_SEARCH = "http://" + STACK_OVERFLOW_API_DOMAIN + "/1.1/users?filter={0}";
    private static String URL_TEMPLATE_USER = "http://" + STACK_OVERFLOW_API_DOMAIN + "/1.1/users/{0}";
    private static String URL_TEMPLATE_USER_TIMELINE = "http://" + STACK_OVERFLOW_API_DOMAIN + "/1.1/users/{0}/timeline";

    private static MessageFormat queryTemplateGetQuestion = new MessageFormat(URL_TEMPLATE_QUESTION);
    private static MessageFormat queryTemplateQuestionsTimeline = new MessageFormat(URL_TEMPLATE_QUESTION_TIMELINE);
    private static MessageFormat queryTemplateGetAnswers = new MessageFormat(URL_TEMPLATE_ANSWERS);

    private static MessageFormat queryTemplateUsers = new MessageFormat(URL_TEMPLATE_USERS_SEARCH);
    private static MessageFormat queryTemplateUser = new MessageFormat(URL_TEMPLATE_USER);
    private static MessageFormat queryTemplateUserTimeline = new MessageFormat(URL_TEMPLATE_USER_TIMELINE);
    private static MessageFormat queryTemplateUsersQuestions = new MessageFormat(URL_TEMPLATE_QUESTIONS_BY_USER);


    /**
     * Get details for a list of questions based on a vectorized list of Ids
     *
     * @param vectorizedIds
     * @param answers       if true, returns all answers to the given questions as well.
     * @param bodies        if true, include bodies of the questions
     * @param comments      if true, include comments
     * @return The  questions identified by the Ids given as argumnets.
     * @throws IOException
     */
    public QuestionResult getQuestions(String vectorizedIds, boolean answers, boolean bodies, boolean comments) throws IOException {

        String[] arguments = new String[]{vectorizedIds};
        StringBuffer sbUrl = new StringBuffer(queryTemplateGetQuestion.format(arguments));

        if (bodies || comments || answers) {
            sbUrl.append("?");
        }

        if (bodies) {
            sbUrl.append("&body=true");
        }

        if (comments) {
            sbUrl.append("&comments=true");
        }

        if (answers) {
            sbUrl.append("&answers=true");
        }
        return readObjectFromURL(sbUrl.toString(), QuestionResult.class);
    }

    /**
     * Get a list of questions, without answers, message bodies or comments.
     *
     * @param vectorizedIds
     * @return
     * @throws IOException
     */
    public QuestionResult getQuestions(String vectorizedIds) throws IOException {
        return getQuestions(vectorizedIds, false, false, false);
    }

    /**
     * Get the Timeline events related to questions, like "accepted", "upvote", for a given set of questions.
     *
     * @param vectorizedIds the question Ids to get a timeline for
     * @param page          what page to get, zero based
     * @return
     * @throws IOException
     */
    public PostTimelineResult getTimeLine(String vectorizedIds, int page) throws IOException {

        Object[] arguments = new Object[]{vectorizedIds, DEFAULT_PAGESIZE, page};
        String urlString = queryTemplateQuestionsTimeline.format(arguments);
        return readObjectFromURL(urlString, PostTimelineResult.class);
    }

    /**
     * Get all timeline entries for the given list of questions. will fetch all events. Please beware that one
     * Http request till be done per page of data.
     *
     * @param vectorizedIds
     * @return
     * @throws IOException
     */
    public List<TimelineEntry> getTimeLine(String vectorizedIds) throws IOException {

        List<TimelineEntry> allTimeLineEntries = new ArrayList<TimelineEntry>();
        PostTimelineResult res = getTimeLine(vectorizedIds, 1);
        allTimeLineEntries.addAll(res.getTimeLineResult());

        if (res.getTotal() > res.getPagesize()) {
            int totalPages = (int) Math.ceil(res.getTotal() / res.getPagesize());
            for (int i = 2; i <= totalPages + 1; i++) {
                PostTimelineResult pageOfEntries = getTimeLine(vectorizedIds, i);
                allTimeLineEntries.addAll(pageOfEntries.getTimeLineResult());
            }
        }
        return allTimeLineEntries;
    }

    /**
     * Query stackoverflow for users based on a filter.
     *
     * @param wildcard input to a wildcard search
     * @return the list of users that match the wild card
     * @throws IOException
     */
    public Users getUsers(String wildcard) throws IOException {
        String[] arguments = new String[]{URLEncoder.encode(wildcard)};
        String urlString = queryTemplateUsers.format(arguments);
        return readObjectFromURL(urlString, Users.class);
    }

    /**
     * Get details for a specified user
     *
     * @param userId
     * @return
     * @throws IOException
     */
    public User getUser(long userId) throws IOException {
        String[] arguments = new String[]{String.valueOf(userId)};
        String urlString = queryTemplateUser.format(arguments);
        Users u = readObjectFromURL(urlString, Users.class);
        return u.getUserList().get(0);
    }

    /**
     * Get the timeline for a user
     *
     * @param userId the stackoverflow user Id
     * @return
     * @throws IOException
     */
    public UserTimelineResult getUserTimeline(long userId) throws IOException {
        String[] arguments = new String[]{String.valueOf(userId)};
        String urlString = queryTemplateUserTimeline.format(arguments);
        return readObjectFromURL(urlString, UserTimelineResult.class);
    }

    /**
     * @param userId
     * @param afterDate
     * @return
     * @throws IOException
     */
    public List<Question> getQuestionsWithActivityAfter(final long userId, long afterDate) throws IOException {
        final List<Question> questions = new ArrayList<Question>();
        final QuestionResult res = getQuestionsForUser(userId, 1, afterDate);
        questions.addAll(res.getQuestions());

        if (res.getTotal() == 0) {
            return new ArrayList<Question>();
        }

        if (res.getTotal() > res.getPagesize()) {
            int totalPages = (int) Math.ceil((float) (res.getTotal()) / res.getPagesize());
            for (int i = 2; i <= totalPages + 1; i++) {
                QuestionResult pageOfQuestions = getQuestionsForUser(userId, i, afterDate);
                questions.addAll(pageOfQuestions.getQuestions());
            }
        }

        StringBuffer vectorizedIds = new StringBuffer();
        Map<Long, Question> questionMap = new HashMap<Long, Question>();

        for (Question q : questions) {
            questionMap.put(q.getQuestionId(), q);
            vectorizedIds.append(q.getQuestionId() + ";");
        }

        vectorizedIds.append(questions.get(0).getQuestionId());
        List<TimelineEntry> allTimelineEntries = getTimeLine(vectorizedIds.toString());
        for (TimelineEntry t : allTimelineEntries) {
            Question q = questionMap.get(Long.parseLong(t.getPostId()));
            // not related to a question, might be related to answer
            if (q == null)
                continue;
            if (t.getCreationDate() > afterDate) {
                q.getTimelineEntries().add(t);
            }
        }
        return new ArrayList<Question>(questionMap.values());
    }

    public List<Answer> getAnswersWithActivityAfter(long userId, long date) throws IOException {
        final List<Answer> answers = new ArrayList<Answer>();
        AnswersResult res = getAnswersForUser(userId, 1, date);
        answers.addAll(res.getAnswers());
        if (res.getTotal() > res.getPagesize()) {
            int totalPages = (int) Math.ceil(res.getTotal() / res.getPagesize());
            for (int i = 2; i <= totalPages + 1; i++) {
                AnswersResult pageOfAnswers = getAnswersForUser(userId, i, date);
                answers.addAll(pageOfAnswers.getAnswers());
            }
        }
        return answers;
    }


    /**
     * Returns all questions belonging to a given user
     *
     * @param userId
     * @return
     * @throws java.io.IOException
     */

    public QuestionResult getQuestionsForUser(long userId, int pageNumber) throws IOException {
        String url = queryTemplateUsersQuestions.format(new Object[]{String.valueOf(userId), DEFAULT_PAGESIZE, pageNumber, 0});
        return readObjectFromURL(url, QuestionResult.class);
    }

    /**
     * Returns all questions belonging to a given user, but only with activity after a given UNIX EPIOCH in seconds
     *
     * @param userId     the user to get questions from
     * @param pageNumber the pageNumber to get starting with 1
     * @param fromDate   only questions with activity after this date are returned
     * @return
     * @throws java.io.IOException
     */
    public QuestionResult getQuestionsForUser(long userId, int pageNumber, long fromDate) throws IOException {
        String url = queryTemplateUsersQuestions.format(new Object[]{String.valueOf(userId), DEFAULT_PAGESIZE, pageNumber, Long.toString(fromDate)});
        return readObjectFromURL(url, QuestionResult.class);
    }

    /**
     * @param ids
     * @param page
     * @return
     * @throws java.io.IOException
     */
    public PostTimelineResult getQuestionsTimeline(long[] ids, int page) throws IOException {
        String vectorizedIds = vectorize(ids);
        String url = queryTemplateQuestionsTimeline.format(new Object[]{vectorizedIds, page, DEFAULT_PAGESIZE});
        return readObjectFromURL(url, PostTimelineResult.class);
    }

    /**
     * @param userId
     * @param page
     * @return
     * @throws IOException
     */
    public AnswersResult getAnswersForUser(long userId, int page) throws IOException {
        return getAnswersForUser(userId, page, 0);
    }

    /**
     * @param userId
     * @param page
     * @param minDate
     * @return
     * @throws IOException
     */

    public AnswersResult getAnswersForUser(long userId, int page, long minDate) throws IOException {
        String url = queryTemplateGetAnswers.format(new Object[]{String.valueOf(userId), page, DEFAULT_PAGESIZE, minDate});
        return readObjectFromURL(url, AnswersResult.class);
    }


    /**
     * Generic method used read and map an Object from a JSON end point.
     *
     * @param urlString the JSON end point to map
     * @param clazz     what Class to map into
     * @return
     * @throws IOException
     */

    static <T> T readObjectFromURL(String urlString, Class<T> clazz) throws IOException {
        URL url = new URL(urlString);
        URLConnection c = url.openConnection();
        InputStream is = c.getInputStream();

        if ("gzip".equals(c.getContentEncoding())) {
            is = new GZIPInputStream(is);
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(is, clazz);
    }


    /**
     * Utility method to vectorize Ids
     *
     * @param ids
     * @return
     */
    static String vectorize(long[] ids) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < ids.length; i++) {
            stringBuilder.append(ids[i]);
            if (i < ids.length - 1) {
                stringBuilder.append(";");
            }
        }
        return stringBuilder.toString();
    }
}

