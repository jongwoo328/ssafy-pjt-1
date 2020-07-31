package com.web.curation.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.web.curation.model.Qna;

@Mapper
public interface QnaDAO {
	public List<Qna> selectQna();
	public Qna selectQnaByNo (int qnano);
	public int insertQna(Qna qna);
	public int updateQna(Qna qna);
	public int deleteQna(int qnano);
	
}
