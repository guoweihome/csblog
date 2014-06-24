package com.csdig.cms.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csdig.cms.dao.CommonDao;
import com.csdig.cms.service.CommonService;

@Service
public class CommonServiceImpl implements CommonService {

	@Autowired
	private CommonDao commonDao;

	@Override
	public List<Map<String, Object>> findList(String table, String columns, String selection, String selectionArg,
			String orderBy) throws Exception {
		return commonDao.findList(table, columns, selection, selectionArg, orderBy);
	}

}
