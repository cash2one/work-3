/**
 * 增删改查用到的js
 * js中
 * var obj = new Object();
 * obj.name = '张三';
 * 
 * var orgOper = new orgOper();
 * orgOper.addOrg();
 * 
*/

function  orgOper(){
	
	this.addOrg = function(){
		//alert('addOrg');
		//layer插件initHtml中的html代码弹出来
		//初始化弹出框html代码
		var htmlStr = orgOper.initHtml();
		
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
	 * 修改弹框
	 * 先请求数据 再赋值
	 */
	this.updateAndQueryOrg = function(orgId,oper){
		//1ajax 请求服务端 获取当前组织及当前组织的父组织等信息
		//2.把ajax返回的对象信息 赋值给layer弹框的输入框
      var htmlStr = orgOper.initHtml();
      
     
      
		//ajax 查询当前要修改的这条数据的值
		//$.ajax({});
		var param = new Object();
		param.orgId = orgId;
		alert(path);
		$.getJSON(path+"/org/queryOrg",param,function(responseData){
			
			  $.layer({
					type : 1,
					title : false,
					area : [ 'auto', 'auto' ],
					page : {
						html : htmlStr
					}
				  });	
			  if (oper=="query") {
					$("#saveOrgTr").hide();
				 }
			//把该ajax请求回来的对象  赋值给 layer插件弹出来的  页面输入框
			  $("#orgParentId").val(responseData.orgParentId);
			  $("#orgParentOrgPath").val(responseData.orgParentPath);
			  $("#orgName").val(responseData.orgName);
				//alert($("#state1").val());
			  $("#state1").val(responseData.state);
			  $("#dispIndex").val(responseData.dispIndex);
			  $("#orgDesc").val(responseData.orgDesc);
			  //当前节点的父组织名称
			  $("#orgParentName").val(responseData.orgParentName);
			  //当前组织的orgId
			  $("#orgId").val(responseData.orgId);
			
		});
		
	}
	
	
	/**
	 * 新增和修改公用一个
	 * orgId：有值  修改
	 * 		  无值  新增
	 */
	this.saveOrg = function(){
		
		var orgId = $("#orgId").val();
		var desc = "新增";
		var url=path+"/org/addOrg";
		//创建一个对象
		var param = new Object();
		
		if(orgId){
			desc = "修改";
			url = path+"/org/updateOrg";
			param.orgId = orgId;
		}
		
		//封装用户输入的值  封装到Object对象
		param.orgParentId = $("#orgParentId").val();
		param.orgParentOrgPath = $("#orgParentOrgPath").val();
		param.orgName = $("#orgName").val();
		//alert($("#state1").val());
		param.state = $("#state1").val();
		
		param.dispIndex = $("#dispIndex").val();
		param.orgDesc = $("#orgDesc").val();
		
		//{orgId:1,orgName:}
		$.ajax({
			url:url,
			data:param,
			dataType:"json",
			type:"POST",
			success:function(responseData){
				//alert(responseData);
				if(responseData.isSuccess){
					//新增成
					 var alerts= layer.alert(desc+"组织成功",1,function(){
		  				   layer.close(alerts);
		  				   location.reload();//刷新页面  加载js文件，  ajax 查询组织列表
		  			   });	  

				}else{
					 var alerts= layer.alert(desc+"组织失败",5,function(){
		  				   layer.close(alerts);
		  				   location.reload();//刷新页面  加载js文件，  ajax 查询组织列表
		  			   });
				}
			}
			
		});	
	}
	
	
	this.delete = function(orgId){
		//ajax  getJSON
		var param = new Object();
		param.orgId = orgId;
		$.get(path+"/org/delete",param,function(responseData){
			 if(true==responseData.isSuccess||responseData.isSuccess=="true"){
				 var alerts= layer.alert("删除组织成功",1,function(){
	  				   layer.close(alerts);
	  				   location.reload();//刷新页面   js重新加载  ajax
	  			   });	  
			 }else{
				 var alerts= layer.alert("删除组织失败",5,function(){
	  				   layer.close(alerts);
	  			   });
			 }   		
		});
	}
	
	
	this.initHtml = function(){		 
			var htmlStr = '';
			htmlStr += '<div style="width:600px;" >';
			htmlStr += '<div style="display:block; padding-bottom:20px;" align="center"  >';		
			htmlStr += '<table border="0" >';		
			htmlStr += '<tr style="display:none">';
			htmlStr += '<td colspan="2">';
			htmlStr += '<span>组织ID:</span>';
			htmlStr += '<input type="text" id="orgId" style="width:220px;">';
			htmlStr += '</td>';
			htmlStr += '</tr>';
			htmlStr += '<tr>';
			htmlStr += '<td>';
			htmlStr += '<span>父组织:</span>';
			htmlStr += '<input type="hidden"  id="orgParentId" style="width:120px;">';
			htmlStr += '<input type="hidden"  id="orgParentOrgPath" style="width:120px;">';
			htmlStr += '<input type="text"  id="orgParentName"  readonly="readonly" style="width:120px;">';
			htmlStr += '<input type="button"  value="父组织" onclick="AddOrgTreeLayer();" style="width:60px;">';		
			htmlStr += '</td>';
			htmlStr += '<td>';
			htmlStr += '<span>组织名称:</span>';
			htmlStr += '<input type="text"  id="orgName" style="width:220px;">';
			htmlStr += '</td>';
			htmlStr += '</tr>';
			htmlStr += '<tr>';
			
			htmlStr += '<td>';
			htmlStr += '<span>组织状态:</span>';
			htmlStr += '<select id="state1" style="width:220px;">';
			htmlStr += '<option value="">请选择</option>';
			htmlStr += '<option value="1">可用</option>';
			htmlStr += '<option value="2">不可用</option>';
			htmlStr += '</select>';
			htmlStr += '</td>';
			
			htmlStr += '<td>';
			htmlStr += '<span>显示顺序:</span>';
			htmlStr += '<input type="text"  id="dispIndex" style="width:220px;">';
			htmlStr += '</td>';

			htmlStr += '</tr>';
			htmlStr += '<tr>';
			htmlStr += '<td colspan="2" align="center" >';	 
			htmlStr += '<span>组织描述:</span>';
			htmlStr += '<textarea id="orgDesc" rows="3"   style="width:500px;"></textarea>';
			htmlStr += '</td>';
			htmlStr += '</tr>';
			htmlStr += '<tr id="saveOrgTr"><td colspan="2" align="center">';
			htmlStr += '<input id="saveButton"  type="button"  onclick="javascript:orgOper.saveOrg();" value="保存"  />';		
			htmlStr += '</td></tr>';
			htmlStr += '</table>';
			htmlStr += '</div>';
			htmlStr += '</div>';
			
			return htmlStr;
		}
	
}