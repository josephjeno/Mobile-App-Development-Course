package com.example.fragments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Attach my fragment to the container that's present in my layout
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        // Add a function to your transaction
        // 1. containerID (framelayout)
        // 2. which fragment should I add
        transaction.add(R.id.fragmentContainer, new MainFragment());

        // Commit the changes
        transaction.commit();
    }

    // override back button
    @Override
    public void onBackPressed() {

        // Attach my fragment to the container that's present in my layout
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        // Add a function to your transaction
        // 1. containerID (framelayout)
        // 2. which fragment should I add
        transaction.replace(R.id.fragmentContainer, new MainFragment());

        // Commit the changes
        transaction.commit();

    }
}
