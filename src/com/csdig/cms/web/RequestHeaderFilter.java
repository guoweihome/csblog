package com.csdig.cms.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.csdig.cms.common.ConstantDefine;
import com.csdig.cms.model.CmsChannel;
import com.csdig.cms.service.FrontPageService;
import com.csdig.cms.utils.BeanFactory;

public class RequestHeaderFilter implements Filter {

	private FrontPageService frontPageSv;

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain chain) throws IOException,
			ServletException {
		HttpServletRequest request = (HttpServletRequest) arg0;
		if (frontPageSv == null)
			frontPageSv = BeanFactory.getBeanByType(FrontPageService.class);
		String contextPath = request.getContextPath();
		boolean doFilter = false;
		String uri = request.getRequestURI();
		if (uri.equals(contextPath + "/")) { // 针对http://locahost:8080/
			doFilter = true;
		}
		if (!doFilter) {
			List<CmsChannel> list = frontPageSv.getChannelList();
			if (list != null && list.size() > 0) {
				for (CmsChannel channel : list) {
					String path = channel.getChannelPath();
					String realPath = path.startsWith("/") ? contextPath + path : contextPath + "/" + path;
					if (uri.startsWith(realPath + ConstantDefine.DYNAMIC_EXTENTION)
							|| uri.startsWith(realPath + ConstantDefine.STATIC_EXTENTION)) {
						doFilter = true;
						break;
					}
				}
			}
		}
		if (doFilter) {
			frontPageSv.setRefer2session(request);
		}
		chain.doFilter(arg0, arg1);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
