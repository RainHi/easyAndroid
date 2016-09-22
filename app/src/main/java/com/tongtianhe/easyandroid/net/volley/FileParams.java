package com.tongtianhe.easyandroid.net.volley;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

public class FileParams extends BaseParams {
	
	private Map<String, byte[]> mFiles=new HashMap<String, byte[]>();

	@Override
	public Params addFile(String name, byte[] data) {
		mFiles.put(name, data);
		return this;
	}


	@Override
	public Map<String, byte[]> getFiles() {
		return mFiles;
	}
	
	
	public static byte[] bitmap2Bytes(Bitmap image){
		ByteArrayOutputStream baos=new ByteArrayOutputStream();
		image.compress(CompressFormat.JPEG, 100, baos);
		return baos.toByteArray();
	}

}
