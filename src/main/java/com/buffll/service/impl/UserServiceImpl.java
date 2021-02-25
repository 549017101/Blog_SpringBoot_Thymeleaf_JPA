package com.buffll.service.impl;

import com.buffll.dao.UserDao;
import com.buffll.entity.User;
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
}
