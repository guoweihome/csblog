<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<form id="editlogform" method="post" action="${admin_base}/log/doUpdate.at" novalidate>
	<table align="center" width="100%" >
		<tr>   
			<td>所属栏目:</td>
			<td>
				<select name="channelId">
						<c:forEach items="${channelList}" var="channel">
							<c:set var="v">${log.channelId}</c:set>
							<option value="${channel.channelId}" <c:if test="${channel.channelId==v}">selected = "selected"</c:if>>${channel.name}</option>
						</c:forEach>
				</select>
			</td>
		</tr>
		
		<tr>
			<td>标题:</td>
			<td>
				<input class="easyui-validatebox textbox" type="text" maxlength=30 name="title" data-options="required:true" value="${log.title}"></input>
			</td>
		</tr>
		
		<tr>
			<td>更新日期:</td>
			<td>
				<input class="easyui-datebox" type="text" name="disDate" data-options="required:true" value="${log.disDate}"></input>
			</td>
		</tr>
		
		<tr>
			<td>应用类型:</td>
			<td>
				<select name="appType">
					<c:set var="v">${log.appType}</c:set>
					<option value="1" <c:if test="${1==v}">selected = "selected"</c:if>>Android</option>
					<option value="2" <c:if test="${2==v}">selected = "selected"</c:if>>iOS</option>
				</select>
			</td>
		</tr>
	</table>
	<br/>
	<table id="t_log" align="center" width="100%" >
		<tbody>
		<c:forEach items="${log.logItems}" var="logItem">
			<tr>
				<td>日志条目:</td>
				<td>
					条目顺序：<input class="easyui-validatebox textbox" style="width: 50px" type="number" name="priority" data-options="required:true" value="${logItem.priority}"></input>
					条目描述：<input class="easyui-validatebox textbox" style="width: 220px" type="text" maxlength=30 name="desc" data-options="required:true" value="${logItem.desc}"></input>
					<a href="#" class="easyui-linkbutton" onclick="delItem(this)">删除</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	
	<input type="hidden" hidden="true" name="changeLogId" value="${log.changeLogId}"></input>
</form>

<br/><br/>
新增条目：
条目顺序：<input type="number" id="itempriority" class="easyui-validatebox textbox" style="width: 50px"></input>
条目描述：<input type="text" id="itemdesc" maxlength=30 class="easyui-validatebox textbox" style="width: 220px"></input>
<a href="#" onclick="add()" class="easyui-linkbutton">添加</a>

<script type="text/javascript">
	 function add(){
		 var itemPriority = $("#itempriority").val()
		 var itemDesc = $("#itemdesc").val()
		 
		 $("#t_log tbody").append("<tr>" +
		 "<td>日志条目:</td>"+ 
		 "<td>条目顺序：<input type='number' name='priority' style='width: 50px' class='easyui-validatebox textbox' required='true' value="+itemPriority+"></input>"+
		 "条目描述：<input type='text' name='desc' style='width: 220px' maxlength=30 class='easyui-validatebox textbox' required='true' value="+itemDesc+"></input>"+
		 "<a href='#' class='easyui-linkbutton' onclick='delItem(this)'>删除</a>"+
		 "</td>"+
		 "</tr>");
		 
		 $("#itempriority").val("");
		 $("#itemdesc").val("");
		 $.parser.parse("#t_log");
	 }
	 function delItem(btn){
		 $(btn).parent().parent().remove(); 
	 }
 </script> 
