package com.buffll.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 处理业务逻辑中的异常类
 * @author pxz
 * @create 2021-02-23 13:36
 */
@ResponseStatus(HttpStatus.NOT_FOUND) //该注解可以指定异常返回的状态,要想实现错误页面跳转,必须要这个注解
public class NotFoundException extends RuntimeException{
	public NotFoundException() {
	}
	
	public NotFoundException(String message) {
		super(message);
	}
	
	public NotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
