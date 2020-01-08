package cn.edu.dgut.school_helper.common;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="spring.security") //可以通过配置文件配置
public class SecurityProperties {
	
	private BrowserProperites browser = new BrowserProperites();

	private LoginType loginType = LoginType.REDIRECT;//默认重定向


	public LoginType getLoginType() {
		return loginType;
	}

	public void setLoginType(LoginType loginType) {
		this.loginType = loginType;
	}

	public BrowserProperites getBrowser() {
		return browser;
	}

	public void setBrowser(BrowserProperites browser) {
		this.browser = browser;
	}
	
}
