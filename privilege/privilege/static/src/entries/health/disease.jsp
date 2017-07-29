<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<head>
	<title>重疾绿通介绍</title>
	<script src="${path}/js/jquery-2.2.3.min.js"></script>
	<style>
	 .ZJcontainer{
	 	width: 100%;
	 }
     .ZJmain{
     	width: 60%;
     	 height: 200px;
     	margin: 0 auto;
     	border: 1px solid #ccc;
     	position: relative;
     }
     .linkItem{
     	text-decoration: none;
     	border: 1px solid #ccc;
     	background: #ddd;
     	text-align: center;
     	padding: 5px 15px;
     }
     .clickItem{
     	position: absolute;
     	bottom: 10px;
        left: 20px;
     }
	</style>
</head>
<body>
	<h1>重疾绿通介绍</h1>
	<div class="ZJcontainer">
	   <div class="ZJmain">
		<p>
			什么是重疾绿通？ 
        当客户经二级以上医院初步诊断罹患约定的重大疾病时，经医生建议需进行二次诊断、住院或手术时，客户可以申请享受绿色通道服务。
        具体内容包括：
        安排指定专家为客户会诊治疗
        安排入客户入住指定的专家病房
		</p>
		<div class="clickItem">
			<a href="javascript:void(0);" class="linkItem" id="continue">继续</a>
			<a href="javascript:void(0);" class="linkItem" onclick="history.go(-1);">返回</a>
		</div>
	 </div>
	</div>
	<script>
	$('#continue').click(function(){
		$.ajax({
   	    	url:'${path}/check',
   	    	type:'post',
   	    	data:{"userId":1},
   	    	dataType:'json',
   	    	success:function(data){
   	    		//"Y"有资格 "N"没资格
   	    		alert(data.flag);
   	    	},
   	    	error:function(data){
   	    		alert(error);
   	    	}
    	})
	})	
	</script>
</body>
</html>