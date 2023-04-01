package com.example.country_learner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CountrySearchActivity extends AppCompatActivity implements NetworkingManager.NetworkingCallBackInterface{

    private Button btnClear, btnSubmit;
    private EditText countryInput;
    private NetworkingManager networkingManager;
    private Country thisCountry;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_search);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        btnClear = findViewById(R.id.country_search_button_clear);
        btnSubmit = findViewById(R.id.country_search_button_submit);
        countryInput = findViewById(R.id.search_input);
        networkingManager = ((MyApp)getApplication()).networkingManager;
        networkingManager.listener = this;

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Submit button", "Clicked");

                networkingManager.getCountry("Canada");
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                countryInput.setText("");
            }
        });


    }


    @Override
    public void networkingManagerCompleteWithJSonString(String jsonString) {
        //parse json string
        thisCountry = JsonManager.fromStringToCountry(jsonString);

        //btnSubmit.setText(thisCountry.getCapital());
    }
}