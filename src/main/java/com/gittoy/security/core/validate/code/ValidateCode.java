package com.gittoy.security.core.validate.code;

import java.time.LocalDateTime;

/**
 * ValidateCode.java
 * 
 * @author GaoYu 2017年10月28日 下午10:15:59
 */
public class ValidateCode {

	private String code;

	private LocalDateTime expireTime;

	public ValidateCode(String code, int expireIn) {
		super();
		this.code = code;
		this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
	}

	public ValidateCode(String code, LocalDateTime expireTime) {
		super();
		this.code = code;
		this.expireTime = expireTime;
	}

	public boolean isExpried() {
		return LocalDateTime.now().isAfter(expireTime);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public LocalDateTime getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(LocalDateTime expireTime) {
		this.expireTime = expireTime;
	}
}

/** ===========================================================================

	1）image：一个图片，展示给用户看的
	2）code:随机数，image是根据code生成的，该随机数需要保存到session中，供后期用户登录时校验使用
	3）expireTime：验证码过期时间
	4）图形验证码基本参数配置：请求级配置--> （覆盖） --> 应用级配置 --> （覆盖） --> 默认配置
	5）默认配置值写在gittoy-security-core上
	6）应用级配置写在gittoy-security-demo上
	7）请求级配置在调用接口时传递

============================================================================= */