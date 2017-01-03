package com.mkain.marvelcomics.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.facebook.login.LoginManager;
import com.mkain.marvelcomics.MarvelComicsApp;
import com.mkain.marvelcomics.R;
import com.mkain.marvelcomics.adapters.ComicAdapter;
import com.mkain.marvelcomics.decorators.DividerItemDecorator;
import com.mkain.marvelcomics.domain.Comic;
import com.mkain.marvelcomics.helpers.AuthenticationHelper;
import com.mkain.marvelcomics.network.clients.MarvelServiceFactory;
import com.mkain.marvelcomics.network.clients.MarvelComicsClient;
import com.mkain.marvelcomics.network.dtos.ComicDataWrapper;
import com.mkain.marvelcomics.transformers.ComicTransformer;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String LIMIT = "30";
    private static final int HTTP_STATUS_CODE_OK = 200;

    private ComicDataWrapper comicDataWrapper;
    private RecyclerView comicRecyclerView;
    private LinearLayoutManager layoutManager;
    private ComicAdapter comicAdapter;
    private ComicTransformer comicTransformer = new ComicTransformer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        isAuthenticate();
        setupComicRecyclerView();
        fetchComics();
    }

    private void setupComicRecyclerView() {
        comicRecyclerView = (RecyclerView) findViewById(R.id.comics_recycler_view);
        comicRecyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        comicRecyclerView.addItemDecoration(new DividerItemDecorator(this, LinearLayoutManager.VERTICAL));
        comicRecyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    // TODO: add functionality.
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                Toast.makeText(this, "Search selected", Toast.LENGTH_SHORT)
                        .show();
                break;
            case R.id.action_refresh:
                Toast.makeText(this, "Refresh selected", Toast.LENGTH_SHORT)
                        .show();
                break;
            case R.id.action_profile:
                Toast.makeText(this, "Profile selected", Toast.LENGTH_SHORT)
                        .show();
                break;
            case R.id.action_logout:
                logout();
                break;
        }
        return true;
    }

    private void fetchComics() {
        // TODO: should be in other class.
        Call<ComicDataWrapper> comicsCall = new MarvelServiceFactory().create(MarvelComicsClient.class).fetchComics(LIMIT);
        comicsCall.enqueue(new Callback<ComicDataWrapper>() {
            @Override
            public void onResponse(Call<ComicDataWrapper> call, Response<ComicDataWrapper> response) {
                if (response.code() == HTTP_STATUS_CODE_OK) {
                    comicDataWrapper = response.body();
                    List<Comic> comics = comicTransformer.transform(comicDataWrapper.getData().getComics());
                    comicAdapter = new ComicAdapter(comics, getApplicationContext());
                    comicRecyclerView.setAdapter(comicAdapter);
                }
            }

            @Override
            public void onFailure(Call<ComicDataWrapper> call, Throwable t) {
                Log.d("Call<ComicDataWrapper>", t.getMessage());
            }
        });
    }

    private void isAuthenticate() {
        if (!AuthenticationHelper.isAuthenticate()) {
            getAuthenticationActivity();
        }
    }

    private void getAuthenticationActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void logout() {
        if (AuthenticationHelper.isAuthenticateWithGoogle()) {
            MarvelComicsApp.getGoogleApiHelper().getInstance().disconnect();
        }

        if (AuthenticationHelper.isAuthenticateWithFacebook()) {
            LoginManager.getInstance().logOut();
        }

        getAuthenticationActivity();
    }

}
