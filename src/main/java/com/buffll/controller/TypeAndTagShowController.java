package com.buffll.controller;

import com.buffll.entity.Tag;
import com.buffll.entity.Type;
import com.buffll.service.BlogService;
import com.buffll.service.TagService;
import com.buffll.service.TypeService;
import com.buffll.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * 分类和标签展示页的控制器
 * @author pxz
 * @create 2021-03-03 17:23
 */
@Controller
public class TypeAndTagShowController {
	@Autowired
	private TypeService typeService;
	
	@Autowired
	private BlogService blogService;
	
	@Autowired
	private TagService tagService;
	
	/**
	 * 根据不同的分类展示相应的博客
	 * @param pageable
	 * @param id 分类的id
	 * @param model
	 * @return
	 */
	@GetMapping("/types/{id}")
	public String types(@PageableDefault(size = 6, sort = {"updateTime"},
	                                     direction = Sort.Direction.DESC) Pageable pageable,
	                    @PathVariable Long id, Model model) {
		List<Type> types = typeService.listTypeTop(10000);
		if (id == 0) {
			//第一次加载分类页面时,并没有选择显示哪一个分类,所以在前台默认传一个0,这就代表第一次打开分类页面
			// 0 就代表第一次从导航栏进入分类页面
			//默认获取第一个分类
			id = types.get(0).getId();
		}
		BlogQuery blogQuery = new BlogQuery();
		blogQuery.setTypeId(id);
		model.addAttribute("types", types);
		model.addAttribute("page", blogService.listBlog(pageable, blogQuery));
		//activeTypeId 代表当前处于活跃状态(被选中)的分类,用于在前端页面进行高亮展示
		model.addAttribute("activeTypeId", id);
		return "pages/types";
	}
	
	/**
	 * 根据不同的标签展示相应的博客
	 * @param pageable
	 * @param id 标签id
	 * @param model
	 * @return
	 */
	@GetMapping("/tags/{id}")
	public String tags(@PageableDefault(size = 6, sort = {"updateTime"},
	                                    direction = Sort.Direction.DESC) Pageable pageable,
	                   @PathVariable Long id, Model model) {
		List<Tag> tags = tagService.listTagTop(10000);
		if (id == 0) {
			id = tags.get(0).getId();
		}
		model.addAttribute("tags", tags);
		model.addAttribute("page", blogService.listBlog(id, pageable));
		model.addAttribute("activeTagId", id);
		return "pages/tags";
	}
}
