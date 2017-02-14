package com.antran.weathersearch.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.antran.weathersearch.R;
import com.antran.weathersearch.model.WeatherData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AN TRAN on 2/14/17.
 */

public class SearchHistoryAdapter extends RecyclerView.Adapter<SearchHistoryAdapter.ViewHolder> {

    private List<WeatherData> weatherDataList;
    private Context context;
    private ClickItemEvent clickItemEvent = null;

    public SearchHistoryAdapter(Context context, List<WeatherData> weatherData) {
        this.context = context;
        this.weatherDataList = weatherData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View itemView = inflater.inflate(R.layout.item_search_history, parent, false);

        ViewHolder viewHolder = new ViewHolder(itemView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final WeatherData weatherData = weatherDataList.get(position);

        TextView cityNameView = holder.cityName;
        cityNameView.setText(weatherData.request.get(0).query);
        cityNameView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clickItemEvent != null) {
                    clickItemEvent.onClickItem(weatherData);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return weatherDataList.size();
    }

    public void setOnClickItemEvent(ClickItemEvent clickItemEvent) {
        this.clickItemEvent = clickItemEvent;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView cityName;

        public ViewHolder(View itemView) {
            super(itemView);

            cityName = (TextView) itemView.findViewById(R.id.city_name);
        }
    }

    public interface ClickItemEvent {
        void onClickItem(WeatherData weatherData);
    }
}
