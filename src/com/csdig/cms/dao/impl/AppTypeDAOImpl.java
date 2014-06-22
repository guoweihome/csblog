package com.csdig.cms.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.csdig.cms.dao.AppTypeDAO;
import com.csdig.cms.model.AppType;
import com.csdig.db.DBUtils;
import com.csdig.db.model.Pagination;
import com.csdig.db.model.Sql;

@Repository
public class AppTypeDAOImpl implements AppTypeDAO {
	

	@Override
	public void add(AppType vo) throws Exception {
	    Sql sql = new Sql("insert into app_type(id,name,tpl_file,path,display_order,blank,tpl_model)");
	    sql.append("values(:id,:name,:tpl_file,:path,:display_order,:blank,:tpl_model)");
	   	sql.set("id",vo.getId());
	   	sql.set("name",vo.getName());
	   	sql.set("tpl_file",vo.getTplFile());
	   	sql.set("path",vo.getPath());
	   	sql.set("display_order",vo.getDisplayOrder());
	   	sql.set("blank",vo.getBlank());
	   	sql.set("tpl_model",vo.getTplModel());
	    DBUtils.exeUpdate(sql);
	}

	@Override
	public void delete(AppType vo) throws Exception {
	    String sql = "delete from app_type where id=?";
	    DBUtils.exeUpdate(sql, new Object[] {vo.getId() });
	}

	@Override
	public Pagination<AppType> findByCondition(AppType vo, int pageNo,
			int pageSize) throws Exception{
	    Sql sql=new Sql("select * from app_type where 1=1 ");
	    ///add condtion here  
	    return DBUtils.queryPagination(sql, null, pageNo, pageSize,AppType.class);
	}

	@Override
	public List<AppType> findByCondition(AppType vo) throws Exception{
	    Sql sql=new Sql("select * from app_type where 1=1 ");
	    ///add condtion here 
	    return DBUtils.queryBeanList(sql, AppType.class);
	}

	@Override
	public AppType findById(java.lang.Integer id) throws Exception {
	    String sql = "select * from app_type where id=?";
	    return DBUtils.queryBean(sql,AppType.class,id);
	}

	@Override
	public List<AppType> listAll() throws Exception {
	    return findByCondition(null);
	}

	@Override
	public void update(AppType vo) throws Exception {
	    String sql = "update app_type set name=?,tpl_file=?,path=?,display_order=?,blank=?,tpl_model=?  where id=?";
	    DBUtils.exeUpdate(sql,new Object[]{vo.getName(),vo.getTplFile(),vo.getPath(),vo.getDisplayOrder(),vo.getBlank(),vo.getTplModel(),vo.getId()});
	}

	@Override
	public List<AppType> findAll(String order) throws Exception {
		Sql sql = new Sql("select * from app_type order by display_order ");
		if(StringUtils.isNotBlank(order)){
			sql.append(order);
		}
		return DBUtils.queryBeanList(sql, AppType.class);
	}
    
}
