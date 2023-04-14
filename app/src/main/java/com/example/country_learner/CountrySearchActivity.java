package com.example.country_learner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class CountrySearchActivity extends AppCompatActivity implements NetworkingManager.NetworkingCallBackInterface{

    private Button btnClear, btnSubmit, btnFavourite;
    private EditText countryInput;
    private NetworkingManager networkingManager;
    private DatabaseManager databaseManager;
    private Country thisCountry;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_search);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

//        if (thisCountry == null){
//            thisCountry = new Country();
//
//        }

        recyclerView.setLayoutFrozen(true);
        btnClear = findViewById(R.id.country_search_button_clear);
        btnSubmit = findViewById(R.id.country_search_button_submit);
        btnFavourite = findViewById(R.id.course_search_favourite_btn);
        countryInput = findViewById(R.id.search_input);

        networkingManager = ((MyApp)getApplication()).networkingManager;
        networkingManager.listener = this;

        databaseManager = ((MyApp)getApplication()).databaseManager;
        DatabaseManager.getDB(this);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Submit button", "Clicked");

                networkingManager.getCountry(String.valueOf(countryInput.getText()));
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                countryInput.setText("");
            }
        });

        btnFavourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //DatabaseManager.getDB(getApplicationContext()).getCountryDao().insertNewCountry(thisCountry);

                Toast.makeText(getApplicationContext(), "Added to favourites", Toast.LENGTH_SHORT).show();
            }
        });


    }


    @Override
    public void networkingManagerCompleteWithJSonString(String jsonString) {
        //parse json string
        JsonManager jsonManager = new JsonManager();
        thisCountry = jsonManager.fromStringToCountry(jsonString);

        //Add View to page
        adapter = new CountryAdapter(thisCountry, this);
        recyclerView.setAdapter(adapter);


    }


}