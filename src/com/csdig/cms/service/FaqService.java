package com.csdig.cms.service;

import java.util.List;

import com.csdig.cms.model.CmsFaq;

public interface FaqService {
	public List<CmsFaq> listAll() throws Exception;

	public void add(CmsFaq faq) throws Exception;

	public void delete(int faqId) throws Exception;

	public CmsFaq findById(int faqId) throws Exception;

	public void update(CmsFaq faq) throws Exception;

	public List<CmsFaq> getByCondition(String channelPath, String orderDesc) throws Exception;
}
