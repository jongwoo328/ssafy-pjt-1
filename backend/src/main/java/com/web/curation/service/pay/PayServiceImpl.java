package com.web.curation.service.pay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.curation.dao.pay.PayDAO;
import com.web.curation.model.Pay;

@Service
public class PayServiceImpl implements PayService{

	@Autowired
	PayDAO dao;
	
	@Override
	public boolean insert(Pay pay) {
		return dao.insert(pay) == 1;
	}

	@Override
	public Pay serchPayed(int userno, int servno) {
		return dao.searchPayed(userno, servno);
	}
	
}
