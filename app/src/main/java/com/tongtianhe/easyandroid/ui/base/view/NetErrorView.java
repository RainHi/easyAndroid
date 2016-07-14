package com.tongtianhe.easyandroid.ui.base.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.tongtianhe.easyandroid.R;

/**
 * 网络错误控件
 * Created by free on 16/2/29.
 */
public class NetErrorView extends FrameLayout {

    public static final int ID_RETRY_BUTTON=R.id.btn_retry;

    private Button mBtnRetry;

    public NetErrorView(Context context) {
        this(context, null, 0);
    }

    public NetErrorView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NetErrorView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init(){
        LayoutInflater.from(getContext()).inflate(R.layout.view_net_error, this, true);
        mBtnRetry= (Button) findViewById(R.id.btn_retry);
    }

    public void setRetryClickListener(OnClickListener listener){
        if(listener!=null){
            mBtnRetry.setOnClickListener(listener);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return true;
    }


}
