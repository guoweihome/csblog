package com.csdig.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.csdig.cms.dao.CmsAppChangeLogItemDAO;
import com.csdig.cms.model.CmsAppChangeLogItem;
import com.csdig.cms.service.LogItemService;

@Service
@Transactional
public class LogItemServiceImpl implements LogItemService{

	@Autowired
	private CmsAppChangeLogItemDAO logItemDao;
	
	@Override
	public void deleteByLogId(int logId) throws Exception {
		logItemDao.deleteByLogId(logId);
	}

	@Override
	public void add(CmsAppChangeLogItem logItem) throws Exception {
		logItemDao.add(logItem);
	}

	@Override
	public List<CmsAppChangeLogItem> findByLogId(int logId) throws Exception {
		return logItemDao.findByLogId(logId);
	}


}
