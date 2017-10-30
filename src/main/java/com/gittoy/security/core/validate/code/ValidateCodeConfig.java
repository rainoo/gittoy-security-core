package com.gittoy.security.core.validate.code;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.gittoy.security.core.properties.SecurityProperties;

/**
 * ValidateCodeConfig.java
 * 
 * @author GaoYu 2017年10月29日 下午5:54:32
 */
@Configuration
public class ValidateCodeConfig {

	@Autowired
	private SecurityProperties securityProperties;

	@Bean
	// 如果不存在名字为imageCodeGenerator的Bean的时候，才用下面的配置。
	@ConditionalOnMissingBean(name = "imageCodeGenerator")
	public ValidateCodeGenerator imageCodeGenerator() {
		ImageCodeGenerator codeGenerator = new ImageCodeGenerator();
		codeGenerator.setSecurityProperties(securityProperties);
		return codeGenerator;
	}
}
