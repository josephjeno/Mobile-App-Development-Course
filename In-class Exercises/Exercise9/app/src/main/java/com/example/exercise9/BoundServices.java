package com.example.exercise9;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;

public class BoundServices extends Service {

    MediaPlayer mediaPlayer;

    private final IBinder ibinder = new BoundServicesKey();

    private String audioStatus;

    public BoundServices() {
    }

    public String getAudioStatus() {
        return audioStatus;
    }

    public void setAudioStatus(String audioStatus) {
        this.audioStatus = audioStatus;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mediaPlayer = MediaPlayer.create(this, R.raw.spiderman);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return ibinder;
    }

    public void PlayMusic(){
        setAudioStatus("Playing");
        mediaPlayer.start();
    }

    public void PauseMusic(){
        setAudioStatus("Paused");
        mediaPlayer.pause();
    }

    public class BoundServicesKey extends Binder {
        BoundServices getService(){
            // return the instance of this service
            return  BoundServices.this;
        }
    }
}
