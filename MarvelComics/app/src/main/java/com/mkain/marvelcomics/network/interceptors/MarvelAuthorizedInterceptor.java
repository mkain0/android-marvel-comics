package com.mkain.marvelcomics.network.interceptors;

import com.mkain.marvelcomics.BuildConfig;
import com.mkain.marvelcomics.network.helpers.HashGenerator;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class MarvelAuthorizedInterceptor implements Interceptor {

    private static final String publicKey = BuildConfig.MARVEL_PUBLIC_API_KEY;
    private static final String privateKey = BuildConfig.MARVEL_PRIVATE_API_KEY;
    private static final String TIMESTAMP_PARAM = "ts";
    private static final String APIKEY_PARAM = "apikey";
    private static final String HASH_PARAM = "hash";

    @Override
    public Response intercept(Chain chain) throws IOException {
        String timestamp = String.valueOf(System.currentTimeMillis());
        Request request = chain.request();
        HttpUrl url = addKeysToParams(request, timestamp, generateHash(timestamp));
        request = request.newBuilder().url(url).build();
        return chain.proceed(request);
    }

    private String generateHash(String timestamp) {
         return HashGenerator.generate(timestamp, publicKey, privateKey);
    }

    private HttpUrl addKeysToParams(Request request, String timestamp, String hash) {
        return request.url()
                .newBuilder()
                .addQueryParameter(TIMESTAMP_PARAM, timestamp)
                .addQueryParameter(APIKEY_PARAM, publicKey)
                .addQueryParameter(HASH_PARAM, hash)
                .build();
    }

}
