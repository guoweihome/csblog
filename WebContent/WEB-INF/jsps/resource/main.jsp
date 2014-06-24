<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>资源管理</title><%@include file="/WEB-INF/jsps/header.jsp"%>
<script type="text/javascript" src="${base }/res/uploadify/jquery.uploadify.min.js"></script>
<link rel="stylesheet" type="text/css" href="${base }/res/uploadify/uploadify.css">
<script type="text/javascript">
var type = "${type}";
$("document").ready(function(){
	
	//刷新按钮
	$("#refrsh_btn").click(function(){
		$('#tt').tree("reload");
	});
	
	$('#tt').tree({
		onClick:function(node){
			if(node.editable){//点击的是可编辑的文件
				loadFileEditView(node.id);
			}else{//点击的是目录
				loadFileListView(node.id);			
			}
		}
	});
	
});

//保存文件
function saveFile(id){
	var c = $("#editText").val();
	goAjax({
		url:"write.at",
		data:{id:id,content:c},
		callback:function(json){
			parent.alert('保存成功','info');
		}
	});
}

//文件列表视图
function loadFileListView(id){
	$("#content").html("<table id='fileData' title='"+id+"'></table>");//清空content
	$("#fileData").datagrid({
		url:"fileList.at",
		queryParams:{"id":id,"type":type},
		fitColumns:true,
		columns:[[
				{field:'id',checkbox:true},
				{field:'ico',title:'',width:3,align:"center",
						formatter:function(value,row,index){
							return '<img src="${base}/res/images/file/'+value+'.gif"/>';
						}
				},
				{field:'filename',title:'文件名',width:25,align:"center",
					formatter:function(value,row,index){
						if(row.directory){//目录
							return "<a href='#' onclick='loadFileListView(\""+row.name+"\")'>"+value+"</a>";
						}
						if(row.image){
							var da="<a href=\"${base}/"+row.id+"\" onmouseover=\"showImage(this)\" onmouseout=\"hideImage(this)\" target=\"_blank\">";
							da+=value+"</a>";
							return da;
						}
						return value;	
					}
				},
				{field:'size',title:'文件大小',width:30,align:"center",
					formatter:function(value,row,index){
						return value+"kb";
					}
				},
				{field:'lastModifiedDate',title:'最后修改时间',width:25,align:"center"},
				{field:'text',title:'操作',width:10,align:"center",
					formatter:function(value,row,index){
						var a = "<a href=\"#\" style=\"color:blue;padding:3px;\" onclick=\"delFile({id:'"+row.id+"'})\">删除</a>";
						a+="<a href=\"#\" style=\"color:blue;padding:3px;\" onclick=\"rename('"+id+"','"+row.filename+"')\">重命名</a>";
						if(row.editable){
							a+="<a href='#' style=\"color:blue;padding:3px;\" onclick='loadFileEditView(\""+row.name+"\")'>修改</a>";
						}
						return a;
					}
				}
			]],
		toolbar :[
			{text: '删除',iconCls: 'icon-del-file',
				handler: function(){
					var selectId = $("#fileData").datagrid("getSelections");
					if(selectId.length==0){
						parent.alert("请选择要删除的文件");
						return;
					}
					
					var param = {};
					for(var i=0;i<selectId.length;i++){
						if(param.id){
							param.id=param.id+","+selectId[i]['id'];
						}else{
							param.id= selectId[i]['id'];
						}
					}
					delFile(param);
				}
			},
			{text: '上传',iconCls: 'icon-up',
				handler: function(){
					$("#dlg").dialog({
						title:"文件上传",
						width:400,
						height:300,
						modal : true,
						href:"uploadView.at?id="+id,
						closable : true
					});
				}
			},
			{text: '新建文件夹',iconCls: 'icon-new-file',
				handler: function(){
					parent.$.messager.prompt('新建文件夹', '请输入文件夹名', function(r){
						if (r){
							createDir(id,r);
						}
					});
				}
			},
			{text: '后退',iconCls: 'icon-back',
				handler: function(){
					goBack(id);
				}
			}
		]
	});
}

function goBack(id){
	var paths = id.split("/");
	if(paths.length==2)return;
	var newId = "";
	for(var i=0;i<(paths.length-1);i++){
		newId+=paths[i]+"/";
	}
	loadFileListView(newId.substring(0,newId.length-1));
}


//修改文件视图
function loadFileEditView(id){
	goAjax({
		url:"editView.at",
		data:{id:id},
		dataType:"html",
		callback:function(html){
			$("#content").html(html);
			$.parser.parse("#content");
			$("#saveFileBtn").click(function(){
				saveFile(id);
			});
			$("#backBtn").click(function(){
				goBack(id);
			});
		}
	});
}

function rename(path,origName){
	parent.$.messager.prompt('文件重命名', '当前文件名：'+origName+'请输入修改后的文件名', function(r){
		if (r){//destName
			goAjax({
				url:"rename.at",
				data:{origName:path+"/"+origName,destName:path+"/"+r},
				callback:function(json){
					$("#fileData").datagrid("reload");
					$('#tt').tree("reload");
				}
			});
		}
	});
}

function createDir(path,dirName){
	goAjax({
		url:"createDir.at",
		data:{path:path,dirName:dirName},
		callback:function(json){
			$("#fileData").datagrid("reload");
			$('#tt').tree("reload");
		}
	});
}

function delFile(fileData){
	parent.$.messager.confirm("提示","确定要删除吗？",function(r){
		if(r){
			goAjax({
				url:"delFile.at",
				data:fileData,
				callback:function(json){
					$("#fileData").datagrid("reload");
					$('#tt').tree("reload");
				}
			});
		}
	});
}

/////图片预览
function showImage(a) {
	var max=300;
	var img = $("<img src='"+ a.href +"' style='border:1px solid #ccc;'/>");
	var imgDiv = $("#sd").append(img);	
	imgDiv.show();
	
	var	width = img.width();
	var	height = img.height();
	if(width>=height&&width>max) {
		height = height*max/width;
		width = max;
	} else if(height>=width||height>max) {
		width = width*max/height;
		height = max;
	}
	
	var offset = $(a).offset();
	imgDiv.css("left",offset.left);	
	var buttom = $(window).height()+$(document).scrollTop()-max-offset.top;
	if(buttom<0) {
		imgDiv.css("top",offset.top+buttom-3);
	} else {
		imgDiv.css("top",offset.top-3);
	}
	img.width(width);
	img.height(height);
}
function hideImage(a) {
	$("#sd").empty().hide();
}

</script>
</head>
<body class="easyui-layout">
<div region="west" border="true" title="资源目录" collapsible="false" style="width:250px;">
<div class="easyui-panel" style="padding:5px;">
<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-reload'" id="refrsh_btn" style="width:80px">刷新</a>
</div>
	<ul id="tt" class="easyui-tree" url="tree.at?type=${type }">
	</ul>
</div>
<div region="center">
<div id="content" style="width: 98%"></div>
<div id="sd" style="display:none;position:absolute;height:300px;width:300px"></div>
</div>

<div id="dlg"></div>
</body>
</html>