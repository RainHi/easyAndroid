package com.tongtianhe.easyandroid.ui.base.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by free on 16/11/29.
 * ViewPager嵌套在可滚动的控件中时，高度会不显示，使用此ViewPager可正常显示
 */
public class WrapContentViewPager extends ViewPager{

    public WrapContentViewPager(Context context) {
        super(context);
    }

    public WrapContentViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int heightMode=MeasureSpec.getMode(heightMeasureSpec);
        if(heightMode!=MeasureSpec.UNSPECIFIED){
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            return;
        }
        //UNSPECIFIED时，自己去算高度
        int height=0;
        for (int i=0;i<getChildCount();i++){
            View child=getChildAt(i);
            child.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0,MeasureSpec.UNSPECIFIED));
            int h=child.getMeasuredHeight();
            if(h>height){
                height=h;
            }
        }
        super.onMeasure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(height+getPaddingTop()+getPaddingBottom(), MeasureSpec.EXACTLY));
    }
}
