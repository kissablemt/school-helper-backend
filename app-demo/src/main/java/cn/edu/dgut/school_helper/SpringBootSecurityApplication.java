package cn.edu.dgut.school_helper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication  
@EnableAutoConfiguration(exclude = {SecurityAutoConfiguration.class}) //先关闭basic认证
@ComponentScan(basePackages= {"cn.edu.dgut.school_helper"})
@MapperScan(basePackages= {"cn.edu.dgut.school_helper.**.mapper"})
public class SpringBootSecurityApplication {
	
	public static void main(String[] args) throws Exception {
		SpringApplication.run(SpringBootSecurityApplication.class, args);
	}

}
