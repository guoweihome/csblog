package com.csdig.cms.common;

public class ConstantDefine {

	public static class AdminUrl {
		public static final String BASE = "/admin/cms";
		public static final String LOGIN = "/cms_admin/login.at";
		public static final String LOGOUT = "/cms_admin/logout.at";
	}

	public static class ResType {
		public static final String TEMPLATE = "tpl";// 模型
		public static final String RESOURCE = "r";// 资源
	}

	public static class SessionAttr {
		public static final String REFER = "referer";
	}

	// 模板文件后缀
	public static String TPL_EXTENTION = ".ftl";

	/**
	 * 模板路径
	 */
	public static final String TPL_BASE = "/WEB-INF/ftls";
	// 资源路径
	public static String ROOT_RES_DIR = "/r";

	
	
	/**
	 * 路径分隔符
	 */
	public static final String SPT = "/";

	public static final String DYNAMIC_EXTENTION = ".jhtml";
	public static final String STATIC_EXTENTION = ".html";

	//
	public static String DOWNLOAD_DIR = "/download";

}
