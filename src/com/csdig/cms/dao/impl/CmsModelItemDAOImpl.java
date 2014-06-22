package com.csdig.cms.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.csdig.cms.dao.CmsModelItemDAO;
import com.csdig.cms.model.CmsModelItem;
import com.csdig.db.DBUtils;
import com.csdig.db.model.Pagination;
import com.csdig.db.model.Sql;

@Repository
public class CmsModelItemDAOImpl implements CmsModelItemDAO {

	@Override
	public void add(CmsModelItem vo) throws Exception {
		Sql sql = new Sql(
				"insert into cms_model_item(model_item_id,model_id,field,item_label,priority,def_value,opt_value,data_type,is_single,is_required)");
		sql.append("values(:model_item_id,:model_id,:field,:item_label,:priority,:def_value,:opt_value,:data_type,:is_single,:is_required)");
		sql.set("model_item_id", vo.getModelItemId());
		sql.set("model_id", vo.getModelId());
		sql.set("field", vo.getField());
		sql.set("item_label", vo.getItemLabel());
		sql.set("priority", vo.getPriority());
		sql.set("def_value", vo.getDefValue());
		sql.set("opt_value", vo.getOptValue());
		sql.set("data_type", vo.getDataType());
		sql.set("is_single", vo.getIsSingle());
		sql.set("is_required", vo.getIsRequired());
		DBUtils.exeUpdate(sql);
	}

	@Override
	public void delete(CmsModelItem vo) throws Exception {
		String sql = "delete from cms_model_item where model_item_id=?";
		DBUtils.exeUpdate(sql, new Object[] { vo.getModelItemId() });
	}
	
	@Override
	public void deleteByModelId(Integer modelId) throws Exception {
		String sql = "delete from cms_model_item where model_id=?";
		DBUtils.exeUpdate(sql, new Object[] { modelId });
	}

	@Override
	public Pagination<CmsModelItem> findByCondition(CmsModelItem vo, int pageNo, int pageSize) throws Exception {
		Sql sql = new Sql("select * from cms_model_item where 1=1 ");
		// /add condtion here
		return DBUtils.queryPagination(sql, null, pageNo, pageSize, CmsModelItem.class);
	}

	@Override
	public List<CmsModelItem> findByCondition(CmsModelItem vo) throws Exception {
		Sql sql = new Sql("select * from cms_model_item where 1=1 ");
		// /add condtion here
		return DBUtils.queryBeanList(sql, CmsModelItem.class);
	}

	@Override
	public CmsModelItem findById(java.lang.Integer id) throws Exception {
		String sql = "select * from cms_model_item where model_item_id=?";
		return DBUtils.queryBean(sql, CmsModelItem.class, id);
	}

	@Override
	public List<CmsModelItem> listAll() throws Exception {
		return findByCondition(null);
	}

	@Override
	public void update(CmsModelItem vo) throws Exception {
		String sql = "update cms_model_item set model_id=?,field=?,item_label=?,priority=?,def_value=?,opt_value=?,data_type=?,is_single=?,is_required=?  where model_item_id=?";
		DBUtils.exeUpdate(sql,
				new Object[] { vo.getModelId(), vo.getField(), vo.getItemLabel(), vo.getPriority(), vo.getDefValue(),
						vo.getOptValue(), vo.getDataType(), vo.getIsSingle(), vo.getIsRequired(), vo.getModelItemId() });
	}

	@Override
	public List<CmsModelItem> findByModelId(Integer modelId) throws Exception {
		Sql sql = new Sql("select * from cms_model_item where 1=1 and model_id=:model_id");
		sql.set("model_id", modelId);
		return DBUtils.queryBeanList(sql, CmsModelItem.class);
	}

	@Override
	public CmsModelItem getByModelIdAndField(int modelId, String field) throws Exception {
		Sql sql = new Sql("select * from cms_model_item where model_id=:modelId and field=:field");
		sql.set("modelId", modelId);
		sql.set("field", field);
		return DBUtils.queryBean(sql, CmsModelItem.class);
	}

	

}
