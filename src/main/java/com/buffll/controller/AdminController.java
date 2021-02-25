package com.buffll.controller;

import com.buffll.entity.User;
import com.buffll.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * 后台管理功能的控制器
 * @author pxz
 * @create 2021-02-25 11:34
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private UserService userService;
	
	/**
	 * 跳转到登录页面
	 * @return
	 */
	@GetMapping //已经配置过该控制器的基础路径,如果注解不写具体的路径,则就跳转到@RequestMapping指定的路径
	public String toLoginPage(){
		return "pages/admin/login";
	}
	
	/**
	 * 处理登录功能
	 * @param username 用户名
	 * @param password 密码
	 * @param session session,用于存放登录后的用户对象
	 * @param attributes 用于重定向之后还能带参数跳转的的工具类的对象
	 * @return
	 */
	@PostMapping("/login")
	public String login(@RequestParam String username,
	                    @RequestParam String password,
	                    HttpSession session,
	                    RedirectAttributes attributes){
		User user = userService.checkUser(username, password);
		if(user != null){
			//判断完毕后,将密码置为空,否则会显示在页面上,不安全
			user.setPassword(null);
			session.setAttribute("user",user);
			return "pages/admin/admin_index";
		}else {
			//RedirectAttributes 是Spring mvc 3.1版本之后出来的一个功能，专门用于重定向之后还能带参数跳转的的工具类
			//addFlashAttribute,这种方式用于达到重新向带参，而且能隐藏参数，其原理就是放到session中，session在跳到页面后马上移除对象,用这个方法给用户一个友好的提示
			attributes.addFlashAttribute("message","用户名或密码错误");
			return "redirect:/admin";
		}
	}
	
	/**
	 * 注销登录
	 * @return
	 */
	@GetMapping("/logout")
	public String logout(HttpSession session){
		//注销时将用户信息从session中删除
		session.removeAttribute("user");
		return "redirect:/admin";
	}
}
