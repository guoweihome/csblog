package com.csdig.cms.service;

import java.util.List;

import com.csdig.cms.model.CmsModel;
import com.csdig.cms.model.CmsModelItem;
import com.csdig.cms.model.CmsModelTpl;

public interface ModelService {

	public List<CmsModel> listAllModel() throws Exception;

	public void saveModel(CmsModel model) throws Exception;

	public void delModel(Integer modelId) throws Exception;

	public CmsModel findById(Integer modelId) throws Exception;

	public void update(CmsModel model) throws Exception;

	public CmsModelTpl findTplByModelId(Integer modelId) throws Exception;

	public List<CmsModelItem> findModelItemByModelId(Integer modelId) throws Exception;

	public void saveModelItems(Integer modelId, String tplTxt, List<CmsModelItem> modelItems)
			throws Exception;

	public List<CmsModelItem> getModelItem(int channelId) throws Exception;
	
	public CmsModelItem getModelItemByModelIdAndField(int modelId,String field) throws Exception;
}
