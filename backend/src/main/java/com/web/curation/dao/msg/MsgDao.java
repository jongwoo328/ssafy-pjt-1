package com.web.curation.dao.msg;

import java.util.List;

import com.web.curation.model.Msg;

public interface MsgDao {
	int sendMsg(Msg msg);
	Msg getMsgByNo(int msgno);
	List<Msg> getReceiveMsg(int userno);
	List<Msg> getSendMsg(int userno);
}
