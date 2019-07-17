package com.example.exercise5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import utils.CharacterInfoAdapterItem;
import utils.CustomCharacterAdapter;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<CharacterInfoAdapterItem> favouriteCharacters = new ArrayList<CharacterInfoAdapterItem>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.myListView);

        //Populate the data into the arrayList
        PopulateList();

        //Create an array adapter
        //ArrayAdapter myAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, favouriteCharacters);
        CustomCharacterAdapter myAdapter = new CustomCharacterAdapter(this, favouriteCharacters);

        //Connect the adapter to hte list view
        listView.setAdapter(myAdapter);

        //Set the click listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                // trigger the second activity - character info
                Intent intent = new Intent(MainActivity.this, CharacterInfoActivity.class);

                // Pass in the character name to second activity
                intent.putExtra("CharacterName", favouriteCharacters.get(i).getCharacterName());
                startActivity(intent);
            }
        });
    }

    //Add data into arrayList
    private void PopulateList(){
        favouriteCharacters.add(new CharacterInfoAdapterItem(R.drawable.batman, "Batman"));
        favouriteCharacters.add(new CharacterInfoAdapterItem(R.drawable.joker, "Joker"));
        favouriteCharacters.add(new CharacterInfoAdapterItem(R.drawable.spiderman, "Spiderman"));
        favouriteCharacters.add(new CharacterInfoAdapterItem(R.drawable.thanos, "Thanos"));
    }
}
