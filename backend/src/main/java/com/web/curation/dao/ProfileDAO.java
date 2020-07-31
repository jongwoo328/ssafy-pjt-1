package com.web.curation.dao;

import org.apache.ibatis.annotations.Mapper;

import com.web.curation.model.Profile;

@Mapper
public interface ProfileDAO {
	public Profile selectProfileByNo(int userno);
	public int insertProfile(Profile profile);
	public int updateProfile(Profile profile);
	public int deleteProfile(int userno);
}
