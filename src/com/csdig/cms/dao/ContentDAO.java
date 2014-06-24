package com.csdig.cms.dao;

import java.util.List;

import com.csdig.cms.model.Content;
import com.csdig.db.model.Pagination;


public interface ContentDAO {


    public void add(Content vo) throws Exception;

    public void update(Content vo) throws Exception;

    public void delete(Content vo) throws Exception;

    public Content findById(java.lang.Integer id) throws Exception;

    public List<Content> listAll() throws Exception;

    public Pagination<Content> findByCondition(Content vo, int pageNo, int pageSize)throws Exception;
    
    public List<Content> findByCondition(Content bean)throws Exception;
    
}
