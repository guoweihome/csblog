package com.csdig.db;

import java.sql.Connection;

import org.springframework.jdbc.datasource.DataSourceUtils;

import com.csdig.cms.utils.BeanFactory;

public class ConnectionFactory {

	public static Connection getConnection() {
		Connection conn = DataSourceUtils.getConnection(BeanFactory.getDatasource());
		return conn;
	}

	
	public static void releaseConnection(Connection con){
		DataSourceUtils.releaseConnection(con, BeanFactory.getDatasource());
	}

}
