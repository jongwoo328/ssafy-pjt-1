package com.web.curation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.curation.dao.FselectDAO;
import com.web.curation.model.Category;
import com.web.curation.model.DongCode;
import com.web.curation.model.SidoCode;

@Service
public class FselectServiceImpl implements FselectService {
	
	@Autowired
	FselectDAO dao;

	@Override
	public List<SidoCode> selectSido() {
		return dao.selectSido();
	}

	@Override
	public List<SidoCode> selectGugun(String sido) {
		return dao.selectGugun(sido);
	}

	@Override
	public List<DongCode> selectDong(String gugun) {
		return dao.selectDong(gugun);
	}

	@Override
	public List<Category> selectCategory() {
		return dao.selectCategory();
	}
	
	
}
