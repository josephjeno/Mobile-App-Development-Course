package com.example.exercise9;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ImageButton playButton;
    private ImageButton pauseButton;
    private TextView resultTextView;

    // variable to hold MyBound Services
    BoundServices myBoundService;

    // Boolean check to check for the connectivity status
    boolean isBoundConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playButton = findViewById(R.id.playButton);
        pauseButton = findViewById(R.id.pauseButton);
        resultTextView = findViewById(R.id.resultTextView);

        // establish the binding to service
        Intent bindServiceIntent = new Intent(this, BoundServices.class);

        // initiate binding process
        bindService(bindServiceIntent,accessToGarage, Context.BIND_AUTO_CREATE);

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isBoundConnection) {
                    myBoundService.PlayMusic();
                    resultTextView.setText(myBoundService.getAudioStatus());
                }
            }
        });

        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(isBoundConnection){
                    myBoundService.PauseMusic();
                    resultTextView.setText(myBoundService.getAudioStatus());
                }
            }
        });
    }

    // establish a service connection
    private ServiceConnection accessToGarage = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

            Log.d("TEST", "onServiceConnected: ");

            //if connected
            isBoundConnection = true;

            // grab the key to garage
            BoundServices.BoundServicesKey keyDeliveredToActivity = (BoundServices.BoundServicesKey) service;
            myBoundService = keyDeliveredToActivity.getService();

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBoundConnection = false;
        }
    };
}
