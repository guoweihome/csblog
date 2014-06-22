package com.csdig.cms.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import com.csdig.cms.exception.BusinessException;
import com.csdig.cms.utils.ExceptionUtils;

public class CustomSimpleMappingExceptionResolver extends SimpleMappingExceptionResolver {

	private static final Log log = LogFactory.getLog(CustomSimpleMappingExceptionResolver.class);

	private String ajaxErrorView;

	@Override
	protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response,
			Object handler, Exception ex) {
		String requestURI = request.getRequestURI();
		String viewName = determineViewName(ex, request);
		if (viewName != null) {// 
			if (!(ex instanceof BusinessException)) {
				ex.printStackTrace(System.err);
				log.error(ExceptionUtils.getErrorInfoFromException(ex));
			}
			
			if(requestURI.startsWith(request.getContextPath()+"/mobile/api/")){
				return getModelAndView(ajaxErrorView, ex, request);
			}
			
			if (!(request.getHeader("accept").indexOf("application/json") > -1 || (request
					.getHeader("X-Requested-With") != null && request.getHeader("X-Requested-With").indexOf(
					"XMLHttpRequest") > -1))) {
				Integer statusCode = determineStatusCode(request, viewName);
				if (statusCode != null) {
					applyStatusCodeIfPossible(request, response, statusCode);
				}
				return getModelAndView(viewName, ex, request);
			} else {
				return getModelAndView(ajaxErrorView, ex, request);
			}
		} else {
			return null;
		}
	}

	public void setAjaxErrorView(String ajaxErrorView) {
		this.ajaxErrorView = ajaxErrorView;
	}

}
