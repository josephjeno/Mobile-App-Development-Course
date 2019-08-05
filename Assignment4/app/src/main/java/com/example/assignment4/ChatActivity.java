package com.example.assignment4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import utils.CustomMessageAdapter;
import utils.Message;

public class ChatActivity extends AppCompatActivity {

    private ListView messagesListView;
    private EditText messageBody;
    private Button postMessage;

    private String messageText="";
    private String emailAddress="";

    // get database reference
    private DatabaseReference dbRootRef;

    // Firebase authentication variable
    private FirebaseAuth authenticationRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        messageBody = findViewById(R.id.chatMessageTextEntry);
        postMessage = findViewById(R.id.chatMessageTextSubmitButton);
        messagesListView = findViewById(R.id.chatMessageList);

        //create reference for database root
        dbRootRef = FirebaseDatabase.getInstance().getReference();

        // Get the reference of Firebase authentication
        authenticationRef = FirebaseAuth.getInstance();

        // get the child reference
        final DatabaseReference dbChildReference = dbRootRef.child("UserMessages");

        //init the custom adapter
        final CustomMessageAdapter customMessageAdapter = new CustomMessageAdapter(this, dbChildReference);

        // set this adapter to list view
        messagesListView.setAdapter(customMessageAdapter);

        postMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emailAddress = authenticationRef.getCurrentUser().getEmail();
                messageText = messageBody.getText().toString();

                //Post data to firebase database
                Message messageContents = new Message(emailAddress, messageText);

                //Pass this object to Database reference
                dbChildReference.push().setValue(messageContents);
            }
        });
    }
}
