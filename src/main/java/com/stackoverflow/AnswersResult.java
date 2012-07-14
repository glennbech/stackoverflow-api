package com.stackoverflow;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

/**
 * @author Glenn Bech
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AnswersResult {

    private int total;
    private int page;
    private int pagesize;

    @JsonProperty("answers")
    List<Answer> answers;

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPagesize() {
        return pagesize;
    }

    public void setPagesize(int pagesize) {
        this.pagesize = pagesize;
    }

    @Override
    public String toString() {
        return "AnswersResult{" +
                "total=" + total +
                ", page=" + page +
                ", pagesize=" + pagesize +
                ", answers=" + answers +
                '}';
    }


}
