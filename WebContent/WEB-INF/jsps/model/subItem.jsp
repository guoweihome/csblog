<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>模板子栏目</title><%@include file="/WEB-INF/jsps/header.jsp"%>
<script type="text/javascript">


function save(){
	var form  = $("#form1");
	var valid = form.form("validate");
	if(valid){
		sortInputField();
		goAjax({
			url:"${admin_base}/model/saveSubItem.at",
			data:form.serialize(),
			callback:function(json){
				$.messager.alert('提示','保存成功','info',function(){
					
				});
			}
		});
	}
}

function sortInputField(){
	$("#table tbody tr").each(function(i){
		$(this).find("input").each(function(i2){
			var name = $(this).attr("name");
			$(this).attr("name",$.formatString(name,i));
		});
		$(this).find("select").each(function(i2){
			var name = $(this).attr("name");
			$(this).attr("name",$.formatString(name,i));
		});
	});
}


var i=0;
function addItem(){
	var itemModel = $("#table_input tbody tr").first().clone();
	itemModel.find("td:last").html('<a href="#" class="easyui-linkbutton" style="width:50px" onclick="removeItem(this)">删除</a>')
	$("#table tbody").append(itemModel);
	$("#table_input tbody tr").find("input").val("");
	$.parser.parse("#form1");
}

function removeItem(elm){
	$(elm).parent().parent().remove();
}

</script>
</head>
<body>
	
	<form action="${admin_base}/model/saveSubItem.at" id="form1">
		<input type="hidden" name="modelId" value="${vo.modelId }"/>
		<div class="easyui-panel" title="模板内容--${vo.modelName }（${vo.modelPath }）"
			data-options="iconCls:'icon-save',collapsible:true">
			<textarea rows="20" cols="20" name="tplTxt"
				style="width: 95%; height: 400px; margin: 15px" class="easyui-validatebox textbox" required="true">${tpl.txt }</textarea>
		</div>
		<br/>
		<div class="easyui-panel" title="子栏目" style="text-align: center;"
			data-options="iconCls:'icon-save',collapsible:true">
			<table id="table">
				<tbody>
					<c:forEach items="${list}" var="item" varStatus="sts">
						<tr>
						<th>字段解释：</th>
						<td><input type="text" name="modelItems[{0}].itemLabel" value="${item.itemLabel }" class="easyui-validatebox textbox"  required="true"/></td>
						<th>字段名：</th>
						<td><input type="text" readonly="readonly" name="modelItems[{0}].field" value="${item.field }" class="easyui-validatebox textbox"  required="true"/></td>
						<th>数据类型：</th>
						<td>
							<select name="modelItems[{0}].dataType">
								<option value="1"${item.dataType==1?' selected="selected"':'' }>文本</option>
								<option value="2"${item.dataType==2?' selected="selected"':'' }>图片</option>
								<option value="3"${item.dataType==3?' selected="selected"':'' }>数字</option>
							</select>
						</td>
						<th>值唯一性：</th>
						<td>
							<select name="modelItems[{0}].isSingle">
								<option value="true"${item.isSingle?' selected="selected"':'' }>唯一</option>
								<option value="false"${item.isSingle?'':' selected="selected"' }>不唯一</option>
							</select>
						</td>
						<th>是否可以为空：</th>
						<td>
							<select name="modelItems[{0}].isRequired">
								<option value="true"${item.isRequired?' selected="selected"':'' }>不可以</option>
								<option value="false"${item.isRequired?'':' selected="selected"' }>可以</option>
							</select>
						</td>
						<th>顺序：</th>
						<td><input type="number" name="modelItems[{0}].priority" value="${item.priority }" class="easyui-validatebox textbox"  required="true"/></td>
						<th>默认值：</th>
						<td><input type="text" name="modelItems[{0}].defValue" value="${item.defValue }"/></td>
						<th>可选值：</th>
						<td><input type="text" name="modelItems[{0}].optValue" value="${item.optValue }" title="用逗号分隔" class="easyui-tooltip"/></td>
						
						<td>
							<a href="#" class="easyui-linkbutton" style="width:50px" onclick="removeItem(this)">删除</a>
						</td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</form>	
	
	<div class="easyui-panel" title="新增" style="text-align: center;"
			data-options="iconCls:'icon-save',collapsible:true">
		<table id="table_input">
			<tbody>
				<tr>
					<th>字段解释：</th>
					<td><input type="text" name="modelItems[{0}].itemLabel" value="" class="easyui-validatebox textbox"  required="true"/></td>
					<th>字段名：</th>
					<td><input type="text" name="modelItems[{0}].field" value="" class="easyui-validatebox textbox"  required="true"/></td>
					<th>数据类型：</th>
					<td>
						<select name="modelItems[{0}].dataType">
							<option value="1">文本</option>
							<option value="2">图片</option>
							<option value="3">数字</option>
						</select>
					</td>
					<th>值唯一性：</th>
					<td>
						<select name="modelItems[{0}].isSingle">
							<option value="true">唯一</option>
							<option value="false">不唯一</option>
						</select>
					</td>
					<th>是否可以为空：</th>
					<td>
						<select name="modelItems[{0}].isRequired">
							<option value="true">不可以</option>
							<option value="false">可以</option>
						</select>
					</td>
					<th>顺序：</th>
					<td><input type="number" name="modelItems[{0}].priority" value="" class="easyui-validatebox textbox"  required="true"/></td>
					<th>默认值：</th>
					<td><input type="text" name="modelItems[{0}].defValue" value=""/></td>
					<th>可选值：</th>
					<td><input type="text" name="modelItems[{0}].optValue" value="" title="用逗号分隔" class="easyui-tooltip"/></td>
					<td>
						<a href="#" class="easyui-linkbutton" style="width:50px" onclick="addItem()">新增</a>
					</td>
			</tbody>
		</table>
	</div>
	
		<div style="text-align: center;margin-top: 20px;padding: 30px;">
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'"  style="width:80px" onclick="save()">保  存</a>
		</div>
	
</body>
</html>