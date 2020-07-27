package com.web.curation.service;

import java.util.List;

import com.web.curation.model.Qna;

public interface QnaService {
	public List<Qna> retrieveQna();
	public Qna detailQna(int qnano);
	public boolean writeQna(Qna qna);
	public boolean updateQna(Qna qna);
	public boolean deleteQna(int qnano);
}
