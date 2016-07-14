package com.tongtianhe.easyandroid.ui.base.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.tongtianhe.easyandroid.R;

/**
 * Created by free on 16/5/20.
 * 固定宽高比例的ImageView
 * 主要用于GridView中加载图片
 */
public class FixRatioImageView extends ImageView {

    private float HWRatio=-1;//高宽比

    public FixRatioImageView(Context context) {
        super(context);
    }

    public FixRatioImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FixRatioImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        TypedArray ta=context.obtainStyledAttributes(attrs, R.styleable.FixRatioImageView);
        HWRatio=ta.getFloat(R.styleable.FixRatioImageView_ratio, HWRatio);
        ta.recycle();
    }

    public void setRatio(float heightWidthRatio){
        HWRatio=heightWidthRatio;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if(HWRatio>0){
            int w=getMeasuredWidth();
            int h= (int) (w*HWRatio);
            setMeasuredDimension(w, h);
        }
    }
}
