package com.stackoverflow;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * @author Glenn Bech
 */


@JsonIgnoreProperties(ignoreUnknown = true)
public class TimelineEntry {

    @JsonProperty("timeline_type")
    private String timelinetype;

    @JsonProperty("post_type")
    private String postType;

    @JsonProperty("comment_id")
    private String commentId;

    @JsonProperty("post_id")
    private String postId;

    @JsonProperty("questionId")
    private String questionId;

    @JsonProperty("action")
    private String action;

    @JsonProperty("creation_date")
    private long creationDate;

    @JsonProperty("description")
    private String description;

    @JsonProperty("owner")
    private Owner owner;


    public String getTimelinetype() {
        return timelinetype;
    }

    public void setTimelinetype(String timelinetype) {
        this.timelinetype = timelinetype;
    }


    public String getPostType() {
        return postType;
    }

    public void setPostType(String postType) {
        this.postType = postType;
    }

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public long getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(long creationDate) {
        this.creationDate = creationDate;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "TimelineEntry{" +
                "owner=" + owner +
                ", description='" + description + '\'' +
                ", creationDate=" + creationDate +
                ", action='" + action + '\'' +
                ", questionId='" + questionId + '\'' +
                ", postId='" + postId + '\'' +
                ", commentId='" + commentId + '\'' +
                ", postType='" + postType + '\'' +
                ", timelinetype='" + timelinetype + '\'' +
                '}';
    }
}
