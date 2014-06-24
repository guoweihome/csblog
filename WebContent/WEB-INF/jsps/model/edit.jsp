<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="ftitle">修改模板</div>
<form id="editForm" action="${admin_base }/model/update.at" method="post">
<input type="hidden" name="modelId" value="${vo.modelId }"/>
<table align="center" width="100%" style="padding-left: 20px">
<tbody>
	<tr>
		<th>名称</th>
		<td>
		<input type="text" name="modelName" class="easyui-validatebox textbox" required="true" value="${vo.modelName }"/>
		</td>
	</tr>
	<tr>
		<th>路径</th>
		<td>
		<input type="text" name="modelPath" class="easyui-validatebox textbox" required="true" value="${vo.modelPath }"/>
		</td>
	</tr>
	<tr>
		<th>顺序</th>
		<td>
		<input type="number" name="priority" class="easyui-validatebox textbox" required="true" value="${vo.priority }"/>
		</td>
	</tr>
	<tr>
		<th>是否可用</th>
		<td>
		<select name="isDisabled">
			<option value="false"${vo.isDisabled?'':' selected="selected"'  }>可用</option>
			<option value="true"${vo.isDisabled?' selected="selected"':''  }>不可用</option>
		</select>
		</td>
	</tr>
</tbody>
</table>
</form>