package com.csdig.cms.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.csdig.cms.dao.CmsFaqDAO;
import com.csdig.cms.model.CmsFaq;
import com.csdig.db.DBUtils;
import com.csdig.db.model.Pagination;
import com.csdig.db.model.Sql;

@Repository
public class CmsFaqDAOImpl implements CmsFaqDAO {
	

	@Override
	public void add(CmsFaq vo) throws Exception {
	    Sql sql = new Sql("insert into cms_faq(faq_id,channel_id,title,`desc`)");
	    sql.append("values(:faq_id,:channel_id,:title,:desc)");
	   	sql.set("faq_id",vo.getFaqId());
	   	sql.set("channel_id",vo.getChannelId());
	   	sql.set("title",vo.getTitle());
	   	sql.set("desc",vo.getDesc());
	    DBUtils.exeUpdate(sql);
	}

	@Override
	public void delete(CmsFaq vo) throws Exception {
	    String sql = "delete from cms_faq where faq_id=?";
	    DBUtils.exeUpdate(sql, new Object[] {vo.getFaqId() });
	}

	@Override
	public Pagination<CmsFaq> findByCondition(CmsFaq vo, int pageNo,
			int pageSize) throws Exception{
	    Sql sql=new Sql("select * from cms_faq where 1=1 ");
	    ///add condtion here  
	    return DBUtils.queryPagination(sql, null, pageNo, pageSize,CmsFaq.class);
	}

	@Override
	public List<CmsFaq> findByCondition(CmsFaq vo) throws Exception{
	    Sql sql=new Sql("select * from cms_faq where 1=1 ");
	    ///add condtion here 
	    return DBUtils.queryBeanList(sql, CmsFaq.class);
	}

	@Override
	public CmsFaq findById(java.lang.Integer id) throws Exception {
	    String sql = "select * from cms_faq where faq_id=?";
	    return DBUtils.queryBean(sql,CmsFaq.class,id);
	}

	@Override
	public List<CmsFaq> listAll() throws Exception {
	    return findByCondition(null);
	}

	@Override
	public void update(CmsFaq vo) throws Exception {
	    String sql = "update cms_faq set channel_id=?,title=?,`desc`=?  where faq_id=?";
	    DBUtils.exeUpdate(sql,new Object[]{vo.getChannelId(),vo.getTitle(),vo.getDesc(),vo.getFaqId()});
	}

	@Override
	public void deleteByChannelId(int channelId) throws Exception {
		String sql = "delete from cms_faq where channel_id=?";
		DBUtils.exeUpdate(sql, new Object[]{channelId});
	}

	@Override
	public List<CmsFaq> findByCondition(int channelId, String orderDesc) throws Exception {
		String sql = "select * from cms_faq where channel_id=? order by ?";
		return DBUtils.queryBeanList(sql,CmsFaq.class,new Object[]{channelId,orderDesc});
	}
    
}
