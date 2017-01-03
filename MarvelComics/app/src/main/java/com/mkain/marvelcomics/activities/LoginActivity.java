package com.mkain.marvelcomics.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.SignInButton;
import com.mkain.marvelcomics.MarvelComicsApp;
import com.mkain.marvelcomics.R;

import java.util.Arrays;

public class LoginActivity extends AppCompatActivity {

    private static final String FACEBOOK_PERMISSIONS = "public_profile, email";
    private static final String ON_CANCEL_MESSAGE = "User cancelled";
    private static final String ON_ERROR_MESSAGE = "Error on Login";
    private static final int RC_SIGN_IN = 9001;

    private LoginButton facebookSignInButton;
    private SignInButton googleSignInButton;
    private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setUpFacebookLogin();
        setUpGoogleLogin();
    }

    private void setUpGoogleLogin() {
        googleSignInButton = (SignInButton) findViewById(R.id.btn_google_sign_in);
        googleSignInButton.setSize(SignInButton.SIZE_WIDE);
        googleSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signInWithGoogle();
            }
        });
    }

    private void setUpFacebookLogin() {
        facebookSignInButton = (LoginButton) findViewById(R.id.btn_facebook_sign_in);
        facebookSignInButton.setReadPermissions(Arrays.asList(FACEBOOK_PERMISSIONS));

        callbackManager = CallbackManager.Factory.create();
        facebookSignInButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                getMainActivity();
            }

            @Override
            public void onCancel() {
                Toast.makeText(LoginActivity.this, ON_CANCEL_MESSAGE, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException exception) {
                Toast.makeText(LoginActivity.this, ON_ERROR_MESSAGE, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void signInWithGoogle() {
        MarvelComicsApp.getGoogleApiHelper().getInstance().connect();
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(MarvelComicsApp.getGoogleApiHelper().getInstance());
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private void getMainActivity() {
        Intent mainIntent = new Intent(this, MainActivity.class);
        mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(mainIntent);
    }

    // TODO: fetch account information.
    private void getProfileInformation() {

    }

    @Override
    protected void onActivityResult(int requestCode, int responseCode, Intent intent) {
        super.onActivityResult(requestCode, responseCode, intent);
        if (requestCode == RC_SIGN_IN) {
            handleGoogleSignInResult(intent);
        } else {
            handleFacebookSignInResult(requestCode, responseCode, intent);
        }
    }

    private void handleGoogleSignInResult(Intent intent) {
        GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(intent);
        if (result.isSuccess()) {
            getMainActivity();
        }
    }

    private void handleFacebookSignInResult(int requestCode, int responseCode, Intent intent) {
        callbackManager.onActivityResult(requestCode, responseCode, intent);
    }

}