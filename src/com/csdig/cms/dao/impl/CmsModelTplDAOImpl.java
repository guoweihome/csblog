package com.csdig.cms.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.csdig.cms.dao.CmsModelTplDAO;
import com.csdig.cms.model.CmsModelTpl;
import com.csdig.db.DBUtils;
import com.csdig.db.model.Pagination;
import com.csdig.db.model.Sql;

@Repository
public class CmsModelTplDAOImpl implements CmsModelTplDAO {

	@Override
	public void add(CmsModelTpl vo) throws Exception {
		Sql sql = new Sql("insert into cms_model_tpl(id,model_id,txt)");
		sql.append("values(:id,:model_id,:txt)");
		sql.set("id", vo.getId());
		sql.set("model_id", vo.getModelId());
		sql.set("txt", vo.getTxt());
		DBUtils.exeUpdate(sql);
	}

	@Override
	public void deleteByModelId(Integer modelId) throws Exception {
		String sql = "delete from cms_model_tpl where model_id=?";
		DBUtils.exeUpdate(sql, new Object[] { modelId });
	}

	@Override
	public Pagination<CmsModelTpl> findByCondition(CmsModelTpl vo, int pageNo, int pageSize) throws Exception {
		Sql sql = new Sql("select * from cms_model_tpl where 1=1 ");
		// /add condtion here
		return DBUtils.queryPagination(sql, null, pageNo, pageSize, CmsModelTpl.class);
	}

	@Override
	public List<CmsModelTpl> findByCondition(CmsModelTpl vo) throws Exception {
		Sql sql = new Sql("select * from cms_model_tpl where 1=1 ");
		// /add condtion here
		return DBUtils.queryBeanList(sql, CmsModelTpl.class);
	}

	@Override
	public CmsModelTpl findById(java.lang.Integer id) throws Exception {
		String sql = "select * from cms_model_tpl where id=?";
		return DBUtils.queryBean(sql, CmsModelTpl.class, id);
	}

	@Override
	public List<CmsModelTpl> listAll() throws Exception {
		return findByCondition(null);
	}

	@Override
	public void update(CmsModelTpl vo) throws Exception {
		String sql = "update cms_model_tpl set model_id=?,txt=?  where id=?";
		DBUtils.exeUpdate(sql, new Object[] { vo.getModelId(), vo.getTxt(), vo.getId() });
	}

	@Override
	public CmsModelTpl findByModelId(Integer modelId) throws Exception {
		String sql = "select * from cms_model_tpl where model_id=?";
		return DBUtils.queryBean(sql, CmsModelTpl.class, modelId);
	}

	@Override
	public CmsModelTpl findByModelPath(String modelPath) throws Exception {
		Sql sql = new Sql("select b.* from cms_model a,cms_model_tpl b where a.model_id = b.model_id");
		sql.append(" and a.model_path=:model_path ");
		sql.set("model_path", modelPath);
		return DBUtils.queryBean(sql, CmsModelTpl.class);
	}

}
