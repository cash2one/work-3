var pageSize = 3;
var totalCount = 0;
function loadUserReturnPageData(current){
	$.ajax({
		url:path+'/authorization/userList',
		data:{roleId:$("#sys_role").val(),startIndex:pageSize*current,pageSize:pageSize},
		dataType:"text",
		success:function(responseText){
			$("#authorizationListData").html(responseText);
			
			loadUserReturnPageNumberData(current);
		}
	});
	
}
/**
 * 加载用户列表的页码
 * @param current
 * @returns
 */
function loadUserReturnPageNumberData(current){
	$.ajax({
		url:path+'/authorization/userPageNumber',
		data:{total:totalCount,
			startIndex:pageSize*current,
			pageSize:pageSize
		},
		dataType:"text",
		success:function(responseText){
			$("#authorizationPageNumberData").html(responseText);
		}
	});
	
}