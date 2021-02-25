package com.buffll.service.impl;

import com.buffll.dao.TagDao;
import com.buffll.entity.Tag;
import com.buffll.exception.NotFoundException;
import com.buffll.service.TagService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
