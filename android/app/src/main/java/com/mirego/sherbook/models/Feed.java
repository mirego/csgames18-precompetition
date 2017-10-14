package com.mirego.sherbook.models;

import java.util.Date;
import java.util.List;

public class Feed {
    private String feed;
    private Date lastUpdate;
    private List<Post> posts = null;

    private List<PostFriend> friendsPosts = null;

    public String getFeed() {
        return feed;
    }

    public void setFeed(String feed) {
        this.feed = feed;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public List<PostFriend> getFriendPosts() {
        return friendsPosts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public void setFriendPosts(List<PostFriend> posts) {
        this.friendsPosts = posts;
    }

}
