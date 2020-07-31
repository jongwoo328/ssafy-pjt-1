package com.web.curation.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.web.curation.model.ConnectorService;

//@Mapper
public interface ServiceDAO {
	public List<ConnectorService> selectServiceByUserno(int userno);
	public List<ConnectorService> selectServiceByDongcode(String seraddr, String word);
	public ConnectorService selectServiceByNo(int servno);
	public int insertService(ConnectorService service);
	public int deleteService(int servno);
}
