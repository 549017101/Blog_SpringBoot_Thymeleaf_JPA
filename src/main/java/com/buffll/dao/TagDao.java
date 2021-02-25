package com.buffll.dao;

import com.buffll.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

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
}
