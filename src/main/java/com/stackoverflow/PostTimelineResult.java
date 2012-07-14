/*
 * I, the copyright holder of this work, hereby release it into the public domain. This applies worldwide.
 *
 * In case this is not legally possible, I grant any entity the right to use this work for any purpose,
 * without any conditions, unless such conditions are required by law.
 */

package com.stackoverflow;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Glenn Bech
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class PostTimelineResult implements Serializable {


    @JsonProperty("pagesize")
    private int pagesize;


    @JsonProperty("total")
    private int total;

    @JsonProperty("post_timelines")
    List<TimelineEntry> timeLineResult = new ArrayList<TimelineEntry>();


    public List<TimelineEntry> getTimeLineResult() {
        return timeLineResult;
    }

    public void setTimeLineResult(List<TimelineEntry> timeLineResult) {
        this.timeLineResult = timeLineResult;
    }

    @Override
    public String toString() {
        return "TimelineResult{" +
                "timeLineResult=" + timeLineResult +
                '}';
    }


    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPagesize() {
        return pagesize;
    }

    public void setPagesize(int pagesize) {
        this.pagesize = pagesize;
    }
}
