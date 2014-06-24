package com.csdig.cms.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.csdig.cms.dao.ContentDAO;
import com.csdig.cms.model.Content;
import com.csdig.db.DBUtils;
import com.csdig.db.model.Pagination;
import com.csdig.db.model.Sql;

@Repository
public class ContentDAOImpl implements ContentDAO {
	

	@Override
	public void add(Content vo) throws Exception {
	    Sql sql = new Sql("insert into content(content_id,channel_id,title,description,link,release_date,view_count,ups,downs)");
	    sql.append("values(:content_id,:channel_id,:title,:description,:link,:release_date,:view_count,:ups,:downs)");
	   	sql.set("content_id",vo.getContentId());
	   	sql.set("channel_id",vo.getChannelId());
	   	sql.set("title",vo.getTitle());
	   	sql.set("description",vo.getDescription());
	   	sql.set("link",vo.getLink());
	   	sql.set("release_date",vo.getReleaseDate());
	   	sql.set("view_count",vo.getViewCount());
	   	sql.set("ups",vo.getUps());
	   	sql.set("downs",vo.getDowns());
	    DBUtils.exeUpdate(sql);
	}

	@Override
	public void delete(Content vo) throws Exception {
	    String sql = "delete from content where content_id=?";
	    DBUtils.exeUpdate(sql, new Object[] {vo.getContentId() });
	}

	@Override
	public Pagination<Content> findByCondition(Content vo, int pageNo,
			int pageSize) throws Exception{
	    Sql sql=new Sql("select * from content where 1=1 ");
	    ///add condtion here  
	    return DBUtils.queryPagination(sql, null, pageNo, pageSize,Content.class);
	}

	@Override
	public List<Content> findByCondition(Content vo) throws Exception{
	    Sql sql=new Sql("select * from content where 1=1 ");
	    ///add condtion here 
	    return DBUtils.queryBeanList(sql, Content.class);
	}

	@Override
	public Content findById(java.lang.Integer id) throws Exception {
	    String sql = "select * from content where content_id=?";
	    return DBUtils.queryBean(sql,Content.class,id);
	}

	@Override
	public List<Content> listAll() throws Exception {
	    return findByCondition(null);
	}

	@Override
	public void update(Content vo) throws Exception {
	    String sql = "update content set channel_id=?,title=?,description=?,link=?,release_date=?,view_count=?,ups=?,downs=?  where content_id=?";
	    DBUtils.exeUpdate(sql,new Object[]{vo.getChannelId(),vo.getTitle(),vo.getDescription(),vo.getLink(),vo.getReleaseDate(),vo.getViewCount(),vo.getUps(),vo.getDowns(),vo.getContentId()});
	}
    
}
