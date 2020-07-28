package com.web.curation.service;

import com.web.curation.model.User;

public interface UserService {
	User getUserByEmail(String email);
	User getUserByUid(String uid);
	User getUserByTel(String tel);
	boolean insert(User user);
	boolean samplePw(User user);
	int update(User user);
    User login(User user);
    User findUserByEmailAndName(User user);   
}
