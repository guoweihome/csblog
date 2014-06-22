package com.csdig.cms.dao;

import java.util.List;

import com.csdig.cms.model.AppType;
import com.csdig.db.model.Pagination;


public interface AppTypeDAO {


    public void add(AppType vo) throws Exception;

    public void update(AppType vo) throws Exception;

    public void delete(AppType vo) throws Exception;

    public AppType findById(java.lang.Integer id) throws Exception;

    public List<AppType> listAll() throws Exception;

    public Pagination<AppType> findByCondition(AppType vo, int pageNo, int pageSize)throws Exception;
    
    public List<AppType> findByCondition(AppType bean)throws Exception;
    
    public List<AppType> findAll(String order) throws Exception;
    
}
