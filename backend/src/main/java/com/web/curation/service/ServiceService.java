package com.web.curation.service;

import java.util.List;

import com.web.curation.model.ConnectorService;

public interface ServiceService {
	public List<ConnectorService> selectServiceByUserno(int userno);
	public List<ConnectorService> selectServiceByDongcode(int cateno, String addr5, List<String> word);
	public ConnectorService detailService(int servno);
	public boolean resistService(ConnectorService service);
	public boolean updateService(ConnectorService service);
	public boolean deleteService(int servno);
}
