package com.buffll.vo;

/**
 * 由博客查询页面的查询条件单独封装而成的值对象
 * @author pxz
 * @create 2021-02-26 17:15
 */
public class BlogQuery {
	// vo 包: 主要用于传输数据,用于向页面返回数据,值对象一般放在这个包下
	
	/**
	 * 所查询的博客标题
	 */
	private String title;
	
	/**
	 * 所查询的博客所属分类的id
	 */
	private Long typeId;
	
	/**
	 * 所查询的博客是否选中推荐
	 */
	private boolean recommend;
	
	public BlogQuery() {
	}
	
	public BlogQuery(String title, Long typeId, boolean recommend) {
		this.title = title;
		this.typeId = typeId;
		this.recommend = recommend;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public Long getTypeId() {
		return typeId;
	}
	
	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}
	
	public boolean isRecommend() {
		return recommend;
	}
	
	public void setRecommend(boolean recommend) {
		this.recommend = recommend;
	}
	
	@Override
	public String toString() {
		return "BlogQuery{" +
				"title='" + title + '\'' +
				", typeId=" + typeId +
				", recommend=" + recommend +
				'}';
	}
}
