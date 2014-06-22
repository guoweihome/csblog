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

	/**
	 * 下载
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "doDownLoad")
	@ResponseBody
	public Map<String, String> doDownLoad(HttpServletRequest request, String productName, String pvid, String loadType)
			throws Exception {
		String userAgentStr = request.getHeader("User-Agent");
		String referer = (String) request.getSession().getAttribute(ConstantDefine.SessionAttr.REFER);
		String downloadUrl = "";
		// 买车宝
		if ("mcbao".equals(productName)) {
			if (userAgentStr.toLowerCase().indexOf("iphone") > 0 || "iPhone".equals(loadType)) {
				if (userAgentStr.toLowerCase().indexOf("micromessenger") > 0) {// 浏览器来自微信
					downloadUrl = ConstantDefine.MCBAO_IPHONE_DOWNLOAD_URL_WEIXIN;
				} else {
					downloadUrl = ConstantDefine.MCBAO_IPHONE_DOWNLOAD_URL;
				}
			}
			if (userAgentStr.toLowerCase().indexOf("android") > 0 || "android".equals(loadType)) {
				downloadUrl = ConstantDefine.MCBAO_ANDROID_DOWNLOAD_URL;
			}
		}
		// 搜狐汽车
		if ("shqc".equals(productName)) {
			if (userAgentStr.toLowerCase().indexOf("iphone") > 0 || "iPhone".equals(loadType)) {
				if (userAgentStr.toLowerCase().indexOf("micromessenger") > 0) {// 浏览器来自微信
					downloadUrl = ConstantDefine.SHQC_IPHONE_DOWNLOAD_URL_WEIXIN;
				} else {
					downloadUrl = ConstantDefine.SHQC_IPHONE_DOWNLOAD_URL;
				}
			}
			if (userAgentStr.toLowerCase().indexOf("android") > 0 || "android".equals(loadType)) {
				if (ConstantDefine.SHOU_SOU_PVID.equals(pvid))
					downloadUrl = ConstantDefine.SHQC_ANDROID_DOWNLOAD_SHOUSOU_URL;
				else
					downloadUrl = ConstantDefine.SHQC_ANDROID_DOWNLOAD_URL;
			}
		}

		// 违章查询
		if ("wzcx".equals(productName)) {
			if (userAgentStr.toLowerCase().indexOf("iphone") > 0 || "iPhone".equals(loadType)) {
				if (userAgentStr.toLowerCase().indexOf("micromessenger") > 0) {// 浏览器来自微信
					downloadUrl = ConstantDefine.WZCX_IPHONE_DOWNLOAD_URL_WEIXIN;
				} else {
					downloadUrl = ConstantDefine.WZCX_IPHONE_DOWNLOAD_URL;
				}
			}
			if (userAgentStr.toLowerCase().indexOf("android") > 0 || "android".equals(loadType)) {
				String apkname = commonService.getPromotionAppName(ConstantDefine.WZCX_APPID, referer);
				if (apkname != null) {
					downloadUrl = "http://mobile.auto.sohu.com/download/" + apkname;
				} else if (ConstantDefine.SHOU_SOU_PVID.equals(pvid)) {
					downloadUrl = ConstantDefine.WZCX_ANDROID_DOWNLOAD_SHOUSOU_URL;
				} else {
					downloadUrl = ConstantDefine.WZCX_ANDROID_DOWNLOAD_URL;
				}
			}
		}
		// 违章查询wap
		if ("wzcx_wap".equals(productName)) {
			if (userAgentStr.toLowerCase().indexOf("iphone") > 0 || "iPhone".equals(loadType)) {
				if (userAgentStr.toLowerCase().indexOf("micromessenger") > 0) {// 浏览器来自微信
					downloadUrl = ConstantDefine.WZCX_IPHONE_DOWNLOAD_URL_WEIXIN;
				} else {
					downloadUrl = ConstantDefine.WZCX_IPHONE_DOWNLOAD_URL;
				}
			}
			if (userAgentStr.toLowerCase().indexOf("android") > 0 || "android".equals(loadType)) {
				String apkname = commonService.getPromotionAppName(ConstantDefine.WZCX_APPID, referer);
				;
				if (apkname != null) {
					downloadUrl = "http://mobile.auto.sohu.com/download/" + apkname;
				} else if (ConstantDefine.SHOU_SOU_PVID.equals(pvid)) {
					downloadUrl = ConstantDefine.WZCX_ANDROID_DOWNLOAD_SHOUSOU_URL;
				} else {
					downloadUrl = ConstantDefine.WZCX_WAP_ANDROID_DOWNLOAD_URL;
				}
			}
		}

		if ("wzcx_carexpro".equals(productName)) {
			if (userAgentStr.toLowerCase().indexOf("iphone") > 0 || "iPhone".equals(loadType)) {
				if (userAgentStr.toLowerCase().indexOf("micromessenger") > 0) {// 浏览器来自微信
					downloadUrl = ConstantDefine.WZCX_IPHONE_DOWNLOAD_URL_WEIXIN;
				} else {
					downloadUrl = ConstantDefine.WZCX_IPHONE_DOWNLOAD_URL;
				}
			}
			if (userAgentStr.toLowerCase().indexOf("android") > 0 || "android".equals(loadType)) {
				String apkname = "DriverHelper_chezhan.apk";
				downloadUrl = "http://mobile.auto.sohu.com/download/" + apkname;
			}
		}
		// 汽车投诉
		if ("qcts".equals(productName)) {
			if (userAgentStr.toLowerCase().indexOf("iphone") > 0 || "iPhone".equals(loadType)) {
				if (userAgentStr.toLowerCase().indexOf("micromessenger") > 0) {// 浏览器来自微信
					downloadUrl = ConstantDefine.QCTS_IPHONE_DOWNLOAD_URL_WEIXIN;
				} else {
					downloadUrl = ConstantDefine.QCTS_IPHONE_DOWNLOAD_URL;
				}
			}
			if (userAgentStr.toLowerCase().indexOf("android") > 0 || "android".equals(loadType)) {
				if (ConstantDefine.SHOU_SOU_PVID.equals(pvid))
					downloadUrl = ConstantDefine.QCTS_ANDROID_DOWNLOAD_SHOUSOU_URL;
				else
					downloadUrl = ConstantDefine.QCTS_ANDROID_DOWNLOAD_URL;
			}
		}
		// 加油驿站
		if ("jyez".equals(productName)) {
			if (userAgentStr.toLowerCase().indexOf("iphone") > 0 || "iPhone".equals(loadType)) {
				if (userAgentStr.toLowerCase().indexOf("micromessenger") > 0) {// 浏览器来自微信
					downloadUrl = ConstantDefine.JYEZ_IPHONE_DOWNLOAD_URL_WEIXIN;
				} else {
					downloadUrl = ConstantDefine.JYEZ_IPHONE_DOWNLOAD_URL;
				}
			}
			if (userAgentStr.toLowerCase().indexOf("android") > 0 || "android".equals(loadType)) {
				downloadUrl = ConstantDefine.JYEZ_ANDROID_DOWNLOAD_URL;
			}
		}
		// 搜狐车讯
		if ("shcx".equals(productName)) {
			if (userAgentStr.toLowerCase().indexOf("iphone") > 0 || "iPhone".equals(loadType)) {
				downloadUrl = ConstantDefine.SHCX_IPHONE_DOWNLOAD_URL;
			}
			if (userAgentStr.toLowerCase().indexOf("android") > 0 || "android".equals(loadType)) {
				downloadUrl = ConstantDefine.SHCX_ANDROID_DOWNLOAD_URL;
			}
		}

		if ("app".equals(productName)) {
			if (userAgentStr.toLowerCase().indexOf("android") > 0 || "android".equals(loadType)) {
				downloadUrl = ConstantDefine.APP_ANDROID_DOWNLOAD_URL;
			}
		}

		if ("gfan".equals(productName)) {
			if (userAgentStr.toLowerCase().indexOf("android") > 0 || "android".equals(loadType)) {
				downloadUrl = ConstantDefine.GFAN_ANDROID_DOWNLOAD_URL;
			}
		}
		if ("anzhi".equals(productName)) {
			if (userAgentStr.toLowerCase().indexOf("android") > 0 || "android".equals(loadType)) {
				downloadUrl = ConstantDefine.ANZHI_ANDROID_DOWNLOAD_URL;
			}
		}
		if ("ershou".equals(productName)) {
			if (userAgentStr.toLowerCase().indexOf("iphone") > 0 || "iPhone".equals(loadType)) {
				if (userAgentStr.toLowerCase().indexOf("micromessenger") > 0) {// 浏览器来自微信
					downloadUrl = ConstantDefine.ERSHOU_IPHONE_DOWNLOAD_URL_WEIXIN;
				} else {
					downloadUrl = ConstantDefine.ERSHOU_IPHONE_DOWNLOAD_URL;
				}
			}
			if (userAgentStr.toLowerCase().indexOf("android") > 0 || "android".equals(loadType)) {
				if (ConstantDefine.SHOU_SOU_PVID.equals(pvid))
					downloadUrl = ConstantDefine.ERSHOU_ANDROID_DOWNLOAD_SHOUSOU_URL;
				else
					downloadUrl = ConstantDefine.ERSHOU_ANDROID_DOWNLOAD_URL;
			}
		}

		//
		if (StringUtils.isNotEmpty(pvid)) {
			if (downloadUrl.contains("?")) {
				downloadUrl += "&pvid=" + pvid;
			} else {
				downloadUrl += "?pvid=" + pvid;
			}
		}
		Map<String, String> result = new HashMap<String, String>();
		result.put("url", downloadUrl);
		return result;
	}

}
