package com.csdig.cms.dao;

import java.util.List;

import com.csdig.cms.model.CmsChannelAttr;
import com.csdig.db.model.Pagination;


public interface CmsChannelAttrDAO {


    public void add(CmsChannelAttr vo) throws Exception;

    public void update(CmsChannelAttr vo) throws Exception;

    public void delete(CmsChannelAttr vo) throws Exception;

    public CmsChannelAttr findById(java.lang.Integer id) throws Exception;

    public List<CmsChannelAttr> listAll() throws Exception;

    public Pagination<CmsChannelAttr> findByCondition(CmsChannelAttr vo, int pageNo, int pageSize)throws Exception;
    
    public List<CmsChannelAttr> findByCondition(CmsChannelAttr bean) throws Exception;
    
    public void deleteByChannelId(int channelId) throws Exception;
    
    public List<CmsChannelAttr> getByChannelId(int channelId) throws Exception;
    
    public List<CmsChannelAttr> getByChannelIdAndAttrName(int channelId,String attrName) throws Exception;
    
    public List<CmsChannelAttr> getByChannelPath(String channelPath) throws Exception;
    
    public void cleanDirtyData() throws Exception;
    
}
