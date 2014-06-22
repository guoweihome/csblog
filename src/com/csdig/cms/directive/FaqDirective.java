package com.csdig.cms.directive;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csdig.cms.model.CmsFaq;
import com.csdig.cms.service.FaqService;
import com.csdig.cms.utils.DirectiveUtils;

import freemarker.core.Environment;
import freemarker.template.ObjectWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

@Service("faqDirective")
public class FaqDirective implements TemplateDirectiveModel {

	public static final String PARAM_CHANNEL = "channel";
	public static final String PARAM_ORDER = "orderBy";
	
	@Autowired
	private FaqService faqService;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void execute(Environment env, Map params, TemplateModel[] templateModels, TemplateDirectiveBody body) throws TemplateException, IOException {
		String channelPath = DirectiveUtils.getString(PARAM_CHANNEL, params);
		String orderDesc = DirectiveUtils.getString(PARAM_ORDER, params);
		if (orderDesc == null) {
			orderDesc = "asc";
		}
		List<CmsFaq> list = new ArrayList<CmsFaq>();
		try {
			list = faqService.getByCondition(channelPath,orderDesc);
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
