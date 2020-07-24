package com.web.curation.service.msg;

import java.util.List;

import com.web.curation.model.Msg;

public interface MsgService {
	boolean sendMsg(Msg msg);
	Msg getMsgByNo(int msgno);
	List<Msg> getReceiveMsg(int userno);
	List<Msg> getSendMsg(int userno);
}
