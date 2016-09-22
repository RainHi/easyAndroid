package com.tongtianhe.easyandroid.net.volley;

import com.tongtianhe.easyandroid.utils.MD5Utils;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public abstract class BaseParams implements Params {

    protected Map<String, String> mHeaderParams = new HashMap<String, String>();
    private Map<String, String> mParams = new HashMap<String, String>();

    public BaseParams() {
        addCommonHeaders();
    }

    @Override
    public void addCommonHeaders() {

    }

    @Override
    public void addSignHeader() {
        Map<String, String> allParams = new HashMap<String, String>();
        allParams.putAll(mHeaderParams);
        allParams.putAll(mParams);
        mHeaderParams.put("sign", sortParamsThenCreateSign(allParams));
    }

    /**
     * 根据传入的参数得到签名
     *
     * @param params
     */
    public static String sortParamsThenCreateSign(Map<String, String> params) {
        String[] queryParams = null;
        queryParams = new String[params.size()];
        if (params != null) {
            Iterator<Map.Entry<String, String>> it = params.entrySet().iterator();
            for (int i = 0; it.hasNext(); i++) {
                Map.Entry<String, String> p = it.next();
                queryParams[i] = p.getKey() + "=" + p.getValue();
            }
        }
        Arrays.sort(queryParams, String.CASE_INSENSITIVE_ORDER);
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < queryParams.length; i++) {
            sb.append(queryParams[i]);
            sb.append("&");
        }
        String substring = sb.toString().substring(0,
                sb.toString().length() - 1);
        String string = "";
        try {
            string = new String(substring.getBytes("utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return MD5Utils.getMD5String(string);
    }

    @Override
    public Map<String, String> getHeaders() {
        return mHeaderParams;
    }

    @Override
    public Map<String, String> getParams() {
        return mParams;
    }

    @Override
    public Params addParam(String name, String value) {
        mParams.put(name, value);
        return this;
    }
}
