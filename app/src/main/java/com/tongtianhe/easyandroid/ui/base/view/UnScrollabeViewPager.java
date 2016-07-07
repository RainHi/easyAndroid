package com.tongtianhe.easyandroid.ui.base.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

/**
 * Created by free on 16/4/17.
 * 可以禁用滑动功能的ViewPager
 */
public class UnScrollabeViewPager extends ViewPager {

    private boolean mScrollable=true;

    public UnScrollabeViewPager(Context context) {
        super(context);
    }

    public UnScrollabeViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setScrollable(boolean scrollable){
        mScrollable=scrollable;
    }

    @Override
    public void scrollTo(int x, int y) {
        if(mScrollable){
            super.scrollTo(x, y);
        }
    }
}
