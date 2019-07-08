package com.example.exercisee3movieplayerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {


    // Step 1 : Link the video view from xml to activity
    VideoView video;

    // Step 2 : Assign the video clip to the video view


    // Step 3 : Play the video

    // video location
    int myCurrentPositionInVideo = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        video = findViewById(R.id.myVideoView);

        video.setVideoPath("android.resource://"+getPackageName()+"/"+R.raw.harrypotter);

        video.start();
    }

    @Override
    protected void onPause() {
        super.onPause();

        // store the position
        myCurrentPositionInVideo = video.getCurrentPosition();

        // pause the video
        video.pause();

    }

    @Override
    protected void onResume() {
        super.onResume();

        video.seekTo(myCurrentPositionInVideo);

        // start the video
        video.start();
    }
}
