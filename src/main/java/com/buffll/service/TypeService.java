package com.buffll.service;

import com.buffll.entity.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 分类功能的业务层接口
 * @author pxz
 * @create 2021-02-25 16:05
 */
public interface TypeService {
	/**
	 * 保存分类
	 * @param type
	 * @return
	 */
	Type saveType(Type type);
	
	/**
	 * 根据id查询分类
	 * @param id
	 * @return
	 */
	Type getType(Long id);
	
	/**
	 * 分页查询
	 * @param pageable
	 * @return
	 */
	Page<Type> listType(Pageable pageable);
	
	/**
	 * 获取所有分类(非分页查询)
	 */
	List<Type> listType();
	
	/**
	 * 修改分类
	 * @param id 要修改的分类的id
	 * @param type 修改后的分类对象
	 * @return
	 */
	Type updateType(Long id, Type type);
	
	/**
	 * 根据id删除分类
	 * @param id
	 */
	void deleteType(Long id);
	
	/**
	 * 根据名称查询分类
	 * @param name
	 * @return
	 */
	Type getTypeByName(String name);
}
