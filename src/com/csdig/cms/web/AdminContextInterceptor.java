package com.csdig.cms.web;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import com.csdig.cms.common.ConstantDefine;
import com.csdig.cms.model.CmsUser;

public class AdminContextInterceptor extends HandlerInterceptorAdapter {

	public static final String SESSION_USER_INFO = "user";

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String jsessionId = request.getParameter("jsessionid");
		if (jsessionId != null) {
			Cookie userCookie = new Cookie("JSESSIONID", jsessionId);
			response.addCookie(userCookie);
		}
		String adminBase = request.getContextPath() + ConstantDefine.AdminUrl.BASE;
		String uri = request.getRequestURI();
		if (uri.startsWith(adminBase)) {
			CmsUser user = (CmsUser) WebUtils.getSessionAttribute(request, SESSION_USER_INFO);
			if (user == null) {
				response.sendRedirect(request.getContextPath() + ConstantDefine.AdminUrl.LOGIN);
				return false;
			}
		}
		return super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		request.setAttribute("base", request.getContextPath());
		String adminBase = request.getContextPath() + ConstantDefine.AdminUrl.BASE;
		request.setAttribute("admin_base", adminBase);
		super.postHandle(request, response, handler, modelAndView);
	}

}
