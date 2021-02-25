package com.buffll.dao;

import com.buffll.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 用户持久层接口,使用JPA的方式实现
 * @author pxz
 * @create 2021-02-25 11:25
 */
public interface UserDao extends JpaRepository<User,Long> {
	//JpaRepository接口中已经定义了基本的增删改查,其他的需求还是要自己定义才行,也要符合jpa的命名规范
	//下面根据用户名密码查询的方法,也属于基本的增删改查,但是JpaRepository接口中并没有定义这种查询,所以需要我们自己定义,但是要符合JpaRepository接口的命名规范,如findByXXXXX  这就是查询方法的命名规范(具体的百度查)  首字母是小写
	
	/**
	 * 根据用户名和密码查询数据库
	 * @param username
	 * @param password
	 * @return
	 */
	User findByUsernameAndPassword(String username, String password);
}
