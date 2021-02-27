package com.buffll.service.impl;

import com.buffll.dao.TagDao;
import com.buffll.entity.Tag;
import com.buffll.exception.NotFoundException;
import com.buffll.service.TagService;
import com.buffll.utils.MyUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author pxz
 * @create 2021-02-25 23:57
 */
@Service
public class TagServiceImpl implements TagService {
	@Autowired
	private TagDao tagDao;
	
	@Transactional
	@Override
	public Tag saveTag(Tag tag) {
		return tagDao.save(tag);
	}
	
	@Transactional
	@Override
	public Tag getTag(Long id) {
		return tagDao.getOne(id);
	}
	
	@Override
	public Tag getTagByName(String name) {
		return tagDao.findByName(name);
	}
	
	@Transactional
	@Override
	public Page<Tag> listTag(Pageable pageable) {
		return tagDao.findAll(pageable);
	}
	
	@Transactional
	@Override
	public Tag updateTag(Long id, Tag tag) {
		Tag t = tagDao.getOne(id);
		if (t == null) {
			throw new NotFoundException("该标签不存在");
		}
		BeanUtils.copyProperties(tag, t);
		return tagDao.save(t);
	}
	
	@Transactional
	@Override
	public void deleteTag(Long id) {
		tagDao.deleteById(id);
	}
	
	@Override
	public List<Tag> listTag() {
		return tagDao.findAll();
	}
	
	@Override
	public List<Tag> listTag(String ids) {
		//此时参数传入的ids是一个字符串,是1,2,3,4,5....这种形式的字符串,需要将这个字符串转换为集合
		return tagDao.findAllById(MyUtils.convertToList(ids));
	}
}
