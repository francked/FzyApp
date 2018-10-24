package com.example.ryan.fzyapp;

import android.app.Application;
import android.content.Context;

/**
 * Created by ryan on 18-10-19.
 */

public class MyApplication extends Application {

    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }

    public static Context getContext(){
        return  context;
    }
}
