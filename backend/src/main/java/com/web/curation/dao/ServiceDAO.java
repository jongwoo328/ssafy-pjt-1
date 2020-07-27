package com.web.curation.dao;

import java.util.List;

import com.web.curation.model.Service;

public interface ServiceDAO {
	public List<Service> selectService(int userno);
	public Service selectServiceByNo(int userno, int servno);
	public int insertServie(Service service);
}
