package com.csdig.cms.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.csdig.cms.dao.ChannelAttrDAO;
import com.csdig.cms.model.ChannelAttr;
import com.csdig.db.DBUtils;
import com.csdig.db.model.Pagination;
import com.csdig.db.model.Sql;

@Repository
public class ChannelAttrDAOImpl implements ChannelAttrDAO {
	

	@Override
	public void add(ChannelAttr vo) throws Exception {
	    Sql sql = new Sql("insert into channel_attr(attr_id,channel_id,attr_name,attr_value,priority)");
	    sql.append("values(:attr_id,:channel_id,:attr_name,:attr_value,:priority)");
	   	sql.set("attr_id",vo.getAttrId());
	   	sql.set("channel_id",vo.getChannelId());
	   	sql.set("attr_name",vo.getAttrName());
	   	sql.set("attr_value",vo.getAttrValue());
	   	sql.set("priority",vo.getPriority());
	    DBUtils.exeUpdate(sql);
	}

	@Override
	public void delete(ChannelAttr vo) throws Exception {
	    String sql = "delete from channel_attr where attr_id=?";
	    DBUtils.exeUpdate(sql, new Object[] {vo.getAttrId() });
	}
	
	@Override
	public void deleteByChannelId(int channel_id) throws Exception {
		String sql = "delete from channel_attr where channel_id=?";
	    DBUtils.exeUpdate(sql, new Object[] {channel_id });
	}

	@Override
	public Pagination<ChannelAttr> findByCondition(ChannelAttr vo, int pageNo,
			int pageSize) throws Exception{
	    Sql sql=new Sql("select * from channel_attr where 1=1 ");
	    ///add condtion here  
	    return DBUtils.queryPagination(sql, null, pageNo, pageSize,ChannelAttr.class);
	}

	@Override
	public List<ChannelAttr> findByCondition(ChannelAttr vo) throws Exception{
	    Sql sql=new Sql("select * from channel_attr where 1=1 ");
	    ///add condtion here 
	    return DBUtils.queryBeanList(sql, ChannelAttr.class);
	}

	@Override
	public ChannelAttr findById(java.lang.Integer id) throws Exception {
	    String sql = "select * from channel_attr where attr_id=?";
	    return DBUtils.queryBean(sql,ChannelAttr.class,id);
	}

	@Override
	public List<ChannelAttr> listAll() throws Exception {
	    return findByCondition(null);
	}

	@Override
	public void update(ChannelAttr vo) throws Exception {
	    String sql = "update channel_attr set channel_id=?,attr_name=?,attr_value=?,priority=?  where attr_id=?";
	    DBUtils.exeUpdate(sql,new Object[]{vo.getChannelId(),vo.getAttrName(),vo.getAttrValue(),vo.getPriority(),vo.getAttrId()});
	}

	
    
}
