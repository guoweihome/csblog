package com.csdig.cms.dao;

import java.util.List;

import com.csdig.cms.model.CmsModelItem;
import com.csdig.db.model.Pagination;


public interface CmsModelItemDAO {


    public void add(CmsModelItem vo) throws Exception;

    public void update(CmsModelItem vo) throws Exception;

    public void delete(CmsModelItem vo) throws Exception;
    
    public void deleteByModelId(Integer modelId) throws Exception;

    public CmsModelItem findById(java.lang.Integer id) throws Exception;

    public List<CmsModelItem> listAll() throws Exception;

    public Pagination<CmsModelItem> findByCondition(CmsModelItem vo, int pageNo, int pageSize)throws Exception;
    
	public List<CmsModelItem> findByCondition(CmsModelItem bean) throws Exception;

	public List<CmsModelItem> findByModelId(Integer modelId) throws Exception;
    
    public CmsModelItem getByModelIdAndField(int modelId, String field) throws Exception;
    
}
