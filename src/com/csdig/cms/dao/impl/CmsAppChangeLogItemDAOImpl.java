package com.csdig.cms.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.csdig.cms.dao.CmsAppChangeLogItemDAO;
import com.csdig.cms.model.CmsAppChangeLogItem;
import com.csdig.db.DBUtils;
import com.csdig.db.model.Pagination;
import com.csdig.db.model.Sql;

@Repository
public class CmsAppChangeLogItemDAOImpl implements CmsAppChangeLogItemDAO {
	
	@Override
	public void add(CmsAppChangeLogItem vo) throws Exception {
	    Sql sql = new Sql("insert into cms_app_change_log_item(change_log_item_id,change_log_id,`desc`,priority)");
	    sql.append("values(:change_log_item_id,:change_log_id,:desc,:priority)");
	   	sql.set("change_log_item_id",vo.getChangeLogItemId());
	   	sql.set("change_log_id",vo.getChangeLogId());
	   	sql.set("desc",vo.getDesc());
	   	sql.set("priority",vo.getPriority());
	    DBUtils.exeUpdate(sql);
	}

	@Override
	public void delete(CmsAppChangeLogItem vo) throws Exception {
	    String sql = "delete from cms_app_change_log_item where change_log_item_id=?";
	    DBUtils.exeUpdate(sql, new Object[] {vo.getChangeLogItemId() });
	}

	@Override
	public void deleteByLogId(int logId) throws Exception {
	    String sql = "delete from cms_app_change_log_item where change_log_id=?";
	    DBUtils.exeUpdate(sql, new Object[] {logId});
	}
	
	@Override
	public Pagination<CmsAppChangeLogItem> findByCondition(CmsAppChangeLogItem vo, int pageNo,
			int pageSize) throws Exception{
	    Sql sql=new Sql("select * from cms_app_change_log_item where 1=1 ");
	    ///add condtion here  
	    return DBUtils.queryPagination(sql, null, pageNo, pageSize,CmsAppChangeLogItem.class);
	}

	@Override
	public List<CmsAppChangeLogItem> findByCondition(CmsAppChangeLogItem vo) throws Exception{
	    Sql sql=new Sql("select * from cms_app_change_log_item where 1=1 ");
	    ///add condtion here 
	    return DBUtils.queryBeanList(sql, CmsAppChangeLogItem.class);
	}
	
	

	@Override
	public CmsAppChangeLogItem findById(java.lang.Integer id) throws Exception {
	    String sql = "select * from cms_app_change_log_item where change_log_item_id=?";
	    return DBUtils.queryBean(sql,CmsAppChangeLogItem.class,id);
	}

	@Override
	public List<CmsAppChangeLogItem> listAll() throws Exception {
	    return findByCondition(null);
	}

	@Override
	public void update(CmsAppChangeLogItem vo) throws Exception {
	    String sql = "update cms_app_change_log_item set change_log_id=?,desc=?,priority=?  where change_log_item_id=?";
	    DBUtils.exeUpdate(sql,new Object[]{vo.getChangeLogId(),vo.getDesc(),vo.getPriority(),vo.getChangeLogItemId()});
	}

	@Override
	public List<CmsAppChangeLogItem> findByLogId(int logId) throws Exception {
	    Sql sql=new Sql("select * from cms_app_change_log_item ");
	    sql.append(" where change_log_id=:change_log_id ");
	    sql.append(" order by priority ");
		sql.set("change_log_id", logId);
	    return DBUtils.queryBeanList(sql, CmsAppChangeLogItem.class);
	}
	
}
