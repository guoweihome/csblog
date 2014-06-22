package com.csdig.cms.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csdig.cms.dao.CmsChannelDAO;
import com.csdig.cms.dao.CmsFaqDAO;
import com.csdig.cms.model.CmsChannel;
import com.csdig.cms.model.CmsFaq;
import com.csdig.cms.service.FaqService;

@Service
public class FaqServiceImpl implements FaqService {

	@Autowired
	private CmsFaqDAO faqDao;
	
	@Autowired
	private CmsChannelDAO channelDao;
	
	@Override
	public List<CmsFaq> listAll() throws Exception {
		List<CmsFaq> list = faqDao.listAll();
		Map<Integer, String> channelNameMap = new HashMap<Integer, String>();
		for (CmsFaq faq : list) {
			Integer channelId = faq.getChannelId();
			if (!channelNameMap.containsKey(channelId)) {
				CmsChannel channel = channelDao.findById(channelId);
				channelNameMap.put(channel.getChannelId(), channel.getName());
			}
			faq.setChannelName(channelNameMap.get(channelId));
		}
		return list;
	}

	@Override
	public void add(CmsFaq faq) throws Exception {
		faqDao.add(faq);
	}

	@Override
	public void delete(int faqId) throws Exception {
		CmsFaq faq = new CmsFaq();
		faq.setFaqId(faqId);
		faqDao.delete(faq);
	}

	@Override
	public CmsFaq findById(int faqId) throws Exception {
		return faqDao.findById(faqId);
	}

	@Override
	public void update(CmsFaq faq) throws Exception {
		faqDao.update(faq);
	}

	@Override
	public List<CmsFaq> getByCondition(String channelPath, String orderDesc) throws Exception{
		CmsChannel channel = channelDao.findByPath(channelPath);
		return faqDao.findByCondition(channel.getChannelId(), orderDesc);
		
	}

}
