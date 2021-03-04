package com.buffll.controller;

import com.buffll.entity.Comment;
import com.buffll.entity.User;
import com.buffll.service.BlogService;
import com.buffll.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

/**
 * 评论功能的控制器
 * @author pxz
 * @create 2021-03-01 16:28
 */
@Controller
public class CommentController {
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private BlogService blogService;
	
	@Value("${comment.avatar}")
	private String avatar; //从配置文件中获取评论的默认头像
	
	/**
	 * 返回评论片段
	 * @param blogId
	 * @param model
	 * @return
	 */
	@GetMapping("/comments/{blogId}")
	public String comments(@PathVariable Long blogId, Model model) {
		model.addAttribute("comments", commentService.listCommentByBlogId(blogId));
		model.addAttribute("commentCount", commentService.getCommentCount(blogId));
		return "pages/blog :: commentList";
	}
	
	/**
	 * 提交评论信息,包括新增评论与回复某条评论
	 * @param comment
	 * @param session
	 * @return
	 */
	@PostMapping("/comments")
	public String commitComment(Comment comment, HttpSession session) {
		Long blogId = comment.getBlog().getId();
		comment.setBlog(blogService.getBlog(blogId));
		User user = (User) session.getAttribute("user");
		if (user != null) {
			comment.setAvatar(user.getAvatar());
			comment.setAdminComment(true);
		} else {
			comment.setAvatar(avatar);
		}
		commentService.saveComment(comment);
		return "redirect:/comments/" + blogId;
	}
}
