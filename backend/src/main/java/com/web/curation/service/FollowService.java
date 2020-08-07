package com.web.curation.service;

import java.util.List;

import com.web.curation.model.Follow;

public interface FollowService {
	public boolean insertFollow(Follow follow);
	public boolean deleteFollow(Follow follow);
	public int followCount(int prono);
	public List<Follow> selectFollowingList(int userno);
	public List<Follow> selectFollowerList(int userno);
	public Follow selectFollow(Follow follow);
}
