package com.csdig.cms.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.csdig.cms.dao.CmsAppChangeLogDAO;
import com.csdig.cms.dao.CmsAppChangeLogItemDAO;
import com.csdig.cms.dao.CmsChannelDAO;
import com.csdig.cms.model.CmsAppChangeLog;
import com.csdig.cms.model.CmsAppChangeLogItem;
import com.csdig.cms.model.CmsChannel;
import com.csdig.cms.service.LogService;

@Service
@Transactional
public class LogServiceImpl implements LogService {

	@Autowired
	private CmsAppChangeLogDAO logDao;
	
	@Autowired
	private CmsChannelDAO channelDao;
	
	@Autowired
	private CmsAppChangeLogItemDAO logItemDao;

	@Override
	public List<CmsAppChangeLog> listAllLog() throws Exception {
		List<CmsAppChangeLog> list = logDao.listAll();
		Map<Integer, String> channelNameMap = new HashMap<Integer, String>();
		for (CmsAppChangeLog log : list) {
			Integer channelId = log.getChannelId();
			if (!channelNameMap.containsKey(channelId)) {
				CmsChannel channel = channelDao.findById(channelId);
				if (channel == null) {
					continue;
				}
				channelNameMap.put(channel.getChannelId(), channel.getName());
			}
			log.setChannelName(channelNameMap.get(channelId));
			String appTypeName = "Android";
			if (log.getAppType() == 2) {
				appTypeName = "iOS";
			}
			log.setAppTypeName(appTypeName);
		}
		return list;
	}

	@Override
	public void add(CmsAppChangeLog log,String itemPrioritys,String itemDescs) throws Exception {
		int logId = logDao.add(log);
		if (itemPrioritys != null && itemDescs != null) {
			String[] priorityArray = itemPrioritys.split(",");
			String[] descArray = itemDescs.split(",");
			
			for (int i = 0; i < priorityArray.length; i++) {
				CmsAppChangeLogItem logItem = new CmsAppChangeLogItem();
				logItem.setChangeLogId(logId);
				logItem.setPriority(Integer.parseInt(priorityArray[i]));
				logItem.setDesc(descArray[i]);
				logItemDao.add(logItem);
			}
		}
	}

	@Override
	public void delete(int logId) throws Exception {
		CmsAppChangeLog log = new CmsAppChangeLog();
		log.setChangeLogId(logId);
		logDao.delete(log);
		logItemDao.deleteByLogId(logId);
	}

	@Override
	public CmsAppChangeLog findById(int id) throws Exception {
		CmsAppChangeLog log = logDao.findById(id);
		List<CmsAppChangeLogItem> logItems = logItemDao.findByLogId(log.getChangeLogId());
		log.setLogItems(logItems);
		return log;
	}

	@Override
	public void update(CmsAppChangeLog log,String itemPrioritys,String itemDescs) throws Exception {
		logDao.update(log);
		logItemDao.deleteByLogId(log.getChangeLogId());
		
		if (itemPrioritys != null && itemDescs != null) {
			String[] priorityArray = itemPrioritys.split(",");
			String[] descArray = itemDescs.split(",");
			
			for (int i = 0; i < priorityArray.length; i++) {
				CmsAppChangeLogItem logItem = new CmsAppChangeLogItem();
				logItem.setChangeLogId(log.getChangeLogId());
				logItem.setPriority(Integer.parseInt(priorityArray[i]));
				logItem.setDesc(descArray[i]);
				logItemDao.add(logItem);
			}
		}
	}

	@Override
	public List<CmsAppChangeLog> getByCondition(String channelPath, String appType, String orderDesc) throws Exception{
		CmsChannel channel = channelDao.findByPath(channelPath);
		List<CmsAppChangeLog> logs = logDao.getByCondition(channel.getChannelId(), appType, orderDesc);
		for (CmsAppChangeLog log : logs) {
			List<CmsAppChangeLogItem> logItems = logItemDao.findByLogId(log.getChangeLogId());
			log.setLogItems(logItems);
		}
		return logs;
	}

	@Override
	public List<CmsAppChangeLog> getByCondition(String channelPath, String orderDesc) throws Exception {
		CmsChannel channel = channelDao.findByPath(channelPath);
		List<CmsAppChangeLog> logs = logDao.getByCondition(channel.getChannelId(), orderDesc);
		for (CmsAppChangeLog log : logs) {
			List<CmsAppChangeLogItem> logItems = logItemDao.findByLogId(log.getChangeLogId());
			log.setLogItems(logItems);
		}
		return logs;
	}

}
