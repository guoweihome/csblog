package com.csdig.cms.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.csdig.cms.common.ConstantDefine;
import com.csdig.cms.common.RealPathResolver;
import com.csdig.cms.dao.CmsChannelAttrDAO;
import com.csdig.cms.dao.CmsChannelDAO;
import com.csdig.cms.dao.CmsModelTplDAO;
import com.csdig.cms.model.CmsChannel;
import com.csdig.cms.model.CmsChannelAttr;
import com.csdig.cms.model.CmsModelTpl;
import com.csdig.cms.service.FrontPageService;

import freemarker.template.Template;
import freemarker.template.TemplateException;

@Service
public class FrontPageServiceImpl implements FrontPageService {

	public static final String SYS_VAR_CHILDREN = "_sys_children";
	public static final String CHANNEL_OBJECT = "CHANNEL_OBJECT";
	public static final String SYS_VAR_PARENT = "_sys_parent";

	@Autowired
	private CmsChannelDAO channelDao;

	@Autowired
	private CmsChannelAttrDAO channelAttrDao;

	@Autowired
	private CmsModelTplDAO modelTplDao;

	@Autowired
	private RealPathResolver realPathResolver;

	@Resource(name = "freemarkerConfig")
	private FreeMarkerConfigurer conf;

	@Override
	public String findTemplateByChanelPath(String channelPath) throws Exception {
		List<Map<String, Object>> list = channelDao.findChannlAndModel(channelPath);
		if (list != null && list.size() > 0) {
			return (String) list.get(0).get("model_path");
		}
		return null;
	}

	@Override
	public Map<String, Object> frontData(String channelPath, String ftlPath, String extention) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();

		CmsChannel channel = channelDao.findByPath(channelPath);
		// 先把本栏目的数据放到model中
		model.putAll(this.frontData(channel));

		if (channel.getIsPcRelat()) {
			if (channel.getParentId().intValue() == -1) {
				//
				List<CmsChannel> children = channelDao.findByPid(channel.getChannelId());
				if (children != null && children.size() > 0) {
					List<Map<String, Object>> childModelList = new ArrayList<Map<String, Object>>();
					for (CmsChannel child : children) {
						// String childChannelPath = child.getChannelPath();
						Map<String, Object> childMap = frontData(child);
						childModelList.add(childMap);
					}
					// 把关联的子节点加入到model中
					model.put(SYS_VAR_CHILDREN, childModelList);
				}
			} else {
				// 把父节点找出来
				CmsChannel parentChannel = channelDao.findById(channel.getParentId());
				if (parentChannel != null) {
					Map<String, Object> parentMap = frontData(parentChannel);
					model.put(SYS_VAR_PARENT, parentMap);
				}
			}

		}

		// 固定参数
		model.put("_sys_channel_path", channelPath);
		model.put("_sys_now", new Date());
		model.put("_sys_ftl_path", ftlPath);
		// model.put("_sys_refer", refer);
		model.put("_sys_extention", extention);
		return model;
	}

	private Map<String, Object> frontData(CmsChannel channel) throws Exception {
		String channelPath = channel.getChannelPath();
		Map<String, Object> modelMap = new HashMap<String, Object>();
		List<CmsChannelAttr> list = channelAttrDao.getByChannelPath(channelPath);
		if (list != null && list.size() > 0) {
			Map<String, List<Object>> map = new HashMap<String, List<Object>>();
			for (CmsChannelAttr attr : list) {
				String name = attr.getAttrName();
				Object value = attr.getAttrValue();
				if (value == null && attr.getDefValue() != null) {
					value = attr.getDefValue();
				}
				if (!attr.getIsSingle()) {
					List<Object> attrList = map.get(name);
					if (attrList == null) {
						attrList = new ArrayList<Object>();
					}
					attrList.add(value);
					map.put(name, attrList);
				}
				modelMap.put(name, value);
			}
			// 添加列表值
			for (Entry<String, List<Object>> entry : map.entrySet()) {
				modelMap.put(entry.getKey(), entry.getValue());
			}
			modelMap.put(CHANNEL_OBJECT, channel);
		}
		return modelMap;
	}

	@Override
	public void generateTplFile(String fileName) throws Exception {
		// HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
		// .getRequest();
		//
		// String base = request.getSession().getServletContext().getRealPath("/");
		// String dir = base + File.separator + "WEB-INF" + File.separator + "ftls";

		String dir = realPathResolver.get(ConstantDefine.TPL_BASE);

		CmsModelTpl tpl = modelTplDao.findByModelPath(fileName);
		if (tpl != null) {
			File file = new File(dir, fileName + ConstantDefine.TPL_EXTENTION);
			FileUtils.write(file, tpl.getTxt(), "UTF-8");
		}
	}

	@Override
	public void staticFrontPage(String channelPathParam) throws Exception {
		List<Map<String, Object>> list = channelDao.findChannlAndModel(channelPathParam);
		if (list != null && list.size() > 0) {
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
					.getRequest();
			for (Map<String, Object> channelMap : list) {
				// 获取channel访问路径
				String channelPath = (String) channelMap.get("channel_path");
				String modelPath = (String) channelMap.get("model_path");

				generateTplFile(modelPath);

				Map<String, Object> data = frontData(channelPath, modelPath, ConstantDefine.STATIC_EXTENTION);
				data.put("base", request.getContextPath());
				data.put("admin_base", request.getContextPath() + ConstantDefine.AdminUrl.BASE);
				staticPage(channelPath + ConstantDefine.STATIC_EXTENTION, modelPath + ConstantDefine.TPL_EXTENTION,
						data);
			}
		}

	}

	private void staticPage(String requestPath, String tpl, Map<String, Object> data) throws IOException,
			TemplateException {
		long time = System.currentTimeMillis();
		File f = new File(realPathResolver.get("/"), requestPath);
		File parent = f.getParentFile();
		if (!parent.exists()) {
			parent.mkdirs();
		}
		Writer out = null;
		try {
			out = new OutputStreamWriter(new FileOutputStream(f), "UTF-8");
			Template template = conf.getConfiguration().getTemplate(tpl);
			template.process(data, out);
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}

		if (ConstantDefine.STATIC_EXTENTION.equals(".jsp") && f.exists()) {
			String temp = FileUtils.readFileToString(f, "utf-8");
			temp = "<%@ page language=\"java\" pageEncoding=\"UTF-8\"%>" + temp;
			FileUtils.write(f, temp, "utf-8");
		}

		time = System.currentTimeMillis() - time;
	}

	@Override
	public void delStcPage() throws Exception {
		List<CmsChannel> list = channelDao.listAll();
		if (list != null) {
			for (CmsChannel channel : list) {
				String channelPath = channel.getChannelPath();
				File f = new File(realPathResolver.get("/"), channelPath + ConstantDefine.STATIC_EXTENTION);
				if (f.exists()) {
					f.delete();
				}
			}
		}

	}
	

}
