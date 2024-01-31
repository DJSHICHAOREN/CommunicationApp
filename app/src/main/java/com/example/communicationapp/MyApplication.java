package com.example.communicationapp;

import android.app.Application;

import com.example.communicationapp.util.ContextUtil;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        ContextUtil.setContext(getApplicationContext());
    }
}
