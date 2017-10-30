package com.gittoy.security.core.properties;

/**
 * BrowserProperties.java
 *
 * @author GaoYu 2017年10月27日 下午2:11:30
 */
public class BrowserProperties {

	private String loginPage = "/gittoy-signIn.html";

	private LoginType loginType = LoginType.JSON;

	// 记住我：秒数
	private int rememberMeSeconds = 3600;

	public String getLoginPage() {
		return loginPage;
	}

	public void setLoginPage(String loginPage) {
		this.loginPage = loginPage;
	}

	public LoginType getLoginType() {
		return loginType;
	}

	public void setLoginType(LoginType loginType) {
		this.loginType = loginType;
	}

	public int getRememberMeSeconds() {
		return rememberMeSeconds;
	}

	public void setRememberMeSeconds(int rememberMeSeconds) {
		this.rememberMeSeconds = rememberMeSeconds;
	}

}
