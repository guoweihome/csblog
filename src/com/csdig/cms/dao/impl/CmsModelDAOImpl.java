package com.csdig.cms.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.csdig.cms.dao.CmsModelDAO;
import com.csdig.cms.model.CmsModel;
import com.csdig.db.DBUtils;
import com.csdig.db.model.Pagination;
import com.csdig.db.model.Sql;

@Repository
public class CmsModelDAOImpl implements CmsModelDAO {

	@Override
	public Integer add(CmsModel vo) throws Exception {
		Sql sql = new Sql("insert into cms_model(model_id,model_name,model_path,priority,is_disabled,is_def)");
		sql.append("values(:model_id,:model_name,:model_path,:priority,:is_disabled,:is_def)");
		sql.set("model_id", vo.getModelId());
		sql.set("model_name", vo.getModelName());
		sql.set("model_path", vo.getModelPath());
		sql.set("priority", vo.getPriority());
		sql.set("is_disabled", vo.getIsDisabled());
		sql.set("is_def", vo.getIsDef());
		return Long.valueOf(DBUtils.insert(sql)).intValue();
	}

	@Override
	public void delete(Integer modelId) throws Exception {
		String sql = "delete from cms_model where model_id=?";
		DBUtils.exeUpdate(sql, new Object[] { modelId });
	}

	@Override
	public Pagination<CmsModel> findByCondition(CmsModel vo, int pageNo, int pageSize) throws Exception {
		Sql sql = new Sql("select * from cms_model where 1=1 ");
		// /add condtion here
		return DBUtils.queryPagination(sql, null, pageNo, pageSize, CmsModel.class);
	}

	@Override
	public List<CmsModel> findByCondition(CmsModel vo) throws Exception {
		Sql sql = new Sql("select * from cms_model where 1=1 ");
		// /add condtion here
		return DBUtils.queryBeanList(sql, CmsModel.class);
	}

	@Override
	public CmsModel findById(java.lang.Integer id) throws Exception {
		String sql = "select * from cms_model where model_id=?";
		return DBUtils.queryBean(sql, CmsModel.class, id);
	}

	@Override
	public List<CmsModel> listAll() throws Exception {
		return findByCondition(null);
	}

	@Override
	public void update(CmsModel vo) throws Exception {
		String sql = "update cms_model set model_name=?,model_path=?,priority=?,is_disabled=?,is_def=?  where model_id=?";
		DBUtils.exeUpdate(
				sql,
				new Object[] { vo.getModelName(), vo.getModelPath(), vo.getPriority(), vo.getIsDisabled(),
						vo.getIsDef(), vo.getModelId() });
	}

	@Override
	public List<CmsModel> findByPath(String path) throws SQLException{
		Sql sql = new Sql("select * from cms_model a where a.model_path=:model_path");
		sql.set("model_path", path);
		return DBUtils.queryBeanList(sql, CmsModel.class);
	}

}
