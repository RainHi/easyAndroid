package com.tongtianhe.easyandroid.ui.base.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

import com.tongtianhe.easyandroid.net.volley.Net;

/**
 * Created by free on 16/6/17.
 */
public abstract class BaseActivity extends Activity implements IInit{

    protected void setFullScreen(){
        requestWindowFeature(Window.FEATURE_NO_TITLE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setFullScreen();
        preLayout();
        setLayout();
        postLayout();
        setData();
        setEvents();
    }

    @Override
    public void preLayout() {}

    @Override
    public void postLayout() {}

    protected String getTag(){
        return getClass().getSimpleName();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Net.cancelAll(getTag());
    }

}
