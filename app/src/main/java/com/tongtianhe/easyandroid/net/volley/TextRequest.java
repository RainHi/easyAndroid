package com.tongtianhe.easyandroid.net.volley;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.HttpHeaderParser;

import java.io.UnsupportedEncodingException;
import java.util.Map;

public class TextRequest extends Request<String>{
	
	private static final String CONTENT_ENCODING="Content-Encoding";
	public static final int DEFAULT_TIME_OUT=15*1000;
	public static final int DEFAULT_MAX_RETRYIES=0;
	
	private Listener<String> mSuccessListener;
	private Params mParams;
	
	public TextRequest(int method, String url, Params params, Listener<String> successListener, ErrorListener errorListener) {
		super(method, url, errorListener);
		mParams=params;
		mSuccessListener=successListener;
		configRequest();
	}
	
	private void configRequest(){
		setRetryPolicy(new DefaultRetryPolicy(DEFAULT_TIME_OUT, DEFAULT_MAX_RETRYIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
		setShouldCache(false);
	}
	
	
	@Override
	public Map<String, String> getHeaders() throws AuthFailureError {
		if(mParams.getHeaders()!=null){
			return mParams.getHeaders();
		}
		return super.getHeaders();
	}
	
	@Override
	protected Map<String, String> getParams() throws AuthFailureError {
		if(mParams.getParams()!=null){
			return mParams.getParams();
		}
		return super.getParams();
	}
	
	
	@Override
	protected Response<String> parseNetworkResponse(NetworkResponse response) {
		String result=null;
		if(response.headers.containsKey(CONTENT_ENCODING)&&response.headers.get(CONTENT_ENCODING).equals("gzip")){
			result=VolleyUtils.processGzipByte2String(response.data);
		}else{
			try {
				result=new String(response.data, HttpHeaderParser.parseCharset(response.headers));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				return Response.error(new ParseError(e));
			}
		}
		return Response.success(result, HttpHeaderParser.parseCacheHeaders(response));
	}

	@Override
	protected void deliverResponse(String response) {
		mSuccessListener.onResponse(response);
	}

}
