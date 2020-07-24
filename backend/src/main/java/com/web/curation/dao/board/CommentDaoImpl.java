package com.web.curation.dao.board;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.web.curation.model.Comment;

@Repository
public class CommentDaoImpl implements CommentDao {

	@Autowired
	SqlSession sqlSession;
	
	@Override
	public List<Comment> getComment(int boardno) {
		return sqlSession.selectList("comment.getComment", boardno);
	}

	@Override
	public int insert(Comment comment) {
		return sqlSession.insert("comment.insert", comment);
	}

	@Override
	public int update(Comment comment) {
		return sqlSession.update("comment.update", comment);
	}

	@Override
	public int delete(int commno) {
		return sqlSession.delete("comment.delete", commno);
	}

}
