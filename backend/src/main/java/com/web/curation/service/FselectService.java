package com.web.curation.service;

import java.util.List;

import com.web.curation.model.Category;
import com.web.curation.model.DongCode;
import com.web.curation.model.SidoCode;

public interface FselectService {
	List<SidoCode> selectSido();
	List<SidoCode> selectGugun(String sido);
	List<DongCode> selectDong(String gugun);
	List<Category> selectCategory();
}
