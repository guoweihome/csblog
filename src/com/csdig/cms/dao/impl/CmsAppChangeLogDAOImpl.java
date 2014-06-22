package com.csdig.cms.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.csdig.cms.dao.CmsAppChangeLogDAO;
import com.csdig.cms.model.CmsAppChangeLog;
import com.csdig.db.DBUtils;
import com.csdig.db.model.Pagination;
import com.csdig.db.model.Sql;

@Repository
public class CmsAppChangeLogDAOImpl implements CmsAppChangeLogDAO {
	

	@Override
	public Integer add(CmsAppChangeLog vo) throws Exception {
	    Sql sql = new Sql("insert into cms_app_change_log(change_log_id,channel_id,title,dis_date,app_type)");
	    sql.append("values(:change_log_id,:channel_id,:title,:dis_date,:app_type)");
	   	sql.set("change_log_id",vo.getChangeLogId());
	   	sql.set("channel_id",vo.getChannelId());
	   	sql.set("title",vo.getTitle());
	   	sql.set("dis_date",vo.getDisDate());
	   	sql.set("app_type",vo.getAppType());
	    return Long.valueOf(DBUtils.insert(sql)).intValue();
	    
	}

	@Override
	public void delete(CmsAppChangeLog vo) throws Exception {
	    String sql = "delete from cms_app_change_log where change_log_id=?";
	    DBUtils.exeUpdate(sql, new Object[] {vo.getChangeLogId() });
	}

	@Override
	public Pagination<CmsAppChangeLog> findByCondition(CmsAppChangeLog vo, int pageNo,
			int pageSize) throws Exception{
	    Sql sql=new Sql("select * from cms_app_change_log where 1=1 ");
	    ///add condtion here  
	    return DBUtils.queryPagination(sql, null, pageNo, pageSize,CmsAppChangeLog.class);
	}

	@Override
	public List<CmsAppChangeLog> findByCondition(CmsAppChangeLog vo) throws Exception{
	    Sql sql=new Sql("select * from cms_app_change_log where 1=1 ");
	    ///add condtion here 
	    return DBUtils.queryBeanList(sql, CmsAppChangeLog.class);
	}

	@Override
	public CmsAppChangeLog findById(java.lang.Integer id) throws Exception {
	    String sql = "select * from cms_app_change_log where change_log_id=?";
	    return DBUtils.queryBean(sql,CmsAppChangeLog.class,id);
	}

	@Override
	public List<CmsAppChangeLog> listAll() throws Exception {
	    return findByCondition(null);
	}

	@Override
	public void update(CmsAppChangeLog vo) throws Exception {
	    String sql = "update cms_app_change_log set channel_id=?,title=?,dis_date=?,app_type=?  where change_log_id=?";
	    DBUtils.exeUpdate(sql,new Object[]{vo.getChannelId(),vo.getTitle(),vo.getDisDate(),vo.getAppType(),vo.getChangeLogId()});
	}

	@Override
	public List<CmsAppChangeLog> getByChannelId(int channelId) throws Exception {
		String sql = "select * from cms_app_change_log where channel_id=?";
		return DBUtils.queryBeanList(sql,CmsAppChangeLog.class,new Object[]{channelId});
	}

	@Override
	public void deleteByChannelId(int channelId) throws Exception {
		String sql = "delete from cms_app_change_log where channel_id=?";
		DBUtils.exeUpdate(sql,new Object[]{channelId});
	}

	@Override
	public List<CmsAppChangeLog> getByCondition(int channelId,String appType, String orderDesc) throws Exception {
		String sql="select * from cms_app_change_log where channel_id=? and app_type=? order by ?";
		return DBUtils.queryBeanList(sql,CmsAppChangeLog.class,new Object[]{channelId,appType,orderDesc});
	}

	@Override
	public List<CmsAppChangeLog> getByCondition(int channelId, String orderDesc) throws Exception {
		String sql="select * from cms_app_change_log where channel_id=? order by ?";
		return DBUtils.queryBeanList(sql,CmsAppChangeLog.class,new Object[]{channelId,orderDesc});
	}
    
}
