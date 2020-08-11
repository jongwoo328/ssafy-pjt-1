package com.web.curation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.curation.dao.ServiceDAO;
import com.web.curation.model.ConnectorService;


@Service
public class ServiceServiceImpl implements ServiceService{

	@Autowired
	ServiceDAO dao;

	@Override
	public List<ConnectorService> selectServiceByUserno(int userno) {
		return dao.selectServiceByUserno(userno);
	}

	@Override
	public List<ConnectorService> selectServiceByDongcode(int cateno, String saddr5, List<String> word) {
		return dao.selectServiceByDongcode(cateno, saddr5, word);
	}

	@Override
	public ConnectorService detailService(int servno) {
		return dao.selectServiceByNo(servno);
	}

	@Override
	public boolean resistService(ConnectorService service) {
		return dao.insertService(service) == 1;
	}

	@Override
	public boolean deleteService(int servno) {
		return dao.deleteService(servno) == 1;
	}

	@Override
	public boolean updateService(ConnectorService service) {
		return dao.updateService(service) == 1;
	}
	
	

}
