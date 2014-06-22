package com.csdig.cms.web.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.csdig.cms.common.ConstantDefine;
import com.csdig.cms.model.CmsChannel;
import com.csdig.cms.model.CmsModel;
import com.csdig.cms.service.ChannelService;
import com.csdig.cms.service.ModelService;

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
	
	@RequestMapping(value = "getChannels")
	@ResponseBody
	public List<CmsChannel> getChannels() throws Exception {
		return channelService.listAll();
	}
	
	@RequestMapping(value = "add")
	public ModelAndView add() throws Exception {
		ModelAndView modelAndView = new ModelAndView("channel/add");
		List<CmsChannel> parentList = channelService.listAll();
		modelAndView.addObject("parentList", parentList);
		List<CmsModel> modelList = modelService.listAllModel();
		modelAndView.addObject("modelList", modelList);
		return modelAndView;
	}
	
	@RequestMapping(value = "save")
	@ResponseBody
	public Map<String, Object> save(HttpServletRequest request,CmsChannel channel) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		channelService.add(channel);
		return result;
	}
	
	@RequestMapping(value = "update")
	public ModelAndView update(int channelId) throws Exception {
		ModelAndView modelAndView = new ModelAndView("channel/edit");
		
		CmsChannel channel = channelService.getById(channelId);
		modelAndView.addObject("channel", channel);
		
		List<CmsChannel> parentList = channelService.listAll();
		modelAndView.addObject("parentList", parentList);
		
		List<CmsModel> modelList = modelService.listAllModel();
		modelAndView.addObject("modelList", modelList);
		
		return modelAndView;
	}
	
	@RequestMapping(value = "doUpdate")
	@ResponseBody
	public Map<String, Object> doUpdate(HttpServletRequest request,CmsChannel channel) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		channelService.update(channel);
		return result;
	}
	
	@RequestMapping(value = "delete")
	@ResponseBody
	public  Map<String, Object> delete(int channelId) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		channelService.delete(channelId);
		return result;
	}
	
	@RequestMapping(value = "cleanDirtyData")
	@ResponseBody
	public  Map<String, Object> cleanDirtyData() throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		channelService.cleanDirtyData();
		return result;
	}
}
