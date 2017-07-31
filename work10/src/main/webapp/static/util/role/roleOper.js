
function RoleOper(){
	
	this.addRole = function(){
		//弹框 layer
		var htmlStr = roleOper.initHtml();
		
		  $.layer({
				type : 1,
				title : false,
				area : [ 'auto', 'auto' ],
				page : {
					html : htmlStr
				}
			  });		
		
		
		
	}
	
	//ajax把弹框中输入框中的值 传到controller
	this.saveOrUpdateRole = function(){
		
		var param = new Object();
		var roleId =  $("#roleId").val();
		var desc = "新增";
		var url = path+"/role/addRole";
		if (roleId) {
			param.roleId = roleId;
			desc="修改";
			url = path+"/role/updateRole";
		}
		
		param.roleName = $("#roleName").val();
		param.state = $("#state").val();
		param.roleDesc = $("#roleDesc").val();
		
		$.ajax({
			url:url,
			data:param,
			dataType:'json',
			type:'post',
			success:function(responseData){
				if(responseData.isSuccess){
					var alerts= layer.alert(desc+"角色成功",1,function(){
		  				   layer.close(alerts);
		  				//ajax回调函数中 reload 刷新
		  				   location.reload();//刷新页面  加载js文件，  ajax
		  			   });	 
				}
			}
		});
		
		
	}
	
	/**
	 * 修改和查看
	 */
	this.queryOrUp = function(roleId,oper){
		//1，ajax请求数据
		var param = new Object();
		param.roleId = roleId;
		
		$.ajax({
			url:path+"/role/queryRole",
			data:param,
			dataType:'json',
			success:function(responseData){
				//2.弹框
				var htmlStr = roleOper.initHtml();
				$.layer({
					type : 1,
					title : false,
					area : [ 'auto', 'auto' ],
					page : {
						html : htmlStr
					}
				});
				
				//如果是查看把保存按钮隐藏了
				if(oper=='query'){
					$("#saveButton").hide();
				}
				
				//3,把ajax响应内容赋值给弹框中各个的输入框
				$("#roleId").val(responseData.roleId);
				$("#roleName").val(responseData.roleName);
				$("#state").val(responseData.state);
				$("#roleDesc").val(responseData.roleDesc);
				
			}
			
		});
		
		
		
	}
	
	this.deleteRole = function(roleId){
		
		$.ajax({
			url:path+"/role/deleteRole",
			data:{roleId:roleId},
			dataType:'json',
			success:function(responseData){
				if(responseData.isSuccess){
					var alerts= layer.alert("删除角色成功",1,function(){
		  				   layer.close(alerts);
		  				//ajax回调函数中 reload 刷新
		  				   location.reload();//刷新页面  加载js文件，  ajax
		  			   });	 
				}else{
					var alerts= layer.alert("删除角色失败",5,function(){
		  				   layer.close(alerts);
		  				//ajax回调函数中 reload 刷新
		  				   //location.reload();//刷新页面  加载js文件，  ajax
		  			   });	 
				}
			}
		});
		
	}
	
	
	this.initHtml = function(){
	    var htmlStr ="";	 
		htmlStr += '<div style="width:600px;" >';
		htmlStr += '<div style=" line-height:30px; text-indent:10px; margin-bottom:20px; background-color:#eee; position:relative;">角色管理</div>';
		htmlStr += '<div style="display:block; padding-bottom:20px;" align="center"  >';		
		htmlStr += '<table border="0" >';		
		htmlStr += '<tr style="display:none">';
		htmlStr += '<td colspan="2">';
		htmlStr += '<span>角色id:</span>';
		htmlStr += '<input type="text"  id="roleId" style="width:220px;">';
		htmlStr += '</td>';
		htmlStr += '</tr>';
		htmlStr += '<tr>';
		htmlStr += '<td>';
		htmlStr += '<span>角色名称:</span>';
		htmlStr += '<input type="text"  id="roleName" style="width:220px;">';
		htmlStr += '</td>';
		htmlStr += '<td>';
		htmlStr += '<span>是否可用:</span>';
		htmlStr += '<select id="state" style="width:220px;">';
		htmlStr += '<option value="">-请选择-</option>';
		htmlStr += '<option value="1">可用</option>';
		htmlStr += '<option value="2">不可用</option>';
		htmlStr += '</select>';
		htmlStr += '</td>';
		htmlStr += '</tr>';
		htmlStr += '<tr>';
		htmlStr += '<td colspan="2" align="center" >';	 
		htmlStr += '<span>角色描述:</span>';
		htmlStr += '<textarea rows="3"  id="roleDesc" style="width:500px;"></textarea>';
		htmlStr += '</td>';
		htmlStr += '</tr>';
		htmlStr += '<tr><td colspan="2" align="center">';
		htmlStr += '<input id="saveButton"  type="button" onclick="roleOper.saveOrUpdateRole()"   value="保存"  />';		
		htmlStr += '</td></tr>';
		htmlStr += '</table>';
		htmlStr += '</div>';
		htmlStr += '</div>';
     return htmlStr ;
	}
	
}