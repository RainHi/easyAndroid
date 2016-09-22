package com.tongtianhe.easyandroid.net.volley;

import com.google.gson.Gson;

/**
 * Created by free on 16/2/29.
 */
public abstract class GsonCallback<T> extends BaseCallback<T> {


    protected Class<T> mClazz;


    public GsonCallback(Class<T> beanClazz) {
        mClazz = beanClazz;
    }


    /**
     * 钩子方法，需要自定义解析，重写此方法
     * 此方法在子线程运行
     * @param data
     * @return
     */
    @Override
    protected T parseJson(String data) throws Exception {
        return new Gson().fromJson(data, mClazz);
    }

}
