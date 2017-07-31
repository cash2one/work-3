/**
 * 菜单操作
*/

function menuOper(){
	
	this.addMenu=function(){
		//1,弹框
		var  htmlStr = menuOper.initHtml();
		  $.layer({
				type : 1,
				title : false,
				area : [ 'auto', 'auto' ],
				page : {
					html : htmlStr
				}
			  });		
		
	}
	
	/**
	 * 新增  修改
	 */
	this.saveOrUpdateMenu = function (){
		//怎么判断是修改还是新增?
		//menuId  有值  修改  没有值：新增
		var menuId = $("#menuId").val();
		var desc = "新增";
		var url = path+"/menu/addMenu";
		
		var param = new Object();
	
		if (menuId) {//有值
			desc = "修改";
			url=path+"/menu/updateMenu";
			param.menuId = menuId;
		}
		
		param.menuParentId = $("#menuParentId").val();
		param.menuName = $("#menuName").val();
		param.isPublish = $("#isPublish").val();
		param.menuType = $("#menuType").val();
		param.menuType = $("#menuType").val();
		param.menuPath = $("#menuPath").val();
		param.descContent = $("#menuDesc").val();
		
		//2,把用户输入的值通过ajax发送给服务端	
		
		$.ajax({
			url:url,
			data:param,
			dataType:"json",
			type:"post",
			success:function(responseData){
				if(responseData.isSuccess==true){
					//1.成功提示
					//2.刷新页面
					 var alerts= layer.alert(desc+"菜单成功",1,function(){
		  				   layer.close(alerts);
		  				   location.reload();//刷新页面  加载js文件，  ajax 查询组织列表
		  			   });	  
				}
				
			}
		});
		
		
		
	}
	
	/**
	 * 修改  和 查看
	 */
	
	this.updateOrQuery = function(menuId,oper){
		
		//ajax 发送请求  把当前这条记录查出来
		var param = new Object();
		param.menuId = menuId;
		$.ajax({
			url:path+"/menu/queryMenuById",
			data:param,
			dataType:"json",
			success:function(responseData){
				//alert(responseData.menuName+"---"+responseData.menuParentName);\
				//弹框
				var  htmlStr = menuOper.initHtml();
				
				
				  $.layer({
						type : 1,
						title : false,
						area : [ 'auto', 'auto' ],
						page : {
							html : htmlStr
						}
					  });
				  if(oper=="query"){
						//隐藏保存按钮
						alert($("#saveMenuTr"));
						$("#saveMenuTr").hide();
					}
				  
				//把ajax请求回来的数据 赋值给 弹框 中对应的输入框
				   $("#menuParentId").val(responseData.menuParentId);
				   $("#menuParentName").val(responseData.menuParentName);
				   $("#menuName").val(responseData.menuName);
				   $("#isPublish").val(responseData.isPublish);
				   $("#menuType").val(responseData.menuType);
				   $("#menuPath").val(responseData.menuPath);
				   $("#menuDesc").val(responseData.descContent);
				   $("#menuId").val(responseData.menuId);
				   
				   
				   
				  
			}
		});
		
		
	}
	
	
	/**
	 * 删除
	 */
	this.deleteMenu = function(menuId){
		if(menuId==1||menuId==2){
			 var alerts= layer.alert("该目录不能删除",2,function(){
				   layer.close(alerts);
			   });	
		}else{
		
		$.ajax({
			url:path+"/menu/deleteMenu",
			data:{menuId:menuId},
			dataType:"json",
			success:function(responseData){
				if (responseData.isSuccess) {
					 var alerts= layer.alert("删除菜单成功",1,function(){
		  				   layer.close(alerts);
		  				   location.reload();//刷新页面  加载js文件，  ajax 查询组织列表
		  			   });	
				}else{
					 var alerts= layer.alert("删除菜单失败",5,function(){
		  				   layer.close(alerts);
		  				  // location.reload();//刷新页面  加载js文件，  ajax 查询组织列表
		  			   });
				}
				 
			}
		});
	}
		}
	
	
	this.initHtml=function(){
		var htmlStr = '';
		htmlStr += '<div style="width:600px;" >';
		htmlStr += '<div style="display:block; padding-bottom:20px;" align="center"  >';		
		htmlStr += '<table border="0" >';		
		htmlStr += '<tr style="display:none">';
		htmlStr += '<td colspan="2">';
		htmlStr += '<span>菜单ID:</span>';
		htmlStr += '<input type="text" id="menuId" style="width:220px;">';
		htmlStr += '</td>';
		htmlStr += '</tr>';
		htmlStr += '<tr>';
		htmlStr += '<td>';
		htmlStr += '<span>父目录:</span>';
		htmlStr += '<input type="hidden"  id="menuParentId" style="width:120px;">';
		htmlStr += '<input type="text"  id="menuParentName"  readonly="readonly" style="width:120px;">';
		htmlStr += '<input type="button"  value="父目录" onclick="AddMenuTreeLayer();" style="width:60px;">';		
		htmlStr += '</td>';
		htmlStr += '<td>';
		htmlStr += '<span>菜单名称:</span>';
		htmlStr += '<input type="text"  id="menuName" style="width:220px;">';
		htmlStr += '</td>';
		htmlStr += '</tr>';
		htmlStr += '<tr>';
		
		htmlStr += '<td>';
		htmlStr += '<span>菜单状态:</span>';
		htmlStr += '<select id="isPublish" style="width:220px;">';
		htmlStr += '<option value="">请选择</option>';
		htmlStr += '<option value="1">可用</option>';
		htmlStr += '<option value="2">不可用</option>';
		htmlStr += '</select>';
		htmlStr += '</td>';
		
		htmlStr += '<td>';
		htmlStr += '<span>菜单类型:</span>';
		htmlStr += '<select id="menuType" style="width:220px;">';
		htmlStr += '<option value="">请选择</option>';
		htmlStr += '<option value="1">目录</option>';
		htmlStr += '<option value="2">菜单</option>';
		htmlStr += '</select>';
		htmlStr += '</td>';

		htmlStr += '</tr>';
		htmlStr += '<tr>';
		
		htmlStr += '<td>';
		htmlStr += '<span>菜单地址:</span>';
		htmlStr += '<input type="text"  id="menuPath" style="width:220px;">';
		htmlStr += '</td>';
		htmlStr += '</tr>';
		htmlStr += '<tr>';
		htmlStr += '<td colspan="2" align="center" >';	 
		htmlStr += '<span>菜单描述:</span>';
		htmlStr += '<textarea id="menuDesc" rows="3"   style="width:500px;"></textarea>';
		htmlStr += '</td>';
		htmlStr += '</tr>';
		htmlStr += '<tr id="saveMenuTr"><td colspan="2" align="center">';
		htmlStr += '<input id="saveButton"  type="button"  onclick="javascript:menuOper.saveOrUpdateMenu();" value="保存"  />';		
		htmlStr += '</td></tr>';
		htmlStr += '</table>';
		htmlStr += '</div>';
		htmlStr += '</div>';
		
		return htmlStr;
	}
	
	
}