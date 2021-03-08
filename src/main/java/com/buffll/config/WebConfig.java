package com.buffll.config;

import com.buffll.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 配置类
 * @author pxz
 * @create 2021-02-25 15:30
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LoginInterceptor())
				.addPathPatterns("/admin/**")
				.excludePathPatterns("/admin/login")
				.excludePathPatterns("/admin")
				.excludePathPatterns("/admin/captcha/code");
	}
}
