package cn.edu.dgut.school_helper.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;
@WebFilter("/*")
public class OptionsRequestFilter extends OncePerRequestFilter{

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST,GET,PUT,OPTIONS,DELETE"); 
		response.setHeader("Access-Control-Max-Age", "0"); 
		response.setHeader("Access-Control-Allow-Headers", "Authorization,Origin, No-Cache, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type, X-E4M-With,userId,token"); 
		response.setHeader("Access-Control-Allow-Credentials", "true"); 
		response.setHeader("XDomainRequestAllowed","1"); 
		response.setHeader("XDomainRequestAllowed","1"); 

		filterChain.doFilter(request, response);
	}

}
