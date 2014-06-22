package com.csdig.cms.directive;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csdig.cms.model.CmsAppChangeLog;
import com.csdig.cms.service.LogService;
import com.csdig.cms.utils.DirectiveUtils;

import freemarker.core.Environment;
import freemarker.template.ObjectWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

@Service("changeLogDirective")
public class ChangeLogDirective implements TemplateDirectiveModel {

	public static final String PARAM_APP_TYPE = "appType";
	public static final String PARAM_CHANNEL = "channel";
	public static final String PARAM_ORDER = "orderBy";
	
	@Autowired
	private LogService logService;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void execute(Environment env, Map params, TemplateModel[] templateModels, TemplateDirectiveBody body) throws TemplateException, IOException {
		String appType = DirectiveUtils.getString(PARAM_APP_TYPE, params);
		String channelPath = DirectiveUtils.getString(PARAM_CHANNEL, params);
		String orderDesc = DirectiveUtils.getString(PARAM_ORDER, params);
		if (orderDesc == null) {
			orderDesc = "asc";
		}
		
		List<CmsAppChangeLog> list = new ArrayList<CmsAppChangeLog>();
		try {
			if (appType != null) {
				list = logService.getByCondition(channelPath, appType, orderDesc);
			} else {
				list = logService.getByCondition(channelPath, orderDesc);
			}
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
