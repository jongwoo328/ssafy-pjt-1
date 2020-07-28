package com.web.curation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.curation.dao.ProfileDAO;
import com.web.curation.model.Profile;

@Service
public class ProfileServiceImpl implements ProfileService{
	
	@Autowired
	ProfileDAO dao;

	@Override
	public Profile detailProfile(int userno) {
		return dao.selectProfileByNo(userno);
	}

	@Override
	public boolean writeProfile(Profile profile) {
		return dao.insertProfile(profile) == 1;
	}

	@Override
	public boolean updateProfile(Profile profile) {
		return dao.updateProfile(profile) == 1;
	}

	@Override
	public boolean deleteProfile(int userno) {
		return dao.deleteProfile(userno) == 1;
	}
}
