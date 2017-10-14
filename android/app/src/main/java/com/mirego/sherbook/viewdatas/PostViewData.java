package com.mirego.sherbook.viewdatas;

import android.content.Context;
import android.provider.Settings;

import com.mirego.sherbook.models.Post;

import static com.mirego.sherbook.models.Post.SOUND_FILE_TYPE;

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
                return post.getAttachment().getUrl();
            }
        }
        return null;
    }

    public boolean IsAudio() {
        System.out.println("Get attachement = " + (post.getAttachment() != null));
        System.out.println("Value of is audio is " + (post.getAttachment() != null && post.getAttachment().getType().contains(SOUND_FILE_TYPE)) + " for post " + post.getAuthor());
        return post.getAttachment() != null && post.getAttachment().getType().contains(SOUND_FILE_TYPE);
    }
}
