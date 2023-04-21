package com.example.country_learner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.widget.SearchView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class CountrySearchActivity extends AppCompatActivity implements NetworkingManager.NetworkingCallBackInterface, CountryRecycleAdapter.CountryClickListener{

    private NetworkingManager networkingManager;
    private DatabaseManager databaseManager;
    private Country thisCountry;

    private CountryRecycleAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu,menu);
        MenuItem searchViewmenue = menu.findItem(R.id.searchbar);
        SearchView searchView = (SearchView) searchViewmenue.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                networkingManager.getCountry(String.valueOf(query));
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.length() >= 3){
                    networkingManager.getCountrySimpleInfoForRecycleView(String.valueOf(newText));
                }
                return false;
            }
        });
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_search);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(getString(R.string.country_search_activity_title));
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        networkingManager = ((MyApp)getApplication()).networkingManager;
        networkingManager.listener = this;

        databaseManager = ((MyApp)getApplication()).databaseManager;
        DatabaseManager.getDB(this);

    }

    @Override
    public void networkingManagerCompleteWithJSonString(String jsonString) {
        //parse json string
        JsonManager jsonManager = new JsonManager();

        ArrayList<Country> countryList = jsonManager.fromStringToCountriesList(jsonString);

        adapter = new CountryRecycleAdapter(countryList, this);
        adapter.listener = this;

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void networkingManagerSingleCountryWithJSonString(String jsonString) {
    }

    @Override
    public void onCountryClicked(Country selectedCountry) {
        Intent intent = new Intent(this,CountryDetailsActivity.class);

        JsonManager jsonManager = new JsonManager();
        Country test = jsonManager.fromStringToCountry(selectedCountry.getCommonName());

        intent.putExtra("country",selectedCountry);
        startActivity(intent);
    }
}