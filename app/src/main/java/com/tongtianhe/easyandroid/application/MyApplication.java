package com.tongtianhe.easyandroid.application;

import android.app.Application;

/**
 * Created by free on 16/7/14.
 */
public class MyApplication extends Application{

    private static MyApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance=this;
        FileCarshHandler.getInstance().regitster(this);

    }

    public static MyApplication getInstance(){
        return mInstance;
    }


}
