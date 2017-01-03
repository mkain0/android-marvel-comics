package com.mkain.marvelcomics.network.clients;

import com.mkain.marvelcomics.network.dtos.ComicDataWrapper;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface MarvelComicsClient {

    String COMICS_ENDPOINT = "comics";
    String ACCEPT_APPLICATION_JSON = "Accept: application/json";

    @Headers({ACCEPT_APPLICATION_JSON})
    @GET(COMICS_ENDPOINT)
    Call<ComicDataWrapper> fetchComics(@Query("limit") String limit);

}
