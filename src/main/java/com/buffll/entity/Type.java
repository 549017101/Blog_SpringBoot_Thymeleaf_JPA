package com.buffll.entity;

import javax.persistence.*;

import org.hibernate.validator.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

/**
 * 分类的实体类
 * @author pxz
 * @create 2021-02-24 22:32
 */
@Entity
@Table(name = "t_type")
public class Type {
	@Id
	@GeneratedValue
	private Long id;
	
	/**
	 * 分类的名字
	 */
	@NotBlank(message = "分类名称不能为空")
	private String name;
	
	/**
	 * <p>某个分类下的所有博客<p/>
	 * 一对多关系,属于被维护的一方
	 */
	@OneToMany(mappedBy = "type")
	private List<Blog> blogs = new ArrayList<>();
	
	public Type() {
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
	
	@Override
	public String toString() {
		return "Type{" +
				"id=" + id +
				", name='" + name + '\'' +
				", blogs=" + blogs +
				'}';
	}
}
