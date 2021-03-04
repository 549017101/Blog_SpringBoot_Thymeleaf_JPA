package com.buffll.dao;

import com.buffll.entity.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 博客功能的持久层接口
 * @author pxz
 * @create 2021-02-26 10:33
 */
public interface BlogDao extends JpaRepository<Blog, Long>, JpaSpecificationExecutor<Blog> {
	//JpaSpecificationExecutor接口用于生成动态query,可以利用它进行复杂查询
	
	/**
	 * 查找被推荐的博客
	 * @param pageable
	 * @return
	 */
	@Query("select b from Blog b where b.recommend = true")
	List<Blog> findTop(Pageable pageable);
	
	/**
	 * 根据关键字查找博客<br>
	 * 查找的范围是博客标题与博客的内容
	 * @param query
	 * @param pageable
	 * @return
	 */
	@Query("select b from Blog b where b.title like ?1 or b.description like ?1 or b.content like ?1 ")
	Page<Blog> findByQuery(String query, Pageable pageable);
	
	/**
	 * 更新浏览量
	 * @param id
	 * @return
	 */
	@Transactional
	@Modifying
	@Query("update Blog b set b.views = b.views + 1 where b.id = ?1")
	Integer updateViews(Long id);
	
	/**
	 * 查询所有博客的年份,返回一个字符串类型的List
	 * @return
	 */
	@Query(nativeQuery = true,value = "SELECT date_format(b.create_time, '%Y') AS years FROM t_blog b GROUP BY years ORDER BY years DESC")
	List<String> findGroupYear();
	
	/**
	 * 根据指定年份查询博客
	 * @param year
	 * @return
	 */
	@Query("select b from Blog b where function('date_format',b.createTime,'%Y') = ?1")
	List<Blog> findByYear(String year);
}
