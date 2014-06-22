package com.csdig.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csdig.cms.dao.UserDao;
import com.csdig.cms.model.CmsUser;
import com.csdig.cms.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Override
	public List<CmsUser> getUsers() throws Exception {
		return userDao.listAll();
	}

	@Override
	public void add(CmsUser user) throws Exception {
		userDao.add(user);
	}

	@Override
	public void update(CmsUser user) throws Exception {
		userDao.update(user);
	}

	@Override
	public void delete(CmsUser user) throws Exception {
		userDao.delete(user);
	}

}
