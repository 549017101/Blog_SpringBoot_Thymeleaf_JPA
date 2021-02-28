package com.buffll.service.impl;

import com.buffll.dao.TypeDao;
import com.buffll.entity.Type;
import com.buffll.exception.NotFoundException;
import com.buffll.service.TypeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author pxz
 * @create 2021-02-25 16:18
 */
@Service
public class TypeServiceImpl implements TypeService {
	@Autowired
	private TypeDao typeDao;
	
	@Override
	@Transactional
	public Type saveType(Type type) {
		return typeDao.save(type);
	}
	
	@Override
	@Transactional
	public Type getType(Long id) {
		return typeDao.getOne(id);
	}
	
	@Override
	@Transactional
	public Page<Type> listType(Pageable pageable) {
		return typeDao.findAll(pageable);
	}
	
	@Override
	public List<Type> listType() {
		return typeDao.findAll();
	}
	
	@Override
	@Transactional
	public Type updateType(Long id, Type type) {
		Type t = typeDao.getOne(id);
		if(t == null){
		    throw new NotFoundException("该分类不存在!");
		}
		//copyProperties(source,target):将给定源bean的属性值复制到目标bean中
		BeanUtils.copyProperties(type,t);
		return typeDao.save(t);
	}
	
	@Override
	@Transactional
	public void deleteType(Long id) {
		typeDao.deleteById(id);
	}
	
	@Override
	public Type getTypeByName(String name) {
		return typeDao.findByName(name);
	}
	
	@Override
	public List<Type> listTypeTop(Integer size) {
		//springboot2.2.1（含）以上的版本Sort已经不能再实例化了，构造方法已经是私有的了！
		//可以改用Sort.by获得Sort对象 下面的PageRequest也一样,需要改为.of()方法创建对象
		Sort sort = Sort.by(Sort.Direction.DESC, "blogs.size");
		Pageable pageable = PageRequest.of(0, size, sort);
		return typeDao.findTop(pageable);
	}
}
