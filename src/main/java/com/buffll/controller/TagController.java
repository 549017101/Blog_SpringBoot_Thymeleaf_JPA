package com.buffll.controller;

import com.buffll.entity.Tag;
import com.buffll.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * 标签功能的控制器
 * @author pxz
 * @create 2021-02-26 0:01
 */
@Controller
@RequestMapping("/admin")
public class TagController {
	@Autowired
	private TagService tagService;
	
	/**
	 * 跳转到标签列表页面
	 */
	@GetMapping("/tags")
	public String tags(@PageableDefault(size = 5, sort = {"id"}, direction = Sort.Direction.DESC)
			                   Pageable pageable, Model model) {
		model.addAttribute("admin_tags_page", tagService.listTag(pageable));
		return "pages/admin/admin_tags";
	}
	
	/**
	 * 跳转到添加标签页面
	 */
	@GetMapping("/tags/input")
	public String input(Model model) {
		model.addAttribute("tag", new Tag());
		return "pages/admin/admin_tags_input";
	}
	
	/**
	 * 跳转到编辑标签的页面
	 * @param id
	 * @param model
	 * @return
	 */
	@GetMapping("/tags/{id}/input")
	public String editInput(@PathVariable Long id, Model model) {
		model.addAttribute("tag", tagService.getTag(id));
		return "pages/admin/admin_tags_input";
	}
	
	/**
	 * 新增标签
	 * @param tag
	 * @param result
	 * @param attributes
	 * @return
	 */
	@PostMapping("/tags")
	public String saveTag(@Valid Tag tag, BindingResult result, RedirectAttributes attributes) {
		if (tagService.getTagByName(tag.getName()) != null) {
			result.rejectValue("name", "nameError", "该标签已存在,不能重复添加");
		}
		if (result.hasErrors()) {
			return "pages/admin/admin_tags_input";
		}
		if (tagService.saveTag(tag) == null) {
			attributes.addFlashAttribute("message", "添加失败");
		} else {
			attributes.addFlashAttribute("message", "添加成功");
		}
		return "redirect:/admin/tags";
	}
	
	/**
	 * 编辑标签
	 * @param tag
	 * @param result
	 * @param id
	 * @param attributes
	 * @return
	 */
	@PostMapping("/tags/{id}")
	public String editTag(@Valid Tag tag, BindingResult result,
	                      @PathVariable Long id, RedirectAttributes attributes) {
		if (tagService.getTagByName(tag.getName()) != null) {
			result.rejectValue("name", "nameError", "该标签已存在,不能重复添加");
		}
		if (result.hasErrors()) {
			return "pages/admin/admin_tags_input";
		}
		if (tagService.updateTag(id, tag) == null) {
			attributes.addFlashAttribute("message", "更新失败");
		} else {
			attributes.addFlashAttribute("message", "更新成功");
		}
		return "redirect:/admin/tags";
	}
	
	/**
	 * 删除标签
	 * @param id
	 * @param attributes
	 * @return
	 */
	@GetMapping("/tags/{id}/delete")
	public String delete(@PathVariable Long id, RedirectAttributes attributes) {
		tagService.deleteTag(id);
		attributes.addFlashAttribute("message", "删除成功");
		return "redirect:/admin/tags";
	}
}
