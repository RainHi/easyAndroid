package com.tongtianhe.easyandroid.net.volley;

import java.util.Map;

public interface Params {
	
	Map<String, String> getHeaders();
	
	void addCommonHeaders();
	
	void addSignHeader();
	
	Map<String, String> getParams();
	
	Params addParam(String name, String value);
	
	Map<String, byte[]> getFiles();
	
	Params addFile(String name, byte[] data);
	
	

}
