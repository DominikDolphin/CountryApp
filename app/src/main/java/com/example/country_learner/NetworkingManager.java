package com.example.country_learner;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NetworkingManager {

    interface NetworkingCallBackInterface{
        void networkingManagerCompleteWithJSonString(String jsonString);
        void networkingManagerSingleCountryWithJSonString(String jsonString);
    }

    NetworkingCallBackInterface listener;

    String CountryAPIstart = "https://restcountries.com/v3.1/name/";

    // Gets all the info we track.
    void getCountry(String countryName) {
        String CountryAPIend = "?fields=name,capital,currencies,independent,unMember,region,subregion,languages,flags,population";
        getDataFromURL(CountryAPIstart + countryName + CountryAPIend);
    }

    // We dont need to get all the information when searching name.
    // this only gets the essential fields - saving a lot of ressources.
    void getCountrySimpleInfoForRecycleView(String countryName){
//        String CountryAPIend = "?fields=name,flags";

        String CountryAPIend = "?fields=name,capital,currencies,independent,unMember,region,subregion,languages,flags,population";
        getDataFromURL(CountryAPIstart + countryName + CountryAPIend);
    }

    private void getDataFromURL(String fromURL){
        MultithreadManager.executorService.execute(new Runnable() {
            @Override
            public void run() {

                // The code here will run in background thread.
                HttpURLConnection urlConnection = null;
                try {
                    URL url = new URL(fromURL);
                    urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.setRequestMethod("GET");
                    urlConnection.setRequestProperty("content-Type", "application/json");

                    InputStream in = urlConnection.getInputStream();
                    InputStreamReader reader = new InputStreamReader(in);

                    int value = 0;
                    String jsonString = "";

                    //read until end of stream
                    while ((value = reader.read()) != -1){
                        jsonString += (char)value;
                    }
                    //Log.d("countryStream", jsonString);
                    final String json = jsonString;
                    MultithreadManager.handler.post(new Runnable() {
                        @Override
                        public void run() {
                            // the code here will run in main thread (UI/Activities)
                            listener.networkingManagerCompleteWithJSonString(json);
                        }
                    });

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    urlConnection.disconnect();
                }
            }
        });
    }


}
