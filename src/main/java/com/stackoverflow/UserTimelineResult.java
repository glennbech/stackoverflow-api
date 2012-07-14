package com.stackoverflow;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

/**
 * @author Glenn Bech
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserTimelineResult {

    @JsonProperty("user_timelines")
    private List<TimelineEntry> entries;


    public List<TimelineEntry> getEntries() {
        return entries;
    }

    public void setEntries(List<TimelineEntry> entries) {
        this.entries = entries;
    }
}
