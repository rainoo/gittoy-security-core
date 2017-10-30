package com.gittoy.security.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * ValidateCodeGenerator.java
 * 
 * @author GaoYu 2017年10月29日 下午5:45:28
 */
public interface ValidateCodeGenerator {

	ImageCode generate(ServletWebRequest request);
}
