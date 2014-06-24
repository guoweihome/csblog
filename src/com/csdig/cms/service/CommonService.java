package com.csdig.cms.service;

import java.util.List;
import java.util.Map;

public interface CommonService {

	/**
	 * 查询
	 * 
	 * @param table 表明
	 * @param columns 列名
	 * @param selection 查询条件
	 * @param selectionArg 参数
	 * @param orderBy 
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> findList(String table, String columns, String selection, String selectionArg,
			String orderBy) throws Exception;

	
	
}
