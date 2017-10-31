package com.gittoy.security.core.properties;

/**
 * ValidateCodeProperties.java
 * 短信验证码配置和图形验证码的配置
 * 
 * @author GaoYu 2017年10月29日 上午9:54:41
 */
public class ValidateCodeProperties {

	private ImageCodeProperties image = new ImageCodeProperties();

	private SmsCodeProperties sms = new SmsCodeProperties();

	public ImageCodeProperties getImage() {
		return image;
	}

	public void setImage(ImageCodeProperties image) {
		this.image = image;
	}

	public SmsCodeProperties getSms() {
		return sms;
	}

	public void setSms(SmsCodeProperties sms) {
		this.sms = sms;
	}

}
