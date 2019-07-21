package com.example.assignment3;

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
        transaction.add(R.id.fragmentContainer, new BookList());

        // Commit the changes
        transaction.commit();
    }

    // override back button
    @Override
    public void onBackPressed() {

        // Attach my fragment to the container that's present in my layout
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        // Add a function to your transaction
        transaction.replace(R.id.fragmentContainer, new BookList());

        // Commit the changes
        transaction.commit();
    }
}
