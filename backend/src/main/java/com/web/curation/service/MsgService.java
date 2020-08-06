package com.web.curation.service;

import java.util.List;

import com.web.curation.model.Msg;

public interface MsgService {
	int msgCount(int userno);
	public List<Msg> selectSendMsg(int userno);
	public List<Msg> selectRecMsg(int userno);
	public Msg detailMsg(int msgno);
	public boolean senedMsg(Msg msg);
	public boolean deleteSendMsg(List<Integer> msgnoList);
	public boolean deleteRecMsg(List<Integer> msgnoList);
	public boolean readMsg(Msg msg);
	
}
