package com.tongtianhe.easyandroid.helper;

/**
 * Created by free on 16/9/22.
 */
public class ClickHelper {

    private static final long DELAY=500;
    private static long lastClickTime=0;

    private ClickHelper(){}

    /**
     * 判断是否是快速双击
     * @return
     */
    public boolean isFastDoubleClick(){
        long now=System.currentTimeMillis();
        long delta=now-lastClickTime;
        if(delta>0&&delta<DELAY){
            return true;
        }
        lastClickTime=now;
        return false;
    }
}
