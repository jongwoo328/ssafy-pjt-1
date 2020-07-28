package com.web.curation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.curation.dao.BoardDao;
import com.web.curation.model.Board;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	BoardDao boardDao;
	
	@Override
	public List<Board> getBoard() {
		return boardDao.getBoard();
	}

	@Override
	public Board getBoardByNo(int boardno) {
		return boardDao.getBoardByNo(boardno);
	}

	@Override
	public boolean insert(Board board) {
		return boardDao.insert(board) == 1;
	}

	@Override
	public boolean update(Board board) {
		return boardDao.update(board) == 1;
	}

	@Override
	public boolean delete(int boardno) {
		return boardDao.delete(boardno) == 1;
	}

}
