package com.csdig.cms.dao;

import java.util.List;

import com.csdig.cms.model.CmsAppChangeLogItem;
import com.csdig.db.model.Pagination;


public interface CmsAppChangeLogItemDAO {


    public void add(CmsAppChangeLogItem vo) throws Exception;

    public void update(CmsAppChangeLogItem vo) throws Exception;

    public void delete(CmsAppChangeLogItem vo) throws Exception;

    public CmsAppChangeLogItem findById(java.lang.Integer id) throws Exception;

    public List<CmsAppChangeLogItem> listAll() throws Exception;

    public Pagination<CmsAppChangeLogItem> findByCondition(CmsAppChangeLogItem vo, int pageNo, int pageSize)throws Exception;
    
    public List<CmsAppChangeLogItem> findByCondition(CmsAppChangeLogItem bean)throws Exception;
    
	public List<CmsAppChangeLogItem> findByLogId(int logId) throws Exception;
    
    public void deleteByLogId(int logId) throws Exception;
    
}
