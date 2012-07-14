/*
 * I, the copyright holder of this work, hereby release it into the public domain. This applies worldwide.
 *
 * In case this is not legally possible, I grant any entity the right to use this work for any purpose,
 * without any conditions, unless such conditions are required by law.
 */

package com.stackoverflow;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * @author Glenn Bech
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Comment {

    @JsonProperty("comment_id")
    private long commentId;

    @JsonProperty("creation_date")
    private long creationDate;

    @JsonProperty("body")
    private String body;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
