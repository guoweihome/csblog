package com.csdig.cms.dao;

import java.util.List;

import com.csdig.cms.model.ChannelAttr;
import com.csdig.db.model.Pagination;


public interface ChannelAttrDAO {


    public void add(ChannelAttr vo) throws Exception;

    public void update(ChannelAttr vo) throws Exception;

    public void delete(ChannelAttr vo) throws Exception;

    public void deleteByChannelId(int id)throws Exception;
    
    public ChannelAttr findById(java.lang.Integer id) throws Exception;

    public List<ChannelAttr> listAll() throws Exception;

    public Pagination<ChannelAttr> findByCondition(ChannelAttr vo, int pageNo, int pageSize)throws Exception;
    
    public List<ChannelAttr> findByCondition(ChannelAttr bean)throws Exception;

	
    
}
