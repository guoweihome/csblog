package com.csdig.cms.common.springmvc;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.csdig.cms.utils.BeanFactory;

public class InitContext implements ApplicationContextAware {

	@Override
	public void setApplicationContext(ApplicationContext arg0)
			throws BeansException {
		BeanFactory.setContext(arg0);
	}
	
}
