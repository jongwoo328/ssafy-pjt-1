package com.web.curation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.curation.dao.UserDAO;
import com.web.curation.model.Admin;
import com.web.curation.model.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDAO userDao;
	
	@Override
	public User getUserByEmail(String email) {
		return userDao.getUserByEmail(email);
	}

	@Override
	public User getUserByName(String name) {
		return userDao.getUserByName(name);
	}
	
	@Override
	public User getUserByTel(String tel) {
		return userDao.getUserByTel(tel);
	}

	@Override
	public boolean insert(User user) {
		return userDao.insert(user) == 1;
	}
	
	@Override
	public boolean samplePw(User user) {
		return userDao.samplePw(user) == 1;
	}

	@Override
	public int update(User user) {
		return userDao.updateUser(user);
	}

	@Override
	public User login(User user) {
		return userDao.login(user);
	}

	@Override
	public User findUserByEmailAndName(User user) {
		return userDao.findUserByEmailAndName(user);
	}

	@Override
	public User getUserByUserno(int userno) {
		return userDao.getUserByUserno(userno);
	}

	@Override
	public Admin checkAdmin(int userno) {
		return userDao.checkAdmin(userno);
	}

	@Override
	public List<String> totalUserName(String word) {
		return userDao.totalUserName(word);
	}

}
