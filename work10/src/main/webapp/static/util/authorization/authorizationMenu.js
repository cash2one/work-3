
/**
 * 加载菜单信息
 * @param current
 * @returns
 */
function loadMenuReturnPageData(current){
	$.ajax({
		url:path+'/authorization/menuList',
		data:{roleId:$("#sys_role").val(),startIndex:pageSize*current,pageSize:pageSize},
		dataType:"text",
		success:function(responseText){
			$("#authorizationListData").html(responseText);
			
			loadMenuReturnPageNumberData(current);
		}
	});
	
}

function loadMenuReturnPageNumberData(current){
	$.ajax({
		url:path+'/authorization/menuPageNumber',
		data:{total:totalCount,startIndex:pageSize*current,pageSize:pageSize},
		dataType:"text",
		success:function(responseText){
			$("#authorizationPageNumberData").html(responseText);
			}
	});
}