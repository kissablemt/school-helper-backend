package cn.edu.dgut.school_helper.config;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

import cn.edu.dgut.school_helper.config.jwt.service.JwtUserService;



public class AuthenticationSuccessfulHandlerConfig extends SavedRequestAwareAuthenticationSuccessHandler {

	private static final Logger log = LoggerFactory.getLogger(AuthenticationSuccessfulHandlerConfig.class);

	ObjectMapper mapper = new ObjectMapper();
	
	JwtUserService jwtUserService;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws ServletException, IOException {
		log.info("登录成功");
//		String token = jwtUserService.saveUserLoginInfo((UserDetails) authentication.getPrincipal());
		//创建accessToken以及refreshToken
		UserDetails details = (UserDetails) authentication.getPrincipal();
		String refreshToken = jwtUserService.refreshTokenCreate(details);
		String accessToken = jwtUserService.accessTokenCreate(details);
		
		//返回map
		Map tokens = MapUtils.putAll(new HashMap(),
				new String[] {
				"accessToken",accessToken,
				"refreshToken",refreshToken});
		
		
		//设置响应头
		//ps 不知道怎么返回两个数据
		response.setHeader("Authorization", accessToken);
		
		response.getWriter().write(mapper.writeValueAsString(tokens));
	}

	public JwtUserService getJwtUserService() {
		return jwtUserService;
	}

	public void setJwtUserService(JwtUserService jwtUserService) {
		this.jwtUserService = jwtUserService;
	}

}
