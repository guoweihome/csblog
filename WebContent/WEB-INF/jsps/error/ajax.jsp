<%@page import="com.csdig.cms.exception.BusinessException"%><%@ page language="java" pageEncoding="utf-8"%><%
	Exception exp = (Exception) request.getAttribute("exception");
	if (exp instanceof BusinessException) {
		BusinessException be = (BusinessException) exp;
		out.print("{\"STATUS\":\"300\",\"ERRCODE\":\""
				+ be.getErrcode() + "\",\"msg\":\"" + exp.getMessage()
				+ "\"}");
	} else {
		out.print("{\"STATUS\":\"300\",\"ERRCODE\":\"5001\",\"msg\":\"系统错误"+exp.getMessage()+"\"}");
	}
%>