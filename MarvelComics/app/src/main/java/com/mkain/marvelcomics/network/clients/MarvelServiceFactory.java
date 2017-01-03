package com.mkain.marvelcomics.network.clients;

import com.mkain.marvelcomics.BuildConfig;
import com.mkain.marvelcomics.network.interceptors.MarvelAuthorizedInterceptor;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MarvelServiceFactory {

    public static <S> S create(Class<S> serviceClass){
        Retrofit retrofit = buildRetrofit();
        return retrofit.create(serviceClass);
    }

    private static Retrofit buildRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.MARVEL_API_ENDPOINT)
                .client(buildOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private static OkHttpClient buildOkHttpClient() {
        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
        okHttpClientBuilder.addInterceptor(new MarvelAuthorizedInterceptor());

        if(BuildConfig.DEBUG) {
            addHttpLoggingInterceptor(okHttpClientBuilder);
        }

        return okHttpClientBuilder.build();
    }

    private static void addHttpLoggingInterceptor(OkHttpClient.Builder okHttpClientBuilder) {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpClientBuilder.networkInterceptors().add(httpLoggingInterceptor);
    }

}
