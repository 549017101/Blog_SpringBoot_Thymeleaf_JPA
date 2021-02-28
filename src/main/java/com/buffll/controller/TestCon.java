package com.buffll.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author pxz
 * @create 2021-02-23 10:51
 */
@Controller
public class TestCon {
	
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
		return "pages/admin/admin_blogs";
	}
	
	@GetMapping("/blogs-input")
	public String index7() {
		return "pages/admin/admin_blogs_input";
	}
	
	@GetMapping("/login11")
	public String login(){
		return "pages/admin/login";
	}
	
	@GetMapping("/login1")
	public String login1() {
		return "pages/admin/admin_index";
	}
	
	@GetMapping("/admin_types")
	public String aTypes(){
		return "pages/admin/admin_types";
	}
	
	@GetMapping("/admin_type_input1")
	public String aTypes1() {
		return "pages/admin/admin_type_input";
	}
	
	@GetMapping("/se")
	public String sear() {
		return "pages/search";
	}
}
