package cn.edu.dgut.school_helper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication  
@EnableGlobalMethodSecurity(prePostEnabled=true)
@ComponentScan(basePackages= {"cn.edu.dgut.school_helper"})
public class SpringBootSecurityApplication {
	
	public static void main(String[] args) throws Exception {
		SpringApplication.run(SpringBootSecurityApplication.class, args);
	}

}
