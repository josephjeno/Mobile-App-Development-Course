package com.example.exercise10;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {


    private Uri imageURIfromDevice;
    private static final int REQUEST_CODE=1;

    //variable to hold the bitmap
    private Bitmap sourceImageBitmap;

    private ImageButton addImageButton;
    private TextView smileProbabilityTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addImageButton = findViewById(R.id.addImageButton);
        smileProbabilityTextView = findViewById(R.id.smileProbabilityText);

        addImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Extract hte image from the gallery
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                // Set the type of file to be selected
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            // grab the data and set it on my image
            imageURIfromDevice = data.getData(); // --> grabs the image from the gallery

            // override add image button
            addImageButton.setImageURI(imageURIfromDevice);

            // take the image URI and convert to Bitmap
            try {
                sourceImageBitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageURIfromDevice);

                // start the face detection process
                
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
