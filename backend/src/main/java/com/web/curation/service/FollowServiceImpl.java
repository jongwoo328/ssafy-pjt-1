package com.web.curation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.curation.dao.FollowDAO;
import com.web.curation.model.Follow;

@Service
public class FollowServiceImpl implements FollowService{

	@Autowired
	FollowDAO dao;
	
	@Override
	public boolean insertFollow(Follow follow) {
		return dao.insertFollow(follow) == 1;
	}

	@Override
	public boolean deleteFollow(Follow follow) {
		return dao.deleteFollow(follow) == 1;
	}

	@Override
	public int followCount(int prono) {
		return dao.followCount(prono);
	}

	@Override
	public List<Follow> selectFollowingList(int userno) {
		return dao.selectFollowingList(userno);
	}

	@Override
	public Follow selectFollow(Follow follow) {
		return dao.selectFollow(follow);
	}

	@Override
	public List<Follow> selectFollowerList(int userno) {
		return dao.selectFollowerList(userno);
	}

}
