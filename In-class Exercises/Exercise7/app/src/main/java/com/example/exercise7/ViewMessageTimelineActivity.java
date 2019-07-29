package com.example.exercise7;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import utils.CustomMessageAdapter;

public class ViewMessageTimelineActivity extends AppCompatActivity {

    private ListView mymessagesListView;

    // get database reference
    private DatabaseReference dbRootRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_message_timeline);

        mymessagesListView = findViewById(R.id.myMessagesListView);

        //create reference for database root
        dbRootRef = FirebaseDatabase.getInstance().getReference();

        // get hte child reference
        final DatabaseReference dbChildReference = dbRootRef.child("UserMessages");

        //init the custom adapter
        CustomMessageAdapter customMessageAdapter = new CustomMessageAdapter(this, dbChildReference);

        // set htis adapter to list view
        mymessagesListView.setAdapter(customMessageAdapter);
    }
}
