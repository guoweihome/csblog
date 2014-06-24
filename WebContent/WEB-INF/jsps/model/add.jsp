<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="ftitle">模板信息</div>
<form action="${admin_base}/model/save.at" id="form1" method="post" novalidate>
<table align="center" width="100%" style="padding-left: 20px">
<tbody>
	<tr>
		<th>名称</th>
		<td>
		<input type="text" name="modelName" class="easyui-validatebox textbox" required="true"/>
		</td>
	</tr>
	<tr>
		<th>路径</th>
		<td>
		<input type="text" name="modelPath" class="easyui-validatebox textbox" required="true"/>
		</td>
	</tr>
	<tr>
		<th>顺序</th>
		<td>
		<input type="number" name="priority" class="easyui-validatebox textbox" required="true"/>
		</td>
	</tr>
	<tr>
		<th>是否可用</th>
		<td>
		<select name="isDisabled">
			<option value="false">可用</option>
			<option value="true">不可用</option>
		</select>
		</td>
	</tr>
</tbody>
</table>
</form>