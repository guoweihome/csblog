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
import com.csdig.cms.model.CmsFaq;
import com.csdig.cms.service.ChannelService;
import com.csdig.cms.service.FaqService;

@Controller
@RequestMapping(value = ConstantDefine.AdminUrl.BASE + "/faq")
public class FaqController {

	@Autowired
	private FaqService faqService;

	@Autowired
	private ChannelService channelService;

	@RequestMapping(value = "list")
	public String list(HttpServletRequest request) throws Exception {
		return "faq/list";
	}

	@RequestMapping(value = "getFaqs")
	@ResponseBody
	public List<CmsFaq> getFaqs() throws Exception {
		return faqService.listAll();
	}

	@RequestMapping(value = "add")
	public ModelAndView add() throws Exception {
		ModelAndView modelAndView = new ModelAndView("faq/edit");
		List<CmsChannel> channelList = channelService.listAll();
		modelAndView.addObject("channelList", channelList);
		return modelAndView;
	}

	@RequestMapping(value = "save")
	@ResponseBody
	public Map<String, Object> save(CmsFaq faq) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		if (faq.getFaqId() == null) {
			faqService.add(faq);
		} else {
			faqService.update(faq);
		}
		return result;
	}

	@RequestMapping(value = "delete")
	@ResponseBody
	public Map<String, Object> delete(int faqId) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		faqService.delete(faqId);
		return result;
	}

	@RequestMapping(value = "update")
	public ModelAndView update(int faqId) throws Exception {
		ModelAndView modelAndView = new ModelAndView("faq/edit");
		List<CmsChannel> channelList = channelService.listAll();
		modelAndView.addObject("channelList", channelList);
		CmsFaq faq = faqService.findById(faqId);
		modelAndView.addObject("faq", faq);
		return modelAndView;
	}
}
