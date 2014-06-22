package com.csdig.cms.dao;

import java.util.List;
import java.util.Map;

public interface CommonDao {

	public List<Map<String, Object>> findList(String table, String columns, String selection, String selectionArg,
			String orderBy) throws Exception;
	
}
