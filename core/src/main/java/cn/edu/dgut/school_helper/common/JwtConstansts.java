package cn.edu.dgut.school_helper.common;

public class JwtConstansts {

	public static String jwtId = "jwt";
//	public static String jwtSecrect = "hong1mu2zhi3ruan4jian5";
	/**
	 * accessToken过期时间
	 * 5分钟
	 */
	public static int jwtTtl = 5 * 60 * 1000; // millisecond
	/**
	 * accessToken刷新间隔
	 * 
	 */
	public static int jwtRefreshInterval = 55 * 60 * 1000; // millisecond
	/**
	 * refreshToken过期时间
	 */
	public static int jwtRefreshTokenTtl = 12 * 60 * 60 * 1000; // millisecond

	

	
}
