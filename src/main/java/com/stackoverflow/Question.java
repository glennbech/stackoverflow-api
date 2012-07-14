/*
 * I, the copyright holder of this work, hereby release it into the public domain. This applies worldwide.
 *
 * In case this is not legally possible, I grant any entity the right to use this work for any purpose,
 * without any conditions, unless such conditions are required by law.
 */

package com.stackoverflow;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Glenn Bech
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Question extends Post {

    @JsonProperty("question_id")
    private long questionId;

    @JsonProperty("bounty_closes_date")
    private long bountyClosesDate;

    @JsonProperty("bounty_amount")
    private long bountyAmount;

    @JsonProperty("tags")
    private List<String> tags;

    @JsonProperty("answers")
    private List<Answer> answers;

    private List<TimelineEntry> timelineEntries = new ArrayList<TimelineEntry>();

    public Question(long questionId, long lastActivityDate) {
        this.questionId = questionId;
        this.lastActivityDate = lastActivityDate;
    }


    public Question(long questionId) {
        this.questionId = questionId;
    }

    public Question() {
    }

    public long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(long questionId) {
        this.questionId = questionId;
    }

    public long getBountyClosesDate() {
        return bountyClosesDate;
    }

    public void setBountyClosesDate(long bountyClosesDate) {
        this.bountyClosesDate = bountyClosesDate;
    }


    public long getBountyAmount() {
        return bountyAmount;
    }

    public void setBountyAmount(long bountyAmount) {
        this.bountyAmount = bountyAmount;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }


    public List<Answer> getAnswers() {
        return answers;
    }

    public List<TimelineEntry> getTimelineEntries() {
        return timelineEntries;
    }

    public void setTimelineEntries(List<TimelineEntry> timelineEntries) {
        this.timelineEntries = timelineEntries;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    @Override
    public String toString() {
        return "Question{" +
                "answers=" + answers +
                ", tags=" + tags +
                ", bountyAmount=" + bountyAmount +
                ", bountyClosesDate=" + bountyClosesDate +
                ", questionId=" + questionId +
                '}' + super.toString();
    }
}
