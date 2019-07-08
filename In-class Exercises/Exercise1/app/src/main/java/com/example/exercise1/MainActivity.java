package com.example.exercise1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    // 1. Link the views from layout to your code
    // 2. Create an array and store the various mood images

    ImageView ghostImage;

    // Array of Ghost Mood IDs
    int[] moodImages = {
        R.drawable.angry,
        R.drawable.exhausted,
        R.drawable.happy,
        R.drawable.neutral,
        R.drawable.sad
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set ghostImage to ImageView
        ghostImage = findViewById(R.id.myImageView);
    }


    //FUNCTION for my button click
    public void ChangeMoodButton(View view) {

        // Randomize
        Random random = new Random();

        // Generate a random number between 0 and 4
        int rNumber = random.nextInt(5);

        // Update my image
        ghostImage.setImageResource(moodImages[rNumber]);
    }
}
