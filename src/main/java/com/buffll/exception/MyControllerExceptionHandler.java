package com.buffll.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>自定义全局统一异常处理类</p>
 * 对拦截控制器产生的所有异常,统一进行处理
 * @author pxz
 * @create 2021-02-23 10:58
 */
@ControllerAdvice //这个注解可以对所有标注了@Controller的控制器进行拦截,以进行 全局异常处理
public class MyControllerExceptionHandler {
	//@ControllerAdvice 注解,除了可以进行全局异常处理之外,还可以进行 全局数据绑定 和 全局数据预处理,是一个增强的Controller
	
	//获取日志对象用来记录异常信息
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * 处理异常信息
	 * @param request
	 * @param e
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	public ModelAndView exceptionHandler(HttpServletRequest request,Exception e) throws Exception {
		//@ExceptionHandler这个注解用于标注一个处理异常的方法,和 @ControllerAdvice配合使用
		//@ExceptionHandler 注解的参数填写一个异常类的字节码,表示如果抛出了某个具体的异常,则执行这个方法来处理
		
		//记录异常信息
		logger.error("@@异常信息:  请求的URL: {}, 异常信息: {} ",request.getRequestURL(),e.getMessage());
		
		//当前这个异常处理类是全局的异常处理,会将整个项目运行期间所有的异常进行拦截捕获,也包括自定义的异常,比如资源找不到等异常,
		//这些是自己定义的异常,希望发生这些异常后可以执行另外的逻辑,但是由于这个全局异常处理类的存在,会导致其他异常处理类不起作用
		//所以需要在此处做一个判断,如果是标注了 @ResponseStatus 注解的异常处理类(即标识了状态码的),就不要处理,交给springboot本身去处理即可(springboot本身的异常处理就是返回到对应状态码的页面 如404.html 500.html等)
		
		if(AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null){
			//AnnotationUtils.findAnnotation() 可以判断是否存在某个注解
			//若存在,则将异常直接抛出,交给springboot处理即可
		    throw e;
		}
		
		//记录后返回错误页面
		ModelAndView mv = new ModelAndView();
		mv.addObject("url",request.getRequestURL());
		mv.addObject("exception",e);
		mv.setViewName("/error/error");
		
		return mv;
	}
}
