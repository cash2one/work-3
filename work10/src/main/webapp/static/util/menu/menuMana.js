/*
 * 异步加载数据 把加载到数据  展示到menuList.ftl的页面
 * 然后通过html()方法把加载到的meneList页面和  menuPageNumber 页面 填充到menuMana页面相关div中
 */
var pageSize = 3;
var totalCount   = 0;

var menuOper;


$(function(){
	
	menuOper = new menuOper();
	 
	loadMenuReturnPageData(0);
	
	//给查询按钮绑定一个事件
	$("#queryButton").bind("click",function(){
		loadMenuReturnPageData(0);
	});
	
	$("#resetButton").bind("click",function(){
		$("#qry_parentMenuName").val("");
		$("#qry_parentMenuId").val("");
		$("#qry_menuName").val("");
		$("#qry_state").val("");
		
		loadMenuReturnPageData(0);
	});
	
	
	
});

function   loadMenuReturnPageData(current){
	
	//加载数据
	$.ajax({
		data:{startIndex:current*pageSize,pageSize:pageSize,parentId:$("#qry_parentMenuId").val(),menuName:$("#qry_menuName").val(),state:$("#qry_state").val()},
		url:path+"/menu/menuList",
		dataType:"text",//页面
		success:function(responseText){
			//把响应的内容填充给menuMana.ftl的id为menuListData 的div中
			$("#menuListData").html(responseText);
			loadMenuPageNumber(current);
		}
	});
	
}

function  loadMenuPageNumber(current){
	//加载数据
	$.ajax({
		data:{startIndex:current*pageSize,pageSize:pageSize,total:totalCount},
		url:path+"/menu/menuPageNumber",
		dataType:"text",//页面
		success:function(responseText){
			//把响应的内容填充给menuMana.ftl的id为menuPageNumberData 的div中
			$("#menuPageNumberData").html(responseText);
			
		}
	});
}



