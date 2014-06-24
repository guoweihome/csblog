package com.csdig.cms.dao;

import java.util.List;

import com.csdig.cms.model.ContentTxt;
import com.csdig.db.model.Pagination;


public interface ContentTxtDAO {


    public void add(ContentTxt vo) throws Exception;

    public void update(ContentTxt vo) throws Exception;

    public void delete(ContentTxt vo) throws Exception;

    public List<ContentTxt> listAll() throws Exception;

    public Pagination<ContentTxt> findByCondition(ContentTxt vo, int pageNo, int pageSize)throws Exception;
    
    public List<ContentTxt> findByCondition(ContentTxt bean)throws Exception;
    
}
