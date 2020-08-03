package com.web.curation.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.web.curation.model.Qna;

@Mapper
public interface QnaDAO {
	public List<Qna> selectQna();
	public List<Qna> selectQnaByUserno(int userno);
	public Qna selectQnaByNo (int qnano);
	public int resistAnswerQna(Qna qna);
	public int updateAnswerQna(Qna qna);
	public int insertQna(Qna qna);
	public int updateQna(Qna qna);
	public int deleteQna(int qnano);
	
}
