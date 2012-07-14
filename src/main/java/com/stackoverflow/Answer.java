package com.stackoverflow;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * @author Glenn Bech
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Answer extends Post {

    @JsonProperty("answer_id")
    private long answerId;

    @JsonProperty("accepted")
    private boolean accepted;

    @JsonProperty("question_id")
    private long answerTo;

    public long getAnswerId() {
        return answerId;
    }

    public void setAnswerId(long answerId) {
        this.answerId = answerId;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public long getAnswerTo() {
        return answerTo;
    }

    public void setAnswerTo(long answerTo) {
        this.answerTo = answerTo;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "answerId=" + answerId +
                ", accepted=" + accepted +
                ", answerTo=" + answerTo +
                '}' + super.toString();
    }
}
