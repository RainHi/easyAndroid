package com.tongtianhe.easyandroid.ui.base.activity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.tongtianhe.easyandroid.R;
import com.tongtianhe.easyandroid.ui.base.view.LoadingView;
import com.tongtianhe.easyandroid.ui.base.view.NetErrorView;
import com.tongtianhe.easyandroid.ui.base.view.TitleBar;

/**
 * Created by free on 16/6/22.
 */
public abstract class TitleBarActivity extends BaseActivity {

    private TitleBar mTitleBar;
    private FrameLayout mContentLayout;
    private NetErrorView mNetErrorView;
    private LoadingView mLoadingView;


    @Override
    public void preLayout() {
        super.setContentView(R.layout.activity_title_bar);
        mTitleBar = (TitleBar) findViewById(R.id.atb_title_bar);
        mContentLayout = (FrameLayout) findViewById(R.id.atb_layout_content);
        mNetErrorView = (NetErrorView) findViewById(R.id.atb_net_error_view);
        mLoadingView = (LoadingView) findViewById(R.id.atb_loading_view);
        mNetErrorView.setRetryClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNetRetry();
            }
        });
    }

    @Override
    public void setContentView(int layoutResID) {
        mContentLayout.removeAllViews();
        LayoutInflater.from(this).inflate(layoutResID, mContentLayout);
    }

    @Override
    public void setContentView(View view) {
        mContentLayout.removeAllViews();
        mContentLayout.addView(view);
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        mContentLayout.removeAllViews();
        mContentLayout.addView(view, params);
    }

    public TitleBar getTitleBar() {
        return mTitleBar;
    }

    public void setTitleBarTitle(String title) {
        mTitleBar.setTitle(title);
    }

    public void showLoadingView() {
        mLoadingView.setVisibility(View.VISIBLE);
    }

    public void hideLoadingView() {
        mLoadingView.setVisibility(View.GONE);
    }

    public NetErrorView getNetErrorView() {
        return mNetErrorView;
    }

    public void showErrorView() {
        mNetErrorView.setVisibility(View.VISIBLE);
    }

    public void hideErrorView() {
        mNetErrorView.setVisibility(View.GONE);
    }

    /**
     * NetErrorView的重试按钮单击监听，需要自己设置重写此方法
     */
    protected void onNetRetry() {
    }


}
