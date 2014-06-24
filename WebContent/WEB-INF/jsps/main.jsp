<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head id="Head1">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>后台首页</title>
<link rel="icon" href="${base }/favicon.ico" type="image/x-icon" />
<link rel="stylesheet" type="text/css" href="${base }/res/css/default.css" />
<link rel="stylesheet" type="text/css" href="${base }/res/easyui/themes/gray/easyui-big.css" />
<link rel="stylesheet" type="text/css" href="${base }/res/easyui/themes/icon.css" />
<script src="http://libs.baidu.com/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="${base }/res/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${base }/res/easyui/easyloader.js"></script>
<script type="text/javascript" src='${base }/res/js/extEasyUI.js'> </script>
<script type="text/javascript" src='${base }/res/js/outlook.js'> </script>
<script type="text/javascript" src="${base }/res/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
var _menus = {"menus":[
		{"menuid":"1","icon":"icon-sys","menuname":"菜单",
			"menus":[{"mid":"12","menuname":"模型管理","icon":"icon-project","url":"${admin_base}/resource/mainView.at?type=tpl"},
					{"mid":"13","menuname":"栏目管理","icon":"icon-project","url":"${admin_base}/channel/list.at"},
					{"mid":"15","menuname":"资源管理","icon":"icon-project","url":"${admin_base}/resource/mainView.at?type=r"}
					]
		},{"menuid":"8","icon":"icon-sys","menuname":"系统管理",
			"menus":[{"mid":"81","menuname":"用户管理","icon":"icon-users","url":"${admin_base}/user/list.at"}
				]
		}
]};

function alert(msg){
	$.messager.alert('提示',msg,'info');
}
$(function() {
    $.parser.parse();

    $('#loginOut').click(function() {
        $.messager.confirm('系统提示', '您确定要退出本次登录吗?', function(r) {
            if (r) {
            	location.href = '${base}/cms_admin/logout.at';
            }
        });
    });
});
</script>
</head>
<body class="easyui-layout" style="overflow-y: hidden" scroll="no">
		<noscript>
			<div
				style=" position:absolute; z-index:100000; height:2046px;top:0px;left:0px; width:100%; background:white; text-align:center;">
				<img src="${base }/res/images/noscript.gif" alt='抱歉，请开启脚本支持！' />
			</div>
		</noscript>
		<div region="north" split="false" border="false" style="overflow: hidden; height: 30px;background-color:#666666;
        line-height: 20px;color: #fff;padding-top: 3px;">
			<span style="float:right; padding-right:20px;" class="head">
				<span style="padding: 0px 10px;">您登陆的手机号码是：<b>${sessionScope.user.telNum }</b></span>今天是 <span id="bgclock"></span>&nbsp;<a href="javascript:void(0);" style="background-color: white" class="easyui-linkbutton" id="loginOut">安全退出</a>
			</span>
			<span style="padding-left:10px; font-size: 16px; line-height: 25px">
			<img src="${base }/favicon.ico" width="20" height="20" align="absmiddle" />
			APP内容发布系统
			</span>
		</div>

		<div region="west" split="true" title="菜单导航" style="width:180px;" id="west">
			<div id="menuAccordion" fit="true" border="false"></div>
		</div>

		<div id="mainPanle" region="center"
			style="background: #eee; overflow-y:hidden">
			<div id="tabs" class="easyui-tabs" fit="true" border="false">
				<div title="欢迎使用" href="${admin_base }/welcome.at">
				</div>
			</div>
		</div>

		<div region="south" split="false"
			style="height: 25px; background: #e2e2e2; ">
			<div class="footer">
				Copyright<span class="fontArial">&copy;</span>2014 Sohu.com Inc.
			</div>
		</div>

		<div id="mm" class="easyui-menu" style="width:175px;">
			<div id="mm-tabclose">
				关闭
			</div>
			<div id="mm-tabcloseall">
				全部关闭
			</div>
			<div id="mm-tabcloseother">
				除此之外全部关闭
			</div>
			<div class="menu-sep"></div>
			<div id="mm-tabcloseright">
				当前页右侧全部关闭
			</div>
			<div id="mm-tabcloseleft">
				当前页左侧全部关闭
			</div>
			<div class="menu-sep"></div>
			<div id="mm-exit">
				退出
			</div>
		</div>
	</body>
</html>