package com.gittoy.security.core;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import com.gittoy.security.core.properties.SecurityProperties;

/**
 * SecurityCoreConfig.java
 *
 * @author GaoYu 2017年10月27日 下午2:18:01
 */
@Configuration
@EnableConfigurationProperties(SecurityProperties.class)
public class SecurityCoreConfig {

}
