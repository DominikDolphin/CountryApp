package com.example.country_learner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class FavouriteCountriesActivity extends AppCompatActivity implements DatabaseManager.DatabaseCallbackInterface, CountryRecycleAdapter.CountryClickListener {
    RecyclerView list;
    CountryRecycleAdapter adapter;
    ArrayList<Country> countryArray = new ArrayList<>(0);
    DatabaseManager databaseManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite_countries);
        setTitle("My Favourites");
        list = findViewById(R.id.favCoutnriesRecylceView);
        adapter = new CountryRecycleAdapter(countryArray, this);
        adapter.listener = this;

        DatabaseManager.getDB(this);
        databaseManager = ((MyApp)getApplication()).databaseManager;
        databaseManager.listener = this;

        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        databaseManager.getAllFavouriteCountries();
        adapter.listener = this;
    }
    @Override
    public void databaseManagerCompleteWithArraylistOfCountries(List<Country> favList) {
        adapter.countries = new ArrayList<>(favList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onCountryClicked(Country selectedCountry) {

        //go to country show activity ( to be implemented)
        Intent intent = new Intent(this,CountryDetailsActivity.class);
        intent.putExtra("country",selectedCountry);
        startActivity(intent);

    }
}