<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:if test="${fn:length(modelItemList)!=0}">
<form id="addChannelAttrForm" method="post" action="${admin_base}/channelAttr/save.at" novalidate>
	<table id="t_channel" align="center" width="100%" >
		<tr>   
			<td>所属模板字段:</td>
			<td>
				<select name="attrName" id="attrName_id" onclick="attrNameClick()">
				<c:forEach items="${modelItemList}" var="modelItem">
					<option value="${modelItem.field}" dataType="${modelItem.dataType}">${modelItem.itemLabel}[${modelItem.field}]</option>
				</c:forEach>
				</select>
			</td>
		</tr>
		
		<tr>
			<td>栏目属性值:</td>
			<td>
				<input class="easyui-validatebox textbox" type="text" style="width: 250px" id="attrValue" name="attrValue" data-options="required:true" />
				<input type="button" id="filemanager" value="浏    览"/>
			</td>
		</tr>
		
		<tr>
			<td>栏目属性顺序:</td>
			<td>
				<input class="easyui-validatebox textbox" type="number"  name="priority" data-options="required:true" value=0></input>
			</td>
		</tr>
		
	</table>
	<input type="hidden" hidden="true" name="channelId" value="${channelId}"></input>
</form>
<script type="text/javascript">
function attrNameClick(){
	var dataType = $("#attrName_id").find("option:selected").attr("dataType");
	if(dataType=="2"){
		$("#filemanager").show();
	}else{
		$("#filemanager").hide();
	}
}
attrNameClick();
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


</script>
</c:if>

<c:if test="${fn:length(modelItemList)==0}">
	没有可用的模板字段！
</c:if>
