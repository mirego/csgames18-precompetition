package com.mirego.sherbook.viewdatas;

import android.content.Context;
import android.support.annotation.Nullable;

import com.mirego.sherbook.models.Post;

public class PostViewData {

    private final Post post;
    private Context context;

    public PostViewData(Post post, Context context) {
        this.post = post;
        this.context = context;
    }

    public String author() {
        return post.getAuthor();
    }

    public String message() {
        return post.getMessage();
    }


    public String imageUrl() {
        if (post.getAttachment() != null) {
            if (post.getAttachment().getUrl() != null) {
                if(post.getAttachment().getType().equals("image")) {
                    return post.getAttachment().getUrl();
                }
            }
        }
        return null;
    }

    public String videoUrl() {
        if(post.getAttachment() != null) {
            if(post.getAttachment().getUrl() != null) {
                if(post.getAttachment().getType().equals("video")) {
                    return post.getAttachment().getUrl();
                }
            }
        }
        return null;
    }

    public String id() {
        return post.getId();
    }

    public Integer rating() {
        return post.getRating();
    }
}
