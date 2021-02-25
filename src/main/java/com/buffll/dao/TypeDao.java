package com.buffll.dao;

import com.buffll.entity.Type;
import org.springframework.data.jpa.repository.JpaRepository;

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
}
