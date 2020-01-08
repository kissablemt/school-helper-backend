package cn.edu.dgut.school_helper.config.jwt.service;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.NonceExpiredException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

@RestController
@RequestMapping("/accessToken")
public class JwtAccessTokenController {

	@Autowired
	RedisTemplate<String, String> redisTemplate;
	@Autowired
	JwtUserService jwtUserService;

	@PostMapping
	public String getAccessToken(String refreshToken) {// 通过refreshToken,换取accessToken
		DecodedJWT refeshJwt = JWT.decode(refreshToken);
		String username = refeshJwt.getSubject();

		// 判断是否有该用户,salt是否存在(没有salt说明没有 refreshToken)
		UserDetails details = jwtUserService.getUserLoginInfo(username);
		if (details == null || details.getPassword() == null) {
			throw new NonceExpiredException("Token expires");
			
		}

		String uuid = redisTemplate.opsForValue().get("token:refresh_uuid:" + username);
		String encryptSalt = details.getPassword();
		// 有refreshToken，判断是否为上一次，有没有过期
		// 验证jwt,算法会验证 是否为你的签名 ,是否为携带了uuid(即是否为refreshToken)
		try {
			Algorithm algorithm = Algorithm.HMAC256(encryptSalt);
			JWTVerifier verifier = JWT.require(algorithm).acceptExpiresAt(Calendar.getInstance().getTime().getTime())
					.withSubject(username).withClaim("uuid", uuid).build();
			verifier.verify(refreshToken);

		} catch (IllegalArgumentException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		// 生成accessToken
		return jwtUserService.accessTokenCreate(details);
	}

}
