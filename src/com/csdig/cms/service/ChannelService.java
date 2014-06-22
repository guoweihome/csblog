package com.csdig.cms.service;

import java.util.List;

import com.csdig.cms.model.CmsChannel;

public interface ChannelService {
	public CmsChannel getById(int id) throws Exception;

	public List<CmsChannel> listAll() throws Exception;

	public void add(CmsChannel channel) throws Exception;

	public void update(CmsChannel channel) throws Exception;
	
	public void delete(int id) throws Exception;

	public void cleanDirtyData() throws Exception;
	
}
