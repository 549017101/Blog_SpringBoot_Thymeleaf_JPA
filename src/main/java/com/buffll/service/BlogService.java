package com.buffll.service;

import com.buffll.entity.Blog;
import com.buffll.vo.BlogQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
 * 博客功能的业务层接口
 * @author pxz
 * @create 2021-02-26 10:29
 */
public interface BlogService {
	/**
	 * 根据id查询博客
	 * @param id
	 * @return
	 */
	Blog getBlog(Long id);
	
	/**
	 * 获取并转换博客(将markdown语法转换为html),不改变数据库中的博客内容
	 * @param id
	 * @return
	 */
	Blog getAndConvert(Long id);
	
	/**
	 * 分页查询,查询一组详细的blog数据
	 * @param pageable
	 * @param blog
	 * @return
	 */
	Page<Blog> listBlog(Pageable pageable, BlogQuery blog);
	
	/**
	 * 分页查询,只传递pageable对象查询博客,无需详细查询
	 * @param pageable
	 * @return
	 */
	Page<Blog> listBlog(Pageable pageable);
	
	/**
	 * 根据标签查询博客(关联查询),只要有一个标签属于某个博客,就将它查询出来
	 * @param tagId
	 * @param pageable
	 * @return
	 */
	Page<Blog> listBlog(Long tagId, Pageable pageable);
	
	/**
	 * 分页查询,全局搜索功能
	 * @param query 查询条件
	 * @param pageable
	 * @return
	 */
	Page<Blog> listBlog(String query, Pageable pageable);
	
	/**
	 * 新增博客
	 * @param blog
	 * @return
	 */
	Blog saveBlog(Blog blog);
	
	/**
	 * 修改博客 (先根据id查询博客,在赋给它新的博客对象)
	 * @param id 要修改的博客id
	 * @param blog 新的博客对象
	 * @return
	 */
	Blog updateBlog(Long id, Blog blog);
	
	/**
	 * 删除博客
	 * @param id
	 */
	void deleteBlog(Long id);
	
	/**
	 * 查询博客,并返回一个list,用于展示在首页的推荐博客<br>
	 * 根据传入的size值来取数据列表的大小
	 * @param size 显示的数据条数
	 * @return
	 */
	List<Blog> listRecommendBlogTop(Integer size);
	
	/**
	 * 将博客按照条件归档,存放在Map中
	 * @return
	 */
	Map<String, List<Blog>> archiveBlog();
	
	/**
	 * 获取博客总条数
	 * @return
	 */
	Long countBlog();
}
