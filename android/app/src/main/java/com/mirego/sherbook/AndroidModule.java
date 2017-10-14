package com.mirego.sherbook;

import android.app.Application;

import com.mirego.sherbook.controllers.FeedController;
import com.mirego.sherbook.services.FeedService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
class AndroidModule {

    private final Application application;

    public AndroidModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                .baseUrl("https://www.dropbox.com/s/tbm6apfuv5n3orq/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    FeedService provideFeedService(Retrofit retrofit) {
        return retrofit.create(FeedService.class);
    }

    @Provides
    FeedController provideFeedController(FeedService feedService) {
        return new FeedController(feedService, application);
    }
}