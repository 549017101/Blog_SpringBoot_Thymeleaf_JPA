package com.buffll.service.impl;

import com.buffll.dao.BlogDao;
import com.buffll.entity.Blog;
import com.buffll.entity.Type;
import com.buffll.exception.NotFoundException;
import com.buffll.service.BlogService;
import com.buffll.utils.MarkdownUtils;
import com.buffll.utils.MyBeanUtils;
import com.buffll.vo.BlogQuery;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
	public Blog getAndConvert(Long id) {
		Blog blog = blogDao.getOne(id);
		if (blog == null) {
			throw new NotFoundException("该博客不存在");
		}
		Blog b = new Blog();
		//将获取到的blog复制一份,对这个对象的副本进行格式转换,否则的话会改变数据库中的内容
		BeanUtils.copyProperties(blog, b);
		String content = b.getContent();
		b.setContent(MarkdownUtils.markdownToHtmlExtensions(content));
		return b;
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
	
	@Override
	public Page<Blog> listBlog(Pageable pageable) {
		return blogDao.findAll(pageable);
	}
	
	@Override
	public Page<Blog> listBlog(String query, Pageable pageable) {
		return blogDao.findByQuery(query, pageable);
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
		if (b != null) {
			BeanUtils.copyProperties(blog, b, MyBeanUtils.getNullPropertyNames(blog));
			return blogDao.save(b);
		}else{
			throw new NotFoundException("该博客不存在");
		}
	}
	
	@Transactional
	@Override
	public void deleteBlog(Long id) {
		blogDao.deleteById(id);
	}
	
	@Override
	public List<Blog> listRecommendBlogTop(Integer size) {
		Sort sort = Sort.by(Sort.Direction.DESC, "updateTime");
		Pageable pageable = PageRequest.of(0, size, sort);
		return blogDao.findTop(pageable);
	}
}
