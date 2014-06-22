package com.csdig.cms.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.csdig.cms.dao.CommonDao;
import com.csdig.db.DBUtils;
import com.csdig.db.model.Sql;

@Repository
public class CommonDaoImpl implements CommonDao {

	@Override
	public List<Map<String, Object>> findList(String table, String columns, String selection, String selectionArg,
			String orderBy) throws Exception {
		Sql sql = new Sql("select ");
		if (StringUtils.isNotBlank(columns)) {
			sql.append(columns);
		} else {
			sql.append(" * ");
		}
		sql.append(" from ").append(table).append(" where 1=1 ");
		if (StringUtils.isNotBlank(selection)) {
			sql.append(selection);
		}

		if (StringUtils.isNotEmpty(selectionArg)) {
			
		}

		if (StringUtils.isNotBlank(orderBy)) {
			sql.append(orderBy);
		}
		return DBUtils.queryMapList(sql);
	}

}
