package com.example.assignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {

    String carWebsite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        ImageView carImage = findViewById(R.id.carImageView);
        TextView carDetails = findViewById(R.id.carDetailsTextView);

        // Retrieve car from extras
        Bundle extras = getIntent().getExtras();
        String car = extras.getString("car");

        // Set specific car details
        switch(car) {
            case ("Hatchback"):
                carImage.setImageResource(R.drawable.iconfinder_hatchback);
                carDetails.setText(R.string.hatchback_details);
                carWebsite = getResources().getString(R.string.hatchback_website);
                break;
            case("SUV"):
                carImage.setImageResource(R.drawable.iconfinder_suv);
                carDetails.setText(R.string.suv_details);
                carWebsite = getResources().getString(R.string.suv_website);
                break;
            default:
                carImage.setImageResource(R.drawable.iconfinder_sedan);
                carDetails.setText(R.string.sedan_details);
                carWebsite = getResources().getString(R.string.sedan_website);
                break;
        }
    }

    public void visitWebsite(View view) {
        Uri webpage = Uri.parse(carWebsite);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}
