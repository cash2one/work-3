$(function(){
	//预加载角色信息
	initRoleList();
	
	//给查询按钮绑定一个事件
	$("#queryButton").click(function(){
		queryDataList();
	});
	
});


function queryDataList(){
	//得到用户选择的角色编号
	var roleId = $("#sys_role").val();
	//得到用户选择的类型
	var authorizationObj = $("#type").val();
	if(roleId==''){
		alert("请选择角色");
		return ;
	}
	if(authorizationObj==''){
		alert("请选择授权类型");
		return ;
	}
	
	
	if(authorizationObj==1){//类型为组织
		loadOrgReturnPageData(0);
	}
	if(authorizationObj==2){//类型为个人
		loadUserReturnPageData(0);
	}
	if(authorizationObj==3){//类型为菜单
		loadMenuReturnPageData(0);
	}	
}

/**
 * 加载所有可用的角色信息
 * @returns
 */
function initRoleList(){
	var roleObj = $("#sys_role");
	
	$.ajax({
		url:path+'/authorization/roleList',
		data:{},
		dataType:'json',
		success:function(responseData){//[{},{},{}]  
			roleObj.append("<option value=''>-请选择-</option>");
			//遍历
			$.each(responseData,function(index,data){//
				roleObj.append("<option value='"+data.roleId+"'>"+data.roleName+"</option>");
			});
		}
	});
}