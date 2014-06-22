package com.csdig.cms.web.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.csdig.cms.common.ConstantDefine;
import com.csdig.cms.model.CmsModel;
import com.csdig.cms.model.CmsModelItem;
import com.csdig.cms.model.CmsModelTpl;
import com.csdig.cms.model.binder.CmsModelItemBinder;
import com.csdig.cms.service.ModelService;

@Controller
@RequestMapping(ConstantDefine.AdminUrl.BASE + "/model")
public class ModelController {

	@Autowired
	private ModelService modelService;

	@RequestMapping(value = "/{name}View")
	public String list(@PathVariable String name) throws Exception {
		return "model/" + name;
	}

	@RequestMapping(value = "listModelData")
	@ResponseBody
	public Map<String, Object> listModelData() throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		List<CmsModel> list = modelService.listAllModel();
		result.put("total", list.size());
		result.put("rows", list);
		return result;
	}

	@RequestMapping(value = "save")
	@ResponseBody
	public Map<String, Object> save(CmsModel model) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		modelService.saveModel(model);
		return result;
	}

	// 删除
	@RequestMapping(value = "del")
	@ResponseBody
	public Map<String, Object> del(Integer modelId) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		modelService.delModel(modelId);
		return result;
	}

	// 编辑界面
	@RequestMapping(value = "editView")
	public String editView(HttpServletRequest request, Integer modelId) throws Exception {
		Assert.notNull(modelId, "参数错误");
		request.setAttribute("vo", modelService.findById(modelId));
		return "model/edit";
	}

	// 保存更新
	@RequestMapping(value = "update")
	@ResponseBody
	public Map<String, Object> update(HttpServletRequest request, CmsModel model) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		modelService.update(model);
		return result;
	}

	// 编辑子栏目
	@RequestMapping(value = "subItem")
	public String subItem(HttpServletRequest request, Integer modelId) throws Exception {
		Assert.notNull(modelId, "参数错误");
		request.setAttribute("vo", modelService.findById(modelId));
		CmsModelTpl tpl = modelService.findTplByModelId(modelId);
		List<CmsModelItem> list = modelService.findModelItemByModelId(modelId);
		request.setAttribute("tpl", tpl);
		request.setAttribute("list", list);
		return "model/subItem";
	}

	@RequestMapping(value = "saveSubItem")
	@ResponseBody
	public Map<String, Object> saveSubItem(Integer modelId, String tplTxt, CmsModelItemBinder items)
			throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		modelService.saveModelItems(modelId, tplTxt, items.getModelItems());
		return result;
	}

}
