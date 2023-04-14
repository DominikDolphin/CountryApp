package com.example.country_learner;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Country.class}, version = 1)
public abstract class FavouriteCountriesDatabase extends RoomDatabase {

    public abstract CountryDao getCountryDao();
}
