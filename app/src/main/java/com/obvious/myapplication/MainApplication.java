package com.obvious.myapplication;

import android.app.Application;
import android.content.Context;

public class MainApplication extends Application {

    private static Context mContext;

    @Override
    public void onCreate(){

        mContext = getApplicationContext();

        super.onCreate();
    }
    public static Context getMainApplicationContext() {
        return mContext;
    }

}
