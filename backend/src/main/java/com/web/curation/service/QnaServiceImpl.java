package com.web.curation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.curation.dao.QnaDAO;
import com.web.curation.model.Qna;

@Service
public class QnaServiceImpl implements QnaService {
	
	@Autowired
	QnaDAO dao;
	
	
	@Override
	public List<Qna> retrieveQna() {
		return dao.selectQna();
	}

	@Override
	public Qna detailQna(int qnano) {
		return dao.selectQnaByNo(qnano);
	}

	@Override
	public boolean writeQna(Qna qna) {
		return dao.insertQna(qna) == 1;
	}

	@Override
	@Transactional
	public boolean updateQna(Qna qna) {
		return dao.updateQna(qna) == 1;
	}

	@Override
	@Transactional
	public boolean deleteQna(int qnano) {
		return dao.deleteQna(qnano) == 1;
	}

	@Override
	public List<Qna> selectQnaByUserno(int userno) {
		return dao.selectQnaByUserno(userno);
	}

	@Override
	public boolean resistAnswerQna(Qna qna) {
		return dao.resistAnswerQna(qna) == 1;
	}

	@Override
	public boolean updateAnswerQna(Qna qna) {
		return dao.updateAnswerQna(qna) == 1;
	}
	
}
