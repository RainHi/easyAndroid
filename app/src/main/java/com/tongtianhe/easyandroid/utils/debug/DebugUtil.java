package com.tongtianhe.easyandroid.utils.debug;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.tongtianhe.easyandroid.application.MyApplication;
import com.tongtianhe.easyandroid.utils.ApkUtils;

import java.lang.reflect.Field;

/**
 * Created by free on 17/1/18.
 * 调试工具类
 */
public class DebugUtil {

    public static final String DEBUG_TAG="debug";
    public static final boolean DEBUGING=true;
    public static final String END_LINE="-----------------------------------------------------------------";


    public static boolean isDebug(){
        return DEBUGING&&ApkUtils.isApkDebugable(MyApplication.getInstance());
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


    public static void injectAllView(View topParent){
        if(!isDebug()){
            return;
        }
        try {
            if(topParent==null)
                return;
            if(topParent instanceof ViewGroup){
                ViewGroup vg= (ViewGroup) topParent;
                for (int i=0; i<vg.getChildCount(); i++){
                    injectAllView(vg.getChildAt(i));
                }
            }else{
                injectOnClickListener(topParent, false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void injectOnClickListener(View target, boolean onlyInjectClickableView) throws Exception {
        if(target==null||(onlyInjectClickableView&&!target.isClickable()))
            return;
        Field fieldMListenerInfo=View.class.getDeclaredField("mListenerInfo");
        fieldMListenerInfo.setAccessible(true);
        Object mListenerInfo=fieldMListenerInfo.get(target);
        if(mListenerInfo!=null){
            Field fieldMOnClickListener=mListenerInfo.getClass().getDeclaredField("mOnClickListener");
            View.OnClickListener originL= (View.OnClickListener) fieldMOnClickListener.get(mListenerInfo);
            fieldMOnClickListener.set(mListenerInfo, new DebugOnClickListener(originL));
        }else {
            target.setOnClickListener(new DebugOnClickListener(null));
        }

    }
}
