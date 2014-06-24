package com.csdig.cms.service;

import java.util.List;
import java.util.Map;

import com.csdig.cms.model.CmsChannel;

public interface FrontPageService {

	public String findTemplateByChanelPath(String channelPath)throws Exception;
	
	
	public Map<String,Object> frontData(String channelPath,String ftlPath,String extention)throws Exception;


	public void generateTplFile(String fileName)throws Exception;
	
	//静态化栏目页面
	public void staticFrontPage(String channelPath)throws Exception;


	public void delStcPage()throws Exception;
	
	
}
