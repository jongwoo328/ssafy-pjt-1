package com.web.curation.service;

import com.web.curation.model.Pay;

public interface PayService {
	public boolean insert(Pay pay);
	public Pay serchPayed(int userno, int servno);
}
