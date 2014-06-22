package com.csdig.cms.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.csdig.cms.dao.CmsChannelDAO;
import com.csdig.cms.model.CmsChannel;
import com.csdig.db.DBUtils;
import com.csdig.db.model.Pagination;
import com.csdig.db.model.Sql;

@Repository
public class CmsChannelDAOImpl implements CmsChannelDAO {

	@Override
	public Integer add(CmsChannel vo) throws Exception {
		Sql sql = new Sql(
				"insert into cms_channel(channel_id,model_id,channel_path,priority,name,channel_type,parent_id,is_pc_relat)");
		sql.append("values(:channel_id,:model_id,:channel_path,:priority,:name,:channel_type,:parent_id,:is_pc_relat)");
		sql.set("channel_id", vo.getChannelId());
		sql.set("model_id", vo.getModelId());
		sql.set("channel_path", vo.getChannelPath());
		sql.set("priority", vo.getPriority());
		sql.set("name", vo.getName());
		sql.set("channel_type", vo.getChannelType());
		sql.set("parent_id", vo.getParentId());
		sql.set("is_pc_relat", vo.getIsPcRelat());
		return Long.valueOf(DBUtils.insert(sql)).intValue();
	}

	@Override
	public void delete(CmsChannel vo) throws Exception {
		String sql = "delete from cms_channel where channel_id=?";
		DBUtils.exeUpdate(sql, new Object[] { vo.getChannelId() });
	}

	@Override
	public Pagination<CmsChannel> findByCondition(CmsChannel vo, int pageNo, int pageSize) throws Exception {
		Sql sql = new Sql("select * from cms_channel where 1=1 ");
		// /add condtion here
		return DBUtils.queryPagination(sql, null, pageNo, pageSize, CmsChannel.class);
	}

	@Override
	public List<CmsChannel> findByCondition(CmsChannel vo) throws Exception {
		Sql sql = new Sql("select * from cms_channel where 1=1 ");
		// /add condtion here
		return DBUtils.queryBeanList(sql, CmsChannel.class);
	}

	@Override
	public CmsChannel findById(java.lang.Integer id) throws Exception {
		String sql = "select * from cms_channel where channel_id=?";
		return DBUtils.queryBean(sql, CmsChannel.class, id);
	}

	@Override
	public List<CmsChannel> listAll() throws Exception {
		return findByCondition(null);
	}

	@Override
	public void update(CmsChannel vo) throws Exception {
		String sql = "update cms_channel set model_id=?,channel_path=?,priority=?,name=?,channel_type=?,parent_id=?,is_pc_relat=?  where channel_id=?";
		DBUtils.exeUpdate(sql,
				new Object[] { vo.getModelId(), vo.getChannelPath(), vo.getPriority(), vo.getName(), vo.getChannelType(),
						vo.getParentId(), vo.getIsPcRelat(), vo.getChannelId() });
	}

	@Override
	public List<Map<String, Object>> findChannlAndModel(String channelPath) throws SQLException {
		Sql sql = new Sql("select a.*,b.model_path from cms_channel a,cms_model b where a.model_id=b.model_id");
		sql.append(" and b.is_disabled=:is_disabled ");
		sql.set("is_disabled", Boolean.FALSE);
		if (StringUtils.isNotEmpty(channelPath)) {
			sql.append(" and a.channel_path=:channel_path ");
			sql.set("channel_path", channelPath);
		}
		return DBUtils.queryMapList(sql);
	}

	@Override
	public CmsChannel findByPath(String channelPath) throws SQLException {
		Sql sql = new Sql("select * from cms_channel  where channel_path=:channel_path");
		sql.set("channel_path", channelPath);
		return DBUtils.queryBean(sql, CmsChannel.class);
	}

	@Override
	public List<CmsChannel> findByPid(Integer parentId) throws SQLException {
		Sql sql = new Sql("select * from cms_channel  where parent_id=:parent_id");
		sql.set("parent_id", parentId);
		return DBUtils.queryBeanList(sql, CmsChannel.class);
	}

	@Override
	public List<CmsChannel> listAllFromCache() throws Exception {
		return listAll();
	}

}
