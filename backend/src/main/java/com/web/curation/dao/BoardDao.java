package com.web.curation.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.web.curation.model.Board;

@Mapper
public interface BoardDao {
	List<Board> getBoard();
	Board getBoardByNo(int boardno);
	int insert(Board board);
	int update(Board board);
	int delete(int boardno);
}
