package com.tongtianhe.easyandroid.utils;

import android.app.Activity;
import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * 输入法工具类
 * Created by free on 16/9/21.
 */
public class InputMethodUtils {

    /**
     * 隐藏输入法
     * @param activity
     */
    public static void hideSoftInput(Activity activity){
        View view = activity.getCurrentFocus();
        if (view != null && view.getWindowToken() != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    /**
     * 触摸软键盘以外区域，隐藏软键盘
     * ex.重写activity的dispatchTouchEvent()，在其中调用此方法检测触摸在键盘外关闭键盘
     * @param activity
     * @param ev
     */
    public static void hideSoftInputWhenTouchOutside(Activity activity, MotionEvent ev) {
        if (activity.getCurrentFocus() != null) {
            boolean flag = false;
            switch (ev.getAction()) {
                case MotionEvent.ACTION_DOWN:
                case MotionEvent.ACTION_MOVE:
                    // 从ACTION_DOWN的到currentFocus变化 有一定的延迟
                    if (ev.getEventTime() - ev.getDownTime() > 100) {
                        flag = true;
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    flag = true;
                    break;
            }
            if (!flag) {
                return;
            }
            final View currentFocus = activity.getCurrentFocus();
            if (!(currentFocus instanceof EditText) || !isTouchInsideView(ev, currentFocus)) {
                ((InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }


    /**
     * 判断触摸点是否在view以内
     * @param ev
     * @param currentFocus
     * @return
     */
    public static boolean isTouchInsideView(final MotionEvent ev, final View currentFocus) {
        final int[] loc = new int[2];
        currentFocus.getLocationOnScreen(loc);
        return ev.getX() > loc[0] && ev.getY() > loc[1] && ev.getX() < (loc[0] + currentFocus.getWidth()) && ev.getY() < (loc[1] + currentFocus.getHeight());
    }


}
