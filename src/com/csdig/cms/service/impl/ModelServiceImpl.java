package com.csdig.cms.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.csdig.cms.dao.CmsChannelAttrDAO;
import com.csdig.cms.dao.CmsModelDAO;
import com.csdig.cms.dao.CmsModelItemDAO;
import com.csdig.cms.dao.CmsModelTplDAO;
import com.csdig.cms.exception.BusinessException;
import com.csdig.cms.model.CmsModel;
import com.csdig.cms.model.CmsModelItem;
import com.csdig.cms.model.CmsModelTpl;
import com.csdig.cms.service.ModelService;

@Service
@Transactional
public class ModelServiceImpl implements ModelService {

	@Autowired
	private CmsModelDAO modelDao;

	@Autowired
	private CmsModelTplDAO modelTplDao;

	@Autowired
	private CmsModelItemDAO modelItemDao;
	
	@Autowired
	private CmsChannelAttrDAO channelAttrDao;

	@Override
	public List<CmsModel> listAllModel() throws Exception {
		return modelDao.listAll();
	}

	@Override
	public void saveModel(CmsModel model) throws Exception {
		Assert.notNull(model, "参数错误");
		List<CmsModel> list = modelDao.findByPath(model.getModelPath());
		if (list != null && list.size() > 0) {
			throw new BusinessException("", "文件地址已存在，请更换其他文件地址");
		}

		Integer modelId = modelDao.add(model);

		// 保存模板
		CmsModelTpl tpl = new CmsModelTpl();
		tpl.setModelId(modelId);
		tpl.setTxt("");
		modelTplDao.add(tpl);
	}

	@Override
	public void delModel(Integer modelId) throws Exception {
		modelDao.delete(modelId);
	}

	@Override
	public CmsModel findById(Integer modelId) throws Exception {
		return modelDao.findById(modelId);
	}

	@Override
	public void update(CmsModel model) throws Exception {
		Assert.notNull(model, "参数错误");
		CmsModel oldModel = modelDao.findById(model.getModelId());
		if (!StringUtils.equals(model.getModelPath(), oldModel.getModelPath())) {

		}
		modelDao.update(model);
	}

	@Override
	public CmsModelTpl findTplByModelId(Integer modelId) throws Exception {
		return modelTplDao.findByModelId(modelId);
	}

	@Override
	public List<CmsModelItem> findModelItemByModelId(Integer modelId) throws Exception {
		return modelItemDao.findByModelId(modelId);
	}

	@Override
	public void saveModelItems(Integer modelId, String tplTxt, List<CmsModelItem> modelItems) throws Exception {
		// 保存模板
		CmsModelTpl tpl = modelTplDao.findByModelId(modelId);
		if (tpl == null) {
			tpl = new CmsModelTpl();
			tpl.setModelId(modelId);
			tpl.setTxt(tplTxt);
			modelTplDao.add(tpl);
		} else {
			tpl.setTxt(tplTxt);
			modelTplDao.update(tpl);
		}
		// 保存子栏目
		modelItemDao.deleteByModelId(modelId);
		if (modelItems != null) {
			for (int i = 0; i < modelItems.size(); i++) {
				CmsModelItem item = modelItems.get(i);
				item.setModelId(modelId);
				modelItemDao.add(item);
			}
		}
		//清楚channel_attr表中的垃圾字段
		//channelAttrDao.cleanDirtyDataByModelId();
	}

	@Override
	public List<CmsModelItem> getModelItem(int channelId) throws Exception {

		return null;
	}

	@Override
	public CmsModelItem getModelItemByModelIdAndField(int modelId, String field) throws Exception {
		return modelItemDao.getByModelIdAndField(modelId, field);
	}

}
