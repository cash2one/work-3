<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Basic Form - jQuery EasyUI Demo</title>
	
	<link rel="stylesheet" type="text/css" href="${c.contextPath}/static/easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${c.contextPath}/static/easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="${c.contextPath}/static/easyui/demo.css">
	<script type="text/javascript" src="${c.contextPath}/static/easyui/jquery.min.js"></script>
	<script type="text/javascript" src="${c.contextPath}/static/easyui/jquery.easyui.min.js"></script>
</head>
<body bgcolor="#c3f0a9">
	<div align="center">
	<div style="margin:180px 0;"></div>
	<div class="easyui-panel" title="登录" style="width:100%;max-width:400px;padding:30px 60px;">
		<form id="ff" method="post" action="${c.contextPath}/user/login">
			<div style="margin-bottom:20px">
				<input class="easyui-textbox" name="userName" style="width:100%" data-options="label:'Name:',required:true">
			</div>
			<div style="margin-bottom:20px">
				<input class="easyui-textbox" name="userPassword" style="width:100%" data-options="label:'Email:',required:true,validType:'email'">
			</div>	
		</form>
		<div style="text-align:center;padding:5px 0">
			<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()" style="width:80px">登录</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()" style="width:80px">取消</a>
		</div>
	</div>
	</div>
	<script>
		function submitForm(){
			document.getElementById("ff").submit();
		}
		function clearForm(){
			$('#ff').form('clear');
		}
	</script>
</body>
</html>