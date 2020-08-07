
package com.web.curation.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.web.curation.model.Admin;
import com.web.curation.model.User;

@Mapper
public interface UserDAO {
	User getUserByEmail(String email);
	User getUserByName(String name);
	User getUserByTel(String tel);
	User getUserByUserno(int userno);
	List<String> totalUserName(String word);
	Admin checkAdmin(int userno);
	int insert(User user);
	int updateUser(User user);
	int samplePw(User user);
	User login(User user);
	User findUserByEmailAndName(User user);   
}

//public interface UserDao extends JpaRepository<User, String> {
//    User getUserByEmail(String email);
//	User getUserByUid(String uid);
//	User save(User user);
//    Optional<User> findUserByEmailAndPassword(String email, String password);
//    Optional<User> findUserByUidAndPassword(String email, String password);
//    
//}