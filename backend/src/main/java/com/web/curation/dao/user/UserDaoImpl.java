package com.web.curation.dao.user;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.web.curation.model.User;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	SqlSession sqlSession;
	
	@Override
	public User getUserByEmail(String email) {
		return sqlSession.selectOne("user.selectOneUserByEmail", email);
	}

	@Override
	public int insert(User user) {
		return sqlSession.insert("user.join", user);
	}

	@Override
	public int update(User user) {
		// TODO Auto-generated method stub
		return sqlSession.insert("user.updateUser", user);
	}

	@Override
	public User login(User user) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("user.login", user);
	}

	@Override
	public User findUserByEmailAndPassword(User user) {
		return sqlSession.selectOne("user.findpw", user);
	}



}
