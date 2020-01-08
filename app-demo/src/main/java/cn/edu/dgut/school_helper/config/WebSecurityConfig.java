package cn.edu.dgut.school_helper.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.header.Header;
import org.springframework.security.web.header.writers.StaticHeadersWriter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import cn.edu.dgut.school_helper.common.SecurityProperties;
import cn.edu.dgut.school_helper.config.jwt.JwtAuthticationSecurityConfig;
import cn.edu.dgut.school_helper.config.jwt.TokenClearLogoutHandler;
import cn.edu.dgut.school_helper.config.jwt.filter.OptionsRequestFilter;

//@EnableWebSecurity //包含了Configuration  先不开启springSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


	@Autowired
	SecurityProperties securityProperties;

	@Autowired
	JwtAuthticationSecurityConfig jwtAuthticationSecurityConfig;
	
	@Autowired
	AuthenticationSuccessHandler authenticationSuccessHandler;
	
	@Autowired
	AuthenticationFailureHandler authenticationFailureHandler;
	
	@Autowired
	TokenClearLogoutHandler tokenClearLogoutHandler;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		super.configure(auth); //默认或者指定自己的哪个detailsService
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers(securityProperties.getBrowser().getLoginPage(),
				securityProperties.getBrowser().getNoLoginProcessingUrl())
		.permitAll()
		.anyRequest().authenticated()
		.and()
		.formLogin()
		.loginPage(securityProperties.getBrowser().getNoLoginProcessingUrl())//经过处理后转发
		.loginProcessingUrl(securityProperties.getBrowser().getLoginProcessingUrl())
		.successHandler(authenticationSuccessHandler)
		.failureHandler(authenticationFailureHandler)
		.and()
		.cors()//加载cors配置
		.and()
		.headers()
		.addHeaderWriter(new StaticHeadersWriter(Arrays.asList(
				new Header("Access-control-Allow-Origin","*"),
				new Header("Access-Control-Expose-Headers","Authorization"))))
		.and()
		.csrf().disable()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)//去掉session
		.and()
		.addFilterAfter(new OptionsRequestFilter(), CorsFilter.class)
		.logout()
		//		        .logoutUrl("/logout")   //默认就是"/logout"
		.addLogoutHandler(tokenClearLogoutHandler)
		.logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler())
		.and()
		.apply(jwtAuthticationSecurityConfig)
		.and();
	}


	@Bean
	protected CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("*"));
		configuration.setAllowedMethods(Arrays.asList("GET","POST","HEAD", "OPTION"));
		configuration.setAllowedHeaders(Arrays.asList("*"));
		configuration.addExposedHeader("Authorization");
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}
}

