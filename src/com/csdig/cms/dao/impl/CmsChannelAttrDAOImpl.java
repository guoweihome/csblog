package com.csdig.cms.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.csdig.cms.dao.CmsChannelAttrDAO;
import com.csdig.cms.model.CmsChannelAttr;
import com.csdig.db.DBUtils;
import com.csdig.db.model.Pagination;
import com.csdig.db.model.Sql;

@Repository
public class CmsChannelAttrDAOImpl implements CmsChannelAttrDAO {

	@Override
	public void add(CmsChannelAttr vo) throws Exception {
		Sql sql = new Sql("insert into cms_channel_attr(channel_attr_id,channel_id,attr_name,attr_value,priority)");
		sql.append("values(:channel_attr_id,:channel_id,:attr_name,:attr_value,:priority)");
		sql.set("channel_attr_id", vo.getChannelAttrId());
		sql.set("channel_id", vo.getChannelId());
		sql.set("attr_name", vo.getAttrName());
		sql.set("attr_value", vo.getAttrValue());
		sql.set("priority", vo.getPriority());
		DBUtils.exeUpdate(sql);
	}

	@Override
	public void delete(CmsChannelAttr vo) throws Exception {
		String sql = "delete from cms_channel_attr where channel_attr_id=?";
		DBUtils.exeUpdate(sql, new Object[] { vo.getChannelAttrId() });
	}

	@Override
	public Pagination<CmsChannelAttr> findByCondition(CmsChannelAttr vo, int pageNo, int pageSize) throws Exception {
		Sql sql = new Sql("select * from cms_channel_attr where 1=1 ");
		// /add condtion here
		return DBUtils.queryPagination(sql, null, pageNo, pageSize, CmsChannelAttr.class);
	}

	@Override
	public List<CmsChannelAttr> findByCondition(CmsChannelAttr vo) throws Exception {
		Sql sql = new Sql("select * from cms_channel_attr where 1=1 ");
		// /add condtion here
		return DBUtils.queryBeanList(sql, CmsChannelAttr.class);
	}

	@Override
	public CmsChannelAttr findById(java.lang.Integer id) throws Exception {
		String sql = "select * from cms_channel_attr where channel_attr_id=?";
		return DBUtils.queryBean(sql, CmsChannelAttr.class, id);
	}

	@Override
	public List<CmsChannelAttr> listAll() throws Exception {
		return findByCondition(null);
	}

	@Override
	public void update(CmsChannelAttr vo) throws Exception {
		String sql = "update cms_channel_attr set channel_id=?,attr_name=?,attr_value=?,priority=?  where channel_attr_id=?";
		DBUtils.exeUpdate(sql, new Object[] { vo.getChannelId(), vo.getAttrName(), vo.getAttrValue(), vo.getPriority(),
				vo.getChannelAttrId() });
	}

	@Override
	public void deleteByChannelId(int channelId) throws Exception {
		String sql = "delete from cms_channel_attr where channel_id=?";
		DBUtils.exeUpdate(sql, new Object[] { channelId });
	}

	@Override
	public List<CmsChannelAttr> getByChannelId(int channelId) throws Exception {
		Sql sql = new Sql("select * from cms_channel_attr ");
		sql.append(" where channel_id=:channel_id");
		sql.append(" order by attr_name asc, priority asc");
		sql.set("channel_id", channelId);
		return DBUtils.queryBeanList(sql, CmsChannelAttr.class);
	}

	@Override
	public List<CmsChannelAttr> getByChannelIdAndAttrName(int channelId, String attrName) throws Exception {
		Sql sql = new Sql("select * from cms_channel_attr ");
		sql.append(" where channel_id=:channel_id ");
		sql.append(" and attr_name=:attr_name ");
		sql.set("channel_id", channelId);
		sql.set("attr_name", attrName);
		return DBUtils.queryBeanList(sql, CmsChannelAttr.class);
	}

	@Override
	public List<CmsChannelAttr> getByChannelPath(String channelPath) throws Exception {
		Sql sql = new Sql(
				"select b.*,c.is_single,c.is_required,c.opt_value from  cms_channel a,cms_channel_attr b,cms_model_item c ");
		sql.append("where a.channel_id = b.channel_id and a.model_id = c.model_id and b.attr_name=c.`field`");
		sql.append(" and a.channel_path=:channel_path ");
		sql.set("channel_path", channelPath);
		return DBUtils.queryBeanList(sql, CmsChannelAttr.class);
	}

	@Override
	public void cleanDirtyData() throws Exception {
		Sql sql = new Sql("delete a.* from cms_channel_attr a where a.attr_name not in(");
		sql.append("select c.`field` from cms_channel b,cms_model_item c where b.model_id = c.model_id and a.channel_id = b.channel_id");
		sql.append(")");
		DBUtils.exeUpdate(sql);
	}

}
