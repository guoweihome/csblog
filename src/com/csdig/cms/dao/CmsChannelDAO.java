package com.csdig.cms.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.cache.annotation.Cacheable;

import com.csdig.cms.model.CmsChannel;
import com.csdig.db.model.Pagination;


public interface CmsChannelDAO {


    public Integer add(CmsChannel vo) throws Exception;

    public void update(CmsChannel vo) throws Exception;

    public void delete(CmsChannel vo) throws Exception;

    public CmsChannel findById(java.lang.Integer id) throws Exception;

    public List<CmsChannel> listAll() throws Exception;
    
    @Cacheable(value="com.sohu.auto.cms.dao.CmsChannelDAO.listAllFromCache")
    public List<CmsChannel> listAllFromCache() throws Exception;

    public Pagination<CmsChannel> findByCondition(CmsChannel vo, int pageNo, int pageSize)throws Exception;
    
    public List<CmsChannel> findByCondition(CmsChannel bean)throws Exception;
    
    public List<Map<String,Object>> findChannlAndModel(String channelPath)throws SQLException;
    
    public CmsChannel findByPath(String channelPath) throws SQLException;

	public List<CmsChannel> findByPid(Integer parentId)throws SQLException;
}
