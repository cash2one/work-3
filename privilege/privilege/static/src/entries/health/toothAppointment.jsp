<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<head>
	<title>预约齿科</title>
	<link rel="stylesheet" type="text/css" href="${path}/css/birthDate.css">
	<script src="${path}/js/jquery-2.2.3.min.js"></script>
	<style>
      .formMain{
      	width: 80%;
      	margin: 0 auto;
      	border: 1px solid #ccc;
      }
      .formItem{
      	list-style: none;
      	margin: 15px;
      }
	</style>
	<script type="text/javascript">
		$(function(){
			$("#healthOrg option").click(function(){
				var orgId = $(this).attr("point");
				$("#healthItem").html("");
				$("#itemPoint").html("");
				$("#consumeIntegral").val("");
				getItemsByOrgId(orgId);
			});
		});
		
		//根据机构id获取项目
		function getItemsByOrgId(orgId){
			$.ajax({
				url:"${path}/getItemByOrgId",
				type:"post",
				data:{"orgId":orgId},
				dataType:"json",
				success:function(data){
					var str = "";
					for(i=0;i<data.length;i++){
						str += "<option point=\""+data[i].itemPoint+"\" value=\""+data[i].itemName+"\">"+data[i].itemName+"</option>";
						if(i==0){
							$("#itemPoint").html(data[i].itemPoint);
							$("#consumeIntegral").val(data[i].itemPoint);
						}
					}
					$("#healthItem").html(str);
					$("#healthItem option").click(function(){
						var point = $(this).attr("point");
						$("#itemPoint").html("");
						$("#consumeIntegral").val("");
						$("#itemPoint").html(point);
						$("#consumeIntegral").val(point);
					});
				}
			})
		}
	
	</script>
</head>
<body>
	<div class="header">
		信美相互人寿
	</div>
    <div class="container">
    <h3>齿科预约</h3>
    	<form action="${path}/save" method="post">
    	<input type="hidden" name="serverType" value="000"/>
    	<ul class="formMain">
    		<li class="formItem">
    			<label for="">当前积分：</label>
    			<span>${myPoints}</span>         
    		</li>
    		<li class="formItem">
    			<label for="">齿科机构：</label>
    			
				<select id="healthOrg" name="healthOrg">
						<option value="0" select="select"></option>
					<c:forEach items="${orgs}" var="org">
						<option point="${org.id}" value="${org.orgName}">${org.orgName}</option>
					</c:forEach>
	            </select>
    		</li>
    		<li class="formItem">
    			<label for="">齿科项目：</label>
				<select id="healthItem" name="healthItem">
	            </select>
    		</li>
    		<li class="formItem">
    		     <label for="">预约时间：</label>
    			 <input class="Wdate" name="appointmentTime" type="text" id="date" />
    		</li>
    		<li class="formItem">
    			<label for="">消耗积分：</label>
    			<input id="consumeIntegral" type="hidden" name="consumeIntegral" value=""/>
    			<span id="itemPoint"></span>         
    		</li>
    		<li class="formItem">
    			<input type="submit" value="Submit" id="g" />
    		</li>
    	</ul>
    	</form>
    </div>
    <script src="${path}/js/birthDate.js"></script>
    <script>
	    $(function(){
			$('#date').mobiscroll().datetime(opt); 
		});
	 	var now = new Date();
		var opt = {  
				theme: 'android-holo',    
		        lang: 'zh', 
		        display: 'center', 
		        mode: 'datetimeInvalid',       
				min: new Date(now.getFullYear()-110, now.getMonth(), now.getDate()), 
				endYear:2016, //结束年份
				dateFormat: 'yy-mm-dd', //返回结果格式化为年月格式  
			    timeWheels: 'HHii',
			    invalid: [  { start: '00:00', end: '08:00' },{  start: '12:00', end: '23:59'} ]
			    };
		 $('#datetimeInvalid-show').click(function () {
		     $('#datetimeInvalid-demo').mobiscroll('show');
		     return false;
		 });
		
		 $('#datetimeInvalid-clear').click(function () {
		     $('#datetimeInvalid-demo').mobiscroll('clear');
		     return false;
		 });
 	</script>
</body>
</html>