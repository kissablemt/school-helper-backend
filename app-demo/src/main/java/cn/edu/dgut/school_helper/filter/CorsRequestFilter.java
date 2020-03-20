package cn.edu.dgut.school_helper.filter;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 使用@ServletComponentScan,扫描注解，无法自定义过滤顺序，只能根据类的开头a-z，顺序过滤
 * cors需要第一个过滤，记录一下
 * @author 星星星
 *
 */
@WebFilter(urlPatterns = {"/*"})
public class CorsRequestFilter extends OncePerRequestFilter{

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
