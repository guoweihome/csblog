<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<head>
	<title>栏目属性管理</title> <%@include file="/WEB-INF/jsps/header.jsp"%>
	<link rel="stylesheet" href="${base }/res/kindeditor/themes/default/default.css" />
	<script charset="utf-8" src="${base }/res/kindeditor/kindeditor-min.js"></script>
	<script charset="utf-8" src="${base }/res/kindeditor/lang/zh_CN.js"></script>
	<script type="text/javascript">
		var editor;
		$("document").ready(function() {
			var dataList = $("#dataList").datagrid({
				url:"${admin_base}/channelAttr/getByChannelId.at?channelId=${channelId}",
				singleSelect:true,
				rownumbers: true,
				fitColumns:true,
				nowrap:false,
				idField:'channelAttrId',
				columns:[[
					{field:'channelAttrId',checkbox:true},
					{field:'modelItem',title:'字段解释',width:80,align:"center",
						formatter:function(value,row,index){
							if(value){
								return value.itemLabel;
							}
						}	
					},
					{field:'attrName',title:'字段名',width:80,align:"center"},
					{field:'attrValue',title:'栏目属性值',width:100,align:"center"},
					{field:'priority',title:'栏目属性顺序',width:80,align:"center"}
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
					}
				]
			});
			
			/**新增**/
			function add(){
				$("#dlg").dialog({
					title:"新增属性",
					width:500,
					height:300,
					modal : true,
					closable : true,
					href:"${admin_base}/channelAttr/add.at?channelId=${channelId}",
					buttons:[
						{
							text:"保存",
							handler:function(){
								saveAttr();
							}
						}    
					]
					
				});
			}
			
			function saveAttr(){
				var form  = $("#addChannelAttrForm");
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
							title:"编辑栏目属性",
							width:500,
							height:300,
							modal : true,
							closable : true,
							href:"${admin_base}/channelAttr/update.at?channelAttrId="+row.channelAttrId,
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
					var form  = $("#editChannelAttrForm");
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
									url:"${admin_base}/channelAttr/delete.at",
									data:{channelId:'${channelId}',channelAttrId:row.channelAttrId},
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
			
			function relaodData(){
				dataList.datagrid('reload'); 
			}
			
			$.parser.parse();
			
			
			KindEditor.ready(function(K) {
				editor = K.editor({
					fileManagerJson : 'imageSelect.at'
				});
			});
			
		});
		
		
	</script>
	</head>
	<body class="easyui-layout">

		<div region="center" title="栏目【${channelName}】属性管理" iconCls="icon-edit">
			<table id="dataList">
			</table>
			<div id="dlg"></div>
		</div>
	</body>
</html>