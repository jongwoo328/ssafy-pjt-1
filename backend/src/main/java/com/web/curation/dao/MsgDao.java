package com.web.curation.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.web.curation.model.Msg;

@Mapper
public interface MsgDao {
	int sendMsg(Msg msg);
	Msg getMsgByNo(int msgno);
	List<Msg> getReceiveMsg(int userno);
	List<Msg> getSendMsg(int userno);
}
