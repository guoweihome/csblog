package com.csdig.cms.dao;

import java.util.List;

import com.csdig.cms.model.CmsModelTpl;
import com.csdig.db.model.Pagination;


public interface CmsModelTplDAO {


    public void add(CmsModelTpl vo) throws Exception;

    public void update(CmsModelTpl vo) throws Exception;

    public void deleteByModelId(Integer modelId) throws Exception;

    public CmsModelTpl findById(java.lang.Integer id) throws Exception;
   
    public CmsModelTpl findByModelId(java.lang.Integer modelId) throws Exception;

    public List<CmsModelTpl> listAll() throws Exception;

    public Pagination<CmsModelTpl> findByCondition(CmsModelTpl vo, int pageNo, int pageSize)throws Exception;
    
    public List<CmsModelTpl> findByCondition(CmsModelTpl bean)throws Exception;
    
    
    public CmsModelTpl findByModelPath(String modelPath)throws Exception;
    
}
