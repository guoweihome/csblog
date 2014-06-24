<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<head>
	<title>栏目属性管理</title> <%@include file="/WEB-INF/jsps/header.jsp"%>
	<script type="text/javascript">
		
		$("document").ready(function() {
			var dataList = $("#dataList").datagrid({
				url:"${admin_base}/channelAttr/getChannelAttrs.at",
				singleSelect:true,
				rownumbers: true,
				fitColumns:true,
				idField:'channelId',
				columns:[[
					{field:'channelId',checkbox:true},
					{field:'modelName',title:'模板名称',width:80,align:"center"},
					{field:'name',title:'栏目名称',width:100,align:"center"},
					{field:'channelPath',title:'栏目路径',width:100,align:"center"},
					{field:'appType',title:'应用类型',width:80,align:"center",
						formatter:function(value,row,index){
							if(value==1) return "Android";
							else if(value==2) return "iOS";
						}
					},
					{field:'priority',title:'栏目顺序',width:80,align:"center"},
					{field:'parentName',title:'父栏目',width:80,align:"center"},
					{field:'isPcRelat',title:'是否关联',width:90,align:"center",
						formatter:function(value,row,index){
							if(value) return "关联";
							else return "不关联";
						}
					}
					]],
				toolbar : [
					{text: '新增',iconCls: 'icon-add',
						handler: add
					},
					{text: '修改',iconCls: 'icon-edit',
						handler: edit
					},
					{text: '删除',iconCls: 'icon-remove',
						handler: del
					},
					{text: '编辑栏目属性',iconCls: 'icon-edit',
						handler: editChannelAtrr
					},
					{text: '刷新',iconCls: 'icon-reload',
						handler: relaodData
					}
				]
			});
			
			/**新增**/
			function add(){
				$("#dlg").dialog({
					title:"新增栏目",
					width:500,
					height:300,
					modal : true,
					closable : true,
					href:"${admin_base}/channel/add.at",
					buttons:[
						{
							text:"保存",
							handler:function(){
								saveChannel();
							}
						}    
					]
					
				});
			}
			
			function saveChannel(){
				var form  = $("#addChannelForm");
				var valid = form.form("validate");
				if(valid){
					goAjax({
						url:form.attr("action"),
						data:$.serializeObject(form),
						callback:function(json){
							$.messager.alert('提示','保存成功','info',function(){
								$("#dlg").dialog("close");
								relaodData();
							});
						}
					});
				}
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
			
			 function edit(){
		        	var row = $('#dataList').datagrid('getSelected');
		        	if(alertEmptyRec(row)){
			        	$("#dlg").dialog({
							title:"编辑栏目",
							width:900,
							height:800,
							modal : true,
							closable : true,
							href:"${admin_base}/channel/update.at?channelId="+row.channelId,
							buttons:[
								{
									text:"保存",
									handler:function(){
										editSave();
									}
								}    
							]
							
						});
		        	}
		        }
			
			 function editSave(){
					var form  = $("#editChannelForm");
					var valid = form.form("validate");
					if(valid){
						goAjax({
							url:form.attr("action"),
							data:$.serializeObject(form),
							callback:function(json){
								$.messager.alert('提示','保存成功','info',function(){
									$("#dlg").dialog("close");
									relaodData();
								});
							}
						});
					}
				}
			 
			 function del(){
		        	$.messager.confirm("提示","确定要删除吗？",function(r){
		        		if(r){
				        	var row = $('#dataList').datagrid('getSelected');
							if(alertEmptyRec(row)){
								goAjax({
									url:"${admin_base}/channel/delete.at",
									data:{channelId:row.channelId},
									callback:function(json){
										$.messager.alert('提示','删除成功','info',function(){
											relaodData(); 
										});
									}
								});
							}
		        		}
		        	});
		        	
				}
			
			 function editChannelAtrr(){
					$("#dlg").dialog({
						title:"编辑栏目属性",
						width:800,
						height:800,
						modal : true,
						closable : true,
						href:"${admin_base}/channel/editChannelAttr.at?channelId="+row.channelId,
						buttons:[
							{
								text:"保存",
								handler:function(){
									saveChannelAttr();
								}
							}    
						]
						
					});
				}
			 
			function relaodData(){
				dataList.datagrid('reload'); 
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