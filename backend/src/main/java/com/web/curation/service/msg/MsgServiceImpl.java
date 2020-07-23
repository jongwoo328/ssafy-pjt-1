package com.web.curation.service.msg;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.curation.dao.msg.MsgDao;
import com.web.curation.model.Msg;

@Service
public class MsgServiceImpl implements MsgService {

	@Autowired
	MsgDao msgDao;
	
	@Override
	public boolean sendMsg(Msg msg) {
		return msgDao.sendMsg(msg) == 1;
	}

	@Override
	public Msg getMsgByNo(String msgno) {
		return msgDao.getMsgByNo(msgno);
	}

	@Override
	public List<Msg> getReceiveMsg(String userno) {
		return msgDao.getReceiveMsg(userno);
	}

	@Override
	public List<Msg> getSendMsg(String userno) {
		return msgDao.getSendMsg(userno);
	}

}
