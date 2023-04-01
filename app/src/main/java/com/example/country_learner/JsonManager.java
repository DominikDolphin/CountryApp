package com.example.country_learner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonManager {

    //Sample data: https://restcountries.com/v3.1/name/canada
    //Visualizer: https://jsonformatter.curiousconcept.com/#

    static Country fromStringToCountry(String json){
        Country thisCountry = new Country();
        JSONArray rootJsonArray = null;

        try {
            rootJsonArray = new JSONArray(json);
            JSONObject rootJsonObject = rootJsonArray.getJSONObject(0);

            JSONObject nameJsonObject = rootJsonObject.getJSONObject("name");
            thisCountry.setOfficialName(nameJsonObject.getString("official"));

            JSONArray capitalArray = rootJsonObject.getJSONArray("capital");
            thisCountry.setCapital(capitalArray.getString(0));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return thisCountry;
    }
}
