package com.gittoy.security.core.validate.code;

import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * ValidateCodeController.java
 * 
 * @author GaoYu 2017年10月28日 下午10:25:52
 */
@RestController
public class ValidateCodeController {

	public static final String SESSION_KEY = "SESSION_KEY_IMAGE_CODE";

	// Spring操作session类
	private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

	@Autowired
	private ValidateCodeGenerator imageCodeGenerator;

	@GetMapping("/code/image")
	public void createCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 根据随机数生成图片
		ImageCode imageCode = imageCodeGenerator.generate(new ServletWebRequest(request));
		// 将随机数存到session中
		sessionStrategy.setAttribute(new ServletWebRequest(request), SESSION_KEY, imageCode);
		ImageIO.write(imageCode.getImage(), "JPEG", response.getOutputStream());
	}
}
