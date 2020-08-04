package com.web.curation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.curation.dao.MsgDAO;
import com.web.curation.model.Msg;

@Service
public class MsgServiceImpl implements MsgService {

	@Autowired
	MsgDAO dao;

	@Override
	public int msgCount(int userno) {
		return dao.msgCount(userno);
	}

	@Override
	public List<Msg> selectMsg(int userno) {
		return dao.selectMsg(userno);
	}

	@Override
	public Msg detailMsg(int msgno) {
		return dao.selectMsgByMsgno(msgno);
	}

	@Override
	public boolean senedMsg(Msg msg) {
		return dao.insertMsg(msg) == 1;
	}

	@Override
	public boolean deleteMsg(int msgno) {
		return dao.deleteMsg(msgno) == 1;
	}

	@Override
	public boolean readMsg(Msg msg) {
		return dao.updateMsg(msg) == 1;
	}
	
	

}
