/**
*主页面的组合
*把roleList.ftl  rolePageNumber.ftl 组合到 roleMana.ftl页面
*/
var pageSize = 3;
var totalCount = 0;
var roleOper ;
$(function(){
	
	roleOper = new RoleOper();//创建对象
	
	loadRoleListData(0);
	
	$("#queryButton").click(function(){
		loadRoleListData(0);
	});
	
	$("#resetButton").click(function(){
		$("#qry_roleName").val('');
		$("#qry_roleState").val('');
		loadRoleListData(0);
	});
	
});


function loadRoleListData(current){
	
	$.ajax({
		url:path+"/role/roleList",
		data:{
			startIndex:current*pageSize,
			pageSize:pageSize,
			roleName:$("#qry_roleName").val(),
			state:$("#qry_roleState").val()
		},
		dataType:"text",//响应网页
		success:function(responseText){
			$("#roleListDataContainer").html(responseText);
			
			//加载页码
			loadrolePageNumber(current);
		}
	});
	
}


function loadrolePageNumber(current){
	$.ajax({
		url:path+"/role/rolePageNumber",
		data:{
			startIndex:current*pageSize,
			pageSize:pageSize,
			total:totalCount
		},
		dataType:"text",//响应网页
		success:function(responseText){
			//ajax 响应的rolePageumber.ftl
			//把这个页面填充到roleMana.ftl
			$("#rolePageNumberContainer").html(responseText);
		}
	});
	
	
}