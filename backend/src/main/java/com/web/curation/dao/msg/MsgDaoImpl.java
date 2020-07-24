package com.web.curation.dao.msg;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.web.curation.model.Msg;

@Repository
public class MsgDaoImpl implements MsgDao {

	@Autowired
	SqlSession sqlSession;
	
	@Override
	public int sendMsg(Msg msg) {
		return sqlSession.insert("msg.sendMsg", msg);
	}

	@Override
	public Msg getMsgByNo(int msgno) {
		return sqlSession.selectOne("msg.getMsgByNo", msgno);
	}

	@Override
	public List<Msg> getReceiveMsg(int userno) {
		return sqlSession.selectList("msg.getReceiveMsg", userno);
	}

	@Override
	public List<Msg> getSendMsg(int userno) {
		return sqlSession.selectList("msg.getSendMsg", userno);
	}

}
