package com.tongtianhe.easyandroid.net.volley;

import android.text.TextUtils;

import org.json.JSONObject;

/**
 * Created by free on 16/8/30.
 */
public abstract class JsonCallback extends BaseCallback<JSONObject>{

    @Override
    protected JSONObject parseJson(String data) throws Exception {
        if(TextUtils.isEmpty(data)){
            return null;
        }else {
            return new JSONObject(data);
        }
    }
}
