package com.gittoy.security.core.properties;

/**
 * ImageCodeProperties.java
 * 验证码默认配置，如果应用不做任何配置的情况下，则使用该配置。
 * 
 * @author GaoYu 2017年10月29日 上午9:50:47
 */
public class ImageCodeProperties extends SmsCodeProperties {

	public ImageCodeProperties() {
		setLength(4);
	}
	// 验证码图片长度
	private int width = 67;
	// 验证码图片高度
	private int height = 23;

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

}
