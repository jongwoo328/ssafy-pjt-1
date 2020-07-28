package com.web.curation.service;

import java.util.List;

import com.web.curation.model.Board;

public interface BoardService {
	List<Board> getBoard();
	Board getBoardByNo(int boardno);
	boolean insert(Board board);
	boolean update(Board board);
	boolean delete(int boardno);
}
