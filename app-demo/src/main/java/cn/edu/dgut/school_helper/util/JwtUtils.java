package cn.edu.dgut.school_helper.util;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

public class JwtUtils {

	public static final String JWT_ID = "jwt";
	/**
	 * 加密的盐值
	 */
	public static final String JWT_SECRECT = "0xiao1yuan2xiao3zhu4";
	/**
	 * accessToken过期时间 5分钟
	 */
	public static final Integer JWT_TTL = 5 * 60 * 1000; // millisecond
	/**
	 * accessToken刷新间隔
	 * 
	 */
	public static final Integer JWT_REFRESH_INTERVAL = 55 * 60 * 1000; // millisecond
	/**
	 * refreshToken过期时间
	 */
	public static final Integer JWT_REFRESH_TOKEN_TTL = 12 * 60 * 60 * 1000; // millisecond
	/**
	 * redis中的refreshToken表名 
	 */
	public static final String JWT_REFRESH_TABLE = "refreshToken:";

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
	public static String createAccessToken(String str) {
		String accessToken = JWT.create()
				.withSubject(str)
				.withIssuedAt(new Date(System.currentTimeMillis()))
				.withExpiresAt(new Date(System.currentTimeMillis() + JWT_TTL))
				.sign(algorithm);
		return accessToken;
	}

	/**
	 * 根据openid,返回refreshToken
	 */
	public static String createRefreshToken(String str) {

		String refreshToken = JWT.create()
				.withSubject(str)
				.withIssuedAt(new Date(System.currentTimeMillis()))
				.withExpiresAt(new Date(System.currentTimeMillis() + JWT_REFRESH_TOKEN_TTL)).withIssuedAt(new Date())
				.sign(algorithm);
		return refreshToken;
	}

	/**
	 * 根据accessToken,返回openid
	 */
	public static String verifyAccessToken(String jwt) {
		// 判断是否过期
		JWTVerifier verify = JWT.require(algorithm)
				.acceptExpiresAt(Calendar.getInstance().getTimeInMillis())
				.build();
		DecodedJWT decodeJwt = verify.verify(jwt);
		String openId = decodeJwt.getSubject();
		return openId;
	}
	/**
	 * 根据refreshToken,返回openid
	 */
	public static String verifyRefreshToken(String jwt, RedisTemplate<String, String> redisTemplate) {

		DecodedJWT decodedJWT = JWT.decode(jwt);
		String openId = decodedJWT.getSubject();
		String token = redisTemplate.opsForValue().get(JWT_REFRESH_TABLE + openId);
		// 判断是否过期,以及是否为refreshToken
		JWTVerifier verify = JWT.require(algorithm)
				.withClaim(JWT_REFRESH_TABLE + openId, token)
				.acceptExpiresAt(Calendar.getInstance().getTimeInMillis())
				.build();
		try {
			verify.verify(jwt);
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
		return openId;
	}
}
