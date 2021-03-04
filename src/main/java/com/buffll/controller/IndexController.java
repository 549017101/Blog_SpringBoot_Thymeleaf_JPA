package com.buffll.controller;

import com.buffll.service.BlogService;
import com.buffll.service.TagService;
import com.buffll.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author pxz
 * @create 2021-02-28 14:06
 */
@Controller
public class IndexController {
	@Autowired
	private BlogService blogService;
	
	@Autowired
	private TypeService typeService;
	
	@Autowired
	private TagService tagService;
	
	/**
	 * 跳转到项目首页
	 * @return
	 */
	@GetMapping("/")
	public String toIndex(@PageableDefault(size = 6, sort = {"updateTime"},direction = Sort.Direction.DESC) Pageable pageable,Model model) {
		//将查询到的分页数据放在model中(这里查出的blog是简单的数据,只需要标题,描述,浏览量等等,不需要博客的内容)
		model.addAttribute("index_blogs_page", blogService.listBlog(pageable));
		model.addAttribute("index_types",typeService.listTypeTop(6));
		model.addAttribute("index_tags", tagService.listTagTop(8));
		model.addAttribute("index_recommendBlogs", blogService.listRecommendBlogTop(8));
		return "index";
	}
	
	/**
	 * 搜索功能
	 * @param pageable
	 * @param model
	 * @param query
	 * @return
	 */
	@PostMapping("/search")
	public String search(@PageableDefault(size = 6, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable, Model model,@RequestParam String query){
		model.addAttribute("search_blogs_page", blogService.listBlog("%" + query + "%", pageable));
		model.addAttribute("query", query);
		return "pages/search";
	}
	
	/**
	 * 搜索后的翻页功能
	 * @param query
	 * @param pageable
	 * @param model
	 * @return
	 */
	@GetMapping("/search")
	public String searchPage(@RequestParam(value = "query") String query ,@PageableDefault(size = 6, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable, Model model ){
		model.addAttribute("search_blogs_page", blogService.listBlog("%" + query + "%", pageable));
		model.addAttribute("query", query);
		return "pages/search";
	}
	
	/**
	 * 跳转到博客详情页面,并根据博客id查询相对应的博客
	 * @param id
	 * @param model
	 * @return
	 */
	@GetMapping("/blog/{id}")
	public String blog(@PathVariable Long id, Model model) {
		model.addAttribute("blog", blogService.getAndConvert(id));
		return "pages/blog";
	}
	
	/**
	 * 跳转到关于我页面
	 * @return
	 */
	@GetMapping("/about")
	public String about(){
		return "pages/about";
	}
	
	/**
	 * 底部的推荐博客(取最新的三条数据)
	 * @return
	 */
	@GetMapping("/footer/newblog")
	public String footerNewBlogs(Model model) {
		model.addAttribute("newBlogs", blogService.listRecommendBlogTop(3));
		return "commons/fragments :: newblogList";
	}
	
	/**
	 * 跳转到分类页面
	 * @return
	 */
	@GetMapping("/types")
	public String toTypes() {
		return "redirect:/types/0";
	}
	
	/**
	 * 跳转到标签页面
	 * @return
	 */
	//@GetMapping("/tags")
	public String toTags() {
		return "redirect:/tags/0";
	}
}
