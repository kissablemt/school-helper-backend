package cn.edu.dgut.school_helper.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.RedirectStrategy;
import org.springframework.web.filter.OncePerRequestFilter;

import cn.edu.dgut.school_helper.constant.JwtRequestConstant;
import cn.edu.dgut.school_helper.util.JwtUtils;
//@WebFilter(urlPatterns= {"/api/*"})
public class JwtAccessTokenFilter extends OncePerRequestFilter{

	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String accessToken = request.getParameter(JwtRequestConstant.ACCESS_TOKNE);
		String openId = null;
		//根据accessToken获取openId
		if(!StringUtils.isNotBlank(accessToken) || (openId = JwtUtils.verifyAccessToken(accessToken))== null) {
			return;
		}
		request.setAttribute(JwtRequestConstant.OPEN_ID, openId);
		filterChain.doFilter(request, response);
	}
}
