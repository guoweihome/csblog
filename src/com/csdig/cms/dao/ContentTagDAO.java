package com.csdig.cms.dao;

import java.util.List;

import com.csdig.cms.model.ContentTag;
import com.csdig.db.model.Pagination;


public interface ContentTagDAO {


    public void add(ContentTag vo) throws Exception;

    public void update(ContentTag vo) throws Exception;

    public void delete(ContentTag vo) throws Exception;

    public List<ContentTag> listAll() throws Exception;

    public Pagination<ContentTag> findByCondition(ContentTag vo, int pageNo, int pageSize)throws Exception;
    
    public List<ContentTag> findByCondition(ContentTag bean)throws Exception;
    
}
