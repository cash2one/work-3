/*
*把userList.ftl  userPageNumber 整合到userMana.ftl文件中
*ajax
*/
var pageSize = 5;
var totalCount = 0;

var userOper;

$(function(){
	
	userOper = new UserOper();
	
	//三级联动的js
	//var cascade = new Cascade();
	var cascade = new Cascade("qry_provinceId","qry_cityId","qry_countryId");
	
	loadUserListData(0);
	//给查询按钮绑定一个事件
	$("#queryButton").click(function(){
		loadUserListData(0);
	});
	//给查询按钮绑定一个事件
	$("#resetButton").click(function(){
		$("#qry_userCHName").val('');
		$("#qry_telphonenumber").val('');
		$("#qry_provinceId").val('');
		$("#qry_cityId").val('');
		$("#qry_countryId").val('');
		loadUserListData(0);
	});
	
	
	$("#test").click(function(){
		return false;
	});
	
	
});

function loadUserListData(current){
	//ajax加载用户列表
	$.ajax({
		url:path+"/user/userList",
		data:{
			startIndex:current*pageSize,
			pageSize:pageSize,
			orgId:$("#qry_orgId").val(),
			userCHName:$("#qry_userCHName").val(),
			telphone:$("#qry_telphonenumber").val(),
			provinceId:$("#qry_provinceId").val(),
			cityId:$("#qry_cityId").val(),
			countryId:$("#qry_countryId").val()
		},
		 dataType:"text",
		 type:"post",
		 success:function(responseText){
			 $("#userlistDataContainner").html(responseText);//responseText  对应的是userList.ftl
			 
			 loadUserPageNumberData(current);
		 }
	});	
}

/**
 * 加载页码
 * @param current
 * @returns
 */
function  loadUserPageNumberData(current){
	$.ajax({
		url:path+"/user/userPageNumber",
		data:{
			startIndex:current*pageSize,
			pageSize:pageSize,
			total:totalCount
			},
		dataType:'text',
		success:function(responseText){//responseText 代表 userPageNumber.ftl
			$("#userPageNumberContainner").html(responseText);
			}
		});	
}


