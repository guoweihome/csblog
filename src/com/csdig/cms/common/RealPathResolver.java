package com.csdig.cms.common;

import java.io.FileNotFoundException;

public interface RealPathResolver {

	/**
	 * 获取绝对路径
	 * @param path
	 * @return
	 */
	public String get(String path) throws FileNotFoundException;
}
