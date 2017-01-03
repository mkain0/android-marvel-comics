package com.mkain.marvelcomics.helpers;

import com.facebook.AccessToken;
import com.mkain.marvelcomics.MarvelComicsApp;

public class AuthenticationHelper {

    public static boolean isAuthenticate() {
        return isAuthenticateWithFacebook() || isAuthenticateWithGoogle();
    }

    public static boolean isAuthenticateWithFacebook() {
        return AccessToken.getCurrentAccessToken() != null && !MarvelComicsApp.getGoogleApiHelper().getInstance().isConnected();
    }

    public static boolean isAuthenticateWithGoogle() {
        return AccessToken.getCurrentAccessToken() == null && MarvelComicsApp.getGoogleApiHelper().getInstance().isConnected();
    }

}
