package com.csdig.cms.service;

import java.util.List;

import com.csdig.cms.model.AppType;

public interface AppTypeService {

	public List<AppType> findAll(String order)throws Exception;

}
