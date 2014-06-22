package com.csdig.cms.directive;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csdig.cms.model.AppType;
import com.csdig.cms.service.AppTypeService;
import com.csdig.cms.utils.DirectiveUtils;

import freemarker.core.Environment;
import freemarker.template.ObjectWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

@Service("appTypeDirective")
public class AppTypeDirective implements TemplateDirectiveModel {

	@Autowired
	private AppTypeService appTypeService;

	/**
	 * 输入参数，版块ID。
	 */
	public static final String PARAM_ORDER = "order";
	public static final String PARAM_TPL_FILE = "tpl_file";

	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
			throws TemplateException, IOException {
		//String tplFile = DirectiveUtils.getString(PARAM_TPL_FILE, params);
		String order = DirectiveUtils.getString(PARAM_ORDER, params);
		List<AppType> list = new ArrayList<AppType>();
		try {
			list = appTypeService.findAll(order);
		} catch (Exception e) {
			e.printStackTrace();
			throw new TemplateException(e, env);
		}
		Map<String, TemplateModel> paramWrap = new HashMap<String, TemplateModel>(params);
		paramWrap.put(DirectiveUtils.OUT_LIST, ObjectWrapper.DEFAULT_WRAPPER.wrap(list));
		Map<String, TemplateModel> origMap = DirectiveUtils.addParamsToVariable(env, paramWrap);
		body.render(env.getOut());
		DirectiveUtils.removeParamsFromVariable(env, paramWrap, origMap);
	}

}
