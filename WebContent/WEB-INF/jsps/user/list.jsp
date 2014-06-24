<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>栏目管理</title> <%@include file="/WEB-INF/jsps/header.jsp"%>
	<script type="text/javascript">
		
		$("document").ready(function() {
			var dataList = $("#dataList").datagrid({
				url:"${admin_base}/user/getUsers.at",
				singleSelect:true,
				rownumbers: true,
				fitColumns:true,
				idField:'id',
				columns:[[
					{field:'id',checkbox:true},
					{field:'telNum',title:'手机号',width:80,align:"center"},
					]],
				toolbar : [
					{text: '新增',iconCls: 'icon-add',
						handler: function(){
							parent.$.messager.prompt('新增', '请输入手机号码:', function(telName){
								if (telName){
									add(telName);
								}
							});
						}
					},
					{text: '修改',iconCls: 'icon-edit',
						handler: function(){
							var row = $('#dataList').datagrid('getSelected');
				        	if(alertEmptyRec(row)){
								parent.$.messager.prompt('修改', '当前手机号\"'+row.telNum+'\"请输入修改后的手机号:', function(newTelNum){
									if (newTelNum){
										edit(row.id,newTelNum);
									}
								});
				        	}
						}
					},
					{text: '删除',iconCls: 'icon-remove',
						handler: del
					},
					{text: '刷新缓存',iconCls: 'icon-remove',
						handler: refreshCache
					}
				]
			});
			
			function refreshCache(){
				goAjax({
					url:"refreshCaceh.at",
					callback:function(json){
						parent.alert("刷新成功");
					}
				});
			}
			
			function add(telName){
				goAjax({
					url:"add.at",
					data:{telNum:telName},
					callback:function(json){
						$("#dataList").datagrid("reload");
					}
				});
			}
			
			
			function alertEmptyRec(row){
				if(!row){
					$.messager.show({
		                title:'提示',
		                msg:'请选择一条记录',
		                timeout:2000,
		                showType:'slide'
		            });
					return false;
				}
				return true;
			}
			
			
			function edit(id,newTelNum){
				goAjax({
					url:"edit.at",
					data:{id:id,newTelNum:newTelNum},
					callback:function(json){
						$("#dataList").datagrid("reload");
					}
				});
			}
			
			 
			 function del(){
				    var row = $('#dataList').datagrid('getSelected');
					if(alertEmptyRec(row)){
		        		$.messager.confirm("提示","确定要删除吗？",function(r){
		        			if(r){
		        				goAjax({
									url:"${admin_base}/user/delete.at",
									data:{id:row.id},
									callback:function(json){
										$.messager.alert('提示','删除成功','info',function(){
											$("#dataList").datagrid("reload");
										});
										}
									});
								}
		        			});
					};
				 
				}
			
			$.parser.parse();
		});
		
		
	</script>
	</head>
	<body class="easyui-layout">

		<div region="center" title="模板列表" iconCls="icon-edit">
			<table id="dataList">
			</table>
			<div id="dlg"></div>
		</div>
	</body>
</html>