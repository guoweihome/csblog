<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<form id="editChannelForm" method="post" action="${admin_base}/channel/doUpdate.at" novalidate>
	<table id="t_channel" align="center" width="100%" >
		<tr>   
			<td>父栏目:</td>
			<td>
				<input type="hidden" name="parentId" value="${parent==null?'-1':parent.channelId}"/>
				${parent==null?'根节点':parent.channelName}
			</td>
		</tr>
		
		<tr>
			<td>栏目名称:</td>
			<td>
				<input class="easyui-validatebox textbox" type="text" name="channelName" value="${channel.channelName }" data-options="required:true"></input>
			</td>
		</tr>
		
		<tr>
			<td>访问路径:</td>
			<td>
				<input class="easyui-validatebox textbox" type="text" name="channelPath" value="${channel.channelPath }" data-options="required:true"></input>
			</td>
		</tr>
		
		<tr>
			<td>模板路径:</td>
			<td>
				<input class="easyui-validatebox textbox" style="width: 220px" id="tplPath" type="text" name="tplPath" value="${channel.tplPath }" data-options="required:true"></input>
				<input type="button" value="选择" onclick="selectTpl()"/>
			</td>
		</tr>
		
		<tr>
			<td>顺序:</td>
			<td>
				<input class="easyui-validatebox textbox" type="number" maxlength=30 name="priority" value="${channel.priority }" data-options="required:true"></input>
			</td>
		</tr>
		
		<tr>
			<td>是否显示:</td>
			<td>
				<select name="isDisplay">
					<option value="true"${channel.isDisplay?' selected="selected"':'' }>显示</option>
					<option value="false"${channel.isDisplay?'':' selected="selected"' }>隐藏</option>
				</select>
			</td>
		</tr>
		
		<tr>
			<td>是否可静态化:</td>
			<td>
				<select name="canStatic">
					<option value="true">可以</option>
					<option value="false">不可以</option>
				</select>
			</td>
		</tr>
		
		<tr>
			<td>是否单页:</td>
			<td>
				<select name="isSingle">
					<option value="true">是</option>
					<option value="false">不是</option>
				</select>
			</td>
		</tr>
		
	</table>
	<input type="hidden" hidden="true" name="channelId" value="${channel.channelId}"></input>
</form>
<div id="selectTpl2">
</div>
<script type="text/javascript">
function selectTpl(){
	$("#selectTpl2").html("<ul id=\"t3\" class=\"easyui-tree\" url=\"tree.at?type=tpl\" style='height:300px'>");
	$("#selectTpl2").dialog({
		title:"新增栏目",
		width:300,
		height:400,
		modal : true,
		closable : true,
		buttons:[
			{
				text:"选择",
				handler:function(){
					getSelectResult();
				}
			}    
		]
	});
	$("#t3").tree({url:"${admin_base}/resource/tree.at?type=tpl"});
}

function getSelectResult(){
	var node = $('#t3').tree("getSelected");
	if(node!=null){
		$("#tplPath").val("/"+node.id);
	}
	$("#selectTpl2").dialog("close");
}
</script>
