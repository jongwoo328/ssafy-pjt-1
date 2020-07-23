package com.web.curation.service.fselect;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.curation.dao.fselect.FselectDAO;
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
	
	
}
