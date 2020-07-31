package com.web.curation.dao;

import org.apache.ibatis.annotations.Mapper;

import com.web.curation.model.Pay;

@Mapper
public interface PayDAO {
	public int insert(Pay pay);
	public Pay selectPay(int userno, int servno);
}
