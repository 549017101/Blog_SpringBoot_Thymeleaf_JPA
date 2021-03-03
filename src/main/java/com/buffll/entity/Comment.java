package com.buffll.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 评论的实体类
 * @author pxz
 * @create 2021-02-24 22:37
 */
@Entity
@Table(name = "t_comment")
public class Comment {
	@Id
	@GeneratedValue
	private Long id;
	
	/**
	 * 昵称
	 */
	private String nickname;
	
	/**
	 * 评论者邮箱
	 */
	private String email;
	
	/**
	 * 评论内容
	 */
	private String content;
	
	/**
	 * 评论者头像
	 */
	private String avatar;
	
	/**
	 * 创建时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;
	
	/**
	 * <p>评论所属的博客<p/>
	 * 一条评论只能包含一个博客
	 */
	@ManyToOne
	private Blog blog;
	
	/**
	 * <p>评论回复的子类对象<p/>
	 * 一个子评论对象只有一个离它最近的父评论对象
	 */
	@OneToMany(mappedBy = "parentComment")
	private List<Comment> replyComments = new ArrayList<>();
	
	/**
	 * <p>评论回复的父类对象<p/>
	 * 一个父评论对象可以有多个子评论对象,即可以对一条评论再接着评论
	 */
	@ManyToOne
	private Comment parentComment;
	
	/**
	 * 是否为管理员评论
	 */
	private Boolean adminComment;
	
	public Comment() {
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNickname() {
		return nickname;
	}
	
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getAvatar() {
		return avatar;
	}
	
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	
	public Date getCreateTime() {
		return createTime;
	}
	
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public Blog getBlog() {
		return blog;
	}
	
	public void setBlog(Blog blog) {
		this.blog = blog;
	}
	
	public List<Comment> getReplyComments() {
		return replyComments;
	}
	
	public void setReplyComments(List<Comment> replyComments) {
		this.replyComments = replyComments;
	}
	
	public Comment getParentComment() {
		return parentComment;
	}
	
	public void setParentComment(Comment parentComment) {
		this.parentComment = parentComment;
	}
	
	public Boolean getAdminComment() {
		return adminComment;
	}
	
	public void setAdminComment(Boolean adminComment) {
		this.adminComment = adminComment;
	}
}
