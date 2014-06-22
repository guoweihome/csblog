package com.csdig.cms.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.csdig.cms.dao.CmsAppChangeLogDAO;
import com.csdig.cms.dao.CmsAppChangeLogItemDAO;
import com.csdig.cms.dao.CmsChannelAttrDAO;
import com.csdig.cms.dao.CmsChannelDAO;
import com.csdig.cms.dao.CmsFaqDAO;
import com.csdig.cms.dao.CmsModelDAO;
import com.csdig.cms.dao.CmsModelItemDAO;
import com.csdig.cms.model.CmsAppChangeLog;
import com.csdig.cms.model.CmsChannel;
import com.csdig.cms.model.CmsChannelAttr;
import com.csdig.cms.model.CmsModel;
import com.csdig.cms.model.CmsModelItem;
import com.csdig.cms.service.ChannelService;

@Service
@Transactional
public class ChannelServiceImpl implements ChannelService{
	
	@Autowired
	private CmsChannelDAO channelDao;
	
	@Autowired
	private CmsModelDAO modelDao;
	
	@Autowired
	private CmsChannelAttrDAO channelAttrDao;
	
	@Autowired
	private CmsModelItemDAO modelItemDao;
	
	@Autowired
	private CmsFaqDAO faqDao;
	
	@Autowired
	private CmsAppChangeLogDAO logDao;
	
	@Autowired
	private CmsAppChangeLogItemDAO logItemDao;
	
	
	@Override
	public CmsChannel getById(int id) throws Exception {
		List<CmsChannelAttr> channelAttrs = channelAttrDao.getByChannelId(id);
		CmsChannel channel = channelDao.findById(id);
		channel.setChannelAttrs(channelAttrs);
		return channel;
	}

	@Override
	public List<CmsChannel> listAll() throws Exception {
		List<CmsChannel> channelList = channelDao.listAll();
		List<CmsModel> modelList = modelDao.listAll();
		
		Map<Integer,String> modelNameMap = new HashMap<Integer,String>();
		for (CmsModel model : modelList) {
			Integer modelId = model.getModelId();
			if (!modelNameMap.containsKey(modelId)) {
				modelNameMap.put(modelId, model.getModelName());
			}
		}
		
		Map<Integer,CmsChannel> channelMap = new HashMap<Integer,CmsChannel>();
		for(CmsChannel channel:channelList){
			channelMap.put(channel.getChannelId(), channel);
		}
		
		for(CmsChannel channel:channelList){
			Integer parentId = channel.getParentId();
			channel.setModelName(modelNameMap.get(channel.getModelId()));
			String parentName = "无";
			if (parentId != null) {
				CmsChannel parent = channelMap.get(parentId);
				if (parent != null) {
					parentName = parent.getName();
				}
			}
			channel.setParentName(parentName);
			
		}
		
 		return channelList;
	}

	@Override
	public void add(CmsChannel channel) throws Exception{
		int channelId = channelDao.add(channel);
		List<CmsModelItem> modelItemList = modelItemDao.findByModelId(channel.getModelId());
		for (CmsModelItem modelItem : modelItemList) {
			if (!modelItem.getIsRequired()) {
				continue;
			}
			CmsChannelAttr attr = new CmsChannelAttr();
			attr.setChannelId(channelId);
			attr.setPriority(0);
			attr.setAttrName(modelItem.getField());
			attr.setAttrValue("");
			channelAttrDao.add(attr);
		}
			
	}

	@Override
	public void update(CmsChannel channel) throws Exception {
		channelDao.update(channel);
	}

	@Override
	public void delete(int id) throws Exception {
	
		faqDao.deleteByChannelId(id);
		
		List<CmsAppChangeLog> logList = logDao.getByChannelId(id);
		for (CmsAppChangeLog log : logList) {
			logItemDao.deleteByLogId(log.getChangeLogId());
		}
		logDao.deleteByChannelId(id);
		
		channelAttrDao.deleteByChannelId(id);
		
		CmsChannel channel = new CmsChannel();
		channel.setChannelId(id);
		channelDao.delete(channel);
		
		
	}
	
	@Override
	public void cleanDirtyData()throws Exception{
		channelAttrDao.cleanDirtyData();
	}
	

}
