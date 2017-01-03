package com.mkain.marvelcomics;

import android.app.Application;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.mkain.marvelcomics.helpers.GoogleApiHelper;

public class MarvelComicsApp extends Application {

    private GoogleApiHelper googleApiHelper;
    private static MarvelComicsApp marvelComicsApp;

    @Override
    public void onCreate() {
        super.onCreate();
        marvelComicsApp = this;
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);

        googleApiHelper = new GoogleApiHelper(getApplicationContext());
    }

    public static synchronized MarvelComicsApp getInstance() {
        return marvelComicsApp;
    }

    public static GoogleApiHelper getGoogleApiHelper() {
        return getInstance().googleApiHelper;
    }

}
