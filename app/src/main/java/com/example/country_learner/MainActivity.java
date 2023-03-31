package com.example.country_learner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView searchCountry, randomCountry, favourites;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchCountry = findViewById(R.id.menu_button_search_country);
        randomCountry = findViewById(R.id.menu_button_random_country);
        favourites = findViewById(R.id.menu_button_my_favourites);

        searchCountry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Testing Click", "worked");
            }
        });

        randomCountry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Testing Click", "worked");
            }
        });

        favourites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Testing Click", "worked");
            }
        });

    }
}