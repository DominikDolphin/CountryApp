package com.example.country_learner;

import android.content.Context;

import androidx.room.Room;

public class DatabaseManager {
    /*
    To prevent queries from blocking the UI,
    Room does not allow database access on the main thread.
     */
    static FavouriteCountriesDatabase countriesDatabase;

    // Singleton Pattern to ensure only 1 instance since
    // it is ressource heavy.
    public static FavouriteCountriesDatabase getDB(Context context){
        if (countriesDatabase == null){
            return countriesDatabase = Room.databaseBuilder(context,
                    FavouriteCountriesDatabase.class, "database-fav-countries").build();
        } else {
            return countriesDatabase;
        }
    }

}
