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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import cn.edu.dgut.school_helper.constant.JwtRequestConstant;
import cn.edu.dgut.school_helper.util.CommonResponse;
import cn.edu.dgut.school_helper.util.JwtUtils;

@WebFilter(urlPatterns= {"/api/*"})
public class JwtAccessTokenFilter extends OncePerRequestFilter {

	
	private static final Logger log = LoggerFactory.getLogger(JwtAccessTokenFilter.class);

	
	ObjectMapper mapper = new ObjectMapper();
	
	List<String> excludePath = new ArrayList<>();

	{
		excludePath.add("/api/reply/selectAll");
		excludePath.add("/api/post/selectList");
		excludePath.add("/api/post/selectSecondHandList");
		excludePath.add("/api/school/selectAll");
		excludePath.add("/api/user/login");

	}
	
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		// 排除refreshToken的路径
		for (String path : excludePath) {
			if (StringUtils.startsWith(request.getRequestURI(), path)) {
				filterChain.doFilter(request, response);
				return;
			}
		}
		// 获取accessToken
		String accessToken = request.getHeader("Authorization");
		log.info("before:" + accessToken);
		accessToken = StringUtils.removeStart(accessToken, "Bearer ");
		String openId = null;
		// 获取openId
		if (!StringUtils.isNotBlank(accessToken) || (openId = JwtUtils.verifyAccessToken(accessToken)) == null) {
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().print(mapper.writeValueAsString(CommonResponse.error("没有accessToken")));
			return;
		}
		log.info("openId:" + openId);
		request.setAttribute(JwtRequestConstant.OPEN_ID, openId);
		filterChain.doFilter(request, response);
	}
}
