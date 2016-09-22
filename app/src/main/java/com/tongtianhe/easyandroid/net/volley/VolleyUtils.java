package com.tongtianhe.easyandroid.net.volley;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.zip.GZIPInputStream;

public class VolleyUtils {
	
	/**
	 * 解析Gizp压缩后的字节流为字符串
	 * 
	 * @param data
	 * @return
	 */
	public static String processGzipByte2String(byte[] data) {
		if (data == null || data.length == 0) {
			return null;
		}
		byte[] h = new byte[2];
		h[0] = (data)[0];
		h[1] = (data)[1];
		int head = getShort(h);
		boolean t = head == 0x1f8b;
		InputStream in;
		StringBuilder sb = new StringBuilder();
		try {
			ByteArrayInputStream bis = new ByteArrayInputStream(data);
			if (t) {
				in = new GZIPInputStream(bis);
			} else {
				in = bis;
			}
			BufferedReader r = new BufferedReader(new InputStreamReader(in),
					1000);
			for (String line = r.readLine(); line != null; line = r.readLine()) {
				sb.append(line);
			}
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
	
	
	private static int getShort(byte[] data) {
		return (data[0] << 8) | data[1] & 0xFF;
	}

}
