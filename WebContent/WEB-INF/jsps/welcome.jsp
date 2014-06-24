<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div style="padding: 10px">
	<h3>帮助</h3>
	<h4>内置变量说明</h4>
	<table class="doc-table">
		<tr>
			<th>名称</th>
			<th>描述</th>
		</tr>
		<tr>
			<td>base</td>
			<td>根路径。类似于request.getContextPath()</td>
		</tr>
		<tr>
			<td>_sys_children</td>
			<td>
				<p>如果栏目的父子存在关系，父节点可以用该变量引用子节点   key:_sys_children  value:List[map]</p>
			</td>
		</tr>
		<tr>
			<td>CHANNEL_OBJECT</td>
			<td>
				<p>当前栏目对应的栏目对象(需要大写)</p>
			</td>
		</tr>
		<tr>
			<td>_sys_parent</td>
			<td>
				<p>如果栏目的父子存在关系，关联父节点内容  key:_sys_parent  value:map</p>
			</td>
		</tr>
		<tr>
			<td>_sys_channel_path</td>
			<td>
				<p>当前栏目路径</p>
			</td>
		</tr>
		<tr>
			<td>_sys_ftl_path</td>
			<td>
				<p>当前模板路径</p>
			</td>
		</tr>
		<tr>
			<td>_sys_refer</td>
			<td>
				<p>访问来源</p>
			</td>
		</tr>
		<tr>
			<td>_sys_extention</td>
			<td>
				<p>访问路径后缀</p>
			</td>
		</tr>
		<tr>
			<td>_sys_now</td>
			<td>
				<p>当前日期</p>
			</td>
		</tr>
	</table>
	<h4>内置标签说明</h4>
	<table class="doc-table">
		<tr>
			<th>名称</th>
			<th>参数</th>
			<th>描述</th>
		</tr>
		<tr>
			<td>change_log</td>
			<td>appType,channel,orderBy</td>
			<td>
				<p><b>升级日志标签。</b></p>
				<ul>
					<li>appType:引用类型，包括安卓和ios两种</li>
					<li>channel:栏目，即栏目管理中定义的栏目路径</li>
					<li>orderBy:显示顺序，默认按照添加顺序倒序排列</li>
				</ul>
				
			</td>
		</tr>
		<tr>
			<td>faq</td>
			<td>channel,orderBy</td>
			<td>
				<p><b>常见问题。</b></p>
				<ul>
					<li>channel:栏目，即栏目管理中定义的栏目路径</li>
					<li>orderBy:显示顺序，默认按照添加顺序倒序排列</li>
				</ul>
				
			</td>
		</tr>
		<tr>
			<td>sql</td>
			<td>table,columns,selection,orderBy</td>
			<td>
				<p><b>访问数据库。</b></p>
				<ul>
					<li>table:查询的表明</li>
					<li>columns:查询列明</li>
					<li>selection:查询条件</li>
					<li>orderBy:排序</li>
				</ul>
				
			</td>
		</tr>
	</table>
	
</div>