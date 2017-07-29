<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>互助服务</title>
	<style>
	.main{
		width: 100%;

	}
	.comnHelp{
		width:80%;
		margin: 0 auto;
		border: 1px solid #ccc;
		padding: 30px 20px;
	}
	.comnHelp:after{
		content: '';
		display: block;
		clear: both;
	}
	.comnItem{
		width: 40%;
		margin: 20px;
		list-style: none;
		float: left;
		padding: 20px;
		background: #ccc;
		text-align: center;
	}
	.linkDetail{
		text-decoration: none;
		border: 1px solid #ccc;
		background: black;
		color:#fff;
		padding: 5px 15px;
	}
	.margin-right{
	  margin-left:50px;
	}
	</style>
</head>
<body>
	<h1>信美相互人寿</h1>
	<div class="main">
		<ul class="comnHelp">
			<c:forEach items="${products}" var="p">
				<li class="comnItem">
					<p>${p.mutualName}<span class="margin-right">参加人数：${p.mutualJoinPeoples}</span></p>
					<div class="itemDetail">
						<p>总金额：${p.mutualTotalAmount}</p>
						<p>使用金额：${p.mutualUsedAmount}</p>
						<div>
							<a href="" class="linkDetail">查看详情</a>
						</div>
					</div>
				</li>
			</c:forEach>
		</ul>
	</div>
</body>
</html>