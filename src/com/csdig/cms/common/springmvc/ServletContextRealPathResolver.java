package com.csdig.cms.common.springmvc;

import java.io.File;
import java.io.FileNotFoundException;

import javax.servlet.ServletContext;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.util.WebUtils;

import com.csdig.cms.common.ConstantDefine;
import com.csdig.cms.common.RealPathResolver;
import com.csdig.cms.utils.OSUtils;

public class ServletContextRealPathResolver implements RealPathResolver, ServletContextAware {

	private ServletContext context;

	@Override
	public void setServletContext(ServletContext arg0) {
		this.context = arg0;
		init();
	}

	@Override
	public String get(String path) throws FileNotFoundException {
		return WebUtils.getRealPath(context, path);
	}

	private void init() {
		try {
			if (!OSUtils.isWindowOs()) {
				String rootPath = get("/");
				File root = new File(rootPath);
				// root = opt/mobile/app_cms/resin/webapps/ROOT/
				String appCmsFileStr = root.getParentFile().getParentFile().getParent();
				File resourceDir = new File(appCmsFileStr, "resource");
				if (!resourceDir.exists()) {
					resourceDir.mkdirs();
				}
				File resourceLink = new File(rootPath, ConstantDefine.ROOT_RES_DIR);
				if (resourceLink.exists()) {
					resourceLink.delete();
				}
				File downloadLink = new File(rootPath, ConstantDefine.DOWNLOAD_DIR);
				if (downloadLink.exists()) {
					downloadLink.delete();
				}
				
				String line = "ln -s " + resourceDir.getAbsolutePath() + " " + resourceLink.getAbsolutePath();
				String line2 = "ln -s /opt/mobile/wzcx/download " + downloadLink.getAbsolutePath();
				CommandLine cmdLine = CommandLine.parse(line);
				CommandLine cmdLine2 = CommandLine.parse(line2);
				DefaultExecutor executor = new DefaultExecutor();
				executor.execute(cmdLine);
				executor.execute(cmdLine2);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
