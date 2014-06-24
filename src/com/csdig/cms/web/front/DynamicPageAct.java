package com.csdig.cms.web.front;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.csdig.cms.common.ConstantDefine;
import com.csdig.cms.service.CommonService;
import com.csdig.cms.service.FrontPageService;

@Controller
public class DynamicPageAct {

	@Autowired
	private FrontPageService pageService;

	@Autowired
	private CommonService commonService;

	// 访问路径
	@RequestMapping(value = "/{path}.*", method = RequestMethod.GET)
	public String dynamic(HttpServletRequest request, HttpServletResponse response, ModelMap model,
			@PathVariable String path) throws Exception {
		model.addAttribute("User-Agent", request.getHeader("User-Agent"));
		model.addAttribute("Referer", request.getHeader("Referer"));
		String ftlPath = pageService.findTemplateByChanelPath(path);

		if (StringUtils.isEmpty(ftlPath)) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return "404";
		}

		model.addAllAttributes(pageService.frontData(path, ftlPath, ConstantDefine.DYNAMIC_EXTENTION));

		return ftlPath;
	}

	// 二级目录
	@RequestMapping(value = "/{path0}/{path}.*", method = RequestMethod.GET)
	public String dynamic2(HttpServletRequest request, HttpServletResponse response, ModelMap model,
			@PathVariable String path0, @PathVariable String path) throws Exception {
		String realPath = path0 + "/" + path;
		model.addAttribute("User-Agent", request.getHeader("User-Agent"));
		model.addAttribute("Referer", request.getHeader("Referer"));
		String ftlPath = pageService.findTemplateByChanelPath(realPath);
		if (StringUtils.isEmpty(ftlPath)) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return "404";
		}
		model.addAllAttributes(pageService.frontData(realPath, ftlPath, ConstantDefine.DYNAMIC_EXTENTION));
		return ftlPath;
	}

	@RequestMapping(value = ConstantDefine.AdminUrl.BASE + "/front/stc")
	@ResponseBody
	public Map<String, Object> staticPage(String channelPath) throws Exception {
		pageService.staticFrontPage(channelPath);
		Map<String, Object> result = new HashMap<String, Object>();
		return result;
	}

	/**
	 * 删除静态文件
	 * 
	 * @param channelPath
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = ConstantDefine.AdminUrl.BASE + "/front/delStc")
	@ResponseBody
	public Map<String, Object> delStc() throws Exception {
		pageService.delStcPage();
		Map<String, Object> result = new HashMap<String, Object>();
		return result;
	}

}
