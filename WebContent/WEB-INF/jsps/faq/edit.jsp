<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<form id="addfaqform" method="post" action="${admin_base}/faq/save.at" novalidate>
	<table align="center" width="100%">
		<tr>
			<td>所属栏目:</td>
			<td>
				<select name="channelId">
						<c:forEach items="${channelList}" var="channel">
							<c:set var="v">${faq.channelId}</c:set>
							<option value="${channel.channelId}" <c:if test="${channel.channelId==v}">selected = "selected"</c:if>>${channel.name}</option>
						</c:forEach>
				</select>
			</td>
		</tr> 
		<tr>
			<td>问题:</td>
			<td>
				<input type="text" name="title" style="width:70%" maxlength=30 class="easyui-validatebox textbox"  data-options="required:true" value="${faq.title}"></input>
			</td>
		</tr>
		<tr> 
			<td>答案:</td>
			<td>
				<textarea name="desc" maxlength=255 rows="6" style="width:70%" class="easyui-validatebox textbox" data-options="required:true">${faq.desc}</textarea>
			</td>
		</tr>
	</table>
	<input type="hidden" hidden="true" name="faqId" value="${faq.faqId}"></input>
</form>
