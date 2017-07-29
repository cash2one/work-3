<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>医疗服务</title>
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
	}
	.clearfix:after{
		content: '';
		display: block;
		clear: both;
	}

	a{
		text-decoration: none;
	}
	.yiliaoItem{
		list-style: none;
		width: 25%;
		text-align: center;
		float: left;
	}
    .firstlink{
    	width:145px;
    	height: 145px;
    	background: #ccc;
    	display: inline-block;
    	border-radius: 6px;
    	margin-bottom: 20px;
    }
   .yiliaoItems{
		padding: 30px 20px;
       border: 1px solid #ccc;
	}

	</style>
</head>
<body>
<div class="header">
	<h1>信美相互人寿</h1>
</div>
	<div class="container">
		<div class="yiliaoService">
			<ul class="yiliaoItems clearfix">
				<li class="yiliaoItem">
					<a href="${pageContext.request.contextPath}/toDisease" class="firstlink">重疾</a><br>
					<a href="${pageContext.request.contextPath}/toDisease">重疾绿通</a>
				</li>
				<li class="yiliaoItem">
					<a href="" class="firstlink">导诊</a><br>
					<a href="">导诊就医</a>
				</li>
				<li class="yiliaoItem">
					<a href="${pageContext.request.contextPath}/healthTooth" class="firstlink">体检</a><br>
					<a href="${pageContext.request.contextPath}/healthTooth">体检、齿科服务</a>
				</li>
				<li class="yiliaoItem">
					<a href="" class="firstlink">海外</a><br>
					<a href="">海外就医</a>
                    <p>请拨打客服电话（032-039211）</p>
				</li>
			</ul>
		</div>
	</div>
</body>
</html>