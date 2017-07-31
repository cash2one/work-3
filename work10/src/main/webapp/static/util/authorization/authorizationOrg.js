
/**
 * 加载该角色下的：组织-信息
 * @param current
 * @returns
 */
var pageSize = 3;
var totalCount = 0;
//loadOrgReturnPageData
function loadOrgReturnPageData(current){
	//alert($("#sys_role").val());
	$.ajax({
		url:path+"/authorization/orgList",
		data:{roleId:$("#sys_role").val(),startIndex:pageSize*current,pageSize:pageSize},
		dataType:'text',
		success:function(responseText){
			//把响应的内容 填充到authorizationMana对应的div中
			$("#authorizationListData").html(responseText);
			loadOrgReturnPageNumberData(current);
		}
		
	});
	
}
/**
 * 页码
 * @param current
 * @returns
 */

function loadOrgReturnPageNumberData(current){
	$.ajax({
		url:path+"/authorization/orgPageNumber",
		data:{total:totalCount,startIndex:pageSize*current,pageSize:pageSize},
		dataType:'text',
		success:function(responseText){
			$("#authorizationPageNumberData").html(responseText);
		}
		});
}


function  deleteAuthorizationOrg(orgId){
	$.ajax({
		url:path+"/authorization/deleteOrgRel",
		data:{orgId:orgId},
		dataType:'json',
		success:function(responseData){
			if(responseData.isSuccess){
			   layer.alert("删除组织成功",1,function(){
				  layer. closeAll();
				  loadOrgReturnPageData(0);
			   });	
			}else{
				 layer.alert("删除组织失败",5,function(){
					  layer. closeAll();
				   });	
			}
			}
		});
}