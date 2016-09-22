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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

public class UploadFileRequest extends Request<String>{
	
	private static final String CONTENT_TYPE="multipart/form-data", BOUNDARY="----WebKitFormBoundaryS4nmHw9nb2Eeusll";
	private static final String CONTENT_ENCODING="Content-Encoding";
	
	private Params mParams;
	private Listener<String> mSuccessListener;
	

	public UploadFileRequest(String url, Params params, Listener<String> successListener, ErrorListener listener) {
		super(Method.POST, url, listener);
		mParams=params;
		mSuccessListener=successListener;
		configUploadFile();
	}

	private void configUploadFile(){
		setShouldCache(false);
		setRetryPolicy(new DefaultRetryPolicy(5000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
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
	
	@Override
	public Map<String, String> getHeaders() throws AuthFailureError {
		mParams.getHeaders().remove("Content-Type");
		return mParams.getHeaders();
	}
	
	@Override
	protected Map<String, String> getParams() throws AuthFailureError {
		return mParams.getParams();
	}
	
	@Override
	public String getBodyContentType() {
		return CONTENT_TYPE+";boundary="+BOUNDARY;
	}
	
	@Override
	public byte[] getBody() throws AuthFailureError {
		if(mParams.getFiles().isEmpty()){
			return super.getBody();
		}
		try {
			ByteArrayOutputStream baos=new ByteArrayOutputStream(10*1024);
			StringBuffer sb=new StringBuffer();
			for(Map.Entry<String, byte[]> file:mParams.getFiles().entrySet()){
				String name=file.getKey();
				byte[] data=file.getValue();
				
				//--boundary\r\n
				sb.append("--").append(BOUNDARY).append("\r\n");
				//Content-Disposition:form-data;name="xxx";filename="xxx"\r\n
				sb.append("Content-Disposition:form-data;name=\"").append(name).append("\";filename=\"image.jpg\"\r\n");
				//Content-Type:image/jpg\r\n
				sb.append("Content-Type:image/jpg\r\n");
				//\r\n
				sb.append("\r\n");
				//image\r\n
				baos.write(sb.toString().getBytes());
				sb.delete(0, sb.length());
				baos.write(data);
				sb.append("\r\n");
				baos.write(sb.toString().getBytes());
				sb.delete(0, sb.length());
				
			}
			//--boundary--\r\n
			sb.append("--").append(BOUNDARY).append("--\r\n");
			baos.write(sb.toString().getBytes());
			return baos.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	

}
