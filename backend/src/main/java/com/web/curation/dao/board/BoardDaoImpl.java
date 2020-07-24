package com.web.curation.dao.board;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.web.curation.model.Board;

@Repository
public class BoardDaoImpl implements BoardDao {

	@Autowired
	SqlSession sqlSession;
	
	@Override
	public List<Board> getBoard() {
		return sqlSession.selectList("board.getBoard");
	}

	@Override
	public Board getBoardByNo(int boardno) {
		return sqlSession.selectOne("board.getBoardByNo", boardno);
	}

	@Override
	public int insert(Board board) {
		return sqlSession.insert("board.insert", board);
	}

	@Override
	public int update(Board board) {
		return sqlSession.update("board.update", board);
	}

	@Override
	public int delete(int boardno) {
		return sqlSession.delete("board.delete", boardno);
	}
}
