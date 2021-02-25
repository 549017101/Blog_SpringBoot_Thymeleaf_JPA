package com.buffll.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录功能的拦截器
 * @author pxz
 * @create 2021-02-25 15:27
 */
public class LoginInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if(request.getSession().getAttribute("user") == null){
			//如果未登录,则重定向到登录页面
		    response.sendRedirect("/admin");
		    return false;
		}
		return true;
	}
}
