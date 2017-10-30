package com.gittoy.security.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * SecurityProperties.java
 *
 * @author GaoYu 2017年10月27日 下午2:11:18
 */
@ConfigurationProperties(prefix = "gittoy.security")
public class SecurityProperties {

	private BrowserProperties browser = new BrowserProperties();

	private ValidateCodeProperties code = new ValidateCodeProperties();

	public BrowserProperties getBrowser() {
		return browser;
	}

	public void setBrowser(BrowserProperties browser) {
		this.browser = browser;
	}

	public ValidateCodeProperties getCode() {
		return code;
	}

	public void setCode(ValidateCodeProperties code) {
		this.code = code;
	}
}
