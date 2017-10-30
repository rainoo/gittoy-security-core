package com.gittoy.security.core.validate.code;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import com.gittoy.security.core.properties.SecurityProperties;

/**
 * ImageCodeGenerator.java
 * 
 * @author GaoYu 2017年10月29日 下午5:48:18
 */
public class ImageCodeGenerator implements ValidateCodeGenerator {

	@Autowired
	private SecurityProperties securityProperties;

	@Override
	public ImageCode generate(ServletWebRequest request) {
		// 验证码图片长度：取出请求中带来的应用值，如果没有则取默认值
		int width = ServletRequestUtils.getIntParameter(request.getRequest(), "width",
				securityProperties.getCode().getImage().getWidth());
		// 验证码图片高度：取出请求中带来的应用值，如果没有则取默认值
		int height = ServletRequestUtils.getIntParameter(request.getRequest(), "height",
				securityProperties.getCode().getImage().getHeight());

		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		Graphics g = image.getGraphics();

		Random random = new Random();

		g.setColor(getRandColor(200, 250));
		// 绘制背景
		g.fillRect(0, 0, width, height);
		// 定义字体样式
		g.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		g.setColor(getRandColor(160, 200));
		for (int i = 0; i < 150; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int xl = random.nextInt(12);
			int yl = random.nextInt(12);
			g.drawLine(x, y, x + xl, y + yl);
		}

		String sRand = "";

		// i为图片验证码长度
		for (int i = 0; i < securityProperties.getCode().getImage().getLength(); i++) {
			String rand = String.valueOf(random.nextInt(10));
			sRand += rand;
			// 将认证码显示到图象中
			g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
			g.drawString(rand, 13 * i + 6, 16);
		}

		// 输出生成的验证码图片
		g.dispose();

		return new ImageCode(image, sRand, securityProperties.getCode().getImage().getExpireIn());
	}

	/**
	 * 生成随机背景条纹
	 * 
	 * @param fc
	 * @param bc
	 * @return
	 */
	private Color getRandColor(int fc, int bc) {
		Random random = new Random();
		fc = fc > 255 ? 255 : fc;
		bc = bc > 255 ? 255 : bc;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}

	public SecurityProperties getSecurityProperties() {
		return securityProperties;
	}

	public void setSecurityProperties(SecurityProperties securityProperties) {
		this.securityProperties = securityProperties;
	}

}
