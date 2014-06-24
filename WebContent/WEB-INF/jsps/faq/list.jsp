<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>常见问题管理</title>
<%@include file="/WEB-INF/jsps/header.jsp"%>
</head>
<body>
    <table id="dg" title="常见问题管理" class="easyui-datagrid" style="height:100%,width:100%"
            url="${admin_base}/faq/getFaqs.at"
            toolbar="#toolbar" pagination="false"
            rownumbers="true" fitColumns="true" singleSelect="true">
        <thead>
            <tr>
                <th field="faqId" checkbox="true"></th>
                <th field="channelName" width="50">所属栏目</th>
                <th field="title" width="50">问题</th>
                <th field="desc" width="50">答案</th>
            </tr>
        </thead>
        
    </table>
    <div id="toolbar">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addFaq()">新增</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="updateFaq()">编辑</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="deleteFaq()">删除</a>
    </div>
    
    <div id="dlg"></div>
    
    <script type="text/javascript">
        function addFaq(){
        	$("#dlg").dialog({
				title:"新增",
				width:500,
				height:300,
				modal : true,
				closable : true,
				href:"${admin_base}/faq/add.at",
				buttons:[
					{
						text:"保存",
						handler:function(){
							saveFaq();
						}
					}    
				]
				
			});
        }
        
        function updateFaq(){
        	var row = $('#dg').datagrid('getSelected');
        	if(alertEmptyRec(row)){
	        	$("#dlg").dialog({
					title:"编辑",
					width:500,
					height:300,
					modal : true,
					closable : true,
					href:"${admin_base}/faq/update.at?faqId="+row.faqId,
					buttons:[
						{
							text:"保存",
							handler:function(){
								saveFaq();
							}
						}    
					]
					
				});
        	}
        }
        
        function deleteFaq(){
        	
        	$.messager.confirm("提示","确定要删除吗？",function(r){
        		if(r){
					var row = $('#dg').datagrid('getSelected');
					if(alertEmptyRec(row)){
						goAjax({
							url:"${admin_base}/faq/delete.at",
							data:{faqId:row.faqId},
							callback:function(json){
								$.messager.alert('提示','删除成功','info',function(){
									 $('#dg').datagrid('reload');
								});
							}
						});
					}
        			
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
        
        function saveFaq(){
			var form  = $("#addfaqform");
			var valid = form.form("validate");
			if(valid){
				goAjax({
					url:form.attr("action"),
					data:$.serializeObject(form),
					callback:function(json){
						$.messager.alert('提示','保存成功','info',function(){
							$("#dlg").dialog("close");
							 $('#dg').datagrid('reload');
						});
					}
				});
			}
		}
		
        
    </script>
    <style type="text/css">
        #fm{
            margin:0;
            padding:10px 30px;
        }
        .ftitle{
            font-size:14px;
            font-weight:bold;
            padding:5px 0;
            margin-bottom:10px;
            border-bottom:1px solid #ccc;
        }
        .fitem{
            margin-bottom:5px;
        }
        .fitem label{
            display:inline-block;
            width:80px;
        }
    </style>
</body>
</html>