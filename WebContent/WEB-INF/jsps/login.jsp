<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>用户登录--APP内容发布系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="icon" href="${base }/favicon.ico" type="image/x-icon" />
<link href="http://libs.baidu.com/bootstrap/2.3.2/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="http://libs.baidu.com/bootstrap/2.3.2/css/bootstrap-responsive.min.css"
	rel="stylesheet">
<script src="http://libs.baidu.com/jquery/1.9.1/jquery.min.js"></script>
<script src="http://libs.baidu.com/bootstrap/2.3.2/js/bootstrap.min.js"></script>
<!--[if lt IE 9]>
<script src="http://cdn.bootcss.com/html5shiv/3.6.2/html5shiv.min.js"></script>
<![endif]-->
<style type="text/css">
body {
	padding-top: 40px;
	padding-bottom: 40px;
	background-color: rgb(119, 143, 238);
}

.form-signin {
	max-width: 400px;
	max-height: 600px;
	padding: 19px 29px 29px;
	margin: 2px auto 20px;
	background-color: #fff;
	border: 1px solid #e5e5e5;
	-webkit-border-radius: 5px;
	-moz-border-radius: 5px;
	border-radius: 5px;
	-webkit-box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
	-moz-box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
	box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
}

.form-signin 
      .form-signin-heading {
	margin-bottom: 20px;
}

.form-signin input[type="text"],.form-signin input[type="password"] {
	font-size: 16px;
	height: 40px;
	width: 260px;
	margin-bottom: 15px;
	padding: 10px 10px;
}
</style>
<script type="text/javascript">
try{
    if (top.location != self.location){ 
       self.location = top.location;
       top.window.location.href = "${base}/cms_admin/login.at";
       window.location.reload();
    }
}catch(e){;}
	$(function() {
		$('#telNum').keyup(function(event) {
			if (event.keyCode == '13') {
				$('#pwd').focus();
			}
		});
		$('#pwd').keyup(function(event) {
			if (event.keyCode == '13') {
				login();
			}
		});
	});

	function sendMsg(){
		var telNum = $("#telNum").val();
		if(telNum.length==0){
			alert("手机号为空");
			$("#telNum").focus();
			return;
		}
		$.ajax({
			type:"post",
			url:"sendMessage.at",
			async:true,
			data:{telNum:telNum},
			dataType:"json",
			cache: false,
			success: function(json){
				if(json.status=="200"){
					alert("发送完成。");
				}else{
					alert(json.msg);
				}
			},
			error: function(xhr, ajaxOptions, thrownError){
				showMsg("Http status: " + xhr.status + " " + xhr.statusText + "<br>ajaxOptions: " + ajaxOptions + "<br>thrownError:"+thrownError + "<br>" +xhr.responseText);
			}
		});
	}
	

	
	function login() {
		if (loginForm.telNum.value == "") {
			alert("请输入手机号");
			return;
		}
		if (loginForm.pwd.value == "") {
			alert("请输入密码");
			return;
		}
		$.post('dologin.at', $('#loginForm').serialize(), function(result) {
			if (result.STATUS=="200") {
				window.location.href="${admin_base}/main.at";
			} 
			if (result.STATUS=="300") {
				alert(result.msg);
			} 
		}, "JSON");

	}
</script>
</head>
<body>
	<div style="padding: 60px" data-options="region:'center',border:false">
		<form id="loginForm" method="post" class="form-signin">
			<div align="center">
				<span><h1 class="form-signin-heading">APP内容发布系统</h1></span>
			</div>
			<div align="center">
				<a href="${base }/"><img alt="" src="${base }/res/images/sohulogo.jpg"></a>
			</div>
			<div align="center">
				<table height="30%">
					<tr>
						<td align="center"><input type="text" name="telNum"
							id="telNum" class="span2" placeholder="请输入手机号" value="18610643700">
						</td>
					</tr>
					<tr>
						<td align="center"><input type="password" name="pwd" id="pwd"
							class="span2" placeholder="请输入密码" value="g1zvw61hn"></td>
					</tr>
					<tr>
						<td align="center">
							<button id="loginin" class="btn btn-large btn-primary"
								type="button" onClick="login()">登录系统</button>
							<a href="javascript:sendMsg()">发送密码到手机</a>
						</td>
					</tr>
				</table>

			</div>

		</form>
	</div>

	<div id="logininfo" align="center">
		<footer id="footer"> <nav> <span></span> <span>建议使用chome或firfox浏览器登陆
		</nav> </footer>
	</div>
</body>
</html>
