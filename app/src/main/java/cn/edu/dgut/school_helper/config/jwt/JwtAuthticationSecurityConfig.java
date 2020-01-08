package cn.edu.dgut.school_helper.config.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import cn.edu.dgut.school_helper.config.jwt.filter.JwtAuthenticationFilter;
import cn.edu.dgut.school_helper.config.jwt.service.JwtAuthenticationProvider;
import cn.edu.dgut.school_helper.config.jwt.service.JwtUserService;

/**
 * @author Monty
 * @date 2019/08/07
 */
@Component
public class JwtAuthticationSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

	@Autowired
	private AuthenticationSuccessHandler jwtRefreshSuccessHandler; // jwt验证成功后使用他来刷新

	@Autowired
	private AuthenticationFailureHandler authenticationFailureHandler;

	@Autowired
	private JwtUserService jwtUserService;
	
	
	@Override
	public void configure(HttpSecurity builder) throws Exception {
		// TODO Auto-generated method stub
		JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter();
		jwtAuthenticationFilter.setAuthenticationManager(builder.getSharedObject(AuthenticationManager.class));
		jwtAuthenticationFilter.setAuthenticationSuccessHandler(jwtRefreshSuccessHandler);
		jwtAuthenticationFilter.setAuthenticationFailureHandler(authenticationFailureHandler);


		JwtAuthenticationProvider jwtAuthenticationProvider = new JwtAuthenticationProvider();
		jwtAuthenticationProvider.setUserService(jwtUserService);

		builder.authenticationProvider(jwtAuthenticationProvider).addFilterBefore(jwtAuthenticationFilter,
				UsernamePasswordAuthenticationFilter.class);
	}

}
