package com.antran.weathersearch.ui;

import android.app.Activity;
import android.os.Bundle;
import android.widget.RelativeLayout;

import com.antran.weathersearch.R;
import com.antran.weathersearch.ui.searchscreen.SearchScreenController;
import com.antran.weathersearch.ui.searchscreen.SearchScreenControllerListener;
import com.antran.weathersearch.ui.searchscreen.SearchScreenView;

/**
 * Created by AN TRAN on 2/13/17.
 */

public class SearchScreen extends Activity implements SearchScreenControllerListener {

    private final int LAYOUT_ID = R.layout.activity_search_screen;

    private SearchScreenController searchScreenController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(LAYOUT_ID);

        SearchScreenView searchScreenView = new SearchScreenView((RelativeLayout) findViewById(R.id.root_view));
        searchScreenController = new SearchScreenController(this, this, searchScreenView);
        searchScreenView.setListener(searchScreenController);
        searchScreenController.setWeatherApiParams("dd5bc7742928d1633213b5da075ec", "yes", "json", "24", "1");
    }
}
