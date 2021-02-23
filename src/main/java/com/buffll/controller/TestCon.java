package com.buffll.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author pxz
 * @create 2021-02-23 10:51
 */
@Controller
public class TestCon {
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/types")
	public String index1() {
		return "pages/types";
	}
	
	@GetMapping("/tags")
	public String index2() {
		return "pages/tags";
	}
	
	@GetMapping("/blog")
	public String index3() {
		return "pages/blog";
	}
	
	@GetMapping("/archives")
	public String index4() {
		return "pages/archives";
	}
	
	@GetMapping("/about")
	public String index5() {
		return "pages/about";
	}
	
	@GetMapping("/blogs")
	public String index6() {
		return "pages/admin/blogs";
	}
	
	@GetMapping("/blogs-input")
	public String index7() {
		return "pages/admin/blogs-input";
	}
}
