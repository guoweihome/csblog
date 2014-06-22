package com.csdig.cms.dao;

import java.util.List;

import com.csdig.cms.model.CmsFaq;
import com.csdig.db.model.Pagination;


public interface CmsFaqDAO {


    public void add(CmsFaq vo) throws Exception;

    public void update(CmsFaq vo) throws Exception;

    public void delete(CmsFaq vo) throws Exception;

    public CmsFaq findById(java.lang.Integer id) throws Exception;

    public List<CmsFaq> listAll() throws Exception;

    public Pagination<CmsFaq> findByCondition(CmsFaq vo, int pageNo, int pageSize)throws Exception;
    
    public List<CmsFaq> findByCondition(CmsFaq bean) throws Exception;
    
    public void deleteByChannelId(int channelId) throws Exception;
    
    public List<CmsFaq> findByCondition(int channelId, String orderDesc) throws Exception;
    
}
