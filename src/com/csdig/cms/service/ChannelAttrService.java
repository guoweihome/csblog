package com.csdig.cms.service;

import java.io.File;
import java.util.Hashtable;
import java.util.List;

import com.csdig.cms.model.CmsChannelAttr;

public interface ChannelAttrService {

	public List<CmsChannelAttr> getByChannelId(int channelId) throws Exception;
	
	public List<CmsChannelAttr> listAll() throws Exception;
	
	public void add(CmsChannelAttr attr) throws Exception;
	
	public void update(CmsChannelAttr attr) throws Exception;
	
	public void delete(int id) throws Exception;
	
	public CmsChannelAttr getById(int id) throws Exception;
	
	public List<CmsChannelAttr> getByChannelIdAndAttrName(int channelId,String attrName) throws Exception;
	
	
	public List<Hashtable<String,Object>> listSelectFiles(File currentPathFile,String order)throws Exception;
	
}
