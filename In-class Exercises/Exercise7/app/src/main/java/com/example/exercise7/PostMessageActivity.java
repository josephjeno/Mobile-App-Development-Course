package com.example.exercise7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import utils.MessageContents;

public class PostMessageActivity extends AppCompatActivity {

    private EditText messageTitle;
    private EditText messageBody;
    private Button postMessage;
    private Button viewMessageTimeline;



    private String messageTitleText="";
    private String messageBodyText="";

    private DatabaseReference databaseRootref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_message);

        messageTitle = findViewById(R.id.messageTitle);
        messageBody = findViewById(R.id.messageBody);
        postMessage = findViewById(R.id.postButton);
        viewMessageTimeline = findViewById(R.id.viewMessageTimeline);

        databaseRootref = FirebaseDatabase.getInstance().getReference();

        final DatabaseReference databaseChildRef = databaseRootref.child("UserMessages");

        postMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                messageTitleText = messageTitle.getText().toString();
                messageBodyText = messageBody.getText().toString();

                //Post data to firebase database
                MessageContents messageContents = new MessageContents(messageTitleText, messageBodyText);

                //Pass this object to Database reference
                databaseChildRef.push().setValue(messageContents);
            }
        });

        viewMessageTimeline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PostMessageActivity.this, ViewMessageTimelineActivity.class);
                startActivity(intent);
            }
        });
    }


}
