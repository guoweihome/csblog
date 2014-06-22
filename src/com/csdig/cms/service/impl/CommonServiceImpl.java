package com.csdig.cms.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csdig.cms.dao.CommonDao;
import com.csdig.cms.dao.MobileAppPromotionRefererDAO;
import com.csdig.cms.model.MobileAppPromotionReferer;
import com.csdig.cms.service.CommonService;

@Service
public class CommonServiceImpl implements CommonService {

	@Autowired
	private CommonDao commonDao;

	@Autowired
	private MobileAppPromotionRefererDAO appPromotionReferDao;

	@Override
	public List<Map<String, Object>> findList(String table, String columns, String selection, String selectionArg,
			String orderBy) throws Exception {
		return commonDao.findList(table, columns, selection, selectionArg, orderBy);
	}

	@Override
	public String getPromotionAppName(String appid, String refer) throws Exception {
		String apkname = null;
		List<MobileAppPromotionReferer> list = appPromotionReferDao.getAppPromotionConfigInfoByAppid(appid);
		if (list != null) {
			for (MobileAppPromotionReferer appRefer : list) {
				if (appRefer.getReferer().equals(refer)) {
					apkname = appRefer.getApkname();
					break;
				}
			}
		}
		return apkname;
	}
}
