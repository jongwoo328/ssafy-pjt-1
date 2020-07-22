
package com.web.curation.dao.user;

import java.util.Optional;

import com.web.curation.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao {
    User getUserByEmail(String email);
	int insert(User user);
	int update(User user);
    User login(User user);
    User findUserByEmailAndPassword(User user);
}

//public interface UserDao extends JpaRepository<User, String> {
//    User getUserByEmail(String email);
//	User getUserByUid(String uid);
//	User save(User user);
//    Optional<User> findUserByEmailAndPassword(String email, String password);
//    Optional<User> findUserByUidAndPassword(String email, String password);
//    
//}