package com.tongtianhe.easyandroid.net.volley;

import org.json.JSONObject;

/**
 * Created by free on 16/9/22.
 */
public class Error {

    public static final int CODE_PARSE_ERROR=-100,CODE_NET_ERROR=-101;

    public static final String MESSAGE_PARSE_ERROR="json解析错误",MESSAGE_NET_ERROR="网络错误";

    public int code;
    public String message;
    public JSONObject data;

    public Error(JSONObject jsonError) {
        code = jsonError.optInt("code");
        message = jsonError.optString("message");
        data=jsonError.optJSONObject("data");
    }

    public Error(int code){
        this.code=code;
        switch (code){
            case CODE_PARSE_ERROR:
                message=MESSAGE_PARSE_ERROR;
                break;
            case CODE_NET_ERROR:
                message=MESSAGE_NET_ERROR;
                break;
        }
    }

}
