package com.tongtianhe.easyandroid.utils;

import android.app.ActivityManager;
import android.content.Context;

import java.util.List;

/**
 * application相关的工具类
 */
public class AppUtils {


    /**
     * 判断app是否在前台运行
     * @param context
     * @return
     */
    public static boolean isAppOnForeground(Context context){
        ActivityManager am= (ActivityManager) context.getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> runningAppProcessInfos=am.getRunningAppProcesses();
        if(runningAppProcessInfos==null){
            return false;
        }
        String processName=context.getApplicationInfo().processName;
        for (ActivityManager.RunningAppProcessInfo processInfo:runningAppProcessInfos){
            if(processInfo.importance== ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND&&processInfo.processName.equals(processName)){
                return true;
            }
        }
        return false;
    }



}
