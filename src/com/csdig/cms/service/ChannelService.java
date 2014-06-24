package com.csdig.cms.service;

import java.util.List;
import java.util.Map;

import com.csdig.cms.model.Channel;

public interface ChannelService {
	
	public Channel getById(int id) throws Exception;

	public List<Channel> listAll(Integer pid) throws Exception;

	public void add(Channel channel) throws Exception;

	public void update(Channel channel) throws Exception;

	public void delete(int id) throws Exception;

	public void cleanDirtyData() throws Exception;

	// /////////
	public List<Map<String, Object>> listChanelTreeByPid(Integer id) throws Exception;

}
