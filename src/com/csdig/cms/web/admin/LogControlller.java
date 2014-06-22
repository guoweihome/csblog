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
import com.csdig.cms.model.CmsAppChangeLog;
import com.csdig.cms.model.CmsChannel;
import com.csdig.cms.service.ChannelService;
import com.csdig.cms.service.LogService;

@Controller
@RequestMapping(value = ConstantDefine.AdminUrl.BASE + "/log")
public class LogControlller {

	@Autowired
	private LogService logService;

	@Autowired
	private ChannelService channelService;
	
	@RequestMapping(value = "list")
	public String list(HttpServletRequest request) throws Exception {
		 return "log/list";
	}

	@RequestMapping(value = "getLogs")
	@ResponseBody
	public List<CmsAppChangeLog> getLogs() throws Exception {
		return logService.listAllLog();
	}

	@RequestMapping(value = "add")
	public ModelAndView add() throws Exception {
		ModelAndView modelAndView = new ModelAndView("log/add");
		List<CmsChannel> channelList = channelService.listAll();
		modelAndView.addObject("channelList", channelList);
		return modelAndView;
	}
	
	@RequestMapping(value = "save")
	@ResponseBody
	public Map<String, Object> save(HttpServletRequest request,CmsAppChangeLog log) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		
		String itemPrioritys = request.getParameter("priority");
		String itemDescs = request.getParameter("desc");
		
		logService.add(log,itemPrioritys,itemDescs);
		
		return result;
	}
	
	@RequestMapping(value = "delete")
	@ResponseBody
	public  Map<String, Object> delete(int changeLogId) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		logService.delete(changeLogId);
		return result;
	}

	@RequestMapping(value = "update")
	public ModelAndView update(int changeLogId) throws Exception {
		ModelAndView modelAndView = new ModelAndView("log/edit");
		
		List<CmsChannel> channelList = channelService.listAll();
		modelAndView.addObject("channelList", channelList);
		
		CmsAppChangeLog log = logService.findById(changeLogId);
		modelAndView.addObject("log", log);
		
		return modelAndView;
	}
	
	@RequestMapping(value = "doUpdate")
	@ResponseBody
	public Map<String, Object> doUpdate(HttpServletRequest request,CmsAppChangeLog log) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		
		String itemPrioritys = request.getParameter("priority");
		String itemDescs = request.getParameter("desc");
		
		logService.update(log,itemPrioritys,itemDescs);
		return result;
	}
}
