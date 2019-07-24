package com.example.exercise7;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private EditText userNameText, passwordText;
    private Button loginButton, registerButton;

    private String usernameData, passwordData;

    // create a variable to Firebase authentication
    private FirebaseAuth authenticationRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userNameText = findViewById(R.id.userNameEditText);
        passwordText = findViewById(R.id.passwordEditText);

        loginButton = findViewById(R.id.loginButton);
        registerButton = findViewById(R.id.registerButton);

        // Get the reference of Firebase authentication
        authenticationRef = FirebaseAuth.getInstance();

        // Set a listener on my registerButton to launch the authentication service
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get the values from edittext
                usernameData = userNameText.getText().toString();
                passwordData = passwordText.getText().toString();

                // handle the user registration
                authenticationRef.createUserWithEmailAndPassword(usernameData, passwordData).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        // If the task has succeeded or not
                        if(task.isSuccessful()){

                            // Registration successful
                            Toast.makeText(getApplicationContext(), "Registration successful", Toast.LENGTH_SHORT).show();
                        } else {

                            // Registration failed
                            Toast.makeText(getApplicationContext(), "Registration failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        // Set a listener on my loginButton to check for authentication
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Authenticate our user
                // get the values from edittext
                usernameData = userNameText.getText().toString();
                passwordData = passwordText.getText().toString();

                // Check for registered users on Firebase Authentication
                authenticationRef.signInWithEmailAndPassword(usernameData, passwordData).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                            startActivity(intent);
                        } else {
                            // Login failed
                            Toast.makeText(getApplicationContext(), "Login failed", Toast.LENGTH_SHORT).show();
                        }

                    }
                });

            }
        });

    }
}
