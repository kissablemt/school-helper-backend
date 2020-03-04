package cn.edu.dgut.school_helper.config.response;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@EnableWebMvc //如果我们需要全面接管SpringBoot中的SpringMVC配置则开启此注解，
                 //开启后，SpringMVC的自动配置将会失效。
//@Configuration
public class WebMvcConfig implements WebMvcConfigurer{
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new ResponseResultInterceptor()).addPathPatterns("/api/**");
	}
}
