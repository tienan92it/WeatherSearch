package com.antran.weathersearch.ui.searchscreen;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v4.util.LruCache;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.antran.weathersearch.model.WeatherData;
import com.antran.weathersearch.ui.adapter.SearchHistoryAdapter;
import com.antran.weathersearch.util.Domain;
import com.antran.weathersearch.util.ParseHelper;
import com.antran.weathersearch.util.SearchHistoryHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * TODO: document your controller class.
 */
public class SearchScreenController implements SearchScreenViewListener {

    private Context context;
    private SearchScreenView view;
    private SearchScreenControllerListener listener;
    private RequestQueue requestQueue;
    private ImageLoader imageLoader;

    private WeatherData weatherData;
    private String currentQuery = "";

    public SearchScreenController(Context context, SearchScreenControllerListener listener, SearchScreenView view) {
        this.context = context;
        this.listener = listener;
        this.view = view;

        requestQueue = Volley.newRequestQueue(context);
        imageLoader = new ImageLoader(requestQueue, new ImageLoader.ImageCache() {
            private final LruCache<String, Bitmap>
                    cache = new LruCache<String, Bitmap>(20);

            @Override
            public Bitmap getBitmap(String url) {
                return cache.get(url);
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {
                cache.put(url, bitmap);
            }
        });
        weatherData = new WeatherData();
    }

    private String keyParam = "";
    private String fxParam = "";
    private String formatParam = "";
    private String tpParam = "";
    private String numOfDayParam = "";

    public void setWeatherApiParams(String key, String fx, String format, String tp, String numOfDays) {
        keyParam = key;
        fxParam = fx;
        formatParam = format;
        tpParam = tp;
        numOfDayParam = numOfDays;
    }

    public void bindData(WeatherData weatherInfo) {
        this.weatherData = weatherInfo;

        if (weatherData != null) {
            if (weatherData.request != null && weatherData.current_condition != null) {
                view.showWeatherContent();
                view.setCityName(weatherData.request.get(0).query);

                view.setIconWeather(weatherData.current_condition.get(0).weatherIconUrl.get(0).value, imageLoader);
                view.setWeatherDesc(weatherData.current_condition.get(0).weatherDesc.get(0).value);
                view.setHumidity(weatherData.current_condition.get(0).humidity);
                view.setObservationTime(weatherData.current_condition.get(0).observationTime);
                view.setTempC(weatherData.current_condition.get(0).tempC);
            }
            if (weatherData.error != null) {
                view.setErrorMsg(weatherData.error.get(0).msg);
            }
        } else {
            view.hideWeatherContent();
        }
    }

    @Override
    public void onClickSearchButton(String searchText) {
        view.showRefresh();
        currentQuery = searchText;
        final String weatherUrl = Domain.getWeatherApiUrl(keyParam, searchText, fxParam, formatParam, tpParam, numOfDayParam);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, weatherUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                view.hideRefresh();
                try {
                    JSONObject jsonResult = new JSONObject(response);
                    weatherData = ParseHelper.parseWeatherFrom(jsonResult.get("data").toString());
                    if (weatherData.error == null)
                        SearchHistoryHelper.putWeatherData(context, weatherData);
                    bindData(weatherData);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                view.hideRefresh();
                Log.e("Error", error.getMessage());
                view.setErrorMsg(error.getMessage());
            }
        });

        stringRequest.setShouldCache(false);

        requestQueue.add(stringRequest);
    }

    @Override
    public void loadSearchHistory() {
        List<WeatherData> history = SearchHistoryHelper.getWeatherHistory(context);

        if (history != null) {
            SearchHistoryAdapter searchHistoryAdapter = new SearchHistoryAdapter(context, history);
            searchHistoryAdapter.setOnClickItemEvent(new SearchHistoryAdapter.ClickItemEvent() {
                @Override
                public void onClickItem(WeatherData weatherData) {
                    view.hideHistory();
                    view.stopSearching();
                    currentQuery = weatherData.request.get(0).query;
                    bindData(weatherData);
                }
            });
            view.setSearchHistoryAdapter(searchHistoryAdapter);
        } else {
            view.hideHistory();
        }
    }

    @Override
    public void reloadWeather() {
        String weatherUrl = Domain.getWeatherApiUrl(keyParam, currentQuery, fxParam, formatParam, tpParam, numOfDayParam);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, weatherUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                view.hideRefresh();
                try {
                    JSONObject jsonResult = new JSONObject(response);
                    weatherData = ParseHelper.parseWeatherFrom(jsonResult.get("data").toString());
                    bindData(weatherData);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                view.hideRefresh();
                Log.e("Error", error.getMessage());
                view.setErrorMsg(error.getMessage());
            }
        });

        stringRequest.setShouldCache(false);

        requestQueue.add(stringRequest);
    }
}