package com.web.curation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.curation.dao.PayDAO;
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
	public Pay searchPayed(Pay pay) {
		return dao.selectPay(pay);
	}

	@Override
	public int payCount(int servno) {
		return dao.payCount(servno);
	}

	@Override
	public List<Pay> userPay(int userno) {
		return dao.userPay(userno);
	}

	@Override
	public boolean cancelPay(Pay pay) {
		return dao.cancelPay(pay) == 1;
	}

	@Override
	public List<Integer> servPay(int servno) {
		return dao.servPay(servno);
	}

	@Override
	public boolean procancelPay(Pay pay) {
		return dao.procancelPay(pay) == 1;
	}
	
}
