package com.tongtianhe.easyandroid.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.net.Uri;

import java.io.File;

/**
 * Created by free on 16/9/19.
 * apk相关工具类
 */
public class ApkUtils {

    /**
     * 安装apk
     * @param ct
     * @param apkFile
     */
    public static void installApk(Context ct, File apkFile) {
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(android.content.Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(apkFile),
                "application/vnd.android.package-archive");
        ct.startActivity(intent);
    }


    /**
     * 是否是debug模式
     * @param context
     * @return
     */
    public static boolean isApkDebugable(Context context) {
        try {
            ApplicationInfo info= context.getApplicationInfo();
            return (info.flags&ApplicationInfo.FLAG_DEBUGGABLE)!=0;
        } catch (Exception e) {

        }
        return false;
    }


}
