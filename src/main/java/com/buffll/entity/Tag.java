package com.buffll.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 标签的实体类
 * @author pxz
 * @create 2021-02-24 22:34
 */
@Entity
@Table(name = "t_tag")
public class Tag {
	@Id
	@GeneratedValue
	private Long id;
	
	/**
	 * 标签名称
	 */
	private String name;
	
	/**
	 * <p>某个标签下的所有博客<p/>
	 * 多对多关系,属于被维护的一方
	 */
	@ManyToMany(mappedBy = "tags")
	private List<Blog> blogs = new ArrayList<>();
	
	public Tag() {
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public List<Blog> getBlogs() {
		return blogs;
	}
	
	public void setBlogs(List<Blog> blogs) {
		this.blogs = blogs;
	}
}
