<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<form id="editChannelAttrForm" method="post" action="${admin_base}/channelAttr/doUpdate.at" novalidate>
	<table id="t_channelAttr" align="center" width="100%" >
		
		<tr>
			<td>字段名:</td>
			<td>
				<input  type="text" maxlength=30 name="attrName" value="${channelAttr.attrName}" style="background-color: rgb(240,240,240)" readonly="readonly"></input>
			</td>
		</tr>
		
		<tr>
			<td>栏目属性值:</td>
			<td>
				<input class="easyui-validatebox textbox" style="width: 250px" type="text" name="attrValue" id="attrValue" value="${channelAttr.attrValue}" data-options="required:true"></input>
				<input type="button" id="filemanager" value="浏    览"/>
			</td>
		</tr>
		
		<tr>
			<td>栏目属性顺序:</td>
			<td>
				<input class="easyui-validatebox textbox" type="number"  name="priority" value="${channelAttr.priority}" data-options="required:true"></input>
			</td>
		</tr>
		
	</table>
	<input type="hidden" hidden="true" name="channelId" value="${channelAttr.channelId}"></input>
	<input type="hidden" hidden="true" name="channelAttrId" value="${channelAttr.channelAttrId}"></input>
</form>
<script type="text/javascript">
var dataType = "${channelAttr.modelItem.dataType}";
if(dataType=="2"){
	$("#filemanager").show();
	$('#filemanager').click(function() {
		editor.loadPlugin('filemanager', function() {
			editor.plugin.filemanagerDialog({
				viewType : 'VIEW',
				dirName : 'image',
				clickFn : function(url, title) {
					var idx = url.indexOf("${base}");
					var val = url;
					if(idx==0){
						val = url.substring("${base}".length,url.length);
					}
					$('#attrValue').val(val);
					editor.hideDialog();
				}
			});
		});
	});
}else{
	$("#filemanager").hide();
}
</script>