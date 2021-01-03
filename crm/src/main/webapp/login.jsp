<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<!--使用绝对路径引用-->
	<%
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + 	request.getServerPort() + request.getContextPath() + "/";
	%>
	<base href="<%=basePath%>">
<link href="jquery/bootstrap_3.3.0/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="jquery/jquery-1.11.1-min.js"></script>
<script type="text/javascript" src="jquery/bootstrap_3.3.0/js/bootstrap.min.js">
</script>
	<script>
		$
		$(function (){
			var loginAct=$("#loginAct");
			var loginPwd=$("#loginPwd");
			/*当页面加载完毕后，让用户的文本框自动获得焦点*/
			loginAct.focus();
			/*当页面加载完毕之后，自动清空文本框*/
			loginAct.val("");
			loginPwd.val("");
			$("#submitBtn").click(function (){
				/*if(loginAct.equals("") || loginPwd.val()=""){

				}*/

				login();
			})
			/*为当前窗口添加键盘按下事件*/
			$(window).keydown(function (e){


				//alert(e.keyCode);
				if(e.keyCode==13){
					login();
				}
			})
		})

		function login() {
			/*去除前后空白，方面后续的验证*/
			loginAct=$.trim($("#loginAct").val());
			loginPwd=$.trim($("#loginPwd").val());
			var msg=$("#msg");
			if (loginAct=="" || loginPwd==""){
				/*显示信息提示用户需要完整输入用户名和密码*/
				msg.text("用户名密码不能为空！");
				return false;/*如果执行了这个分支，则直接结束函数*/
			}
			$.ajax({
				url:"settings/user/login.do",
				dataType:"json",
				data:{
					"loginAct":loginAct,
					"loginPwd":loginPwd
				},
				type:"post",
				success:function (data){
					if(data.success){//从后台传回json数据，如果登录成功，则跳转至工作台主界面
						window.location.href="workbench/index.jsp";
					}else {
						//$("#msg").val(data.msg);val在这里无法使用val针对表单数据的

						$("#msg").html(data.msg);
					}
					}
				})
		}
	</script>
</head>
<body>
	<div style="position: absolute; top: 0px; left: 0px; width: 60%;">
		<img src="image/IMG_7114.JPG" style="width: 100%; height: 90%; position: relative; top: 50px;">
	</div>
	<div id="top" style="height: 50px; background-color: #3C3C3C; width: 100%;">
		<div style="position: absolute; top: 5px; left: 0px; font-size: 30px; font-weight: 400; color: white; font-family: 'times new roman'">CRM &nbsp;<span style="font-size: 12px;">&copy;2017&nbsp;动力节点</span></div>
	</div>

	<div style="position: absolute; top: 120px; right: 100px;width:450px;height:400px;border:1px solid #D5D5D5">
		<div style="position: absolute; top: 0px; right: 60px;">
			<div class="page-header">
				<h1>登录</h1>
			</div>
			<form action="workbench/index.jsp" class="form-horizontal" role="form">
				<div class="form-group form-group-lg">
					<div style="width: 350px;">
						<input class="form-control" id="loginAct" type="text" placeholder="用户名">
					</div>
					<div style="width: 350px; position: relative;top: 20px;">
						<input class="form-control" id="loginPwd" type="password" placeholder="密码" >
					</div>
					<div class="checkbox"  style="position: relative;top: 30px; left: 10px;">

							<span id="msg" style="color: red"></span>

					</div>
					<button type="button" id="submitBtn" class="btn btn-primary btn-lg btn-block"  style="width: 350px; position: relative;top: 45px;">登录</button>
				</div>
			</form>
		</div>
	</div>
</body>
</html>