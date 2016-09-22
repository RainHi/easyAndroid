package com.tongtianhe.easyandroid.net.volley;

import android.os.AsyncTask;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONObject;


/**
 * Created by free on 16/2/29.
 * callback的基类，T参数不可以是String
 */
public abstract class BaseCallback<T>  implements Callback, Response.Listener<String>, Response.ErrorListener{

    private static final String TAG="BaseCallback";

    private AsyncTask<String, Integer, T> mTask;


    public BaseCallback() {
        mTask=new AsyncTask<String, Integer, T>() {

            public static final String JSON_KEY_SUCCESS="success";
            public static final String JSON_KEY_ERROR="error";
            public static final String JSON_KEY_DATA="data";

            public static final int JSON_VALUE_SUCCESS=1;

            private Error mError;

            @Override
            protected T doInBackground(String... params) {
                try {
                    JSONObject jsonObj=new JSONObject(params[0]);
                    int status=jsonObj.getInt(JSON_KEY_SUCCESS);
                    if(status==JSON_VALUE_SUCCESS){
                        String data=jsonObj.optString(JSON_KEY_DATA);
                        return parseJson(data);
                    }else {
                        mError=new Error(jsonObj.optJSONObject(JSON_KEY_ERROR));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    mError=new Error(Error.CODE_PARSE_ERROR);
                }
                return null;
            }

            @Override
            protected void onPostExecute(T t) {
                try {
                    if(mError==null){
                        onSuccess(t);
                    }else {
                        onFail(mError);
                    }
                }catch (Exception e){
                    onCatchException(e);
                }
            }
        };
    }



    /**
     * 钩子方法，需要自定义解析，重写此方法
     * 此方法在子线程运行
     * @param data
     * @return
     */
    protected abstract T parseJson(String data) throws Exception;

    /**
     * onSuccess, onFail发生异常时，捕获异常处理的回调
     * @param e
     */
    protected void onCatchException(Exception e){
        e.printStackTrace();
    }


    @Override
    public void onErrorResponse(VolleyError volleyError) {
        try {
            onFail(new Error(Error.CODE_NET_ERROR));
        }catch (Exception e){
            onCatchException(e);
        }
    }

    @Override
    public void onResponse(String result) {
        mTask.executeOnExecutor(Net.getParseThreadPool(), result);
    }
}

