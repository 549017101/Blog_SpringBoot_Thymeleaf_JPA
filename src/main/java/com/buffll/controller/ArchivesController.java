package com.buffll.controller;

import com.buffll.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 归档功能的控制器
 * @author pxz
 * @create 2021-03-03 21:16
 */
@Controller
public class ArchivesController {
	@Autowired
	private BlogService blogService;
	
	/**
	 * 处理归档数据
	 * @param model
	 * @return
	 */
	@GetMapping("/archives")
	public String archives(Model model) {
		model.addAttribute("archiveMap", blogService.archiveBlog());
		model.addAttribute("blogCount", blogService.countBlog());
		return "pages/archives";
	}
}
