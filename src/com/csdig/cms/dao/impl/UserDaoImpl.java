package com.csdig.cms.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.csdig.cms.dao.UserDao;
import com.csdig.cms.model.CmsUser;
import com.csdig.db.DBUtils;
import com.csdig.db.model.Sql;

@Repository
public class UserDaoImpl implements UserDao {

	@Override
	public Integer add(CmsUser vo) throws Exception {
		Sql sql = new Sql("insert into cms_user(id,tel_num)");
		sql.append("values(:id,:tel_num)");
		sql.set("id", vo.getId());
		sql.set("tel_num", vo.getTelNum());
		return Long.valueOf(DBUtils.insert(sql)).intValue();
	}

	@Override
	public void delete(CmsUser vo) throws Exception {
		String sql = "delete from cms_user where id=?";
		DBUtils.exeUpdate(sql, new Object[] { vo.getId() });
	}

	@Override
	public CmsUser findById(java.lang.Integer id) throws Exception {
		String sql = "select * from cms_user where id=?";
		return DBUtils.queryBean(sql, CmsUser.class, id);
	}

	@Override
	public List<CmsUser> listAll() throws Exception {
		Sql sql = new Sql("select * from cms_user where 1=1 ");
		return DBUtils.queryBeanList(sql, CmsUser.class);
	}

	@Override
	public void update(CmsUser vo) throws Exception {
		String sql = "update cms_user set tel_num=?,pwd=?,generate_time=? where id=?";
		DBUtils.exeUpdate(sql, new Object[] { vo.getTelNum(), vo.getPwd(),vo.getGenerateTime(),vo.getId() });
	}

	@Override
	public CmsUser getByTelNum(String telNum) throws Exception {
		Sql sql = new Sql("select * from cms_user where tel_num=:tel_num");
		sql.set("tel_num", telNum);
		return DBUtils.queryBean(sql, CmsUser.class);
	}

}
