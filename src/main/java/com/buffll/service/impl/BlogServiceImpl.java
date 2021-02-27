package com.buffll.service.impl;

import com.buffll.dao.BlogDao;
import com.buffll.entity.Blog;
import com.buffll.entity.Type;
import com.buffll.exception.NotFoundException;
import com.buffll.service.BlogService;
import com.buffll.vo.BlogQuery;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author pxz
 * @create 2021-02-26 10:32
 */
@Service
public class BlogServiceImpl implements BlogService {
	@Autowired
	private BlogDao blogDao;
	
	@Override
	public Blog getBlog(Long id) {
		return blogDao.getOne(id);
	}
	
	@Override
	public Page<Blog> listBlog(Pageable pageable, BlogQuery blog) {
		return blogDao.findAll(new Specification<Blog>() {
			@Override
			public Predicate toPredicate(Root<Blog> root,
			                             CriteriaQuery<?> criteriaQuery,
			                             CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicates = new ArrayList<>();
				if (!"".equals(blog.getTitle()) && blog.getTitle() != null) {
					//博客标题非空
					predicates.add(criteriaBuilder
							               .like(root.<String>get("title"), "%" + blog.getTitle() + "%"));
				}
				if (blog.getTypeId() != null) {
					//博客所属的分类非空
					predicates.add(criteriaBuilder
							               .equal(root.<Type>get("type").get("id"), blog.getTypeId()));
				}
				if (blog.isRecommend()) {
					//是否推荐
					predicates.add(criteriaBuilder
							               .equal(root.<Boolean>get("recommend"), blog.isRecommend()));
				}
				criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()]));
				return null;
			}
		}, pageable);
	}
	
	@Transactional
	@Override
	public Blog saveBlog(Blog blog) {
		if (blog.getId() == null) {
			//id为空表示新增,否则是修改
			blog.setCreateTime(new Date());
			blog.setUpdateTime(new Date());
			blog.setViews(0);
			if("".equals(blog.getFlag()) || blog.getFlag() == null){
				//设置默认为原创
				blog.setFlag("原创");
			}
		}
		return blogDao.save(blog);
	}
	
	@Transactional
	@Override
	public Blog updateBlog(Long id, Blog blog) {
		Blog b = blogDao.getOne(id);
		
		//将创建时间保存,形参blog中是个空对象,给它赋值后只有更新时间,创建时间为null,所以后面将创建时间单独设置即可
		Date createTime = b.getCreateTime();
		Integer views = b.getViews();
		
		if (b == null) {
			throw new NotFoundException("该博客不存在");
		}

		BeanUtils.copyProperties(blog, b);
		//BeanUtils.copyProperties(blog, b, MyBeanUtils.getNullPropertyNames(blog));
		b.setUpdateTime(new Date());
		b.setCreateTime(createTime);
		b.setFlag(blog.getFlag());
		b.setViews(views);
		
		return blogDao.save(b);
	}
	
	@Transactional
	@Override
	public void deleteBlog(Long id) {
		blogDao.deleteById(id);
	}
}
