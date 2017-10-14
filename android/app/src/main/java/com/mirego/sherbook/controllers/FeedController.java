package com.mirego.sherbook.controllers;

import android.content.Context;

import com.mirego.sherbook.models.Feed;
import com.mirego.sherbook.models.Post;
import com.mirego.sherbook.services.FeedService;
import com.mirego.sherbook.viewdatas.PostViewData;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeedController {

    public interface RequestCallback<T> {
        void onSuccess(T result);
        void onError();
    }

    private final FeedService feedService;
    private Context context;

    public FeedController(FeedService feedService, Context context) {
        this.feedService = feedService;
        this.context = context;
    }

    public void getPosts(final RequestCallback<List<PostViewData>> postsRequestCallback) {
        feedService.getFeed().enqueue(new Callback<Feed>() {
            @Override
            public void onResponse(Call<Feed> call, Response<Feed> response) {
                Feed feed = response.body();
                if (feed != null) {
                    List<PostViewData> postViewDatas = new ArrayList<>();
                    for (Post post : feed.getPosts()) {
                        postViewDatas.add(new PostViewData(post, context));
                    }
                    postsRequestCallback.onSuccess(postViewDatas);
                } else {
                    postsRequestCallback.onError();
                }
            }

            @Override
            public void onFailure(Call<Feed> call, Throwable t) {
                postsRequestCallback.onError();
            }
        });
    }
}
