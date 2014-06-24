<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>模板管理</title> <%@include file="/WEB-INF/jsps/header.jsp"%>
	<script type="text/javascript">
		
		$("document").ready(function() {
			var dataList = $("#dataList").datagrid({
				url:"${admin_base}/model/listModelData.at",
				singleSelect:true,
				fitColumns:true,
				idField:'modelId',
				columns:[[
					{field:'modelId',title:'ID'},
					{field:'modelName',title:'名称',width:25,align:"center"},
					{field:'modelPath',title:'文件地址',width:30,align:"center"},
					{field:'priority',title:'顺序',width:25,align:"center"},
					{field:'isDisabled',title:'是否可用',width:20,align:"center",
						formatter:function(value,row,index){
							if(value) return "不可用";
							else return "可用";
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
					{text: '编辑子栏目',iconCls: 'icon-edit',
						handler: subItem
					}
				]
			});
			
			/**新增界面**/
			function add(){
				$("#dlg").dialog({
					title:"新增模板",
					width:500,
					height:300,
					modal : true,
					closable : true,
					href:"${admin_base}/model/addView.at",
					buttons:[
						{
							text:"保存",
							handler:function(){
								saveModel();
							}
						}    
					]
					
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
			//修改界面
			function edit(){
				var row = dataList.datagrid("getSelected");
				if(alertEmptyRec(row)){
					$("#dlg").dialog({
						title:"修改模板",
						width:500,
						height:300,
						modal : true,
						closable : true,
						href:"${admin_base}/model/editView.at?modelId="+row.modelId,
						buttons:[
							{
								text:"保存",
								handler:function(){
									saveUpdate();
								}
							}    
						]
						
					});
				}
			}
			
			//编辑子栏目
			function subItem(){
				var row = dataList.datagrid("getSelected");
				if(alertEmptyRec(row)){
					var url = "${admin_base}/model/subItem.at?modelId="+row.modelId;
					parent.updateTab("子栏目",url,"121");
				}
			}
			
			//保存
			function saveModel(){
				var form  = $("#form1");
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
			
			function saveUpdate(){
				var form  = $("#editForm");
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
			
			
			//删除操作
			function del(){
				var row = dataList.datagrid("getSelected");
				if(alertEmptyRec(row)){
					$.messager.confirm("提示","确定要删除吗？",function(r){
						if(r){
							goAjax({
								url:"${admin_base}/model/del.at",
								data:{modelId:row.modelId},
								callback:function(json){
									$.messager.alert('提示','删除成功','info',function(){
										relaodData();
									});
								}
							});
						}
					});
				}
			}
			
			function relaodData(){
				dataList.datagrid('reload'); 
			}
			
			//$.parser.parse();
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