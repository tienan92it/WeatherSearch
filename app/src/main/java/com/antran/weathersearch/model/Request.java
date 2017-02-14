package com.antran.weathersearch.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by AN TRAN on 2/14/17.
 */

public class Request {

    @SerializedName("type")
    public String type;

    @SerializedName("query")
    public String query;

}
