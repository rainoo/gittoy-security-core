package com.gittoy.security.core.validate.code;

import org.springframework.security.core.AuthenticationException;

/**
 * ValidateCodeException.java
 * 
 * @author GaoYu 2017年10月28日 下午11:40:15
 */
public class ValidateCodeException extends AuthenticationException {

	private static final long serialVersionUID = -442685482764630661L;

	public ValidateCodeException(String msg) {
		super(msg);
	}

}
