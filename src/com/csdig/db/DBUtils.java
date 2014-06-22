/*
 *  Copyright (c) 2011 asiainfo-linkage
 */
package com.csdig.db;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.csdig.db.core.ColumnFilter;
import com.csdig.db.core.DataContainer;
import com.csdig.db.core.handlers.BeanHandler;
import com.csdig.db.core.handlers.BeanListHandler;
import com.csdig.db.core.handlers.ColumnListHandler;
import com.csdig.db.core.handlers.DataContainerHnadler;
import com.csdig.db.core.handlers.MapListHandler;
import com.csdig.db.model.Pagination;
import com.csdig.db.model.Sql;

public class DBUtils {

    //private static SqlRunner runner = new SqlRunner();
    
	private static SqlRunner initSqlRunner(){
		return new SqlRunner(ConnectionFactory.getConnection());
	}
	
    //1
    public static <T> T queryBean(String sql, Class<T> type,ColumnFilter columnFiter, Object... param) throws SQLException {
    	SqlRunner runner = initSqlRunner();
    	return runner.query(sql, new BeanHandler<T>(type,columnFiter), param);
    }

    public static <T> T queryBean(String sql, Class<T> type, Object... param) throws SQLException {
    	SqlRunner runner = initSqlRunner();
    	return runner.query(sql, new BeanHandler<T>(type), param);
    }
    
    public static <T> T queryBean(Sql sql, Class<T> type) throws SQLException {
    	SqlRunner runner = initSqlRunner();
    	return runner.query(sql.toString(), sql.getParam(),  new BeanHandler<T>(type));
    }

    //2
    public static <T> List<T> queryBeanList(String sql, Class<T> type,ColumnFilter columnFiter, Object... param) throws SQLException {
    	SqlRunner runner = initSqlRunner();
    	return runner.query(sql, new BeanListHandler<T>(type,columnFiter), param);
    }
    
    public static <T> List<T> queryBeanList(String sql, Class<T> type, Object... param) throws SQLException {
    	SqlRunner runner = initSqlRunner();
    	return runner.query(sql, new BeanListHandler<T>(type), param);
    }
    
    public static <T> List<T> queryBeanList(Sql sql, Class<T> type) throws SQLException {
    	SqlRunner runner = initSqlRunner();
    	return runner.query(sql.toString(),sql.getParam(), new BeanListHandler<T>(type));
    }
    
    //3
    public static <T> List<T> queryBeanList(String sql, ColumnFilter columnFiter,Class<T> type) throws SQLException {
    	SqlRunner runner = initSqlRunner();
    	return runner.query(sql, new BeanListHandler<T>(type,columnFiter));
    }
    
    public static <T> List<T> queryBeanList(String sql, Class<T> type) throws SQLException {
    	SqlRunner runner = initSqlRunner();
    	return runner.query(sql, new BeanListHandler<T>(type));
    }
    
    //4
    public static  List<DataContainer> queryDatacontainerList(String sql,ColumnFilter columnFiter,Object... param) throws SQLException {
    	SqlRunner runner = initSqlRunner();
    	return runner.query(sql, new DataContainerHnadler(columnFiter),param);
    }
    
    public static  List<DataContainer> queryDatacontainerList(String sql,Object... param) throws SQLException {
    	SqlRunner runner = initSqlRunner();
    	return runner.query(sql, new DataContainerHnadler(),param);
    }
    
    public static  List<DataContainer> queryDatacontainerList(Sql sql) throws SQLException {
    	SqlRunner runner = initSqlRunner();
    	return runner.query(sql.toString(),sql.getParam(), new DataContainerHnadler());
    }
    
    //5
    public static List<Map<String,Object>> queryMapList(String sql,ColumnFilter columnFiter,Object[] params)throws SQLException{
    	SqlRunner runner = initSqlRunner();
    	return runner.query(sql,new MapListHandler(columnFiter), params );
    }
    
