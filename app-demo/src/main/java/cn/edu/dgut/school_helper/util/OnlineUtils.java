package cn.edu.dgut.school_helper.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class OnlineUtils {

	public static InputStream getImageStream(String url) {
		try {
			HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
			connection.setReadTimeout(5000);
			connection.setConnectTimeout(5000);
			connection.setRequestMethod("GET");
			if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
				InputStream inputStream = connection.getInputStream();
				return inputStream;
			}
		} catch (IOException e) {
			System.out.println("获取网络图片出现异常，图片路径为：" + url);
			e.printStackTrace();
		}
		return null;
	}

	public static byte[] getImageBytes(String url) {
		InputStream in = getImageStream(url);
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		try {
			while ((len = in.read(buffer)) != -1) {
				byteArrayOutputStream.write(buffer, 0, len);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return byteArrayOutputStream.toByteArray();
	}

}
