package com.buffll.controller;

import com.buffll.entity.Blog;
import com.buffll.entity.User;
import com.buffll.service.BlogService;
import com.buffll.service.TagService;
import com.buffll.service.TypeService;
import com.buffll.utils.MyUtils;
import com.buffll.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * 博客功能的控制器
 * @author pxz
 * @create 2021-02-25 15:22
 */
@Controller
@RequestMapping("/admin")
public class BlogController {
	private static final String INPUT = "pages/admin/admin_blogs_input";
	private static final String LIST = "pages/admin/admin_blogs";
	private static final String REDIRECT_LIST = "redirect:/admin/blogs";
	
	@Autowired
	private BlogService blogService;
	
	@Autowired
	private TypeService typeService;
	
	@Autowired
	private TagService tagService;
	
	/**
	 * 跳转到博客管理页面
	 * @return
	 */
	@GetMapping("/blogs")
	public String blogs(@PageableDefault(size = 5, sort = {"updateTime"},direction = Sort.Direction.DESC) Pageable pageable, BlogQuery blog, Model model){
		model.addAttribute("types",typeService.listType());
		model.addAttribute("admin_blogs_page",blogService.listBlog(pageable,blog));
		return LIST;
	}
	
	/**
	 * 搜索博客
	 * @param pageable
	 * @param blog
	 * @param model
	 * @return
	 */
	@PostMapping("/blogs/search")
	public String search(@PageableDefault(size = 5, sort = {"updateTime"},direction = Sort.Direction.DESC) Pageable pageable, BlogQuery blog, Model model) {
		model.addAttribute("admin_blogs_page", blogService.listBlog(pageable, blog));
		return "pages/admin/admin_blogs :: blogList";
	}
	
	/**
	 * 初始化分类和标签
	 * @param model
	 */
	private void setTypeAndTag(Model model) {
		model.addAttribute("types", typeService.listType());
		model.addAttribute("tags", tagService.listTag());
	}
	
	/**
	 * 跳转到新增博客页面
	 * @return
	 */
	@GetMapping("/blogs/input")
	public String input(Model model){
		setTypeAndTag(model);
		model.addAttribute("blog", new Blog());
		return INPUT;
	}
	
	/**
	 * 跳转到修改博客页面
	 * @return
	 */
	@GetMapping("/blogs/{id}/input")
	public String editBlog(@PathVariable Long id, Model model) {
		setTypeAndTag(model);
		Blog blog = blogService.getBlog(id);
		
		String tagIds = MyUtils.tagsToIds(blog.getTags(),blog);
		blog.setTagIds(tagIds);
		
		model.addAttribute("blog", blog);
		return INPUT;
	}
	
	/**
	 * 博客新增修改二合一方法
	 * @param blog
	 * @param attributes
	 * @param session
	 * @return
	 */
	@PostMapping("/blogs")
	public String post(Blog blog, RedirectAttributes attributes, HttpSession session) {
		blog.setUser((User) session.getAttribute("user"));
		blog.setType(typeService.getType(blog.getType().getId()));
		blog.setTags(tagService.listTag(blog.getTagIds()));
		
		Blog b;
		if (blog.getId() == null) {
			b = blogService.saveBlog(blog);
		} else {
			b = blogService.updateBlog(blog.getId(), blog);
		}
		
		if (b == null) {
			attributes.addFlashAttribute("message", "操作失败");
		} else {
			attributes.addFlashAttribute("message", "操作成功");
		}
		return REDIRECT_LIST;
	}
	
	/**
	 * 删除博客
	 * @param id
	 * @param attributes
	 * @return
	 */
	@GetMapping("/blogs/{id}/delete")
	public String delete(@PathVariable Long id, RedirectAttributes attributes) {
		blogService.deleteBlog(id);
		attributes.addFlashAttribute("message", "删除成功");
		return REDIRECT_LIST;
	}
}