package com.mirego.sherbook.models;

import java.util.Date;
import java.util.List;

public class Feed {
    private String feed;
    private Date lastUpdate;
    private List<Post> posts = null;
    private List<Faculty> faculties = null;

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

    public void setPosts(List<Post> posts) {this.posts = posts;}

    public List<Faculty> getFaculties() {return faculties;}

    public void setFaculties(List<Faculty> faculties) {this.faculties = faculties;}
}
