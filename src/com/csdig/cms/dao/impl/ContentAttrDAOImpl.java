package com.csdig.cms.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.csdig.cms.dao.ContentAttrDAO;
import com.csdig.cms.model.ContentAttr;
import com.csdig.db.DBUtils;
import com.csdig.db.model.Pagination;
import com.csdig.db.model.Sql;

@Repository
public class ContentAttrDAOImpl implements ContentAttrDAO {
	

	@Override
	public void add(ContentAttr vo) throws Exception {
	    Sql sql = new Sql("insert into content_attr(content_id,attr_name,attr_value)");
	    sql.append("values(:content_id,:attr_name,:attr_value)");
	   	sql.set("content_id",vo.getContentId());
	   	sql.set("attr_name",vo.getAttrName());
	   	sql.set("attr_value",vo.getAttrValue());
	    DBUtils.exeUpdate(sql);
	}

	@Override
	public void delete(ContentAttr vo) throws Exception {
	    String sql = "delete from content_attr where ";
	    DBUtils.exeUpdate(sql, new Object[] { });
	}

	@Override
	public Pagination<ContentAttr> findByCondition(ContentAttr vo, int pageNo,
			int pageSize) throws Exception{
	    Sql sql=new Sql("select * from content_attr where 1=1 ");
	    ///add condtion here  
	    return DBUtils.queryPagination(sql, null, pageNo, pageSize,ContentAttr.class);
	}

	@Override
	public List<ContentAttr> findByCondition(ContentAttr vo) throws Exception{
	    Sql sql=new Sql("select * from content_attr where 1=1 ");
	    ///add condtion here 
	    return DBUtils.queryBeanList(sql, ContentAttr.class);
	}

	@Override
	public List<ContentAttr> listAll() throws Exception {
	    return findByCondition(null);
	}

	@Override
	public void update(ContentAttr vo) throws Exception {
	    String sql = "update content_attr set content_id=?,attr_name=?,attr_value=?  where ";
	    DBUtils.exeUpdate(sql,new Object[]{vo.getContentId(),vo.getAttrName(),vo.getAttrValue(),});
	}
    
}
