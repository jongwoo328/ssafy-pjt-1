package com.web.curation.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.curation.dao.user.UserDao;
import com.web.curation.model.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;
	
	@Override
	public User getUserByEmail(String email) {
		return userDao.getUserByEmail(email);
	}

	@Override
	public User getUserByUid(String uid) {
		return userDao.getUserByUid(uid);
	}

	@Override
	public boolean insert(User user) {
		return userDao.insert(user) == 1;
	}

	@Override
	public int update(User user) {
		return userDao.update(user);
	}

	@Override
	public User login(User user) {
		return userDao.login(user);
	}

	@Override
	public User findUserByUidAndPassword(User user) {
		return userDao.findUserByUidAndPassword(user);
	}

}
