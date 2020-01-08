package cn.edu.dgut.school_helper.common;


public class BrowserProperites {

	private String loginPage = "/login.html";

	private String loginProcessingUrl = "/authentication/form";
	
	private String noLoginProcessingUrl = "/authentication/require";
	
	private Integer rememberTime = 60*60*24*7;

	public String getLoginPage() {
		return loginPage;
	}

	public void setLoginPage(String loginPage) {
		this.loginPage = loginPage;
	}

	public String getLoginProcessingUrl() {
		return loginProcessingUrl;
	}

	public void setLoginProcessingUrl(String loginProcessingUrl) {
		this.loginProcessingUrl = loginProcessingUrl;
	}

	public Integer getRememberTime() {
		return rememberTime;
	}

	public void setRememberTime(Integer rememberTime) {
		this.rememberTime = rememberTime;
	}

	public String getNoLoginProcessingUrl() {
		return noLoginProcessingUrl;
	}

	public void setNoLoginProcessingUrl(String noLoginProcessingUrl) {
		this.noLoginProcessingUrl = noLoginProcessingUrl;
	}
	
	
	
	
}
