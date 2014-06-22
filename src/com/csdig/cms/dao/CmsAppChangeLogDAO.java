package com.csdig.cms.dao;

import java.util.List;

import com.csdig.cms.model.CmsAppChangeLog;
import com.csdig.db.model.Pagination;


public interface CmsAppChangeLogDAO {


    public Integer add(CmsAppChangeLog vo) throws Exception;

    public void update(CmsAppChangeLog vo) throws Exception;

    public void delete(CmsAppChangeLog vo) throws Exception;

    public CmsAppChangeLog findById(java.lang.Integer id) throws Exception;

    public List<CmsAppChangeLog> listAll() throws Exception;

    public Pagination<CmsAppChangeLog> findByCondition(CmsAppChangeLog vo, int pageNo, int pageSize)throws Exception;
    
    public List<CmsAppChangeLog> findByCondition(CmsAppChangeLog bean)throws Exception;
    
    public List<CmsAppChangeLog> getByChannelId(int channelId) throws Exception; 
    
    public void deleteByChannelId(int channelId) throws Exception;
    
    public List<CmsAppChangeLog> getByCondition(int channelId,String appType, String orderDesc) throws Exception;
    
    public List<CmsAppChangeLog> getByCondition(int channelId, String orderDesc) throws Exception;
    
}
