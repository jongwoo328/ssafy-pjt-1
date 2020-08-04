package com.web.curation.service;

import java.util.List;

import com.web.curation.model.Msg;

public interface MsgService {
	int msgCount(int userno);
	public List<Msg> selectMsg(int userno);
	public Msg detailMsg(int msgno);
	public boolean senedMsg(Msg msg);
	public boolean deleteMsg(int msgno);
	public boolean readMsg(Msg msg);
	
}
