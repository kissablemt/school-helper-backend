package cn.edu.dgut.school_helper.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;

public class JwtUtils {

	public static final String JWT_ID = "jwt";
	/**
	 * 加密的盐值
	 */
	public static final String JWT_SECRECT = "0xiao1yuan2xiao3zhu4";
	/**
	 * accessToken过期时间 7天
	 */
	public static final Integer JWT_TTL = 7 * 24 * 60 * 60 * 1000; //second
	/**
	 * accessToken刷新间隔
	 * 
	 */
//	public static final Integer JWT_REFRESH_INTERVAL = 25 * 60 * 1000; // second
	/**
	 * refreshToken过期时间
	 */
//	public static final Integer JWT_REFRESH_TOKEN_TTL = 7 * 24 * 60 * 60 * 1000; // second
	/**
	 * redis中的refreshToken表名 
	 */
//	public static final String JWT_REFRESH_TABLE = "refreshToken:";

	private static Algorithm algorithm;

	private static final Logger log = LoggerFactory.getLogger(JwtUtils.class);

	static {
		try {
			algorithm = Algorithm.HMAC256(JWT_SECRECT);
		} catch (IllegalArgumentException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 根据openid,返回accessToken
	 */
	public static String createAccessToken(String openId) {
		String accessToken = JWT.create()
				.withSubject(openId)
				.withIssuedAt(new Date(System.currentTimeMillis()))
				.withExpiresAt(new Date(System.currentTimeMillis() + JWT_TTL))
				.sign(algorithm);
		return accessToken;
	}


	/**
	 * 根据accessToken,返回openid
	 */
	public static String verifyAccessToken(String jwt) {
		// 判断是否过期
		JWTVerifier verify = JWT.require(algorithm)
				.acceptExpiresAt(5) // 允许5秒内弹性
				.build();
		DecodedJWT decodeJwt = null;
		try {
			decodeJwt = verify.verify(jwt);
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
		String openId = decodeJwt.getSubject();
		return openId;
	}
	
	/**
	 * 根据openid,返回refreshToken
	 */
	/*public static String createRefreshToken(String openId, RedisTemplate<String, String> redisTemplate) {
		String refreshUuid = UUID.randomUUID().toString();
		redisTemplate.opsForValue().set(JWT_REFRESH_TABLE + openId, refreshUuid,JWT_REFRESH_TOKEN_TTL,TimeUnit.MICROSECONDS);
		String refreshToken = JWT.create()
				.withSubject(openId)
				.withClaim(JWT_REFRESH_TABLE + openId, refreshUuid)
				.withIssuedAt(new Date(System.currentTimeMillis()))
				.withExpiresAt(new Date(System.currentTimeMillis() + JWT_REFRESH_TOKEN_TTL)).withIssuedAt(new Date())
				.sign(algorithm);
		return refreshToken;
	}*/
	
	/**
	 * 根据refreshToken,返回openid
	 */
	/*public static String verifyRefreshToken(String jwt, RedisTemplate<String, String> redisTemplate) {

		DecodedJWT decodedJWT = JWT.decode(jwt);
		String openId = decodedJWT.getSubject();
		String refreshUuid = redisTemplate.opsForValue().get(JWT_REFRESH_TABLE + openId);
		// 判断是否过期,以及是否为refreshToken
		JWTVerifier verify = JWT.require(algorithm)
				.withClaim(JWT_REFRESH_TABLE + openId, refreshUuid)
				.acceptExpiresAt(Calendar.getInstance().getTimeInMillis())
				.build();
		try {
			verify.verify(jwt);
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
		return openId;
	}*/
}
