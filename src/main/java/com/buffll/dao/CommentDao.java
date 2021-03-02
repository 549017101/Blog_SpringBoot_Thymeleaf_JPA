package com.buffll.dao;

import com.buffll.entity.Comment;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 评论功能的持久层接口
 * @author pxz
 * @create 2021-03-01 16:31
 */
public interface CommentDao extends JpaRepository<Comment, Long> {
	/**
	 * 根据博客id查询评论信息,并根据创建时间进行排序,同时获取父级评论<br>
	 * 父级评论的 ParentCommentId值都为空
	 * @param blogId
	 * @param sort
	 * @return
	 */
	List<Comment> findByBlogIdAndParentCommentNull(Long blogId, Sort sort);
}
