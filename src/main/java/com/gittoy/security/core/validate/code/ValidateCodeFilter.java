package com.gittoy.security.core.validate.code;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import com.gittoy.security.core.properties.SecurityProperties;

/**
 * ValidateCodeFilter.java
 * 
 * @author GaoYu 2017年10月28日 下午11:24:09
 */
public class ValidateCodeFilter extends OncePerRequestFilter implements InitializingBean {

	private AuthenticationFailureHandler authenticationFailureHandler;

	private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

	private Set<String> urls = new HashSet<>();
	
	private SecurityProperties securityProperties;

	private AntPathMatcher pathMatcher = new AntPathMatcher();

	@Override
	public void afterPropertiesSet() throws ServletException {
		super.afterPropertiesSet();
		String[] configUrls = StringUtils.splitByWholeSeparatorPreserveAllTokens(securityProperties.getCode().getImage().getUrl(), ",");
		for (String configUrl : configUrls) {
			urls.add(configUrl);
		}
		urls.add("/authentication/form");
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		boolean action = false;
		for (String url : urls) {
			if (pathMatcher.match(url, request.getRequestURI())) {
				action = true;
			}
		}

		if (action) {
			try {
				validate(new ServletWebRequest(request));
			} catch (ValidateCodeException e) {
				authenticationFailureHandler.onAuthenticationFailure(request, response, e);
				return;
			}
		}
		filterChain.doFilter(request, response);
	}

	/**
	 * 校验逻辑
	 * 
	 * @param servletWebRequest
	 * @throws ServletRequestBindingException 
	 */
	private void validate(ServletWebRequest request) throws ServletRequestBindingException {

		// 从session中取出SESSION_KEY_IMAGE_CODE
		ImageCode codeInSession = (ImageCode) sessionStrategy.getAttribute(request, ValidateCodeController.SESSION_KEY);

		// 从请求中拿到imageCode参数
		String codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(), "imageCode");

		if (StringUtils.isBlank(codeInRequest)) {
			throw new ValidateCodeException("验证码的值不能为空");
		}

		if (codeInSession == null) {
			throw new ValidateCodeException("验证码不存在");
		}

		if (codeInSession.isExpried()) {
			sessionStrategy.removeAttribute(request, ValidateCodeController.SESSION_KEY);
			throw new ValidateCodeException("验证码已过期");
		}

		if (!StringUtils.equals(codeInSession.getCode(), codeInRequest)) {
			throw new ValidateCodeException("验证码不匹配");
		}

		sessionStrategy.removeAttribute(request, ValidateCodeController.SESSION_KEY);
	}

	public AuthenticationFailureHandler getAuthenticationFailureHandler() {
		return authenticationFailureHandler;
	}

	public void setAuthenticationFailureHandler(AuthenticationFailureHandler authenticationFailureHandler) {
		this.authenticationFailureHandler = authenticationFailureHandler;
	}

	public SessionStrategy getSessionStrategy() {
		return sessionStrategy;
	}

	public void setSessionStrategy(SessionStrategy sessionStrategy) {
		this.sessionStrategy = sessionStrategy;
	}

	public Set<String> getUrls() {
		return urls;
	}

	public void setUrls(Set<String> urls) {
		this.urls = urls;
	}

	public SecurityProperties getSecurityProperties() {
		return securityProperties;
	}

	public void setSecurityProperties(SecurityProperties securityProperties) {
		this.securityProperties = securityProperties;
	}

}

/** ===========================================================================

1）OncePerRequestFilter，Spring提供的工具类，用来保证Filter每次只能被调用一次；

============================================================================= */