    public static List<Map<String,Object>> queryMapList(String sql,Object[] params)throws SQLException{
    	SqlRunner runner = initSqlRunner();
    	return runner.query(sql,new MapListHandler(), params );
    }
    
    public static List<Map<String,Object>> queryMapList(Sql sql)throws SQLException{
    	SqlRunner runner = initSqlRunner();
    	return runner.query(sql.toString(),sql.getParam(),new MapListHandler());
    }
    
    public static Map<String,Object> queryMap(Sql sql)throws SQLException{
    	List<Map<String,Object>> listMap = queryMapList(sql);
    	if(listMap!=null&& listMap.size()>0){
    		return listMap.get(0);
    	}
    	return null;
    }
    
    public static Map<String,Object> queryMap(String sql,Object[] params)throws SQLException{
    	List<Map<String,Object>> listMap = queryMapList(sql,params);
    	if(listMap!=null&& listMap.size()>0){
    		return listMap.get(0);
    	}
    	return null;
    }
    
    //6
    public static Pagination<Map<String,Object>> queryMapPagination(String sql,Object[] params,Integer pageNo, Integer pageSize)throws SQLException{
    	return queryMapPagination(sql,params,null,pageNo, pageSize);
    }
    
    public static Pagination<Map<String,Object>> queryMapPagination(String sql,Object[] params,ColumnFilter columnFiter,Integer pageNo, Integer pageSize)throws SQLException{
    	SqlRunner runner = initSqlRunner();
    	String countSql = "select count(*) from ("+sql+") b";
        List<Object> countList = runner.query( countSql,new ColumnListHandler(1), params);
        Object obj = countList.get(0);
        Long count = 0L;
        if (obj instanceof BigDecimal) {
            BigDecimal bcount = (BigDecimal) obj;
            count = bcount.longValue();
        } else {
            count = (Long) countList.get(0);
        }
        Pagination<Map<String,Object>> result = new Pagination<Map<String,Object>>(pageNo, pageSize, count.intValue());
        if (count < 1) {
            result.setList(new ArrayList<Map<String,Object>>());
            return result;
        }
        
        String resultSql = sql+" limit ?,?";
        int size = 2;
        if(params!=null){
            size = params.length+2;
        }
        Object[] objs = new Object[size];
        if(params!=null){
            for(int i=0;i<params.length;i++){
                objs[i]=params[i];
            }
        }
        objs[size-2]= (pageNo-1)*pageSize;
        objs[size-1]= pageSize;
        List<Map<String,Object>> list = runner.query(resultSql,new MapListHandler(columnFiter), objs );
        result.setList(list);
        return result;
    }
    
    //7
    public static <T> Pagination<T> queryPagination(String sql,Object[] params,Integer pageNo, Integer pageSize,Class<T> type)throws SQLException{
        return queryPagination(sql, params,null,pageNo, pageSize, type);
    }   
    
    public static <T> Pagination<T> queryPagination(String sql,Object[] params,ColumnFilter columnFiter,Integer pageNo, Integer pageSize,Class<T> type)throws SQLException{
    	SqlRunner runner = initSqlRunner();
    	String countSql = "select count(*) from ("+sql+") b";
        List<Object> countList = runner.query( countSql,new ColumnListHandler(1), params);
        Object obj = countList.get(0);
        Long count = 0L;
        if (obj instanceof BigDecimal) {
            BigDecimal bcount = (BigDecimal) obj;
            count = bcount.longValue();
        } else {
            count = (Long) countList.get(0);
        }
        Pagination<T> result = new Pagination<T>(pageNo, pageSize, count.intValue());
        if (count < 1) {
            result.setList(new ArrayList<T>());
            return result;
        }
        
        String resultSql = sql+" limit ?,?";
        int size = 2;
        if(params!=null){
            size = params.length+2;
        }
        Object[] objs = new Object[size];
        if(params!=null){
            for(int i=0;i<params.length;i++){
                objs[i]=params[i];
            }
        }
        objs[size-2]= (pageNo-1)*pageSize;
        objs[size-1]= pageSize;
        List<T> list = runner.query(resultSql,new BeanListHandler<T>(type,columnFiter), objs );
        result.setList(list);
        return result;
    }
    
