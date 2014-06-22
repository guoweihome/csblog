package com.csdig.cms.service;

import java.util.List;

import com.csdig.cms.model.CmsAppChangeLog;

public interface LogService {
	public List<CmsAppChangeLog> listAllLog() throws Exception;

	public void add(CmsAppChangeLog log, String itemPrioritys, String itemDescs) throws Exception;

	public void delete(int id) throws Exception;

	public CmsAppChangeLog findById(int id) throws Exception;

	public void update(CmsAppChangeLog log,String itemPrioritys,String itemDescs) throws Exception;

	public List<CmsAppChangeLog> getByCondition(String channelPath, String appType, String orderDesc) throws Exception;
	
	public List<CmsAppChangeLog> getByCondition(String channelPath, String orderDesc) throws Exception;
}
