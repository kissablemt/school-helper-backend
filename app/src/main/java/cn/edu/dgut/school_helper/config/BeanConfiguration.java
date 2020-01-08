package cn.edu.dgut.school_helper.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import cn.edu.dgut.school_helper.config.jwt.JwtRefreshSuccessHandler;
import cn.edu.dgut.school_helper.config.jwt.TokenClearLogoutHandler;
import cn.edu.dgut.school_helper.config.jwt.service.JwtUserService;

@Configuration
public class BeanConfiguration {
	
	
	@Bean
    public PasswordEncoder passwordEncoder() {
        /**
         * BCryptPasswordEncoder：相同的密码明文每次生成的密文都不同，安全性更高
         */
        return new BCryptPasswordEncoder();
    }
	
	@Bean 
	public JwtUserService jwtUserService() {
		JwtUserService jwtUserService =  new JwtUserService();
		jwtUserService.setPasswordEncoder(passwordEncoder());
		return jwtUserService;
	}
	
	@Bean
	public AuthenticationFailureHandlerConfig authenticationFailureHandlerConfig() {
		return new AuthenticationFailureHandlerConfig();
	}
	
	@Bean
	public AuthenticationSuccessfulHandlerConfig authenticationSuccessfulHandlerConfig() {
		AuthenticationSuccessfulHandlerConfig authenticationSuccessfulHandlerConfig =  new AuthenticationSuccessfulHandlerConfig();
		authenticationSuccessfulHandlerConfig.setJwtUserService(jwtUserService());
		return authenticationSuccessfulHandlerConfig;
	}
	
	@Bean
	protected JwtRefreshSuccessHandler jwtRefreshSuccessHandler() {
		return new JwtRefreshSuccessHandler(jwtUserService());
	}
	
	@Bean
	protected TokenClearLogoutHandler tokenClearLogoutHandler() {
		return new TokenClearLogoutHandler(jwtUserService());
	}
	
}
