package com.example.country_learner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonManager {

    //Sample data: https://restcountries.com/v3.1/name/canada
    //Visualizer: https://jsonformatter.curiousconcept.com/#

    //This is used for the basic recycleview preview of  country.
    public ArrayList<Country> fromStringToCountriesList(String json){
        ArrayList<Country> list = new ArrayList<>();
        JSONArray rootJsonArray = null;
        try {
            rootJsonArray = new JSONArray(json);
            for (int i = 0; i < rootJsonArray.length(); i++){
                //list.add(new Country());
                Country thisCountry = new Country();
                JSONObject dataObject = rootJsonArray.getJSONObject(i);

                JSONObject nameJsonObject = dataObject.getJSONObject("name");
                thisCountry.setOfficialName(nameJsonObject.getString("official"));
                thisCountry.setCommonName(nameJsonObject.getString("common"));
//
//                // flag (in png format)
                JSONObject flagJsonObject = dataObject.getJSONObject("flags");
                thisCountry.setFlag(flagJsonObject.getString("png"));
//
                list.add(thisCountry);
            }
        }catch (JSONException e) {
            e.printStackTrace();
        }

        return list;
    }

     public  Country fromStringToCountry(String json){
        Country thisCountry = new Country();
        JSONArray rootJsonArray = null;

        try {

            // API returns in format: [{
            // So, get JSonrray, then jsonObject
            rootJsonArray = new JSONArray(json);
            JSONObject rootJsonObject = rootJsonArray.getJSONObject(0);

            //Official Name & Common Name
            JSONObject nameJsonObject = rootJsonObject.getJSONObject("name");
            thisCountry.setOfficialName(nameJsonObject.getString("official"));
            thisCountry.setCommonName(nameJsonObject.getString("common"));

            // Capital City (change to parse)
            JSONArray capitalArray = rootJsonObject.getJSONArray("capital");
            thisCountry.setCapital(capitalArray.getString(0));

            // Independent & UN Member
            thisCountry.setIndependent(rootJsonObject.getBoolean("independent"));
            thisCountry.setUnMember(rootJsonObject.getBoolean("unMember"));

            // Continent & Subregion
            thisCountry.setSubregion(rootJsonObject.getString("subregion"));
            thisCountry.setRegion(rootJsonObject.getString("region"));

            // flag (in png format)
            JSONObject flagJsonObject = rootJsonObject.getJSONObject("flags");
            thisCountry.setFlag(flagJsonObject.getString("png"));

            //population
            thisCountry.setPopulation(rootJsonObject.getInt("population"));

            // Currency
            thisCountry.setCurrency(
                    getCurrenciesFromJSon(rootJsonObject.getJSONObject("currencies"))
            );

            // Official Languages
            thisCountry.setOfficialLanguages(
                    getLanguagesFromJSON(rootJsonObject.getJSONObject("languages"))
            );

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return thisCountry;
    }


    private String getMultipleJSonObjectsToString(JSONObject currenciesJsonObject, String seperator) throws JSONException {
        String currencyNames = "";
        for (int i = 0; i < currenciesJsonObject.names().length(); i++) {

            // Add comma & tabs unless it's the last index.
            if (i != currenciesJsonObject.names().length()-1)
                currencyNames += currenciesJsonObject.names().getString(i) + seperator;
            else
                currencyNames += currenciesJsonObject.names().getString(i);
        }

        return currencyNames;
    }

    private String getCurrenciesFromJSon(JSONObject currenciesJsonObject) throws JSONException {
        boolean multipleCurrencies = currenciesJsonObject.names().length() > 1;
        String currencyNames = "";

        if (multipleCurrencies)
            currencyNames = getMultipleJSonObjectsToString(currenciesJsonObject, ",\t\t");
        else
            currencyNames = currenciesJsonObject.names().getString(0);

        return currencyNames;
    }

    private String extractMultipleLanguages(JSONObject languageJSonObject, String seperator) throws JSONException {
        String languagesString = "";

        for (int i = 0; i < languageJSonObject.names().length(); i++) {
            // Add comma & tabs unless it's the last index.
            if (i != languageJSonObject.names().length()-1)
                languagesString += languageJSonObject.get(languageJSonObject.names().getString(i)) + seperator;
            else
                languagesString += languageJSonObject.get(languageJSonObject.names().getString(i));
        }

        return languagesString;
    }

    String getLanguagesFromJSON(JSONObject languageJsonObject) throws JSONException {
        boolean multipleLanguages = languageJsonObject.names().length() > 1;
        String allLanguages = "";
        if (multipleLanguages)
            allLanguages = extractMultipleLanguages(languageJsonObject, ", ");
        else
            allLanguages = (String) languageJsonObject.get(languageJsonObject.names().getString(0));

        return allLanguages;
    }

}
