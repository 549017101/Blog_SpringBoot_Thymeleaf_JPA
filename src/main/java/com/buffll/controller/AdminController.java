package com.buffll.controller;

import com.buffll.dao.UserDao;
import com.buffll.entity.User;
import com.buffll.service.UserService;
import com.buffll.utils.Md5Utils;
import com.buffll.utils.ValidateImageCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

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
	
	@Autowired
	private UserDao userDao;
	
	/**
	 * 跳转到登录页面
	 * @return
	 */
	@GetMapping //已经配置过该控制器的基础路径,如果注解不写具体的路径,则就跳转到@RequestMapping指定的路径
	public String toLoginPage(){
		return "pages/admin/login";
	}
	
	/**
	 * 跳转到后台管理首页(只有在登录成功的情况下才能跳转)
	 * @return
	 */
	@GetMapping("index")
	public String toAdminPage(){
		return "pages/admin/admin_index";
	}
	
	/**
	 * 生成图片验证码
	 */
	@GetMapping("/captcha/code")
	public void getImage(HttpSession session, HttpServletResponse response) throws IOException {
		//产生验证码字符串
		String securityCode = ValidateImageCodeUtils.getSecurityCode();
		//将验证码字符串转换为验证码图片
		BufferedImage image = ValidateImageCodeUtils.createImage(securityCode);
		//将生成的验证码存入session,便于校验
		session.setAttribute("securityCode", securityCode);
		
		//将验证码图片通过流的方式输出到前端
		ServletOutputStream outputStream = response.getOutputStream();
		ImageIO.write(image, "png", outputStream);
	}
	
	/**
	 * 处理登录功能
	 * @param username 用户名
	 * @param password 密码
	 * @param scode 用户输入的验证码
	 * @param session session,用于存放登录后的用户对象
	 * @param attributes 用于重定向之后还能带参数跳转的的工具类的对象
	 * @return
	 */
	@PostMapping("/login")
	public String login(@RequestParam String username,
	                    @RequestParam String password,
	                    @RequestParam String scode,
	                    HttpSession session,
	                    RedirectAttributes attributes){
		String code = (String) session.getAttribute("securityCode");
		if(code.equalsIgnoreCase(scode)){
			User user = userService.checkUser(username, password);
			if (user != null) {
				//判断完毕后,将密码置为空,否则会显示在页面上,不安全
				user.setPassword(null);
				session.setAttribute("user", user);
				return "redirect:/admin/index";
			} else {
				//RedirectAttributes 是Spring mvc 3.1版本之后出来的一个功能，专门用于重定向之后还能带参数跳转的的工具类
				//addFlashAttribute,这种方式用于达到重新向带参，而且能隐藏参数，其原理就是放到session中，session在跳到页面后马上移除对象,用这个方法给用户一个友好的提示
				attributes.addFlashAttribute("message", "用户名或密码错误");
				return "redirect:/admin";
			}
		}else {
			attributes.addFlashAttribute("message", "验证码输入有误");
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
	
	/**
	 * 修改密码
	 * @param username 用户名
	 * @param oldpwd 旧密码
	 * @param newpwd 新密码
	 * @param attributes
	 * @return
	 */
	@PostMapping("/change")
	public String changePassword(@RequestParam String username, @RequestParam String oldpwd, @RequestParam String newpwd, RedirectAttributes attributes) {
		User user = userDao.findByUsernameAndPassword(username, Md5Utils.code(oldpwd));
		if(user != null){
			userService.changePassword(username, oldpwd, newpwd);
			attributes.addFlashAttribute("message", "修改成功");
			return "redirect:/admin/logout";
		}else{
			attributes.addFlashAttribute("message", "修改失败");
			return "redirect:/admin/index";
		}
	}
}
