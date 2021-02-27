package com.buffll.service;

import com.buffll.entity.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 标签功能的业务层接口
 * @author pxz
 * @create 2021-02-25 23:55
 */
public interface TagService {
	/**
	 * 保存标签
	 * @param type
	 * @return
	 */
	Tag saveTag(Tag type);
	
	/**
	 * 根据id查询标签
	 * @param id
	 * @return
	 */
	Tag getTag(Long id);
	
	/**
	 * 根据名称查询标签
	 * @param name
	 * @return
	 */
	Tag getTagByName(String name);
	
	/**
	 * 分页查询
	 * @param pageable
	 * @return
	 */
	Page<Tag> listTag(Pageable pageable);
	
	/**
	 * 修改标签
	 * @param id
	 * @param type
	 * @return
	 */
	Tag updateTag(Long id, Tag type);
	
	/**
	 * 根据id删除分类
	 * @param id
	 */
	void deleteTag(Long id);
	
	/**
	 * 获取所有标签(非分页)
	 * @return
	 */
	List<Tag> listTag();
	
	/**
	 * 通过多个id值的集合获取标签
	 * @param ids
	 * @return
	 */
	List<Tag> listTag(String ids);
}
