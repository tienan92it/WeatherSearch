package com.antran.weathersearch.util;

import com.google.gson.Gson;

import com.antran.weathersearch.model.Error;
import com.antran.weathersearch.model.WeatherData;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by AN TRAN on 2/14/17.
 */

public class ParseHelper {

    public static WeatherData parseWeatherFrom(String json) {

        Gson gson = new Gson();

        return gson.fromJson(json, WeatherData.class);
    }
}
