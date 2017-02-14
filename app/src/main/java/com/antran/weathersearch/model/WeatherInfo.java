package com.antran.weathersearch.model;

/**
 * Created by appable on 2/13/17.
 */

public class WeatherInfo {

    private String cityName;
    private String iconWeather;
    private String observationTime;
    private String humidity;
    private String weatherDesc;
    private Error error;

    public WeatherInfo() {
        this.cityName = "";
        this.iconWeather = "";
        this.observationTime = "";
        this.humidity = "";
        this.weatherDesc = "";
        error = null;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getIconWeather() {
        return iconWeather;
    }

    public void setIconWeather(String iconWeather) {
        this.iconWeather = iconWeather;
    }

    public String getObservationTime() {
        return "Updated at " + observationTime;
    }

    public void setObservationTime(String observationTime) {
        this.observationTime = observationTime;
    }

    public String getHumidity() {
        return "Humidity: " + humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getWeatherDesc() {
        return "Weather description: " + weatherDesc;
    }

    public void setWeatherDesc(String weatherDesc) {
        this.weatherDesc = weatherDesc;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }
}
