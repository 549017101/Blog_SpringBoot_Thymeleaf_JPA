package com.buffll.service;

import com.buffll.entity.User;

/**
 * 用户的业务层接口
 * @author pxz
 * @create 2021-02-25 11:22
 */
public interface UserService {
	
	/**
	 * 检查用户名和密码,用于验证登录
	 * @param username 用户名
	 * @param password 密码
	 * @return 用户对象
	 */
	User checkUser(String username, String password);
	
	/**
	 * 修改密码
	 * @param username 要修改密码的用户名
	 * @param oldpwd 旧密码
	 * @param newpwd 新密码
	 * @return
	 */
	User changePassword(String username ,String oldpwd, String newpwd);
}
