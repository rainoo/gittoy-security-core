package com.gittoy.security.core.validate.code.sms;

/**
 * DefaultSmsCodeSender.java
 *
 * @author GaoYu 2017年10月30日 上午9:07:01
 */
public class DefaultSmsCodeSender implements SmsCodeSender {

	@Override
	public void send(String mobile, String code) {
		System.out.println("向手机: " + mobile + " 发送验证码: " + code);
	}

}
