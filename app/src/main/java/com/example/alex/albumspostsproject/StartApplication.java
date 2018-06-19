package com.example.alex.albumspostsproject;

import android.app.Application;
import android.content.Context;

import com.example.alex.albumspostsproject.data.network.NetworkBuilder;
import com.example.alex.albumspostsproject.data.network.RetrofitService;

public class StartApplication extends Application {

    private RetrofitService mService;

    @Override
    public void onCreate() {
        super.onCreate();
        mService = NetworkBuilder.initRetrofit();
    }

    public static StartApplication get(Context context) {
        return (StartApplication) context.getApplicationContext();
    }

    public RetrofitService getService() {
        return mService;
    }
}
