package com.antran.weathersearch.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.content.Context;

import com.antran.weathersearch.model.WeatherData;
import com.antran.weathersearch.util.caching.CachingKeys;
import com.antran.weathersearch.util.caching.CachingService;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by AN TRAN on 2/14/17.
 */

public class SearchHistoryHelper {

    public static void putWeatherData(Context context, WeatherData weatherData) {
        CachingService cachingService = CachingService.getInstance(context);

        Gson gson = new Gson();

        List<WeatherData> weatherDataList = getWeatherHistory(context);

        if (weatherDataList == null) {
            weatherDataList = new ArrayList<>();
            weatherDataList.add(weatherData);
            cachingService.cachingString(CachingKeys.SEARCH_HISTORY, gson.toJson(weatherDataList));
        } else {
            weatherDataList.add(0, weatherData);
            if (weatherDataList.size() > 10) {
                weatherDataList.remove(weatherDataList.size() - 1);
            }
            cachingService.cachingString(CachingKeys.SEARCH_HISTORY, gson.toJson(weatherDataList));
        }
    }

    public static List<WeatherData> getWeatherHistory(Context context) {
        CachingService cachingService = CachingService.getInstance(context);
        String jsonHistory = cachingService.getString(CachingKeys.SEARCH_HISTORY);

        Gson gson = new Gson();

        if (jsonHistory == null || jsonHistory.compareTo("") == 0) {
            return null;
        } else {
            Type listType = new TypeToken<List<WeatherData>>() {
            }.getType();
            List<WeatherData> weatherDataList = (List<WeatherData>) gson.fromJson(jsonHistory, listType);
            return weatherDataList;
        }

    }


}
