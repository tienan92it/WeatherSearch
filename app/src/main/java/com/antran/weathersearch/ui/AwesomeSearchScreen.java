package com.antran.weathersearch.ui;

import android.app.Activity;
import android.os.Bundle;
import android.widget.RelativeLayout;

import com.antran.weathersearch.R;
import com.antran.weathersearch.ui.searchscreen.SearchScreenController;
import com.antran.weathersearch.ui.searchscreen.SearchScreenControllerListener;
import com.antran.weathersearch.ui.searchscreen.SearchScreenView;

/**
 * Created by AN TRAN on 2/14/17.
 */

public class AwesomeSearchScreen extends Activity implements SearchScreenControllerListener {

    private final int LAYOUT_ID = R.layout.activity_search_screen;

    private SearchScreenController searchScreenController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(LAYOUT_ID);

        SearchScreenView searchScreenView = new SearchScreenView((RelativeLayout) findViewById(R.id.root_view));
        searchScreenController = new SearchScreenController(this, this, searchScreenView);
        searchScreenView.setListener(searchScreenController);
        searchScreenController.setWeatherApiParams("f16c45e7217c8641fa4d5ae8e2415", "yes", "json", "24", "1");
    }

}
