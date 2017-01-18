package com.tongtianhe.easyandroid.utils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.tongtianhe.easyandroid.application.MyApplication;

/**
 * Created by free on 17/1/18.
 * 调试工具类
 */
public class DebugUtil {

    public static final String DEBUG_TAG="debug";
    public static final String END_LINE="-----------------------------------------------------------------";

    public static boolean isDebug(){
        return ApkUtils.isApkDebugable(MyApplication.getInstance());
    }

    /**
     * 打印activity的启动参数
     * @param page
     */
    public static void logPageInfo(Activity page){
        if(!isDebug()){
            return;
        }
        Intent intent=page.getIntent();
        Bundle params=intent!=null?intent.getExtras():null;
        Log.d(DEBUG_TAG, "↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓--"+page.getClass().getSimpleName()+"--↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓");
        if(params==null){
            Log.d(DEBUG_TAG, "null");
            Log.d(DEBUG_TAG, END_LINE);
            return;
        }
        for (String key:params.keySet()){
            Log.d(DEBUG_TAG, key + " : " + params.get(key));
        }
        Log.d(DEBUG_TAG, END_LINE);
    }
}
