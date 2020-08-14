package com.web.curation.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.web.curation.model.Pay;

@Mapper
public interface PayDAO {
	public int insert(Pay pay);
	public Pay selectPay(Pay pay);
	public int payCount(int servno);
	public List<Pay> userPay(int userno);
	public List<Integer> servPay(int servno);
	public int cancelPay(Pay pay);
	public int procancelPay(Pay pay);
}
