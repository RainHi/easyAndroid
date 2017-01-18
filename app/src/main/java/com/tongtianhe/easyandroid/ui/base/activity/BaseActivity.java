package com.tongtianhe.easyandroid.ui.base.activity;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Window;
import android.widget.Toast;

import com.tongtianhe.easyandroid.net.volley.Net;
import com.tongtianhe.easyandroid.utils.DebugUtil;

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
    protected void onResume() {
        super.onResume();
        DebugUtil.logPageInfo(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Net.cancelAll(getTag());
    }

    protected void toast(String text){
        if(TextUtils.isEmpty(text)){
            return;
        }
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

}
