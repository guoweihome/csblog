package com.csdig.cms.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.csdig.cms.dao.ContentTagDAO;
import com.csdig.cms.model.ContentTag;
import com.csdig.db.DBUtils;
import com.csdig.db.model.Pagination;
import com.csdig.db.model.Sql;

@Repository
public class ContentTagDAOImpl implements ContentTagDAO {
	

	@Override
	public void add(ContentTag vo) throws Exception {
	    Sql sql = new Sql("insert into content_tag(content_id,tag_id)");
	    sql.append("values(:content_id,:tag_id)");
	   	sql.set("content_id",vo.getContentId());
	   	sql.set("tag_id",vo.getTagId());
	    DBUtils.exeUpdate(sql);
	}

	@Override
	public void delete(ContentTag vo) throws Exception {
	    String sql = "delete from content_tag where ";
	    DBUtils.exeUpdate(sql, new Object[] { });
	}

	@Override
	public Pagination<ContentTag> findByCondition(ContentTag vo, int pageNo,
			int pageSize) throws Exception{
	    Sql sql=new Sql("select * from content_tag where 1=1 ");
	    ///add condtion here  
	    return DBUtils.queryPagination(sql, null, pageNo, pageSize,ContentTag.class);
	}

	@Override
	public List<ContentTag> findByCondition(ContentTag vo) throws Exception{
	    Sql sql=new Sql("select * from content_tag where 1=1 ");
	    ///add condtion here 
	    return DBUtils.queryBeanList(sql, ContentTag.class);
	}


	@Override
	public List<ContentTag> listAll() throws Exception {
	    return findByCondition(null);
	}

	@Override
	public void update(ContentTag vo) throws Exception {
	    String sql = "update content_tag set content_id=?,tag_id=?  where ";
	    DBUtils.exeUpdate(sql,new Object[]{vo.getContentId(),vo.getTagId(),});
	}
    
}
