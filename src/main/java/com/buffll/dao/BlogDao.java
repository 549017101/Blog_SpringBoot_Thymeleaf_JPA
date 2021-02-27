package com.buffll.dao;

import com.buffll.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 博客功能的持久层接口
 * @author pxz
 * @create 2021-02-26 10:33
 */
public interface BlogDao extends JpaRepository<Blog, Long>, JpaSpecificationExecutor<Blog> {
	//JpaSpecificationExecutor接口用于生成动态query,可以利用它进行复杂查询
}
