package com.web.curation.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.web.curation.model.Pay;

@Mapper
public interface PayDAO {
	public int insert(Pay pay);
	public Pay selectPay(@Param ("userno")int userno,@Param("servno") int servno);
}
