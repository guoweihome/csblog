package com.csdig.cms.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.csdig.cms.dao.ChannelDAO;
import com.csdig.cms.model.Channel;
import com.csdig.db.DBUtils;
import com.csdig.db.model.Pagination;
import com.csdig.db.model.Sql;

@Repository
public class ChannelDAOImpl implements ChannelDAO {
	

	@Override
	public void add(Channel vo) throws Exception {
	    Sql sql = new Sql("insert into channel(channel_id,channel_name,channel_path,is_display,tpl_path,priority,can_static,parent_id,is_single)");
	    sql.append("values(:channel_id,:channel_name,:channel_path,:is_display,:tpl_path,:priority,:can_static,:parent_id,:is_single)");
	   	sql.set("channel_id",vo.getChannelId());
	   	sql.set("channel_name",vo.getChannelName());
	   	sql.set("channel_path",vo.getChannelPath());
	   	sql.set("is_display",vo.getIsDisplay());
	   	sql.set("tpl_path",vo.getTplPath());
	   	sql.set("priority",vo.getPriority());
	   	sql.set("can_static",vo.getCanStatic());
	   	sql.set("parent_id",vo.getParentId());
	   	sql.set("is_single",vo.getIsSingle());
	    DBUtils.exeUpdate(sql);
	}

	@Override
	public void delete(Integer id) throws Exception {
	    String sql = "delete from channel where channel_id=?";
	    DBUtils.exeUpdate(sql, new Object[] {id });
	}

	@Override
	public Pagination<Channel> findByCondition(Channel vo, int pageNo,
			int pageSize) throws Exception{
	    Sql sql=new Sql("select * from channel where 1=1 ");
	    ///add condtion here  
	    return DBUtils.queryPagination(sql, null, pageNo, pageSize,Channel.class);
	}

	@Override
	public List<Channel> findByCondition(Channel vo) throws Exception{
	    Sql sql=new Sql("select * from channel where 1=1 ");
	    ///add condtion here 
	    return DBUtils.queryBeanList(sql, Channel.class);
	}

	@Override
	public Channel findById(java.lang.Integer id) throws Exception {
	    String sql = "select * from channel where channel_id=?";
	    return DBUtils.queryBean(sql,Channel.class,id);
	}

	@Override
	public List<Channel> listAll() throws Exception {
	    return findByCondition(null);
	}

	@Override
	public void update(Channel vo) throws Exception {
	    String sql = "update channel set channel_name=?,channel_path=?,is_display=?,tpl_path=?,priority=?,can_static=?,parent_id=?,is_single=?  where channel_id=?";
	    DBUtils.exeUpdate(sql,new Object[]{vo.getChannelName(),vo.getChannelPath(),vo.getIsDisplay(),vo.getTplPath(),vo.getPriority(),vo.getCanStatic(),vo.getParentId(),vo.getIsSingle(),vo.getChannelId()});
	}

	@Override
	public List<Channel> findByPId(Integer pid) throws Exception {
		Sql sql=new Sql("select * from channel where parent_id=:parent_id");
		sql.set("parent_id", pid);
		return DBUtils.queryBeanList(sql, Channel.class);
	}

	@Override
	public List<Channel> findByCondition(String[] fieldNames, Object[] values) throws Exception {
		Sql sql=new Sql("select * from channel where 1=1");
		for(int i=0;i<fieldNames.length;i++){
			String field =  fieldNames[i];
			sql.append(" and "+field+"=:"+field);
			sql.set(field, values[i]);
		}
		return DBUtils.queryBeanList(sql, Channel.class);
	}
    
}
