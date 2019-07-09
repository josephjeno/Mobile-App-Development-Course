package com.example.exercisee3movieplayerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {


    // Step 1 : Link the video view from xml to activity
    VideoView video;

    // Step 2 : Assign the video clip to the video view

    // Step 3 : Play the video

    // video location
    int myCurrentPositionInVideo = 0;

    // Shared preferences variable
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        video = findViewById(R.id.myVideoView);

        video.setVideoPath("android.resource://"+getPackageName()+"/"+R.raw.harrypotter);

        //Restore from permanent file (shared preferences)
        preferences = getSharedPreferences("MyPreferences", MODE_PRIVATE);
        myCurrentPositionInVideo = preferences.getInt("lastPosition", 0);
        video.seekTo(myCurrentPositionInVideo);
        video.start();
    }

    @Override
    protected void onPause() {
        super.onPause();

        // store the position
        myCurrentPositionInVideo = video.getCurrentPosition();

        // pause the video
        video.pause();

        //1. Create shared preferences - specify name and mode
        preferences = getSharedPreferences("MyPreferences", MODE_PRIVATE);

        //2. Create editor
        SharedPreferences.Editor editor = preferences.edit();

        //3. Write my dta to preferences
        editor.putInt("lastPosition", myCurrentPositionInVideo);

        //4. Save my changes to preferences file
        editor.apply();
    }

    @Override
    protected void onResume() {
        super.onResume();

        video.seekTo(myCurrentPositionInVideo);

        // start the video
        video.start();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("lastPositionInVideo", myCurrentPositionInVideo);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        // retrieve the position
        myCurrentPositionInVideo = savedInstanceState.getInt("lastPositionInVideo");
    }
}
