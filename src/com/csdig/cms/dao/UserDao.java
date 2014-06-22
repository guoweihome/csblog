package com.csdig.cms.dao;

import java.util.List;

import com.csdig.cms.model.CmsUser;

public interface UserDao {

    public Integer add(CmsUser vo) throws Exception;

    public void update(CmsUser vo) throws Exception;

    public void delete(CmsUser vo) throws Exception;

    public CmsUser findById(java.lang.Integer id) throws Exception;
    
    public List<CmsUser> listAll() throws Exception;
    
    public CmsUser getByTelNum(String telNum) throws Exception;
    
}
