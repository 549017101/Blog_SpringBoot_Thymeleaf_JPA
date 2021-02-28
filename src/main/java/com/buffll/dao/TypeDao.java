package com.buffll.dao;

import com.buffll.entity.Type;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 分类功能的持久层接口
 * @author pxz
 * @create 2021-02-25 16:19
 */
public interface TypeDao extends JpaRepository<Type, Long> {
	/**
	 * 根据名称查找分类
	 * @param name
	 * @return
	 */
	Type findByName(String name);
	
	/**
	 * 按照每个分类所对应的博客数目进行排序
	 * @param pageable
	 * @return
	 */
	@Query("select t from Type t")
	List<Type> findTop(Pageable pageable);
}
