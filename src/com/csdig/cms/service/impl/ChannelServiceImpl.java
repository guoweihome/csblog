package com.csdig.cms.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.csdig.cms.dao.ChannelAttrDAO;
import com.csdig.cms.dao.ChannelDAO;
import com.csdig.cms.exception.BusinessException;
import com.csdig.cms.model.Channel;
import com.csdig.cms.service.ChannelService;

@Service
@Transactional
public class ChannelServiceImpl implements ChannelService {

	@Autowired
	private ChannelDAO channelDao;
	
	@Autowired
	private ChannelAttrDAO channelAttrDao;
	
	@Override
	public Channel getById(int id) throws Exception {
		return channelDao.findById(id);
	}

	@Override
	public List<Channel> listAll(Integer pid) throws Exception {
		return channelDao.findByPId(pid);
	}

	@Override
	public void add(Channel channel) throws Exception {
		List<Channel> list = channelDao.findByCondition(new String[] { "channel_path", "parent_id" }, new Object[] {
				channel.getChannelPath(), channel.getParentId() });
		if (list != null && list.size() > 0) {
			throw new BusinessException("", "栏目路径已存在");
		}
		channelDao.add(channel);
	}

	@Override
	public void update(Channel channel) throws Exception {
		channelDao.update(channel);
	}

	@Override
	public void delete(int id) throws Exception {
		List<Channel> list = channelDao.findByPId(id);
		if(list!=null && list.size()>0){
			throw new BusinessException("", "栏目存在子栏目，不能删除");
		}
		channelAttrDao.deleteByChannelId(id);
		channelDao.delete(id);
	}

	@Override
	public void cleanDirtyData() throws Exception {
		
	}

	@Override
	public List<Map<String, Object>> listChanelTreeByPid(Integer id) throws Exception {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		if (id == null) {
			Map<String, Object> root = new HashMap<String, Object>();
			root.put("id", "-1");
			root.put("text", "根节点");
			root.put("iconCls", "icon_folder");
			root.put("state", "closed");
			result.add(root);
			return result;
		}

		List<Channel> list = channelDao.findByPId(id);
		if (list != null) {
			for (Channel channel : list) {
				Map<String, Object> map = new HashMap<String, Object>();
				PropertyUtils.copyProperties(map, channel);
				map.put("id", channel.getChannelId());
				map.put("text", channel.getChannelName());

				List<Channel> child = channelDao.findByPId(channel.getChannelId());
				if (channel != null && child.size() > 0) {
					map.put("iconCls", "icon_folder");
					map.put("state", "closed");
				} else {
					map.put("iconCls", "icon-edit-file");
					map.put("state", "open");
				}
				result.add(map);
			}
		}

		return result;
	}

}
