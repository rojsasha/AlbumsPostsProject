package com.example.alex.albumspostsproject.data.network;

import com.example.alex.albumspostsproject.config.AppConstants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkBuilder {

    private static RetrofitService sService = null;

    public static RetrofitService initRetrofit() {
        if (sService == null) {
            return new Retrofit.Builder()
                    .baseUrl(AppConstants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(RetrofitService.class);
        }
        return sService;
    }
}
