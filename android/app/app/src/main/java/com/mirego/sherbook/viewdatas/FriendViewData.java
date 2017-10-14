package com.mirego.sherbook.viewdatas;

import android.content.Context;

import com.mirego.sherbook.models.Post;
import com.mirego.sherbook.models.PostFriend;

public class FriendViewData {

    private final PostFriend post;
    private Context context;

    public FriendViewData(PostFriend post, Context context) {
        this.post = post;
        this.context = context;
    }

    public String name() {
        return post.getName();
    }

    public String city() {
        return post.getCity();
    }
    public String age() {
        return post.getAge();
    }

    public String pictureUrl() {
        if (post.getAttachment() != null) {
            if (post.getAttachment().getUrl() != null) {
                return post.getAttachment().getUrl();
            }
        }
        return null;
    }
}
