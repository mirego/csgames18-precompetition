package com.mirego.sherbook.services;
/**
 * Created by Alexander on 2017-10-14.
 */

import android.Manifest;
import android.app.Activity;
import android.app.Service;
import android.content.pm.PackageManager;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.content.Intent;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import java.io.IOException;

public class PlaybackService extends Service implements OnCompletionListener {
    private MediaPlayer player = null;
    private final IBinder mBinder = new LocalBinder();

    private String dataSource = "/sdcard/song.mp3";

    @Override
    public void onCreate() {
        player = new MediaPlayer();
        player.setOnCompletionListener(this);
        try {
            //player.setDataSource(Environment.getExternalStorageDirectory().getPath() + "/song.mp3");
            player.setDataSource(dataSource);
            player.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        player.start();
        Log.d("TagServiceStart", "Playback service started.");
        return Service.START_NOT_STICKY;
    }

    public void onDestroy() {
        if (player.isPlaying()) {
            player.stop();
        }
        player.release();
    }

    public class LocalBinder extends Binder {
        public PlaybackService getServiceInstance() {
            return PlaybackService.this;
        }
    }

    public void stop() {
        player.stop();
    }

    public void start() {
        player.start();
    }

    public boolean isPlaying() {
        return player.isPlaying();
    }

    public int getCurrentPosition() {
        return player.getCurrentPosition();
    }

    public int getDuration() {
        return player.getDuration();
    }

    public void seekTo(int ms) {
        player.seekTo(ms);
    }

    public String getTrackName() {
        MediaMetadataRetriever metadataRetriever = new MediaMetadataRetriever();
        metadataRetriever.setDataSource(dataSource);
        return metadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        stopSelf();
    }
}