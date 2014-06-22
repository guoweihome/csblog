package com.csdig.cms.directive;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csdig.cms.service.CommonService;
import com.csdig.cms.utils.DirectiveUtils;

import freemarker.core.Environment;
import freemarker.template.ObjectWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;


@Service("sqlDirective")
public class SqlDirective implements TemplateDirectiveModel {

	public static final String TABLE = "table";
	public static final String COLUMNS   = "columns";
	public static final String SELECTION   = "selection";
	public static final String SELECTION_ARGS   = "selectionArgs";
	public static final String ORDER_BY   = "orderBy";
	
	@Autowired
	private CommonService commonSv;
	
	@SuppressWarnings({"rawtypes","unchecked"})
	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
			throws TemplateException, IOException {
		
		String table = DirectiveUtils.getString(TABLE, params);
		String columns = DirectiveUtils.getString(COLUMNS, params);
		String selection = DirectiveUtils.getString(SELECTION, params);
		String selectionArgs = DirectiveUtils.getString(SELECTION_ARGS, params);
		String orderBy = DirectiveUtils.getString(ORDER_BY, params);
		
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		////////////////////////
		try {
			list = commonSv.findList(table, columns, selection, selectionArgs, orderBy);
		} catch (Exception e) {
			e.printStackTrace();
			throw new TemplateException(e, env);
		}
		/////////////////////
		Map<String, TemplateModel> paramWrap = new HashMap<String, TemplateModel>(params);
		paramWrap.put(DirectiveUtils.OUT_LIST, ObjectWrapper.DEFAULT_WRAPPER.wrap(list));
		Map<String, TemplateModel> origMap = DirectiveUtils.addParamsToVariable(env, paramWrap);
		body.render(env.getOut());
		DirectiveUtils.removeParamsFromVariable(env, paramWrap, origMap);
	}

}
