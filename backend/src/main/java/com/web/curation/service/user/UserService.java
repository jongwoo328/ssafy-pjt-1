package com.web.curation.service.user;

import com.web.curation.model.User;

public interface UserService {
	User getUserByEmail(String email);
	User getUserByUid(String uid);
	boolean insert(User user);
	int update(User user);
    User login(User user);
    User findUserByEmailAndPassword(User user);   
}
