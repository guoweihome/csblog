<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<form id="addChannelForm" method="post" action="${admin_base}/channel/save.at" novalidate>
	<table id="t_channel" align="center" width="100%" >
		<tr>   
			<td>父栏目:</td>
			<td>
				<input type="hidden" name="parentId" value="${channel==null?'-1':channel.channelId}"/>
				${channel==null?'根节点':channel.channelName}
			</td>
		</tr>
		
		<tr>
			<td>栏目名称:</td>
			<td>
				<input class="easyui-validatebox textbox" type="text" maxlength=20 name="channelName" data-options="required:true"></input>
			</td>
		</tr>
		
		<tr>
			<td>访问路径:</td>
			<td>
				<input class="easyui-validatebox textbox" type="text" name="channelPath" data-options="required:true"></input>
			</td>
		</tr>
		
		<tr>
			<td>模板路径:</td>
			<td>
				<input class="easyui-validatebox textbox" type="text" id="tplPath" style="width: 220px" name="tplPath" data-options="required:true"></input>
				<input type="button" value="选择" onclick="selectTpl('tplPath')"/>
			</td>
		</tr>
		
		<tr>
			<td>内容模板路径:</td>
			<td>
				<input class="easyui-validatebox textbox" type="text" id="contentPath" style="width: 220px" name="contentPath" data-options="required:true"></input>
				<input type="button" value="选择" onclick="selectTpl('contentPath')"/>
			</td>
		</tr>
		
		<tr>
			<td>顺序:</td>
			<td>
				<input class="easyui-validatebox textbox" type="number" maxlength=30 name="priority" data-options="required:true"></input>
			</td>
		</tr>
		
		<tr>
			<td>是否显示:</td>
			<td>
				<select name="isDisplay">
					<option value="true">显示</option>
					<option value="false">隐藏</option>
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
</form>
<div id="selectTpl">

</div>
<script type="text/javascript">
function selectTpl(srcId){
	$("#selectTpl").html("<ul id=\"t2\" class=\"easyui-tree\" url=\"tree.at?type=tpl\" style='height:300px'>");
	$("#t2").tree({
		url:"${admin_base}/resource/tree.at?type=tpl",
		onLoadSuccess:function(){
			$("#selectTpl").dialog({
				title:"新增栏目",
				width:300,
				height:400,
				modal : true,
				closable : true,
				buttons:[
					{
						text:"选择",
						handler:function(){
							getSelectResult(srcId);
						}
					}    
				]
			});
		}
	});
}

function getSelectResult(srcId){
	var node = $('#t2').tree("getSelected");
	if(node!=null){
		$("#"+srcId).val("/"+node.id);
	}
	$("#selectTpl").dialog("close");
}
</script>
