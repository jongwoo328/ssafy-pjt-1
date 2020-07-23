package com.web.curation.dao.pay;

import org.apache.ibatis.annotations.Mapper;

import com.web.curation.model.Pay;

@Mapper
public interface PayDAO {
	public int insert(Pay pay);
	public Pay searchPayed(int userno, int servno);
}
