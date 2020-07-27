package com.web.curation.service.profile;

import com.web.curation.model.Profile;

public interface ProfileService {
	public Profile detailProfile(int userno);
	public boolean writeProfile(Profile profile);
	public boolean updateProfile(Profile profile);
	public boolean deleteProfile(int userno);
}
