package com.web.curation.service;

import java.util.List;

import com.web.curation.model.Service;

public interface ServiceService {
	public List<Service> retrieveService(int userno, int servno);
	public Service detailSerice(int userno, int servno);
	public boolean resistService(Service service);
}
