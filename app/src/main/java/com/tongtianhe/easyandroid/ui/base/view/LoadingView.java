package com.tongtianhe.easyandroid.ui.base.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.tongtianhe.easyandroid.R;

/**
 * 加载控件
 */
public class LoadingView extends FrameLayout{

    private ProgressBar mProgressBar;
    private TextView mTxtDes;

    public LoadingView(Context context) {
        super(context);
        init();
    }

    public LoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LoadingView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init(){
        LayoutInflater.from(getContext()).inflate(R.layout.view_loading, this, true);
        mProgressBar= (ProgressBar) findViewById(R.id.vl_progress);
        mTxtDes= (TextView) findViewById(R.id.vl_txt);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return true;
    }


}
