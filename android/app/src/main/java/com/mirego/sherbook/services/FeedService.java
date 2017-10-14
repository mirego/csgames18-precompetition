package com.mirego.sherbook.services;

import com.mirego.sherbook.models.Feed;

import retrofit2.Call;
import retrofit2.http.GET;

public interface FeedService {

    @GET("sherbook.json")
    Call<Feed> getFeed();

}
