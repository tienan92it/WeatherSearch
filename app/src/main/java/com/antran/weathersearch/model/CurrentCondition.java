package com.antran.weathersearch.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by AN TRAN on 2/14/17.
 */

public class CurrentCondition {

    @SerializedName("observation_time")
    public String observationTime;

    @SerializedName("temp_C")
    public String tempC;

    public List<WeatherIconUrl> weatherIconUrl;

    public List<WeatherDesc> weatherDesc;

    @SerializedName("humidity")
    public String humidity;

}
