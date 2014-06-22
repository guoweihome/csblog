package com.csdig.cms.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.csdig.cms.dao.MobileAppPromotionRefererDAO;
import com.csdig.cms.model.MobileAppPromotionReferer;
import com.csdig.db.DBUtils;
import com.csdig.db.model.Pagination;
import com.csdig.db.model.Sql;

@Repository
public class MobileAppPromotionRefererDAOImpl implements MobileAppPromotionRefererDAO {
	

	@Override
	public void add(MobileAppPromotionReferer vo) throws Exception {
	    Sql sql = new Sql("insert into mobile_app_promotion_referer(id,appid,referer,apkname,editdate,active,description)");
	    sql.append("values(:id,:appid,:referer,:apkname,:editdate,:active,:description)");
	   	sql.set("id",vo.getId());
	   	sql.set("appid",vo.getAppid());
	   	sql.set("referer",vo.getReferer());
	   	sql.set("apkname",vo.getApkname());
	   	sql.set("editdate",vo.getEditdate());
	   	sql.set("active",vo.getActive());
	   	sql.set("description",vo.getDescription());
	    DBUtils.exeUpdate(sql);
	}

	@Override
	public void delete(MobileAppPromotionReferer vo) throws Exception {
	    String sql = "delete from mobile_app_promotion_referer where id=?";
	    DBUtils.exeUpdate(sql, new Object[] {vo.getId() });
	}

	@Override
	public Pagination<MobileAppPromotionReferer> findByCondition(MobileAppPromotionReferer vo, int pageNo,
			int pageSize) throws Exception{
	    Sql sql=new Sql("select * from mobile_app_promotion_referer where 1=1 ");
	    ///add condtion here  
	    return DBUtils.queryPagination(sql, null, pageNo, pageSize,MobileAppPromotionReferer.class);
	}

	@Override
	public List<MobileAppPromotionReferer> findByCondition(MobileAppPromotionReferer vo) throws Exception{
	    Sql sql=new Sql("select * from mobile_app_promotion_referer where 1=1 ");
	    ///add condtion here 
	    return DBUtils.queryBeanList(sql, MobileAppPromotionReferer.class);
	}

	@Override
	public MobileAppPromotionReferer findById(java.lang.Long id) throws Exception {
	    String sql = "select * from mobile_app_promotion_referer where id=?";
	    return DBUtils.queryBean(sql,MobileAppPromotionReferer.class,id);
	}

	@Override
	public List<MobileAppPromotionReferer> listAll() throws Exception {
	    return findByCondition(null);
	}

	@Override
	public void update(MobileAppPromotionReferer vo) throws Exception {
	    String sql = "update mobile_app_promotion_referer set appid=?,referer=?,apkname=?,editdate=?,active=?,description=?  where id=?";
	    DBUtils.exeUpdate(sql,new Object[]{vo.getAppid(),vo.getReferer(),vo.getApkname(),vo.getEditdate(),vo.getActive(),vo.getDescription(),vo.getId()});
	}

	@Override
	public List<MobileAppPromotionReferer> getAppPromotionConfigInfoByAppid(String appid) throws Exception {
		Sql sql = new Sql("select * from mobile_app_promotion_referer where active = 1");
		if(StringUtils.isNotEmpty(appid)){
			sql.append("  and appid =:appid  ");
			sql.set("appid", appid);
		}
		return DBUtils.queryBeanList(sql, MobileAppPromotionReferer.class);
	}

	@Override
	public List<MobileAppPromotionReferer> getAppPromotionConfigInfoByRefer(String refer) throws Exception {
		Sql sql = new Sql("select * from mobile_app_promotion_referer where active = 1");
		if(StringUtils.isNotEmpty(refer)){
			sql.append("  and referer =:referer  ");
			sql.set("referer", refer);
		}
		return DBUtils.queryBeanList(sql, MobileAppPromotionReferer.class);
	}
    
}
