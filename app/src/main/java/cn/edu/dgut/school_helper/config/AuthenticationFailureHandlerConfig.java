package cn.edu.dgut.school_helper.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import com.fasterxml.jackson.databind.ObjectMapper;


public class AuthenticationFailureHandlerConfig extends SimpleUrlAuthenticationFailureHandler { 


	@Autowired
	ObjectMapper objectMapper;

	private static final Logger log = LoggerFactory.getLogger(AuthenticationFailureHandlerConfig.class);

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		log.info("登录失败");
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().write(objectMapper.writeValueAsString(exception));

	}
	
	
}
