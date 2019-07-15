package com.example.exercise5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CharacterInfoActivity extends AppCompatActivity {

    ImageView characterImage;
    TextView characterName;
    TextView characterDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_info);

        characterImage = findViewById(R.id.characterImg);
        characterName = findViewById(R.id.characterName);
        characterDescription = findViewById(R.id.characterDescription);

        //Extract resourceIDS - Image, TXT, and Audio files
        String dataFromPreviousActivity = getIntent().getStringExtra("CharacterName");

        int ImageResID = getResources().getIdentifier(dataFromPreviousActivity.toLowerCase(), "drawable", getPackageName());
        int textResID = getResources().getIdentifier(dataFromPreviousActivity.toLowerCase() + "text", "raw", getPackageName());

        characterImage.setImageResource(ImageResID);
        characterName.setText(dataFromPreviousActivity);
        characterDescription.setText(ReadFromTXTFile(textResID));


    }


    private String ReadFromTXTFile(int resID){
        //Create a String Builder
        StringBuilder textValue = new StringBuilder();

        try {
            //Create a buffered reader and pass in the textfile resource ID
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(getResources().openRawResource(resID))
            );

            //Store data temporarily
            String line;

            while ((line = reader.readLine()) != null) {
                textValue.append(line);
                textValue.append(' ');
            }
        }

        catch(IOException e){
            e.printStackTrace();
        }

        return textValue.toString();
    }
}
