package com.tongtianhe.easyandroid.main;

import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.tongtianhe.easyandroid.R;
import com.tongtianhe.easyandroid.ui.base.activity.TitleBarActivity;

public class UiContainerActivity extends TitleBarActivity implements View.OnClickListener{

    private Button mBtnLoadOk,  mBtnFail;
    private TextView mTxtResult;

    @Override
    public void setLayout() {
        setContentView(R.layout.activity_ui_container);
        mBtnLoadOk = (Button) findViewById(R.id.am_btn_load_ok);
        mBtnFail = (Button) findViewById(R.id.am_btn_load_fail);
        mTxtResult= (TextView) findViewById(R.id.am_txt_result);


    }

    @Override
    public void setData() {
        setTitleBarTitle("测试");
    }

    @Override
    public void setEvents() {
        mBtnLoadOk.setOnClickListener(this);
        mBtnFail.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.am_btn_load_ok:
                loadData(true);
                break;
            case R.id.am_btn_load_fail:
                loadData(false);
                break;
        }
    }

    private Handler h=new Handler();

    private void loadData(final boolean success){
        showLoadingView();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                hideLoadingView();
                if (success) {
                    hideErrorView();
                    toast("访问成功");
                    mTxtResult.setText("成功返回了结果!");
                } else {
                    showErrorView();
                    toast("访问失败");
                    mTxtResult.setText("成功返回了结果!");
                }
            }
        }, 1000);
    }

    @Override
    protected void onNetRetry() {
        loadData(true);
    }

    @Override
    protected void onDestroy() {
        h.removeCallbacksAndMessages(null);
        super.onDestroy();
    }
}
