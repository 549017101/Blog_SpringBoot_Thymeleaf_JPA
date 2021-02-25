package com.buffll.controller;

import com.buffll.entity.Type;
import com.buffll.service.TypeService;
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
 * 分类功能的控制器
 * @author pxz
 * @create 2021-02-25 16:29
 */
@Controller
@RequestMapping("/admin")
public class TypeController {
	@Autowired
	private TypeService typeService;
	
	/**
	 * 展示分页列表的页面
	 */
	@GetMapping("/types")
	public String list(@PageableDefault(size = 5, sort = {"id"},
	                                    direction = Sort.Direction.DESC) Pageable pageable, Model model) {
		//@PageableDefault注解用于设置分页查询的一些默认参数,如分页数,排序方式等等
		model.addAttribute("admin_types_page", typeService.listType(pageable));
		return "pages/admin/admin_types";
	}
	
	/**
	 * 跳转到添加分类页面
	 * @return
	 */
	@GetMapping("/types/input")
	public String input(Model model) {
		model.addAttribute("type",new Type());
		return "pages/admin/admin_type_input";
	}
	
	/**
	 * 跳转到编辑分类的页面
	 * @param id
	 * @param model
	 * @return
	 */
	@GetMapping("/types/{id}/input")
	public String editInput(@PathVariable Long id, Model model) {
		model.addAttribute("type", typeService.getType(id));
		return "pages/admin/admin_type_input";
	}
	
	/**
	 * 新增分类
	 */
	@PostMapping("/types")
	public String saveType(@Valid Type type, BindingResult result,RedirectAttributes attributes) {
		if(typeService.getTypeByName(type.getName()) != null){
			//已存在相同的类型名
			//添加自定义验证结果
			result.rejectValue("name","nameError","该分类已存在,不能重复添加");
		}
		if(result.hasErrors()){
		    return "pages/admin/admin_type_input";
		}
		if (typeService.saveType(type) == null) {
			//新增失败
			attributes.addFlashAttribute("message", "添加失败");
		} else {
			attributes.addFlashAttribute("message", "添加成功");
		}
		return "redirect:/admin/types";
	}
	
	/**
	 * 修改分类
	 * @param type
	 * @param result
	 * @param id
	 * @param attributes
	 * @return
	 */
	@PostMapping("/types/{id}")
	public String editType(@Valid Type type, BindingResult result,
	                       @PathVariable Long id, RedirectAttributes attributes) {
		if (typeService.getTypeByName(type.getName()) != null) {
			result.rejectValue("name", "nameError", "该分类已存在,不能重复添加");
		}
		if (result.hasErrors()) {
			return "pages/admin/admin_type_input";
		}
		
		if (typeService.updateType(id, type) == null) {
			attributes.addFlashAttribute("message", "更新失败");
		} else {
			attributes.addFlashAttribute("message", "更新成功");
		}
		return "redirect:/admin/types";
	}
	
	/**
	 * 删除分类
	 * @param id
	 * @param attributes
	 * @return
	 */
	@GetMapping("/types/{id}/delete")
	public String delete(@PathVariable Long id, RedirectAttributes attributes) {
		typeService.deleteType(id);
		attributes.addFlashAttribute("message", "删除成功");
		return "redirect:/admin/types";
	}
}
