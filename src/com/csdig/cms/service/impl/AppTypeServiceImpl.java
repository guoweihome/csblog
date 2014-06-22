package com.csdig.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csdig.cms.dao.AppTypeDAO;
import com.csdig.cms.model.AppType;
import com.csdig.cms.service.AppTypeService;

@Service
public class AppTypeServiceImpl implements AppTypeService{

	@Autowired
	private AppTypeDAO appTypeDao;
	
	@Override
	public List<AppType> findAll(String order)throws Exception {
		return appTypeDao.findAll(order);
	}

}
