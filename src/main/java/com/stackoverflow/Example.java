package com.stackoverflow;

import java.io.IOException;

/**
 * @author Glenn Bech
 */
public class Example {

    static final int USER_GLENNBECH = 428400;
    static final int PAGE_NUMBER_ONE_BASED = 1;

    public static void main(String[] args) throws IOException {

        StackOverFlow so = new StackOverFlow();
        QuestionResult result = so.getQuestionsForUser(USER_GLENNBECH, PAGE_NUMBER_ONE_BASED);
        for (Question q : result.getQuestions()) {
            System.out.println(q);
        }
    }

}
