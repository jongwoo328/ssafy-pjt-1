package com.web.curation.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.web.curation.model.Msg;

@Mapper
public interface MsgDAO {
	int msgCount(int userno);
	public List<Msg> selectMsg(int userno);
	public Msg selectMsgByMsgno(int msgno);
	public int updateMsg(Msg msg);
	public int insertMsg(Msg msg);
	public int deleteMsg(int msgno);
}
