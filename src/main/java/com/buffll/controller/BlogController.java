package com.buffll.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 博客功能的控制器
 * @author pxz
 * @create 2021-02-25 15:22
 */
@Controller
@RequestMapping("/admin")
public class BlogController {
	
	/**
	 * 跳转到博客管理页面
	 * @return
	 */
	@GetMapping("/blogs")
	public String blogs(){
		return "pages/admin/blogs";
	}
}
