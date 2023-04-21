package com.example.country_learner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class CountryDetailsActivity extends AppCompatActivity implements DatabaseManager.DatabaseCallbackInterface {
    public TextView countryName, commonName, capital, languages,
            region, subregion, population, currency, symbol, independent, unMember;
    public ImageView flagView;
    public Button favouriteButton;
    public Country country;
    DatabaseManager databaseManager;
    String toastMessage;
    boolean thisCountryIsFavourited;

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
        setTitle(getString(R.string.country_details_activity_title));
        databaseManager = ((MyApp)getApplication()).databaseManager;
        DatabaseManager.getDB(this);
        databaseManager.listener = this;

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
                if (thisCountryIsFavourited) {
                    handleDeleteFromFavourite();
                } else {
                    handleInsertToFavourite();
                }
                Toast.makeText(getApplicationContext(), toastMessage, Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        databaseManager.getAllFavouriteCountries();
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
        thisCountryIsFavourited = false;
        toastMessage = "";
    }

    private void initialSetText(){
        countryName.setText(country.getOfficialName());
        commonName.setText(getString(R.string.country_details_commonly_known_as) + country.getCommonName());
        capital.setText(getString(R.string.country_details_capital_city) + country.getCapital());
        languages.setText(country.getOfficialLanguages());
        region.setText(country.getRegion());
        subregion.setText(country.getSubregion());

        //Adds commas per thousand
        population.setText(
                NumberFormat.getNumberInstance(Locale.US).format(country.getPopulation())
        );

        currency.setText(country.getCurrency());
        symbol.setText(country.getCurrencySymbol());
        independent.setText(country.isIndependent() ? getString(R.string.value_correct) : getString(R.string.value_wrong));
        unMember.setText(country.isUnMember() ? getString(R.string.value_correct) : getString(R.string.value_wrong));

        //Flag needs to fetch from internet
        Glide.with(this).load(country.getFlag()).into(flagView);

    }

    void handleDeleteFromFavourite(){
        databaseManager.deleteCountryFromFavourite(country);
        toastMessage = getString(R.string.country_details_toast_message_remove_from_favourite);

        favouriteButton.setText(R.string.country_details_favoute_button_add);
        favouriteButton.setBackgroundColor(getResources().getColor(R.color.button_add_to_favourites));
    }
    void handleInsertToFavourite(){
        databaseManager.insertNewCountry(country);
        toastMessage = getString(R.string.country_details_toast_message_added_to_favourite);

        favouriteButton.setText(R.string.country_details_favourite_button_remove);
        favouriteButton.setBackgroundColor(getResources().getColor(R.color.button_clear));
    }
    @Override
    public void databaseManagerCompleteWithArraylistOfCountries(List<Country> favList) {

        //Checks to see if this country is already part of the user's favourite list.
        for (Country c : favList){
            if (c.getOfficialName().equals(country.getOfficialName())){
                thisCountryIsFavourited = true;
                favouriteButton.setText(R.string.country_details_favourite_button_remove);
                favouriteButton.setBackgroundColor(getResources().getColor(R.color.button_clear));
                return;
            }
        }
    }
}