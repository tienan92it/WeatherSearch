package com.antran.weathersearch.ui.searchscreen;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.antran.weathersearch.R;
import com.antran.weathersearch.ui.widget.SearchBar;

/**
 * TODO: document your view class.
 */
public class SearchScreenView {

    private RelativeLayout rootView;
    private SearchScreenViewListener listener;

    private SearchBar searchBar;
    private SwipeRefreshLayout swipeRefreshContent;
    private RelativeLayout weatherContent;
    private NetworkImageView iconWeather;
    private TextView cityName;
    private TextView humidity;
    private TextView weatherDesc;
    private TextView observationTime;
    private TextView tempC;
    private TextView errorMsg;
    private RecyclerView searchHistory;

    public SearchScreenView(RelativeLayout rootView) {
        this.rootView = rootView;

        searchBar = (SearchBar) rootView.findViewById(R.id.search_bar);
        swipeRefreshContent = (SwipeRefreshLayout) rootView.findViewById(R.id.swipe_refresh_content);
        weatherContent = (RelativeLayout) rootView.findViewById(R.id.weather_content);
        iconWeather = (NetworkImageView) rootView.findViewById(R.id.icon_weather);
        cityName = (TextView) rootView.findViewById(R.id.city_name);
        humidity = (TextView) rootView.findViewById(R.id.humidity);
        weatherDesc = (TextView) rootView.findViewById(R.id.weather_desc);
        observationTime = (TextView) rootView.findViewById(R.id.observation_time);
        tempC = (TextView) rootView.findViewById(R.id.temp_c);
        errorMsg = (TextView) rootView.findViewById(R.id.error_msg);
        searchHistory = (RecyclerView) rootView.findViewById(R.id.search_history);
    }

    public void setListener(final SearchScreenViewListener listener) {
        this.listener = listener;

        searchBar.setOnClickSearchButton(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClickSearchButton(searchBar.getSearchText());
                searchBar.stopSearch();
            }
        });

        searchBar.setSearchEvent(new SearchBar.SearchEvent() {
            @Override
            public void onSearching() {
                listener.loadSearchHistory();
            }

            @Override
            public void onStopSearching() {
                searchHistory.setVisibility(View.GONE);
            }
        });

        searchHistory.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });

        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchBar.stopSearch();
            }
        });

        swipeRefreshContent.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                listener.reloadWeather();
            }
        });
    }

    public void setIconWeather(String url, ImageLoader imageLoader) {
        iconWeather.setImageUrl(url, imageLoader);
    }

    public void setCityName(String cityName) {
        this.cityName.setText(cityName);
    }

    public void setHumidity(String humidity) {
        this.humidity.setText(humidity);
    }

    public void setWeatherDesc(String weatherDesc) {
        this.weatherDesc.setText(weatherDesc);
    }

    public void setObservationTime(String observationTime) {
        this.observationTime.setText(observationTime);
    }

    public void setTempC(String tempC) {
        this.tempC.setText(tempC + "\u00B0C");
    }

    public void showWeatherContent() {
        weatherContent.setVisibility(View.VISIBLE);
        errorMsg.setVisibility(View.GONE);
    }

    public void hideWeatherContent() {
        weatherContent.setVisibility(View.GONE);
        errorMsg.setVisibility(View.VISIBLE);
    }

    public void setErrorMsg(String msg) {
        hideWeatherContent();
        errorMsg.setText(msg);
    }

    public void setSearchHistoryAdapter(RecyclerView.Adapter adapter) {
        searchHistory.setAdapter(adapter);
        searchHistory.setLayoutManager(new LinearLayoutManager(rootView.getContext()));
        searchHistory.setVisibility(View.VISIBLE);
    }

    public void hideHistory() {
        searchHistory.setVisibility(View.GONE);
    }

    public void stopSearching() {
        searchBar.stopSearch();
    }

    public void showRefresh() {
        swipeRefreshContent.setRefreshing(true);
    }

    public void hideRefresh() {
        swipeRefreshContent.setRefreshing(false);
    }
}