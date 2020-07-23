package com.web.curation.dao.fselect;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.web.curation.model.DongCode;
import com.web.curation.model.SidoCode;

@Mapper
public interface FselectDAO {
	List<SidoCode> selectSido();
	List<SidoCode> selectGugun(String sido);
	List<DongCode> selectDong(String gugun);
}
