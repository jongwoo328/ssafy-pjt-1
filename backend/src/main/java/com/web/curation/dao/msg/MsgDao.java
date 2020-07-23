package com.web.curation.dao.msg;

import java.util.List;

import com.web.curation.model.Msg;

public interface MsgDao {
	int sendMsg(Msg msg);
	Msg getMsgByNo(String msgno);
	List<Msg> getReceiveMsg(String userno);
	List<Msg> getSendMsg(String userno);
}
