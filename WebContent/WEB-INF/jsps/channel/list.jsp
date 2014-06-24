<%@page import="com.csdig.cms.common.ConstantDefine"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>栏目管理</title> <%@include file="/WEB-INF/jsps/header.jsp"%>
	<script type="text/javascript">
		
		$("document").ready(function() {
			//刷新按钮
			$("#refrsh_btn").click(function(){
				$('#tt').tree("reload");
			});
			
			
			$('#tt').tree({
				onClick:function(node){
					loadChannelList(node.id,node.text);
				}
			});
			
			
			$.parser.parse();
		});
		
		
		function loadChannelList(pid,name){
			$("#content").html("<table id='dataList' title='"+name+"'></table>");//清空content
			$("#dataList").datagrid({
				url:"${admin_base}/channel/getChannels.at?pid="+pid,
				singleSelect:true,
				rownumbers: true,
				fitColumns:true,
				nowrap:false,
				idField:'channelId',
				columns:[[
					{field:'channelId',checkbox:true},
					{field:'channelName',title:'栏目名称',width:50,align:"center"},
					{field:'channelPath',title:'栏目路径',width:50,align:"center"},
					{field:'priority',title:'栏目顺序',width:20,align:"center"},
					{field:'tplPath',title:'模板路径',width:180,align:"center"},
					{field:'isDisplay',title:'是否显示',width:30,align:"center",
						formatter:function(value,row,index){
							if(value) return "显示";
							else return "不显示";
						}
					},
					{field:'canStatic',title:'是否可静态化',width:30,align:"center",
						formatter:function(value,row,index){
							if(value) return "可以";
							else return "不可以";
						}
					},
					{field:'isSingle',title:'是否单页',width:30,align:"center",
						formatter:function(value,row,index){
							if(value) return "是";
							else return "否";
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
						handler: listChannelAttr
					},
					{text: '预览',iconCls: 'icon-preview',
						handler: preview
					},
					{text: '清理栏目属性脏数据',iconCls: 'icon-clean',
						handler: cleanDirtyData
					},
					{text: '静态化',iconCls: 'icon-pen',
						handler: staticPage
					},
					{text: '静态化所有栏目',iconCls: 'icon-pen',
						handler: staticAllPage
					},
					{text: '删除静态化文件',iconCls: 'icon-del',
						handler: delStcPage
					}
				]
			});
			
		}
		
		
		function cleanDirtyData(){
			parent.$.messager.confirm("提示","确定要清理吗？",function(r){
				if(r){
					goAjax({
						url:"${admin_base}/channel/cleanDirtyData.at",
						callback:function(json){
							parent.alert('清理完成');
						}
					});
				}
			});
		}
		
		function staticPage(){
			parent.$.messager.confirm("提示","确定要静态化吗？",function(r){
				if(r){
					var row = $('#dataList').datagrid('getSelected');
					var url = "${admin_base}/front/stc<%=ConstantDefine.DYNAMIC_EXTENTION%>";
					if(row){
						url+="?channelPath="+row.channelPath;
					}
					
					goAjax({
						url:url,
						callback:function(json){
							parent.alert('静态化成功');
						}
					});
				}
			});
		}
		
		
		function staticAllPage(){
			parent.$.messager.confirm("提示","确定要静态化所有栏目吗？",function(r){
				if(r){
					var row = $('#dataList').datagrid('getSelected');
					var url = "${admin_base}/front/stc<%=ConstantDefine.DYNAMIC_EXTENTION%>";
					goAjax({
						url:url,
						callback:function(json){
							parent.alert('静态化成功');
						}
					});
				}
			});
		}
		
		function delStcPage(){
			parent.$.messager.confirm("提示","确定要删除吗？",function(r){
				if(r){
					var url = "${admin_base}/front/delStc<%=ConstantDefine.DYNAMIC_EXTENTION%>";
					goAjax({
						url:url,
						callback:function(json){
							parent.alert('删除成功');
						}
					});
				}
			});
		}
		
		function preview(){
			var row = $('#dataList').datagrid('getSelected');
	        	if(alertEmptyRec(row)){
					var url = "${base}/"+row.channelPath+".jhtml"; 
					window.open (url);
				}
        	}
		
		/**新增**/
		function add(){
			var node = $('#tt').tree("getSelected");
			if(node==null){
				parent.alert("请选择父节点");
				return ;
			}
			
			$("#dlg").dialog({
				title:"新增栏目",
				width:600,
				height:400,
				modal : true,
				closable : true,
				href:"${admin_base}/channel/add.at?pid="+node.id,
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
						width:500,
						height:300,
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
		
		 function listChannelAttr(){
				var row = dataList.datagrid("getSelected");
				if(alertEmptyRec(row)){
					var url = "${admin_base}/channelAttr/list.at?channelId="+row.channelId;
					parent.updateTab("栏目属性",url,"131");
				}
			}
		 
		function relaodData(){
			$("#dataList").datagrid('reload');
			$('#tt').tree("reload");
		}
		
		
		
	</script>
	</head>
	<body class="easyui-layout">
<div region="west" border="true" title="栏目树" collapsible="false" style="width:250px;">
<div class="easyui-panel" style="padding:5px;">
<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-reload'" id="refrsh_btn" style="width:80px">刷新</a>
</div>
	<ul id="tt" class="easyui-tree" url="tree.at">
	</ul>
</div>
<div region="center" title="栏目列表" iconCls="icon-edit">
	<div id="content" style="width: 98%"></div>
	<div id="dlg"></div>
	
</div>
	</body>
</html>