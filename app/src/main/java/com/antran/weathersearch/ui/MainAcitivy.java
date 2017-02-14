package com.antran.weathersearch.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.antran.weathersearch.R;

/**
 * Created by AN TRAN on 2/14/17.
 */

public class MainAcitivy extends Activity {

    private static final int LAYOUT_ID = R.layout.activity_main;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(LAYOUT_ID);

        context = this;

        ((Button) findViewById(R.id.weather_search)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent weatherSearch = new Intent(context, SearchScreen.class);
                startActivity(weatherSearch);
            }
        });

        ((Button) findViewById(R.id.awesome_weather_search)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent weatherSearch = new Intent(context, AwesomeSearchScreen.class);
                startActivity(weatherSearch);
            }
        });
    }
}
