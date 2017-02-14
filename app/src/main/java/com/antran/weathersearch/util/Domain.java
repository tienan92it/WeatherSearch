package com.antran.weathersearch.util;

import android.net.Uri;

/**
 * Created by AN TRAN on 2/14/17.
 */

public class Domain {

    public static final String SCHEME = "http";
    public static final String AUTHORITY = "api.worldweatheronline.com";
    public static final String WEATHER_API = "weather.ashx";

    public static String getWeatherApiUrl(String key, String city, String fx, String format, String tp, String numOfDays) {
        Uri.Builder builder = new Uri.Builder();
        builder.scheme(SCHEME)
                .authority(AUTHORITY)
                .appendPath("free")
                .appendPath("v2")
                .appendPath(WEATHER_API)
                .appendQueryParameter("key", key)
                .appendQueryParameter("q", city)
                .appendQueryParameter("fx", fx)
                .appendQueryParameter("format", format)
                .appendQueryParameter("tp", tp)
                .appendQueryParameter("num_of_days", numOfDays);
        return builder.build().toString();
    }
}
