package cn.edu.dgut.school_helper.util;

import java.util.Base64;

public class Base64Utils {
	
	public static final Base64.Decoder decoder = Base64.getDecoder();
	public static final Base64.Encoder encoder = Base64.getEncoder();

	public static byte[] decode(String base64Str) {
		return decoder.decode(base64Str);
	}
	
	public static byte[] encode(byte[] bytes) {
		return encoder.encode(bytes);
	}
}
