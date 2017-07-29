<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>预约成功</title>
	<style>

	*{
	    margin: 0px;
	    padding: 0px;
	    box-sizing:border-box;
	}
	.header{
		width: 1000px;
		margin: 0 auto;

	}
	.header h1{
		padding: 20px;
		border: 1px solid #ccc;
	}
	.container{
		width: 1000px;
		margin: 0 auto;
		height: 500px;
		text-align: center;
		border: 1px solid #ccc;
	}
	.container h1{
		margin-top: 200px;
	}
	.btn{
		display: inline-block;
		width:100px;
		background: blue;
		margin-top: 50px;
		text-align: center;
		padding: 5px 30px;
		border-radius: 6px;
		text-decoration: none;
	}
</style>
</head>
<body>
	<div class="header">
		<h1>信美相互人寿</h1>
	</div>
	<div class="container">
		<h1>预约成功！！！</h1>
		<a href="${pageContext.request.contextPath}/healthTooth" class="btn">确定</a>
	</div>
</body>
</html>