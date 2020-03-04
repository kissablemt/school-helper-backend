package cn.edu.dgut.school_helper.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import cn.edu.dgut.school_helper.constant.JwtRequestConstant;
import cn.edu.dgut.school_helper.util.CommonResponse;
import cn.edu.dgut.school_helper.util.JwtUtils;

//@WebFilter(urlPatterns= {"/api/*"})
public class JwtAccessTokenFilter extends OncePerRequestFilter {

	ObjectMapper mapper = new ObjectMapper();
	
	List<String> excludePath = new ArrayList<>();

	{
//		excludePath.add("/api/user/keepLogin");
		excludePath.add("/api/user/login");
	}
	
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		//排除refreshToken的路径
		for (String path : excludePath) {
			if (StringUtils.equals(path, request.getRequestURI())) {
				filterChain.doFilter(request, response);
				return;
			}
		}
		
		String accessToken = request.getHeader("Authorization");
		accessToken = StringUtils.removeStart(accessToken, "Bearer ");
		String openId = null;
		// 根据accessToken获取openId
		if (!StringUtils.isNotBlank(accessToken) || (openId = JwtUtils.verifyAccessToken(accessToken)) == null) {
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().print(mapper.writeValueAsString(CommonResponse.error("没有accessToken")));
			return;
		}
		request.setAttribute(JwtRequestConstant.OPEN_ID, openId);
		filterChain.doFilter(request, response);
	}
}
