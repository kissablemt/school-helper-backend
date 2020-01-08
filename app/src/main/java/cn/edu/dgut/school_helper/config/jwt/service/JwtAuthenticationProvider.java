package cn.edu.dgut.school_helper.config.jwt.service;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.NonceExpiredException;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import cn.edu.dgut.school_helper.config.jwt.JwtAuthenticationToken;


public class JwtAuthenticationProvider implements AuthenticationProvider {

	private JwtUserService userService;
	
	@Autowired
	RedisTemplate<String, String> redisTemplate;
	

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		DecodedJWT jwt = ((JwtAuthenticationToken) authentication).getToken();
		//判断是否过期
		if (jwt.getExpiresAt().before(Calendar.getInstance().getTime())) {
			throw new NonceExpiredException("Token expires");
		}
		
		//判断是否 salt过期 = refreshToken过期 >  accessToeken过期 
		String username = jwt.getSubject();
		UserDetails user = userService.getUserLoginInfo(username);
		if (user == null || user.getPassword() == null) {
			throw new NonceExpiredException("Token expires");
		}
		
		//验证token有没有被篡改
		String encryptSalt = user.getPassword();
		verifyToken(jwt,username,encryptSalt);
		
		return new JwtAuthenticationToken(user, jwt, user.getAuthorities());

	}
	
	private void verifyToken(DecodedJWT jwt,String username,String encryptSalt) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(encryptSalt);
			JWTVerifier verifier = JWT.require(algorithm).withSubject(username).build();
			verifier.verify(jwt.getToken());
		} catch (Exception e) {
			throw new BadCredentialsException("JWT token verify fail", e);
		}
	}
	

	
	@Override
	public boolean supports(Class<?> authentication) {
		return (JwtAuthenticationToken.class.isAssignableFrom(authentication));
	}
	
	
	public JwtUserService getUserService() {
		return userService;
	}

	public void setUserService(JwtUserService userService) {
		this.userService = userService;
	}
	
	

}
