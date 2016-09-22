package com.tongtianhe.easyandroid.ui.base.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.Window;
import android.widget.Toast;

import com.tongtianhe.easyandroid.utils.InputMethodUtils;

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

    public void toast(CharSequence text){
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    public void toast(int textRes){
        Toast.makeText(this, textRes, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean dispatchTouchEvent(final MotionEvent ev) {
        boolean result = super.dispatchTouchEvent(ev);
        InputMethodUtils.hideSoftInputWhenTouchOutside(this, ev);
        return result;
    }



}
