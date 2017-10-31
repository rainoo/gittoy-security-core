package com.gittoy.security.core.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.gittoy.security.core.properties.SecurityConstants;

/**
 * AbstractChannelSecurityConfig.java
 *
 * @author GaoYu 2017年10月31日 上午10:37:04
 */
public class AbstractChannelSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	protected AuthenticationSuccessHandler imoocAuthenticationSuccessHandler;

	@Autowired
	protected AuthenticationFailureHandler imoocAuthenticationFailureHandler;

	protected void applyPasswordAuthenticationConfig(HttpSecurity http) throws Exception {
		http.formLogin().loginPage(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL)
				.loginProcessingUrl(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM)
				.successHandler(imoocAuthenticationSuccessHandler).failureHandler(imoocAuthenticationFailureHandler);
	}

}
