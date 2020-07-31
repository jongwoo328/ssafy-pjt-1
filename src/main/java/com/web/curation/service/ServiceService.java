package com.web.curation.service;

import java.util.List;

import com.web.curation.model.ConnectorService;

public interface ServiceService {
	public List<ConnectorService> selectServiceByUserno(int userno);
	public List<ConnectorService> selectServiceByDongcode(String dongcode, String word);
	public ConnectorService detailService(int servno);
	public boolean resistService(ConnectorService service);
	public boolean deleteService(int servno);
}
