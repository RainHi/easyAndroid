package com.tongtianhe.easyandroid.ui.base.view;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.tongtianhe.easyandroid.R;


/**
 * 标题条
 */
public class TitleBar extends FrameLayout {

    private ImageButton mImgBtnBack, mImgBtnRight;
    private TextView mTxtTitle, mTxtRight;
    private FrameLayout mLayoutRight;
    private FrameLayout mLayoutCenter;

    private String mTitle, mRightBtnText;
    private Drawable mRightBtnImage;
    private int mRightBtnTextSize;


    public static final int ID_RIGHT_IMAGE_BUTTTON = R.id.vtb_imgbtn_right,
            ID_RIGHT_TEXT_BUTTON = R.id.vtb_txt_right,
            ID_BACK_BUTTON = R.id.vtb_btn_back;


    public TitleBar(Context context) {
        this(context, null);
    }

    public TitleBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TitleBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs);
        initViews();
    }


    private void init(AttributeSet attrSet) {
        TypedArray attrs = getContext().obtainStyledAttributes(attrSet, R.styleable.TitleBar);
        mTitle = attrs.getString(R.styleable.TitleBar_title);
        mRightBtnText = attrs.getString(R.styleable.TitleBar_rightText);
        mRightBtnImage = attrs.getDrawable(R.styleable.TitleBar_rightImage);
        int resId = attrs.getResourceId(R.styleable.TitleBar_rightTextSize, -1);
        if (resId > 0) {
            mRightBtnTextSize = getResources().getDimensionPixelSize(resId);
        } else {
            mRightBtnTextSize = attrs.getDimensionPixelSize(R.styleable.TitleBar_rightTextSize, -1);
        }
        attrs.recycle();
    }

    private void initViews() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_title_bar, this);
        mImgBtnBack = (ImageButton) findViewById(R.id.vtb_btn_back);
        mLayoutCenter = (FrameLayout) findViewById(R.id.vtb_layout_center);
        mTxtTitle = (TextView) findViewById(R.id.vtb_txt_title);
        mTxtTitle.setText(mTitle);
        mLayoutRight = (FrameLayout) findViewById(R.id.vtb_layout_right);
        mTxtRight = (TextView) findViewById(R.id.vtb_txt_right);
        mImgBtnRight = (ImageButton) findViewById(R.id.vtb_imgbtn_right);
        setRightBtn();
        setListeners();
    }


    private void setRightBtn() {
        if (TextUtils.isEmpty(mRightBtnText) && mRightBtnImage == null) {
            mLayoutRight.setVisibility(View.GONE);
            return;
        }
        //rightText属性不为空，右边显示文本按钮
        if (!TextUtils.isEmpty(mRightBtnText)) {
            mLayoutRight.setVisibility(View.VISIBLE);
            mTxtRight.setVisibility(View.VISIBLE);
            mTxtRight.setText(mRightBtnText);
            mImgBtnRight.setVisibility(View.GONE);
            if (mRightBtnTextSize > 0) {
                mTxtRight.setTextSize(TypedValue.COMPLEX_UNIT_PX, mRightBtnTextSize);
            }
            return;
        }
        if (mRightBtnImage != null) {
            mLayoutRight.setVisibility(View.VISIBLE);
            mTxtRight.setVisibility(View.GONE);
            mImgBtnRight.setVisibility(View.VISIBLE);
            mImgBtnRight.setImageDrawable(mRightBtnImage);
            return;
        }
    }

    public void setRightBtnTextSize(int pxSize){
        mRightBtnTextSize=pxSize;
        setRightBtn();
    }

    public void setRightBtnImage(Drawable image) {
        mRightBtnImage = image;
        mRightBtnText = null;
        setRightBtn();
    }

    public void setRightBtnText(String text) {
        mRightBtnImage = null;
        mRightBtnText = text;
        setRightBtn();
    }


    private void setListeners() {
        mImgBtnBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Context ctx = getContext();
                if (ctx instanceof Activity) {
                    ((Activity) ctx).finish();
                } else if (ctx instanceof FragmentActivity) {
                    ((FragmentActivity) ctx).finish();
                }
            }
        });
    }

    /**
     * 设置标题文字
     *
     * @param title
     */
    public void setTitle(CharSequence title) {
        mTxtTitle.setText(title);
    }

    public void setTitle(int resId) {
        mTxtTitle.setText(resId);
    }

    /**
     * 设置回退按钮的监听
     *
     * @param l
     */
    public void setOnBackListener(OnClickListener l) {
        mImgBtnBack.setOnClickListener(l);
    }

    /**
     * 设置右边按钮单击监听
     *
     * @param listener
     */
    public void setRightBtnOnClickListener(OnClickListener listener) {
        View rightBtn;
        if (mImgBtnRight.getVisibility() == View.VISIBLE && mTxtRight.getVisibility() == View.GONE) {
            rightBtn = mImgBtnRight;
        } else if (mImgBtnRight.getVisibility() == View.GONE && mTxtRight.getVisibility() == View.VISIBLE) {
            rightBtn = mTxtRight;
        } else {
            return;
        }
        rightBtn.setOnClickListener(listener);
    }

    /**
     * 设置titlebar中间部分的控件
     *
     * @param view
     */
    public void setCenterView(View view) {
        mLayoutCenter.removeAllViews();
        mLayoutCenter.addView(view);
    }

    public void hideBackButton() {
        mImgBtnBack.setVisibility(View.GONE);
    }


}
