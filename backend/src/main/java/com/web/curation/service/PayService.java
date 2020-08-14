package com.web.curation.service;

import java.util.List;

import com.web.curation.model.Pay;

public interface PayService {
	public boolean insert(Pay pay);
	public boolean cancelPay(Pay pay);
	public boolean procancelPay(Pay pay);
	public Pay searchPayed(Pay pay);
	public int payCount(int servno);
	public List<Pay> userPay(int userno);
	public List<Integer> servPay(int servno);
}
