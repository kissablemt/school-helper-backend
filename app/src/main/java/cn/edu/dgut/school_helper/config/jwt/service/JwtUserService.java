package cn.edu.dgut.school_helper.config.jwt.service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import cn.edu.dgut.school_helper.common.JwtConstansts;


public class JwtUserService implements UserDetailsService { 

	private PasswordEncoder passwordEncoder;
	
	@Autowired
	RedisTemplate<String, String> redisTemplate;

	private static final Logger log = LoggerFactory.getLogger(JwtUserService.class);

	/**
	 * 查询是否有该用户
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<GrantedAuthority> roles = new ArrayList<>();
		roles.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

		return new User("Jack", passwordEncoder.encode("jack-password"), roles);
	}

	/**
	 * 从数据库中或取用户的信息
	 * 
	 * @param username
	 * @return
	 */
	public UserDetails getUserLoginInfo(String username) {
		UserDetails user = loadUserByUsername(username);
		String salt = redisTemplate.opsForValue().get("token:salt:" + username);

		// 将salt放到password字段返回
		return new User(user.getUsername(), salt, user.getAuthorities());
	}

	/**
	 * 之前的想法： 创建refreshToken--jwt，设置签发时间，存储到redis中
	 * 当携带refreshToken来请求时，先解析refreshToken，判断是否过期，取出用户的标识，重新发放accessToken.
	 * 判断accessToken是否有问题：
	 * 判断是否过期，是否符合salt的加密（jwt验证），然后取出用户的refreshToken，判断accessToken的签证日期是否与refreshToken的签证日期相同。
	 * 如果相同：验证通过，刷新accessToken的签证日期。以及refreshToken的签证日期，验证通过
	 * 如果不同：说明该accessToken已经用过，不可以用于访问 问题，如何保证并发时候的使用
	 */
	/**
	 * 
	 * @param user
	 * @return 生成新的accessToken,每隔xx分钟刷新，这个是维持登录状态的
	 * @throws IllegalArgumentException
	 * @throws UnsupportedEncodingException
	 */
	public String accessTokenCreate(UserDetails user) { 
		// 生成accessToken
		String salt = redisTemplate.opsForValue().get("token:salt:" + user.getUsername());
		String accessToken = null;
		try {
			Algorithm algorithm = Algorithm.HMAC256(salt);
			Date date = new Date(System.currentTimeMillis() + JwtConstansts.jwtRefreshInterval); // 设5分钟后过期
			accessToken = JWT.create().withSubject(user.getUsername()).withExpiresAt(date).sign(algorithm);

		} catch (IllegalArgumentException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return accessToken;
	}

	public String refreshTokenCreate(UserDetails user) throws IllegalArgumentException, UnsupportedEncodingException {

		String salt = BCrypt.gensalt();
		redisTemplate.opsForValue().set("token:salt:" + user.getUsername(), salt, JwtConstansts.jwtRefreshTokenTtl,
				TimeUnit.SECONDS);

		Algorithm algorithm = Algorithm.HMAC256(salt);
		String refreshToken = JWT.create().withSubject(user.getUsername())
				.withClaim("token:refresh_uuid", UUID.randomUUID().toString())
				.withExpiresAt(new Date(System.currentTimeMillis() + JwtConstansts.jwtRefreshTokenTtl))
				.withIssuedAt(new Date()).sign(algorithm);

		return refreshToken;
	}

	public void createUser(String username, String password) {

		String encryptPwd = passwordEncoder.encode(password);

		/**
		 * @todo 保存用户名和加密后密码到数据库
		 */
	}

	public void deleteUserLoginInfo(String username) {
		/**
		 * @todo 清除数据库或者缓存中登录salt
		 */
	}

	public PasswordEncoder getPasswordEncoder() {
		return passwordEncoder;
	}

	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

}
