package com.gittoy.security.core.validate.code.sms;

/**
 * SmsCodeSender.java
 *
 * @author GaoYu 2017年10月30日 上午9:05:33
 */
public interface SmsCodeSender {

	void send(String mobile, String code);
}
