package com.example.country_learner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {

    private TextView searchCountry, randomCountry, favourites;
    private ImageView test;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchCountry = findViewById(R.id.menu_button_search_country);
       // randomCountry = findViewById(R.id.menu_button_random_country);
        favourites = findViewById(R.id.menu_button_my_favourites);
        searchCountry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Testing Click", "worked");
                Intent intent = new Intent(view.getContext(), CountrySearchActivity.class);
                view.getContext().startActivity(intent);
            }
        });


//        randomCountry.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.d("Testing Click", "worked");
//            }
//        });

        favourites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), FavouriteCountriesActivity.class);
                view.getContext().startActivity(intent);
                //Log.d("Testing Click", "worked");
            }
        });

    }
}