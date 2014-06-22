package com.csdig.cms.utils;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanFactory {

	private static ApplicationContext context;

	public static Object getBean(String beanName) {
		if (context == null) {
			initContext();
		}
		return context.getBean(beanName);
	}
	
	public static <T> T getBeanByType(Class<T> requiredType) {
		if (context == null) {
			initContext();
		}
		return context.getBean(requiredType);
	}

	public static void setContext(ApplicationContext servletcontext){
		context = servletcontext;
	}
	
	private static void initContext() {
		context = new ClassPathXmlApplicationContext("classpath*:spring/*.xml");
	}
	
	public static ApplicationContext getContext(){
		if (context == null) {
			initContext();
		}
		return context;
	}

	public static Connection getConnection()throws SQLException  {
		DataSource ds = getDatasource();
			if (ds != null)
				return ds.getConnection();
		return null;
	}
	
	public static DataSource getDatasource(){
		return (DataSource) BeanFactory.getBean("dataSource");
	}
	
	
}
