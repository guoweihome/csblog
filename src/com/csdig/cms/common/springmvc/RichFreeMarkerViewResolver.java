package com.csdig.cms.common.springmvc;

import org.springframework.web.servlet.view.AbstractTemplateViewResolver;

public class RichFreeMarkerViewResolver extends AbstractTemplateViewResolver {

	public RichFreeMarkerViewResolver(){
		setViewClass(requiredViewClass());
	}
	
	@Override
	protected Class<?> requiredViewClass() {
		return RichFreeMarkerView.class;
	}
	
}
