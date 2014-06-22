package com.csdig.cms.service;

import java.util.List;

import com.csdig.cms.model.CmsAppChangeLogItem;

public interface LogItemService {

	public void deleteByLogId(int logId) throws Exception;
	
	public void add(CmsAppChangeLogItem logItem) throws Exception;
	
	public List<CmsAppChangeLogItem> findByLogId(int logId) throws Exception;
}
