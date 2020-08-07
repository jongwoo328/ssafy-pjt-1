package com.web.curation.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.web.curation.model.ConnectorService;

@Mapper
public interface ServiceDAO {
	public List<ConnectorService> selectServiceByUserno(int userno);
	public List<ConnectorService> selectServiceByDongcode(@Param("cateno") int cateno, @Param("saddr5")String saddr5,@Param("word") List<String> word);
	public ConnectorService selectServiceByNo(int servno);
	public int insertService(ConnectorService service);
	public int updateService(ConnectorService service);
	public int deleteService(int servno);
}
