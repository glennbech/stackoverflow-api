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

/**
 * @author Glenn Bech
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Owner implements Serializable {

    @JsonProperty("user_id")
    private String userId;

    @JsonProperty("user_type")
    private String userType;


    @JsonProperty("display_name")
    private String displayName;

    @JsonProperty("reputation")
    private int reputation;

    @JsonProperty("email_hash")
    private String emailHash;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public int getReputation() {
        return reputation;
    }

    public void setReputation(int reputation) {
        this.reputation = reputation;
    }
}
