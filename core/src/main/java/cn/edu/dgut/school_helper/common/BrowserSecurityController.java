package cn.edu.dgut.school_helper.common;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import cn.edu.dgut.school_helper.util.CommonResponse;


@RestController
public class BrowserSecurityController {
	
	private static final Logger log = LoggerFactory.getLogger(BrowserSecurityController.class);

	private RequestCache requestCache = new HttpSessionRequestCache();
	
	private RedirectStrategy redirectStrategy= new DefaultRedirectStrategy();
	
	
	
	@Autowired
	SecurityProperties securityProperties;
	 
	@RequestMapping("/authentication/require") 
	@ResponseStatus(code= HttpStatus.UNAUTHORIZED)
	public CommonResponse requireAuthentication(HttpServletRequest request,HttpServletResponse response) throws IOException {
		
		 SavedRequest savedRequest = requestCache.getRequest(request, response);
		 if(savedRequest != null) {
			 String target = savedRequest.getRedirectUrl();
			 if(StringUtils.endsWithIgnoreCase(target, ".html")) {//转到登录页
				 log.info("原本跳转地址是:" + target);
				 redirectStrategy.sendRedirect(request, response, securityProperties.getBrowser().getLoginPage());
			 }
		 }
		return CommonResponse.error("没有身份认证，请引导用户到登录页");
	}
}
