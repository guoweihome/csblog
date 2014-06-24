package com.csdig.cms.web.admin;

import java.io.File;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.csdig.cms.common.ConstantDefine;
import com.csdig.cms.common.RealPathResolver;
import com.csdig.cms.model.Channel;
import com.csdig.cms.model.CmsChannel;
import com.csdig.cms.model.CmsChannelAttr;
import com.csdig.cms.model.CmsModelItem;
import com.csdig.cms.service.ChannelAttrService;
import com.csdig.cms.service.ChannelService;
import com.csdig.cms.service.ModelService;

@Controller
@RequestMapping(value = ConstantDefine.AdminUrl.BASE + "/channelAttr")
public class ChannelAttrController {

	@Autowired
	private ChannelService channelService;

	@Autowired
	private ModelService modelService;

	@Autowired
	private ChannelAttrService channelAttrService;

	@Autowired
	private RealPathResolver realPathResolver;

	@RequestMapping(value = "list")
	public ModelAndView list(int channelId) throws Exception {
		ModelAndView modelAndView = new ModelAndView("channelAttr/list");
		modelAndView.addObject("channelId", channelId);
		Channel channel = channelService.getById(channelId);
		modelAndView.addObject("channelName", channel.getChannelName());
		return modelAndView;
	}

	@RequestMapping(value = "getByChannelId")
	@ResponseBody
	public Map<String, Object> getByChannelId(int channelId) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		List<CmsChannelAttr> channelAttrList = channelAttrService.getByChannelId(channelId);
		result.put("total", channelAttrList.size());
		result.put("rows", channelAttrList);
		return result;
	}

	@RequestMapping(value = "add")
	public String add(int channelId) throws Exception {
//		ModelAndView modelAndView = new ModelAndView("channelAttr/add");
//		Channel channel = channelService.getById(channelId);
//		List<CmsModelItem> modelItemList = modelService.findModelItemByModelId(channel.getModelId());
//
//		Map<String, CmsModelItem> modelItemMap = new HashMap<String, CmsModelItem>();
//		for (CmsModelItem modelItem : modelItemList) {
//			modelItemMap.put(modelItem.getField(), modelItem);
//		}
//
//		List<CmsChannelAttr> channelAttrList = channel.getChannelAttrs();
//		for (CmsChannelAttr attr : channelAttrList) {
//			CmsModelItem modelItem = modelItemMap.get(attr.getAttrName());
//			if (modelItem.getIsRequired() && !modelItem.getIsSingle()) {
//				continue;
//			}
//			modelItemList.remove(modelItem);
//		}
//
//		modelAndView.addObject("channelId", channelId);
//		modelAndView.addObject("modelItemList", modelItemList);
//		return modelAndView;
		return "add";
	}

	@RequestMapping(value = "save")
	@ResponseBody
	public Map<String, Object> save(CmsChannelAttr channelAttr) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		channelAttrService.add(channelAttr);
		return result;
	}

	@RequestMapping(value = "update")
	public ModelAndView update(int channelAttrId) throws Exception {
		ModelAndView modelAndView = new ModelAndView("channelAttr/edit");
		CmsChannelAttr channelAttr = channelAttrService.getById(channelAttrId);
		modelAndView.addObject("channelAttr", channelAttr);
		return modelAndView;
	}

	@RequestMapping(value = "doUpdate")
	@ResponseBody
	public Map<String, Object> doUpdate(CmsChannelAttr channelAttr) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		channelAttrService.update(channelAttr);
		return result;
	}

	@RequestMapping(value = "delete")
	@ResponseBody
	public Map<String, Object> delete(int channelId, int channelAttrId) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		CmsChannelAttr channelAttr = channelAttrService.getById(channelAttrId);
		CmsModelItem modelItem = channelAttr.getModelItem();

		if (modelItem.getIsRequired()) {
			if (!modelItem.getIsSingle()) {
				List<CmsChannelAttr> channelAttrList = channelAttrService.getByChannelIdAndAttrName(channelId,
						modelItem.getField());
				if (channelAttrList != null && channelAttrList.size() <= 1) {
					result.put("STATUS", 300);
					result.put(
							"msg",
							String.format("该条记录是模板条目 %s[%s] 的最后一条栏目属性，不可删除!", modelItem.getItemLabel(),
									modelItem.getField()));
					return result;
				}
			} else {
				result.put("STATUS", 300);
				result.put("msg", "该栏目属性必须存在，不可删除！");
				return result;
			}
		}

		channelAttrService.delete(channelAttrId);
		return result;
	}

	@RequestMapping(value = "imageSelect")
	@ResponseBody
	public Map<String, Object> imageSelect(HttpServletRequest request, String path, String order, String dir)
			throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		String currentUrl = "", moveupDirPath = "";
		if (StringUtils.isEmpty(path)) {
			path = ConstantDefine.ROOT_RES_DIR+"/";
		}
		String currentPath = realPathResolver.get(path);
		currentUrl = request.getContextPath() + path;
		// 目录不存在或不是目录
		File currentPathFile = new File(currentPath);

		if (!StringUtils.equals(path, ConstantDefine.ROOT_RES_DIR+"/")) {
			String str = path.substring(0, path.length() - 1);
			moveupDirPath = str.lastIndexOf("/") >= 0 ? str.substring(0, str.lastIndexOf("/") + 1) : "";
		}

		List<Hashtable<String, Object>> fileList = channelAttrService.listSelectFiles(currentPathFile, order);

		result.put("moveup_dir_path", moveupDirPath);
		result.put("current_dir_path", path);
		result.put("current_url", currentUrl);
		result.put("total_count", fileList.size());
		result.put("file_list", fileList);

		return result;
	}

}
