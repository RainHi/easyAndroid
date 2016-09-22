package com.tongtianhe.easyandroid.net.volley;

/**
 * 网络请求获得数据之后的回调接口
 * @author free
 *
 */
public interface Callback<T>{
	
	void onSuccess(T result) throws Exception;
	
	void onFail(Error error) throws Exception;
	
}
