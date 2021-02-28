package com.buffll.dao;

import com.buffll.entity.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 标签功能的持久层接口
 * @author pxz
 * @create 2021-02-25 23:52
 */
public interface TagDao extends JpaRepository<Tag, Long> {
	/**
	 * 根据名称查找标签
	 * @param name 标签名
	 * @return
	 */
	Tag findByName(String name);
	
	/**
	 * 按照每个标签所对应的博客数目进行排序
	 * @param pageable
	 * @return
	 */
	@Query("select t from Tag t")
	List<Tag> findTop(Pageable pageable);
}
