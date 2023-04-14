package com.example.country_learner;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.ArrayList;

@Dao
public interface CountryDao {

    @Insert
    void insertNewCountry(Country country);

    @Query("select * from Country")
    ArrayList<Country> getAllFavouriteCountries();

    @Query("select * from Country WHERE commonName like :text")
    ArrayList<Country> getAllFavouriteCitiesStartWith(String text);

    @Delete
    void deleteCountry(Country country);
}
