package com.buffll.service;

import com.buffll.entity.Comment;

import java.util.List;

/**
 * 评论功能的业务层接口
 * @author pxz
 * @create 2021-03-01 16:30
 */
public interface CommentService {
	/**
	 * 通过博客id获取评论列表
	 * @param blogId
	 * @return
	 */
	List<Comment> listCommentByBlogId(Long blogId);
	
	/**
	 * 保存评论信息
	 * @param comment
	 * @return
	 */
	Comment saveComment(Comment comment);
	
	/**
	 * 根据博客id查询当前博客的总评论数
	 * @param blogId
	 */
	Integer getCommentCount(Long blogId);
}
