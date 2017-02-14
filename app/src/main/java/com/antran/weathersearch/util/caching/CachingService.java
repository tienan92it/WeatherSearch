package com.antran.weathersearch.util.caching;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by AN TRAN on 2/13/17.
 */

public class CachingService {

    private static CachingService instance;

    private final Context mContext;
    private SharedPreferences prefManager;
    private SharedPreferences.Editor editor;

    public CachingService(Context context) {
        mContext = context;
        prefManager = PreferenceManager.getDefaultSharedPreferences(context);
        editor = prefManager.edit();
    }

    public static CachingService getInstance(Context context) {
        if (instance == null) {
            instance = new CachingService(context);
        }
        return instance;
    }

    public void cachingString(String key, String text) {
        editor.putString(key, text);
        editor.commit();
    }

    public String getString(String key) {
        return prefManager.getString(key, "");
    }
}
