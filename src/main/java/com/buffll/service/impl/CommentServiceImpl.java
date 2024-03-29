package com.buffll.service.impl;

import com.buffll.dao.CommentDao;
import com.buffll.entity.Comment;
import com.buffll.service.CommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author pxz
 * @create 2021-03-01 16:32
 */
@Service
public class CommentServiceImpl implements CommentService {
	@Autowired
	private CommentDao commentDao;
	
	/**
	 * 存放迭代找出的所有子代的集合
	 */
	private List<Comment> tempReplys = new ArrayList<>();
	
	/**
	 * 循环每个顶级的评论节点
	 * @param comments
	 * @return
	 */
	private List<Comment> eachComment(List<Comment> comments) {
		List<Comment> commentsView = new ArrayList<>();
		for (Comment comment : comments) {
			Comment c = new Comment();
			//创建一个新集合,将原来的数据copy进新集合中,避免对数据库的数据进行更改
			BeanUtils.copyProperties(comment, c);
			commentsView.add(c);
		}
		//合并评论的各层子代到第一级子代集合中
		combineChildren(commentsView);
		return commentsView;
	}
	
	/**
	 * @param comments root根节点，blog不为空的对象集合
	 * @return
	 */
	private void combineChildren(List<Comment> comments) {
		
		for (Comment comment : comments) {
			List<Comment> replys1 = comment.getReplyComments();
			for (Comment reply1 : replys1) {
				//循环迭代，找出子代，存放在tempReplys中
				recursively(reply1);
			}
			//修改顶级节点的reply集合为迭代处理后的集合
			comment.setReplyComments(tempReplys);
			//清除临时存放区
			tempReplys = new ArrayList<>();
		}
	}
	
	/**
	 * 递归迭代
	 * @param comment 被迭代的对象
	 * @return
	 */
	private void recursively(Comment comment) {
		//顶节点添加到临时存放集合
		tempReplys.add(comment);
		if (comment.getReplyComments().size() > 0) {
			List<Comment> replys = comment.getReplyComments();
			for (Comment reply : replys) {
				tempReplys.add(reply);
				if (reply.getReplyComments().size() > 0) {
					recursively(reply);
				}
			}
		}
	}
	
	@Override
	public List<Comment> listCommentByBlogId(Long blogId) {
		//根据创建时间对评论进行顺序排序
			Sort sort = Sort.by(Sort.Direction.ASC, "createTime");
		List<Comment> comments = commentDao.findByBlogIdAndParentCommentNull(blogId, sort);
		return eachComment(comments);
	}
	
	@Transactional
	@Override
	public Comment saveComment(Comment comment) {
		Long parentCommentId = comment.getParentComment().getId();
		if (parentCommentId != -1) {
			//若父级id不等于-1,则说明当前评论是对于某一条评论的回复,被回复的评论就是父级
			comment.setParentComment(commentDao.getOne(parentCommentId));
		} else {
			comment.setParentComment(null);
		}
		comment.setCreateTime(new Date());
		return commentDao.save(comment);
	}
	
	@Override
	public Integer getCommentCount(Long blogId) {
		return commentDao.countByComment(blogId);
	}
}
