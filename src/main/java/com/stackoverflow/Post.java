/*
 * I, the copyright holder of this work, hereby release it into the public domain. This applies worldwide.
 *
 * In case this is not legally possible, I grant any entity the right to use this work for any purpose,
 * without any conditions, unless such conditions are required by law.
 */

package com.stackoverflow;

import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

/**
 * @author Glenn Bech
 */
public class Post {

    @JsonProperty("owner")
    protected Owner owner;

    @JsonProperty("title")
    protected String title;

    @JsonProperty("body")
    protected String body;

    @JsonProperty("view_count")
    protected int viewCount;

    @JsonProperty("comments")
    protected List<Comment> comments;

    @JsonProperty("up_vote_count")
    protected int upVoteCount;

    @JsonProperty("down_vote_count")
    protected int downVotecount;

    @JsonProperty("last_activity_date")
    protected long lastActivityDate;

    @JsonProperty("favorites_count")
    protected int favoritesCount;


    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public int getUpVoteCount() {
        return upVoteCount;
    }

    public void setUpVoteCount(int upVoteCount) {
        this.upVoteCount = upVoteCount;
    }

    public int getDownVotecount() {
        return downVotecount;
    }

    public void setDownVotecount(int downVotecount) {
        this.downVotecount = downVotecount;
    }

    public int getFavoritesCount() {
        return favoritesCount;
    }

    public void setFavoritesCount(int favoritesCount) {
        this.favoritesCount = favoritesCount;
    }

    public long getLastActivityDate() {
        return lastActivityDate;
    }

    public void setLastActivityDate(long lastActivityDate) {
        this.lastActivityDate = lastActivityDate;
    }
}
