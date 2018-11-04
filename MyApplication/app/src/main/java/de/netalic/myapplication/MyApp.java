package de.netalic.myapplication;

import android.app.Application;

public class MyApp extends Application {

    private static MyApp sInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
    }

    public static MyApp getApp(){
        return sInstance;
    }
}
