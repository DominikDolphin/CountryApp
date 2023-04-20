package com.example.country_learner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.NumberFormat;
import java.util.Locale;

public class CountryDetailsActivity extends AppCompatActivity {
    public TextView countryName, commonName, capital, languages,
            region, subregion, population, currency, symbol, independent, unMember;
    public ImageView flagView;
    public Button favouriteButton;
    public Country country;
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_details);

        //Because this Activity will be accessed from different Activities,
        // we add the "Back" button prgrammatically onCreate.
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Get country from Intent
        country = (Country) getIntent().getParcelableExtra("country", Country.class);

        // To keep onCreate clean, move all basic extraction to functions
        initiateVariables();
        initialSetText();

        favouriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }
    private void initiateVariables(){
        countryName = findViewById(R.id.view_countryName);
        commonName = findViewById(R.id.view_commonName);
        capital = findViewById(R.id.view_capitalCity);
        languages = findViewById(R.id.view_languages);
        region = findViewById(R.id.view_continent);
        subregion = findViewById(R.id.view_subregion);
        population = findViewById(R.id.view_population);
        currency = findViewById(R.id.view_currency);
        symbol = findViewById(R.id.view_symbol);
        independent = findViewById(R.id.view_independent);
        unMember = findViewById(R.id.view_unMember);
        flagView = findViewById(R.id.imageView2);
        favouriteButton = findViewById(R.id.favButton);
    }

    private void initialSetText(){
        countryName.setText(country.getOfficialName());
        commonName.setText("Commonly known as: " + country.getCommonName());
        capital.setText("Capital City: " + country.getCapital());
        languages.setText(country.getOfficialLanguages());
        region.setText(country.getRegion());
        subregion.setText(country.getSubregion());

        //Adds commas per thousand
        population.setText(
                NumberFormat.getNumberInstance(Locale.US).format(country.getPopulation())
        );

        currency.setText(country.getCurrency());
        symbol.setText(country.getCurrencySymbol());
        independent.setText(country.isIndependent() ? "✅" : "❌");
        unMember.setText(country.isUnMember() ? "✅" : "❌");

        //Flag needs to fetch from internet
        Glide.with(this).load(country.getFlag()).into(flagView);

        //Fav button

    }
}