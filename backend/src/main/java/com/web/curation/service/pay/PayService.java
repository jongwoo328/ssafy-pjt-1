package com.web.curation.service.pay;

import com.web.curation.model.Pay;

public interface PayService {
	public boolean insert(Pay pay);
	public Pay serchPayed(int userno, int servno);
}
