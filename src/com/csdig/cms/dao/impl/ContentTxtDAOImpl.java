package com.csdig.cms.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.csdig.cms.dao.ContentTxtDAO;
import com.csdig.cms.model.ContentTxt;
import com.csdig.db.DBUtils;
import com.csdig.db.model.Pagination;
import com.csdig.db.model.Sql;

@Repository
public class ContentTxtDAOImpl implements ContentTxtDAO {
	

	@Override
	public void add(ContentTxt vo) throws Exception {
	    Sql sql = new Sql("insert into content_txt(content_id,txt,txt1)");
	    sql.append("values(:content_id,:txt,:txt1)");
	   	sql.set("content_id",vo.getContentId());
	   	sql.set("txt",vo.getTxt());
	   	sql.set("txt1",vo.getTxt1());
	    DBUtils.exeUpdate(sql);
	}

	@Override
	public void delete(ContentTxt vo) throws Exception {
	    String sql = "delete from content_txt where ";
	    DBUtils.exeUpdate(sql, new Object[] { });
	}

	@Override
	public Pagination<ContentTxt> findByCondition(ContentTxt vo, int pageNo,
			int pageSize) throws Exception{
	    Sql sql=new Sql("select * from content_txt where 1=1 ");
	    ///add condtion here  
	    return DBUtils.queryPagination(sql, null, pageNo, pageSize,ContentTxt.class);
	}

	@Override
	public List<ContentTxt> findByCondition(ContentTxt vo) throws Exception{
	    Sql sql=new Sql("select * from content_txt where 1=1 ");
	    ///add condtion here 
	    return DBUtils.queryBeanList(sql, ContentTxt.class);
	}


	@Override
	public List<ContentTxt> listAll() throws Exception {
	    return findByCondition(null);
	}

	@Override
	public void update(ContentTxt vo) throws Exception {
	    String sql = "update content_txt set content_id=?,txt=?,txt1=?  where ";
	    DBUtils.exeUpdate(sql,new Object[]{vo.getContentId(),vo.getTxt(),vo.getTxt1(),});
	}
    
}
