package com.antran.weathersearch.ui.searchscreen;

import android.text.Editable;

/**
 * TODO: document your view interface.
 */
public interface SearchScreenViewListener {

    void onClickSearchButton(String searchText);

    void loadSearchHistory();

    void reloadWeather();
}