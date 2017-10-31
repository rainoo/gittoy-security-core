package com.gittoy.security.core.properties;

/**
 * ImageCodeProperties.java 验证码默认配置，如果应用不做任何配置的情况下，则使用该配置。
 * 
 * @author GaoYu 2017年10月29日 上午9:50:47
 */
public class SmsCodeProperties {

	// 验证码长度
	private int length = 6;
	// 失效时间：默认60秒
	private int expireIn = 60;

	private String url;

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getExpireIn() {
		return expireIn;
	}

	public void setExpireIn(int expireIn) {
		this.expireIn = expireIn;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
