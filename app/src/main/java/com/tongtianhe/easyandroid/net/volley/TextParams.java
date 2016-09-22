package com.tongtianhe.easyandroid.net.volley;

import java.util.Map;

public class TextParams extends BaseParams {

	@Override
	public Map<String, byte[]> getFiles() {
		throw new RuntimeException("不支持file参数，请使用FileParams");
	}

	@Override
	public Params addFile(String name, byte[] data) {
		throw new RuntimeException("不支持file参数，请使用FileParams");
	}

}
