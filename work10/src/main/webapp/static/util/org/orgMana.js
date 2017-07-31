/*
 *  异步加载数据  填充到 orgMana.ftl中id为loadOrgListData的div
 */
var pageSize = 3;
var totalCount   = 0;
var orgOper;//声明orgOper对象的

$(function(){
	 orgOper = new orgOper();
	 
	loadOrgData(0);
	
	/**
	 * 查询按钮添加点击事件
	 * queryButton
	 */
	$("#queryButton").click(function(){
		
		loadOrgData(0);
	});
	
	$("#resetButton").click(function(){
		$("#qry_orgId").val("");
		$("#qry_orgName").val("");
		$("#state").val("");
		
	});
});



function loadOrgReturnNumber(current){
	loadOrgData(current);
}

function loadOrgData(current){
	//加载数据
	$.ajax({
		data:{startIndex:current*pageSize,pageSize:pageSize,parentId:$("#qry_orgId").val(),state:$("#state").val()},  //条件查询的条件
		url:path+"/org/orgList",
		dataType:"text",//  页面
		success:function(responseText){
			//alert(responseText);
			$("#loadOrgListData").html(responseText);
			
			loadOrgNumber(current);
		}
	});
}

function loadOrgNumber(current){
	
	//加载数据
	$.ajax({
		data:{startIndex:current*pageSize,pageSize:pageSize,total:totalCount },  //条件查询的条件
		url:path+"/org/orgNumber",
		dataType:"text",//  页面
		success:function(responseText){
			//alert(responseText);
			$("#loadOrgNumber").html(responseText);
		}
	});
}
