package com.csdig.cms.service;

import java.util.List;

import com.csdig.cms.model.CmsUser;

public interface UserService {
	
	public List<CmsUser> getUsers() throws Exception;

	public void add(CmsUser user) throws Exception;

	public void update(CmsUser user) throws Exception;
	
	public void delete(CmsUser user) throws Exception;
}
