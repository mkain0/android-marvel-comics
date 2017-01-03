package com.mkain.marvelcomics.helpers;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

public class GoogleApiHelper implements GoogleApiClient.OnConnectionFailedListener, GoogleApiClient.ConnectionCallbacks {

    private static final String TAG = "GoogleApiHelper";
    private static final String ON_CONNECTION_FAILED = "onConnectionFailed:";
    private GoogleApiClient googleApiClient;
    private GoogleSignInOptions googleSignInOptions;
    private Context context;

    public GoogleApiHelper(Context context) {
        this.context = context;
        buildGoogleApiClient();
    }

    private void buildGoogleApiClient() {
        disconnect();
        googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        googleApiClient = new GoogleApiClient.Builder(context)
                .addApi(Auth.GOOGLE_SIGN_IN_API, googleSignInOptions)
                .addOnConnectionFailedListener(this)
                .addConnectionCallbacks(this)
                .build();
    }

    public void connect() {
        if (googleApiClient != null) {
            googleApiClient.connect();
        }
    }

    public void disconnect() {
        if (googleApiClient != null) {
            googleApiClient.disconnect();
        }
    }

    public GoogleApiClient getInstance() {
        return this.googleApiClient;
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d(TAG, ON_CONNECTION_FAILED + connectionResult);
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        // TODO: fetch user information.
    }

    @Override
    public void onConnectionSuspended(int i) {
        googleApiClient.connect();
    }

}
