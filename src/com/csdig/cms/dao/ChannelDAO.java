package com.csdig.cms.dao;

import java.util.List;

import com.csdig.cms.model.Channel;
import com.csdig.db.model.Pagination;


public interface ChannelDAO {


    public void add(Channel vo) throws Exception;

    public void update(Channel vo) throws Exception;

    public void delete(Integer id) throws Exception;

    public Channel findById(java.lang.Integer id) throws Exception;
    
    public List<Channel> findByPId(java.lang.Integer pid) throws Exception;

    public List<Channel> listAll() throws Exception;

    public Pagination<Channel> findByCondition(Channel vo, int pageNo, int pageSize)throws Exception;
    
    public List<Channel> findByCondition(Channel bean)throws Exception;
    
    public List<Channel> findByCondition(String[] fieldNames,Object[] values)throws Exception;
    
}
