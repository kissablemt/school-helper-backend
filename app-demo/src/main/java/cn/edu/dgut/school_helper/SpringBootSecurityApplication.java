package cn.edu.dgut.school_helper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication  
@ComponentScan(basePackages= {"cn.edu.dgut.school_helper"})
@MapperScan(basePackages= {"cn.edu.dgut.school_helper.**.mapper"})
@ServletComponentScan
public class SpringBootSecurityApplication {
	
	public static void main(String[] args) throws Exception {
		SpringApplication.run(SpringBootSecurityApplication.class, args);
	}

}
