package com.example.assignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity {

    RadioButton selectedCarRadioButton;
    CharSequence selectedCar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set default selectedCar to Sedan
        selectedCarRadioButton = findViewById(R.id.sedanRadioButton);
        selectedCar = selectedCarRadioButton.getText();
    }

    public void viewCar(View view) {
        Intent explicitIntent = new Intent(getApplicationContext(), DetailsActivity.class);
        explicitIntent.putExtra("car", String.valueOf(selectedCar));
        startActivity(explicitIntent);
    }

    public void onRadioButtonClicked(View view) {
        selectedCarRadioButton = (RadioButton) view;
        selectedCar = selectedCarRadioButton.getText();
        Log.d("radio_button_clicked", "buttonID is " + selectedCar);
    }
}
