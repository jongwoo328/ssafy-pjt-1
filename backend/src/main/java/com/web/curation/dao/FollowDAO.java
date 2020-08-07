package com.web.curation.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.web.curation.model.Follow;

@Mapper
public interface FollowDAO {
	public List<Follow> selectFollowingList(int userno);
	public List<Follow> selectFollowerList(int userno);
	public Follow selectFollow(Follow follow);
	public int followCount(int prono);
	public int insertFollow(Follow follow);  
	public int deleteFollow(Follow follow); 
}
