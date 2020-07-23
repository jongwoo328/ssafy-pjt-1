package com.web.curation.service.msg;

import java.util.List;

import com.web.curation.model.Msg;

public interface MsgService {
	boolean sendMsg(Msg msg);
	Msg getMsgByNo(String msgno);
	List<Msg> getReceiveMsg(String userno);
	List<Msg> getSendMsg(String userno);
}
