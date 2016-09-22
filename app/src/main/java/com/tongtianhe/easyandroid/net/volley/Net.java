package com.tongtianhe.easyandroid.net.volley;

import com.android.volley.Request;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.tongtianhe.easyandroid.application.MyApplication;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 网路请求类
 *
 * @author free
 */
public class Net {

    private static RequestQueue mRequestQueue;
    private static ExecutorService mParseThreadPool;

    private static synchronized RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(MyApplication.getInstance());
        }
        return mRequestQueue;
    }

    public static synchronized ExecutorService getParseThreadPool() {
        if (mParseThreadPool == null) {
            mParseThreadPool = Executors.newCachedThreadPool();
        }
        return mParseThreadPool;
    }


    public static void post(String url, Params params, BaseCallback callback, Object tag) {
        Request<String> request = new TextRequest(Method.POST, url, params, callback, callback);
        if (tag != null) {
            request.setTag(tag);
        }
        getRequestQueue().add(request);
    }

    public static void post(String url, Params params, BaseCallback callback) {
        post(url, params, callback, null);
    }


    public static void get(String url, Params params, BaseCallback callback, Object tag) {
        Request<String> request = new TextRequest(Method.GET, createGetUrl(url, params), params, callback, callback);
        if (tag != null) {
            request.setTag(tag);
        }
        getRequestQueue().add(request);
    }

    public static void get(String url, Params params, BaseCallback callback) {
        get(url, params, callback, null);
    }

    private static String createGetUrl(String url, Params params) {
        StringBuffer sb = new StringBuffer();
        if (params != null && !params.getParams().isEmpty()) {
            sb.append(url).append("?");
            for (Map.Entry<String, String> entry : params.getParams().entrySet()) {
                String v = entry.getValue();
                try {
                    v = URLEncoder.encode(v, "utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                sb.append(entry.getKey() + "=" + v + "&");
            }
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    public static void cancelAll(Object tag) {
        getRequestQueue().cancelAll(tag);
    }

}
