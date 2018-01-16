package com.example.android.quakereport;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

/**
 * Created by Nick on 1/16/2018.
 */

public class EarthquakeLoader extends AsyncTaskLoader<List<Earthquake>> {

    /**
     * Tag for log messages
     */
    private static final String LOG_TAG = EarthquakeLoader.class.getName();

    private String url;

    public EarthquakeLoader(Context context, String url) {

        super(context);
        this.url = url;

    }


    @Override
    public List<Earthquake> loadInBackground() {

        if (url == null) {

            return null;

        }

        // Perform the HTTP request for earthquake data and process the response.
        return QueryUtils.extractEarthquakes(url);

    }


    @Override
    protected void onStartLoading() {

        forceLoad();

    }

}
