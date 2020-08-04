package com.web.curation.dao;

import java.util.List;

import com.web.curation.model.Follow;

public interface FollowDAO {
	public List<Follow> selectAll(int userno);
	public Follow selectFollow(Follow follow);
	public int followCount(int prono);
	public int insertFollow(Follow follow);  
	public int deleteFollow(Follow follow); 
}
