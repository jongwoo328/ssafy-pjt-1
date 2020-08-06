package com.web.curation.service;

import java.util.List;

import com.web.curation.model.Admin;
import com.web.curation.model.User;

public interface UserService {
	User getUserByEmail(String email);
	User getUserByName(String name);
	User getUserByTel(String tel);
	User getUserByUserno(int userno);
	List<String> totalUserName(String word);
	boolean insert(User user);
	boolean samplePw(User user);
	Admin checkAdmin(int userno);
	int update(User user);
    User login(User user);
    User findUserByEmailAndName(User user);   
}
