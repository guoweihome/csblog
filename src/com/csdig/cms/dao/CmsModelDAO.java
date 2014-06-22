package com.csdig.cms.dao;

import java.sql.SQLException;
import java.util.List;

import com.csdig.cms.model.CmsModel;
import com.csdig.db.model.Pagination;


public interface CmsModelDAO {


    public Integer add(CmsModel vo) throws Exception;

    public void update(CmsModel vo) throws Exception;

    public void delete(Integer modelId) throws Exception;

    public CmsModel findById(java.lang.Integer id) throws Exception;

    public List<CmsModel> listAll() throws Exception;

    public Pagination<CmsModel> findByCondition(CmsModel vo, int pageNo, int pageSize)throws Exception;
    
    public List<CmsModel> findByCondition(CmsModel bean)throws Exception;
    
    public List<CmsModel> findByPath(String path)throws SQLException;
    
}
