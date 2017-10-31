package com.gittoy.security.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * ValidateCodeProcessor.java
 * 校验码处理器，封装不同校验码的处理逻辑
 *
 * @author GaoYu 2017年10月30日 上午10:28:33
 */
public interface ValidateCodeProcessor {

	/**
	 * 验证码放入session时的前缀
	 */
	String SESSION_KEY_PREFIX = "SESSION_KEY_FOR_CODE_";

	/**
	 * 创建校验码
	 * 
	 * @param request
	 * @throws Exception
	 */
	void create(ServletWebRequest request) throws Exception;

	/**
	 * 校验验证码
	 * 
	 * @param servletWebRequest
	 * @throws Exception
	 */
	void validate(ServletWebRequest servletWebRequest);
}
/** ===========================================================================

			ValidateCodeController
			         ↓
			ValidateCodeProcessor
			         ↑
		AbstractValidateCondeProcessor ┉> ValidateCodeGenerator
			      ↑          ↑
	ImageCodeProcessor      SmsCodeProcessor ┉> SmsCodeGenerator

	1）生成、存储、发送会写到抽象类AbstractValidateCondeProcessor里面
	2）生成的时候，会调用验证码生成器ValidateCodeGenerator
	3）具体的发送会因为写流、或者短信服务商的不同而不同，这需要分别写到ImageCodeProcessor和SmsCodeProcessor子类里面
	4）短信验证码发送的手，会调用短信验证码生成器SmsCodeGenerator

============================================================================= */