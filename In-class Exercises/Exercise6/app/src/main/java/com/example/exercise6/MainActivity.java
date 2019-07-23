package com.example.exercise6;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    DatabaseReference dbRootReference;

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

        // get the root reference of the database
        dbRootReference = FirebaseDatabase.getInstance().getReference();

        // get the child
        DatabaseReference dbChildReference = dbRootReference.child("ghostmood");

        // Set a listener on the childView to monitor for realtime updates
        dbChildReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                int ghostIndex = dataSnapshot.getValue(int.class);

                //update the imageview based on my datasnapshot value
                ghostImage.setImageResource(moodImages[ghostIndex]);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
