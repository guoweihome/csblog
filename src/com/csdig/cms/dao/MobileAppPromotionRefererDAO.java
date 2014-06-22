package com.csdig.cms.dao;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;

import com.csdig.cms.model.MobileAppPromotionReferer;
import com.csdig.db.model.Pagination;

public interface MobileAppPromotionRefererDAO {

	public void add(MobileAppPromotionReferer vo) throws Exception;

	public void update(MobileAppPromotionReferer vo) throws Exception;

	public void delete(MobileAppPromotionReferer vo) throws Exception;

	public MobileAppPromotionReferer findById(java.lang.Long id) throws Exception;

	public List<MobileAppPromotionReferer> listAll() throws Exception;

	public Pagination<MobileAppPromotionReferer> findByCondition(MobileAppPromotionReferer vo, int pageNo, int pageSize)
			throws Exception;

	public List<MobileAppPromotionReferer> findByCondition(MobileAppPromotionReferer bean) throws Exception;

	@Cacheable(key = "#appid", value = "com.sohu.auto.cms.dao.MobileAppPromotionRefererDAO.getAppPromotionConfigInfoByAppid")
	public List<MobileAppPromotionReferer> getAppPromotionConfigInfoByAppid(String appid) throws Exception;
	
	@Cacheable(key = "#refer", value = "com.sohu.auto.cms.dao.MobileAppPromotionRefererDAO.getAppPromotionConfigInfoByRefer")
	public List<MobileAppPromotionReferer> getAppPromotionConfigInfoByRefer(String refer) throws Exception;

}
