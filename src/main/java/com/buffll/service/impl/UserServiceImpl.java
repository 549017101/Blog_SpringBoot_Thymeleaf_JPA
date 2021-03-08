package com.buffll.service.impl;

import com.buffll.dao.UserDao;
import com.buffll.entity.User;
import com.buffll.exception.NotFoundException;
import com.buffll.service.UserService;
import com.buffll.utils.Md5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author pxz
 * @create 2021-02-25 11:25
 */
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;
	
	@Override
	public User checkUser(String username, String password) {
		return userDao.findByUsernameAndPassword(username, Md5Utils.code(password));
	}
	
	/**
	 * 修改密码
	 * @param username 要修改密码的用户名
	 * @param oldpwd   旧密码
	 * @param newpwd   新密码
	 * @return
	 */
	@Override
	public User changePassword(String username, String oldpwd, String newpwd) {
		User user = userDao.findByUsernameAndPassword(username, Md5Utils.code(oldpwd));
		if(user != null){
			user.setPassword(Md5Utils.code(newpwd));
		    return userDao.save(user);
		}else{
			throw new NotFoundException("该用户不存在");
		}
	}
}
