package com.example.exercise7;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class HomeActivity extends AppCompatActivity {

    ImageButton addImageButton;
    Button uploadButton;
    ImageView resultImageView;

    // Hold the URI of the image from device
    Uri imageURIFromDevice;

    // Declare the Request code for the intent
    private final int REQUEST_CODE = 1;

    // Reference for Firebase Storage
    private StorageReference storageRootRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        addImageButton = findViewById(R.id.addImageButton);
        uploadButton = findViewById(R.id.uploadButton);
        resultImageView = findViewById(R.id.resultImageView);

        // Set the reference of firebase root storage
        storageRootRef = FirebaseStorage.getInstance().getReference();

        // Include the transition from Home activity to Gallery
        addImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // trigger the implicit intent to grab image from other activity
                Intent implicitIntent = new Intent(Intent.ACTION_GET_CONTENT);

                // Define the file type
                implicitIntent.setType("image/*");

                // start this intent
                startActivityForResult(implicitIntent,REQUEST_CODE);
            }
        });

        // Upload the image to firebase based on click
        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //uploading an image
                UploadImageToStorage();
            }
        });
    }

    private void UploadImageToStorage(){

        // Create a file path of where you want to store your image in storage
        final StorageReference filepath = storageRootRef.child("MyImages").child(imageURIFromDevice.getLastPathSegment());

        filepath.putFile(imageURIFromDevice).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                // I'll be notified here if my image has been uploaded

                // Extract the URI of the image
                filepath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        // teh result uri of the image on WEB
                        Uri downloadURIofImage = uri;

                        // Pass the URI and ImageView to Picasso
                        Picasso.get().load(downloadURIofImage).into(resultImageView);
                    }
                });

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Grab the data from gallery
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            // I'll have my data here

            // location of the file in my device
            imageURIFromDevice = data.getData();

            addImageButton.setImageURI(imageURIFromDevice);
        }
    }
}
