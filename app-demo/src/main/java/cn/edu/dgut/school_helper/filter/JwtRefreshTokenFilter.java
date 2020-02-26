package cn.edu.dgut.school_helper.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.filter.OncePerRequestFilter;

import cn.edu.dgut.school_helper.constant.JwtRequestConstant;
import cn.edu.dgut.school_helper.util.JwtUtils;
@WebFilter("/api/user/keepLogin")
public class JwtRefreshTokenFilter extends OncePerRequestFilter {
	
	@Autowired
	RedisTemplate<String, String> redisTemplate;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		System.out.println("进故宫了");
		String refreshToken = request.getParameter(JwtRequestConstant.REFRESH_TOKNE);
		String openId = null;
		// 根据refreshToken获取openId;
		if (!StringUtils.isNotBlank(refreshToken) || (openId = JwtUtils.verifyRefreshToken(refreshToken, redisTemplate)) == null) {
			return;
		}
		request.setAttribute(JwtRequestConstant.OPEN_ID, openId);
		filterChain.doFilter(request, response);
	}

}
