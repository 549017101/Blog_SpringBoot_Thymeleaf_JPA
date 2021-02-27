package com.buffll.service;

import com.buffll.entity.Blog;
import com.buffll.vo.BlogQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
	 * 分页查询,查询一组数据
	 * @param pageable
	 * @param blog
	 * @return
	 */
	Page<Blog> listBlog(Pageable pageable, BlogQuery blog);
	
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
}
