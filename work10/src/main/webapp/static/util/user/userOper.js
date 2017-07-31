/**
 * 增删改查公用
 */

function UserOper(){

	
	 
	this.addUser = function(){
		//layer
		//弹框 layer
		var htmlStr = userOper.initHtml();
		
		  $.layer({
				type : 1,
				title : false,
				area : [ 'auto', 'auto' ],
				page : {
					html : htmlStr
				}
			  });	
		  
		  
		  initYearAndMonthAndDay();
			 
		  //初始化新增的三级联动
		 var cascade = new Cascade("provinceId","cityId","countryId");
	}
	
	
	//选择生日的操作
	function initYearAndMonthAndDay(){
		$("#year").append("<option value=''>-请选择-</option>");
		for(var i = 1930;i<=2017;i++){
			$("#year").append("<option value='"+i+"'>"+i+"</option>");
		}
		$("#month").append("<option value=''>-请选择-</option>");
		for(var i = 1;i<=12;i++){
			$("#month").append("<option value='"+i+"'>"+i+"</option>");
		}
		
		$("#day").append("<option value=''>-请选择-</option>");
		
		$("#month").change(function(){
			$("#day option").remove();
			initDay();
		});
	}
	
	function initDay(){
		//得到用户选择的年  和 月
		var year  = $("#year").val();
		var month = $("#month").val();
		
		if(year==''){
			alert("请选择年份");
			return;
		}	
	
//		if(month==2){
//			if(year%400==0||year%4==0&&year%100!=0){//瑞年
//				for(var i = 1;i<=29;i++){
//					$("#day").append("<option value='"+i+"'>"+i+"</option>");
//				}
//			}else{
//				for(var i = 1;i<=28;i++){
//					$("#day").append("<option value='"+i+"'>"+i+"</option>");
//				}
//			}
//			
//		}
//		else if(month==1||month==3||month==5||month==7||month==8||month==10||month==12){
//			for(var i = 1;i<=31;i++){
//				$("#day").append("<option value='"+i+"'>"+i+"</option>");
//			}
//		}else{
//			for(var i = 1;i<=30;i++){
//				$("#day").append("<option value='"+i+"'>"+i+"</option>");
//			}
//		}
		
		for(var i = 1;i<=30;i++){
			if(month==2){
				if(year%400==0||year%4==0&&year%100!=0&&i==29){//瑞年
					break;
			   }else if(i==28){					
					break;
				}
				
			}
			$("#day").append("<option value='"+i+"'>"+i+"</option>");
			if (month==1||month==3||month==5||month==7||month==8||month==10||month==12) {
				$("#day").append("<option value='"+31+"'>"+31+"</option>");
			}
			
			
		}
		
	}
	
	/**
	 * 修改
	 */
	this.updateUser = function(userId){
		
		
		//ajax请求数据库
		$.ajax({
			url:path+"/user/queryUser",
			data:{userId:userId},
			dataType:"json",
			type:"post",
			success:function(responseData){
				//alert(responseData+"---"+responseData.userChName);
			//	if(responseData.isSuccess){//成功
					//弹框 layer
					var htmlStr = userOper.initHtml();
					  $.layer({
							type : 1,
							title : false,
							area : [ 'auto', 'auto' ],
							page : {
								html : htmlStr
							}
						  });	
					  
					initYearAndMonthAndDay();
					  //初始化新增的三级联动
					 var cascade = new Cascade("provinceId","cityId","countryId");
					 
					// alert($("#userChName"));
					//把ajax请求回来的对象 给页面的输入框  赋值
					$("#orgId").val(responseData.orgId);
					$("#userId").val(responseData.userId);
					$("#userChName").val(responseData.userChName);
					$("#userSex").val(responseData.userSex);
					
					$("#mobilePhone").val(responseData.mobilePhone);
					$("#email").val(responseData.email);
					$("#userName").val(responseData.userName);
					$("#userPassword").val(responseData.userPassword);
//					param.provinceId = $("#provinceId").val();//value 属性的值
//				//	alert($("#provinceId").text());
//					param.provinceName = $("#provinceId:selected").text();
//					param.cityId = $("#cityId").val();//value 属性的值
//					param.cityName = $("#userId").text();
//					param.countryId = $("#countryId").val();//value 属性的值
//					param.countryName = $("#countryId").text();
					
					
					
				//}
			}
		});
		
		
		
	
		
		
	}
	
	
	this.saveUser = function(){
		
		var  param = new Object();
		var userId = $("#userId").val();
		var desc = "新增";
		var url = path+"/user/addUser";
		if (userId) {
			param.userId = userId;
			desc = "修改";
			url = path+"/user/updateUser";
		}
		
		param.orgId = $("#orgId").val();
		param.userChName = $("#userChName").val();
		param.userSex = $("#userSex").val();
		param.birthday = $("#year").val()+"-"+ $("#month").val()+"-"+$("#day").val();
		param.mobilePhone = $("#mobilePhone").val();
		param.email = $("#email").val();
		param.userName = $("#userName").val();
		param.userPassword = $("#userPassword").val();
		param.provinceId = $("#provinceId").val();//value 属性的值
	//	alert($("#provinceId").text());
		param.provinceName = $("#provinceId:selected").text();
		param.cityId = $("#cityId").val();//value 属性的值
		param.cityName = $("#userId").text();
		param.countryId = $("#countryId").val();//value 属性的值
		param.countryName = $("#countryId").text();
		
		$.ajax({
			url:url,
			data:param,
			dataType:"json",
			type:"post",
			success:function(responseData){
				if(responseData.isSuccess){//成功
					//reload();
					layer.alert(desc+"用户成功",1,function(){
						//刷新页面 （重新加载页面，执行js或者jquery的入口函数，发送请求 查询数据库）
						location.reload();
						 layer.closeAll();
					});
					
				}
			}
		});
		
		
	}
	
	
	this.initHtml = function(){
		var htmlStr = '';
		htmlStr += '<div style="width:600px;" >';
		htmlStr += '<div style="display:block; padding-bottom:20px;" align="center"  >';
		htmlStr += '<table border="0" >';
		htmlStr += '<tr style="display:none">';
		htmlStr += '<td colspan="2">';
		htmlStr += '<span>用户ID:</span>';
		htmlStr += '<input type="text"  id="userId" style="width:240px;">';
		htmlStr += '</td>';
		htmlStr += '</tr>';
		
		htmlStr += '<tr>';
		htmlStr += '<td>';
		htmlStr += '所属组织：';
		htmlStr += '<input type="text" id="orgName"  style="width:110px;" readonly="readonly" >';
		htmlStr += '<input type="hidden" id="orgId">';
        htmlStr += '<button  onclick="AddOrgTreeLayer();">选择</button>';
		htmlStr += '</td>';
		htmlStr += '<td>';
		htmlStr += '<span>姓名:</span>';
		htmlStr += '<input type="text"  id="userChName" style="width:240px;">';
		htmlStr += '</td>';
		htmlStr += '</tr>';
		
		htmlStr += '<tr>';
		htmlStr += '<td>'; 
		htmlStr += '性别：<input type="radio" id="userSex" name="userSex"  value="1">男'; 
		htmlStr += '<input type="radio" name="userSex"   value="2">女'; 
		htmlStr += '<input type="radio" name="userSex"   value="3">保密'; 
		htmlStr += '</td>';
		htmlStr += '<td>'; 
		htmlStr += '生日： <select id="year"  name="year"></select>年'; 
		htmlStr += '<select id="month" name="month"></select>月'; 
		htmlStr += '<select id="day"  name="day"></select>日'; 
		htmlStr += '</td>';
		htmlStr += '</tr>';
		
	
		htmlStr += '<tr>';
		htmlStr += '<td>';
		htmlStr += '<span>电话:</span>';
		htmlStr += '<input type="text"  id="mobilePhone" style="width:240px;">';
		htmlStr += '</td>';
		htmlStr += '<td>';
		htmlStr += '<span>邮件:</span>';
		htmlStr += '<input type="text"  id="email" style="width:240px;">';
		htmlStr += '</td>';
		htmlStr += '</tr>';
		
		htmlStr += '<tr>';
		htmlStr += '<td>';
		htmlStr += '<span>用户名:</span>';
		htmlStr += '<input type="text"  id="userName" style="width:240px;">';
		htmlStr += '</td>';
		htmlStr += '<td>';
		htmlStr += '<span>密码:</span>';
		htmlStr += '<input type="text"  id="userPassword" style="width:240px;">';
		htmlStr += '</td>';
		htmlStr += '</tr>';
		
		htmlStr += '<tr>';
		htmlStr += '<td>';
		htmlStr += '<span>省份:</span>';
		htmlStr += '<select id="provinceId" style="width:240px;">';
		htmlStr += '</select>';
		htmlStr += '</td>';
		htmlStr += '<td>';
		htmlStr += '<span>地市:</span>';
		htmlStr += '<select id="cityId" style="width:240px;">';
		htmlStr += '</select>';
		htmlStr += '</td>';
		htmlStr += '</tr>';
		
		htmlStr += '<tr>';
		htmlStr += '<td>';
		htmlStr += '<span>区县:</span>';
		htmlStr += '<select id="countryId" style="width:240px;">';
		htmlStr += '</select>';
		htmlStr += '</td>';
		htmlStr += '<td>';
		htmlStr += '</td>';
		htmlStr += '</tr>';
		htmlStr += '<tr id="saveUserTr"><td colspan="2" align="center">';
		htmlStr += '<input id="saveButton" name="" type="button"  onclick="javascript:userOper.saveUser();" value="保存"  />';		
		htmlStr += '</td></tr>';
		htmlStr += '</table>';
		htmlStr += '</div>';
		htmlStr += '</div>';
		
		return htmlStr;
	
	}	
}