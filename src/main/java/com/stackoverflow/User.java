/*
 * I, the copyright holder of this work, hereby release it into the public domain. This applies worldwide.
 *
 * In case this is not legally possible, I grant any entity the right to use this work for any purpose,
 * without any conditions, unless such conditions are required by law.
 */

package com.stackoverflow;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

/**
 * @author Glenn Bech
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

    @JsonProperty("user_id")
    private long userId;

    @JsonProperty("display_name")
    private String displayName;

    @JsonProperty("reputation")
    private String reputation;

    @JsonProperty("last_access_date")
    private long lastAccessdate;

    @JsonProperty("question_count")
    private int questionCount;

    @JsonProperty("answer_count")
    private int answerCount;

    @JsonProperty("accept_rate")
    private float acceptRate;


    @JsonProperty("website_url")
    private String websiteUrl;

    private int age;

    private String location;

    @JsonProperty("up_vote_count")
    private int upVoteCount;

    @JsonProperty("down_vote_count")
    private int downVoteCount;

    private List<TimelineEntry> timeline;

    public String getWebsiteUrl() {
        return websiteUrl;
    }

    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getUpVoteCount() {
        return upVoteCount;
    }

    public void setUpVoteCount(int upVoteCount) {
        this.upVoteCount = upVoteCount;
    }

    public int getDownVoteCount() {
        return downVoteCount;
    }

    public void setDownVoteCount(int downVoteCount) {
        this.downVoteCount = downVoteCount;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getReputation() {
        return reputation;
    }

    public void setReputation(String reputation) {
        this.reputation = reputation;
    }

    public long getLastAccessdate() {
        return lastAccessdate;
    }

    public void setLastAccessdate(long lastAccessdate) {
        this.lastAccessdate = lastAccessdate;
    }

    public int getQuestionCount() {
        return questionCount;
    }

    public void setQuestionCount(int questionCount) {
        this.questionCount = questionCount;
    }

    public int getAnswerCount() {
        return answerCount;
    }

    public void setAnswerCount(int answerCount) {
        this.answerCount = answerCount;
    }

    public float getAcceptRate() {
        return acceptRate;
    }

    public void setAcceptRate(float acceptRate) {
        this.acceptRate = acceptRate;
    }

    public List<TimelineEntry> getTimeline() {
        return timeline;
    }

    public void setTimeline(List<TimelineEntry> timeline) {
        this.timeline = timeline;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", displayName='" + displayName + '\'' +
                ", reputation='" + reputation + '\'' +
                ", lastAccessdate=" + lastAccessdate +
                ", questionCount=" + questionCount +
                ", answerCount=" + answerCount +
                ", acceptRate=" + acceptRate +
                ", timeline=" + timeline +
                '}';
    }
}
