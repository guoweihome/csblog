package com.csdig.cms.common.springmvc;

import java.io.IOException;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.view.freemarker.FreeMarkerView;

import freemarker.template.Template;

public class RichFreeMarkerView extends FreeMarkerView {

	/**
	 * 部署路径属性名称
	 */
	public static final String CONTEXT_PATH = "base";

	@Override
	protected void exposeHelpers(Map<String, Object> model, HttpServletRequest request) throws Exception {
		super.exposeHelpers(model, request);
		model.put(CONTEXT_PATH, request.getContextPath());
	}

	@Override
	protected Template getTemplate(String name, Locale locale) throws IOException {
//		FrontPageService frontSv = BeanFactory.getBeanByType(FrontPageService.class);
//		try {
//			frontSv.generateTplFile(name.substring(0, name.lastIndexOf(".")));
//		} catch (Exception e) {
//			throw new IOException(e);
//		}
		return super.getTemplate(name, locale);
	}

}
