package com.csdig.cms.web.admin;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.csdig.cms.common.ConstantDefine;
import com.csdig.cms.model.Channel;
import com.csdig.cms.service.ChannelService;
import com.csdig.cms.service.ModelService;
import com.csdig.cms.utils.JsonDateValueProcessor;

@Controller
@RequestMapping(value = ConstantDefine.AdminUrl.BASE + "/channel")
public class ChannelController {

	@Autowired
	private ChannelService channelService;

	@Autowired
	private ModelService modelService;

	@RequestMapping(value = "list")
	public String list(HttpServletRequest request) throws Exception {
		return "channel/list";
	}

	// 树
	@RequestMapping(value = "tree")
	@ResponseBody
	public String tree(Integer id) throws Exception {
		List<Map<String, Object>> list = channelService.listChanelTreeByPid(id);
		return JSONArray.fromObject(list, dateJsonConfig()).toString();
	}

	@RequestMapping(value = "getChannels")
	@ResponseBody
	public List<Channel> getChannels(Integer pid) throws Exception {
		return channelService.listAll(pid);
	}

	@RequestMapping(value = "add")
	public String add(HttpServletRequest request,Integer pid) throws Exception {
		Channel channel = channelService.getById(pid);
		request.setAttribute("channel", channel);
		return "channel/add";
	}

	@RequestMapping(value = "save")
	@ResponseBody
	public Map<String, Object> save(HttpServletRequest request, Channel channel) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		channelService.add(channel);
		return result;
	}

	@RequestMapping(value = "update")
	public ModelAndView update(int channelId) throws Exception {
		ModelAndView modelAndView = new ModelAndView("channel/edit");

		Channel channel = channelService.getById(channelId);
		modelAndView.addObject("channel", channel);

		Channel parent = channelService.getById(channel.getParentId());
		modelAndView.addObject("parent", parent);
		return modelAndView;
	}

	@RequestMapping(value = "doUpdate")
	@ResponseBody
	public Map<String, Object> doUpdate(HttpServletRequest request, Channel channel) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		channelService.update(channel);
		return result;
	}

	@RequestMapping(value = "delete")
	@ResponseBody
	public Map<String, Object> delete(int channelId) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		channelService.delete(channelId);
		return result;
	}

	@RequestMapping(value = "cleanDirtyData")
	@ResponseBody
	public Map<String, Object> cleanDirtyData() throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		channelService.cleanDirtyData();
		return result;
	}

	private JsonConfig dateJsonConfig() {
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor("yyyy-MM-dd HH:mm:ss"));
		return config;
	}
}
