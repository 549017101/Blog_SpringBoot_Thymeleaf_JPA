package com.buffll.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 博客核心实体类
 * @author pxz
 * @create 2021-02-24 9:42
 */
@Entity
@Table(name = "t_blog")
public class Blog {
	/**
	 * 主键
	 */
	@Id
	@GeneratedValue  //主键生成策略
	private Long id;
	
	/**
	 * 博客标题
	 */
	private String title;
	
	/**
	 * 博客内容
	 */
	@Basic(fetch = FetchType.LAZY) //懒加载,节省资源
	@Lob //指定持久属性或字段应作为大对象持久保存到数据库支持的大对象类型
	private String content;
	
	/**
	 * 首图
	 * <p>取值为图片地址或64位图片编码</p>
	 */
	private String firstPicture;
	
	/**
	 * 博客标记
	 */
	private String flag;
	
	/**
	 * 浏览次数
	 */
	private Integer views;
	
	/**
	 * 是否开启赞赏
	 */
	private Boolean appreciation;
	
	/**
	 * 转载声明是否开启
	 */
	private Boolean shareStatement;
	
	/**
	 * 是否开启评论
	 */
	private Boolean commentabled;
	
	/**
	 * 是否发布
	 */
	private Boolean published;
	
	/**
	 * 是否推荐
	 */
	private Boolean recommend;
	
	/**
	 * 创建时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;
	
	/**
	 * 更新时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateTime;
	
	/**
	 * <p>博客分类<p/>
	 * 多对一关系<br>
	 * 一个类别可以有多个博客,但一个博客只能属于一个类别
	 */
	@ManyToOne
	private Type type;
	
	/**
	 * <p>博客所属的标签<p/>
	 * 多对多关系
	 */
	@ManyToMany(cascade = {CascadeType.PERSIST})
	private List<Tag> tags = new ArrayList<>();
	
	/**
	 * <p>博客所属的用户<p/>
	 * 多对一关系
	 */
	@ManyToOne
	private User user;
	
	/**
	 * <p>博客所包含的评论<p/>
	 * 一对多关系
	 */
	@OneToMany(mappedBy = "blog")
	private List<Comment> comments = new ArrayList<>();
	
	/**
	 * 博客描述
	 */
	private String description;
	
	/**
	 * 博客所属的标签的Id<br>
	 * 这个属性不需要保存在数据库中,所以用@Transient注解标注
	 */
	@Transient
	private String tagIds;
	
	public Blog() {}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getFirstPicture() {
		return firstPicture;
	}
	
	public void setFirstPicture(String firstPicture) {
		this.firstPicture = firstPicture;
	}
	
	public String getFlag() {
		return flag;
	}
	
	public void setFlag(String flag) {
		this.flag = flag;
	}
	
	public Integer getViews() {
		return views;
	}
	
	public void setViews(Integer views) {
		this.views = views;
	}
	
	public Boolean getAppreciation() {
		return appreciation;
	}
	
	public void setAppreciation(Boolean appreciation) {
		this.appreciation = appreciation;
	}
	
	public Boolean getShareStatement() {
		return shareStatement;
	}
	
	public void setShareStatement(Boolean shareStatement) {
		this.shareStatement = shareStatement;
	}
	
	public Boolean getCommentabled() {
		return commentabled;
	}
	
	public void setCommentabled(Boolean commentabled) {
		this.commentabled = commentabled;
	}
	
	public Boolean getPublished() {
		return published;
	}
	
	public void setPublished(Boolean published) {
		this.published = published;
	}
	
	public Boolean getRecommend() {
		return recommend;
	}
	
	public void setRecommend(Boolean recommend) {
		this.recommend = recommend;
	}
	
	public Date getCreateTime() {
		return createTime;
	}
	
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public Date getUpdateTime() {
		return updateTime;
	}
	
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	public Type getType() {
		return type;
	}
	
	public void setType(Type type) {
		this.type = type;
	}
	
	public List<Tag> getTags() {
		return tags;
	}
	
	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public List<Comment> getComments() {
		return comments;
	}
	
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	
	public String getTagIds() {
		return tagIds;
	}
	
	public void setTagIds(String tagIds) {
		this.tagIds = tagIds;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
}
