package com.csdig.cms.dao;

import java.util.List;

import com.csdig.cms.model.ContentAttr;
import com.csdig.db.model.Pagination;


public interface ContentAttrDAO {


    public void add(ContentAttr vo) throws Exception;

    public void update(ContentAttr vo) throws Exception;

    public void delete(ContentAttr vo) throws Exception;

    public List<ContentAttr> listAll() throws Exception;

    public Pagination<ContentAttr> findByCondition(ContentAttr vo, int pageNo, int pageSize)throws Exception;
    
    public List<ContentAttr> findByCondition(ContentAttr bean)throws Exception;
    
}