    public static <T> Pagination<T> queryPagination(Sql sql,ColumnFilter columnFiter,Integer pageNo, Integer pageSize,Class<T> type)throws SQLException{
    	SqlRunner runner = initSqlRunner();
    	
    	String countSql = sql.getCountSql();
        List<Object> countList = runner.query( countSql,sql.getParam(),new ColumnListHandler(1));
        Object obj = countList.get(0);
        Long count = 0L;
        if (obj instanceof BigDecimal) {
            BigDecimal bcount = (BigDecimal) obj;
            count = bcount.longValue();
        } else {
            count = (Long) countList.get(0);
        }
        Pagination<T> result = new Pagination<T>(pageNo, pageSize, count.intValue());
        if (count < 1) {
            result.setList(new ArrayList<T>());
            return result;
        }
        result.setPageNo(pageNo);
        result.setPageSize(pageSize);
        sql.append(" limit :startPage,:pageSize");
       
        sql.set("startPage", (pageNo-1)*pageSize);
        sql.set("pageSize", pageSize);
        
        List<T> list = runner.query(sql.toString(),sql.getParam(),new BeanListHandler<T>(type,columnFiter));
        result.setList(list);
        return result;
    }
    
    
    public static <T> Pagination<T> queryPagination(Sql sql,Integer pageNo, Integer pageSize,Class<T> type)throws SQLException{
    	return queryPagination(sql,null,pageNo,pageSize,type);
    }
    
    public static Pagination<Map<String,Object>> queryMapPagination(Sql sql,Integer pageNo, Integer pageSize)throws SQLException{
    	SqlRunner runner = initSqlRunner();
    	
    	String countSql = sql.getCountSql();
        List<Object> countList = runner.query( countSql,sql.getParam(),new ColumnListHandler(1));
        Object obj = countList.get(0);
        Long count = 0L;
        if (obj instanceof BigDecimal) {
            BigDecimal bcount = (BigDecimal) obj;
            count = bcount.longValue();
        } else {
            count = (Long) countList.get(0);
        }
        Pagination<Map<String,Object>> result = new Pagination<Map<String,Object>>(pageNo, pageSize, count.intValue());
        if (count < 1) {
            result.setList(new ArrayList<Map<String,Object>>());
            return result;
        }
        result.setPageNo(pageNo);
        result.setPageSize(pageSize);
        sql.append(" limit :startPage,:pageSize");
       
        sql.set("startPage", (pageNo-1)*pageSize);
        sql.set("pageSize", pageSize);
        
        List<Map<String,Object>> list = runner.query(sql.toString(),sql.getParam(),new MapListHandler());
        result.setList(list);
    	
    	return result;
    }
    
    
    //update
    public static int exeUpdate(String sql, Object[] params) throws SQLException {
    	SqlRunner runner = initSqlRunner();
    	return runner.update(sql, params);
    }
    
    public static int exeUpdate(Sql sql) throws SQLException {
    	SqlRunner runner = initSqlRunner();
    	return runner.update(sql.toString(), sql.getParam());
    }
    
	public static long insert(Sql sql) throws SQLException {
		SqlRunner runner = initSqlRunner();
		return runner.insert(sql.toString(), sql.getParam());
	}
    
    public static Long getLastInsertID() throws SQLException {
		Sql sql = new Sql("SELECT LAST_INSERT_ID() AS LII");
		Map<String, Object> map = queryMap( sql);
		if (map != null) {
			return (Long)map.get("LII");
		}
		return 0l;
	}


    public static void main(String[] args) throws SQLException {
       
    }

}
