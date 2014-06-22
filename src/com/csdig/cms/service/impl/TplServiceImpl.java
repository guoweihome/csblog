package com.csdig.cms.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;

import com.csdig.cms.model.CmsChannel;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class TplServiceImpl {

	private Configuration conf;
	
	public void staticPage(CmsChannel chanel) {
		
		
	}
	
	public void index(String tpl, Map<String, Object> data) throws IOException, TemplateException {
		long time = System.currentTimeMillis();
//		File f = new File(getIndexPath(site));
//		File parent = f.getParentFile();
//		if (!parent.exists()) {
//			parent.mkdirs();
//		}
		File f = new File(tpl);
		Writer out = null;
		try {
			// FileWriter不能指定编码确实是个问题，只能用这个代替了。
			out = new OutputStreamWriter(new FileOutputStream(f), "UTF-8");
			Template template = conf.getTemplate(tpl);
			template.process(data, out);
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}
		time = System.currentTimeMillis() - time;
	}

}
