package com.example.country_learner;

import android.content.Context;

import androidx.room.Room;

import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {

    interface DatabaseCallbackInterface {
        void databaseManagerCompleteWithArraylistOfCountries(List<Country> favList);
    }
    DatabaseCallbackInterface listener;
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

    public void insertNewCountry(Country newCountry){
        MultithreadManager.executorService.execute(new Runnable() {
            @Override
            public void run() {
                DatabaseManager.countriesDatabase.getCountryDao().insertNewCountry(newCountry);
            }
        });
    }

    public void getAllFavouriteCountries(){
        MultithreadManager.executorService.execute(new Runnable() {
            @Override
            public void run() {
               List<Country> favs = DatabaseManager.countriesDatabase.getCountryDao().getAllFavouriteCountries();
               MultithreadManager.handler.post(new Runnable() {
                   @Override
                   public void run() {
                       listener.databaseManagerCompleteWithArraylistOfCountries(favs);
                   }
               });
            }
        });
    }

    public void deleteCountryFromFavourite(Country toDelete){
        MultithreadManager.executorService.execute(new Runnable() {
            @Override
            public void run() {
                DatabaseManager.countriesDatabase.getCountryDao().deleteCountry(toDelete);
            }
        });
    }

}
