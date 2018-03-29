package com.test.user.testzimadapplication;

import android.app.Application;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        RetrofitInstance.getRetrofitInstance();
    }
}