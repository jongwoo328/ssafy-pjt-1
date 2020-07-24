package com.web.curation.dao.board;

import java.util.List;

import com.web.curation.model.Board;

public interface BoardDao {
	List<Board> getBoard();
	Board getBoardByNo(int boardno);
	int insert(Board board);
	int update(Board board);
	int delete(int boardno);
}
