/**
 * 1,弹框  layer
 * 2，把所有菜单查询出来   zTree
 * 3,插入到数据库
 * @returns
 */
function menuTreeLayer(){
	
	var roleId = $("#sys_role").val();
	if(roleId==''){
		alert("请选择角色");
		return;
	}
	
	//弹出的内容
	  var htmlStr = "";
	    htmlStr += '<div style="width:300px;height:200px" >';
		htmlStr += '<div style="display:block; padding-bottom:5px;" align="center"  >';
		htmlStr += '<div><table><tr><td><input type="hidden" id="tmp_menuId" width="50px"/><input type="text" id="tmp_menuName" width="50px"><button onclick="saveMenu();">添加</button></td></tr></table></div>';
		htmlStr += '<div id="menuTree" class="ztree"></div>';//QueryMenuTree 是树形结构的挂载点
		htmlStr += '</div>';
		htmlStr += '</div>';
		
	$.layer({
			type : 1,
			title : false,
			area : [ 'auto', 'auto' ],
			border : [ 0 ], 
			closeBtn : [ 0, true ], 
			shift : 'center', 
			page : {   //要弹出的页面对应的html代码
				html : htmlStr
			}
		  });
	
	showMenu();
}

function  showMenu(){
	
	//异步加载  zTree封装的异步加载
	 var setting = {   
		        data: {    
		            simpleData: {    
		                enable: true  //使用简单 Array格式的数据 
		            }    
		        },
		        async: {     //异步加载数据   ajax
		            enable: true, //开启异步加载处理   
		            url:path+"/tree/menuSubList1",    
		            autoParam:["id", "name"], //异步加在时需要向服务端传递的数据  id  name， 第一次没有值   
		            //otherParam:{"otherParam":"zTreeAsyncTest"},//向后台传入其他参数
		            dataType: "json",//默认text  
		            type:"get"//默认post  
		        }  
		        ,callback:{  //回调函数   success:function(responsData)
		            onClick:ClickQueryMenuTreeNodeFunc
		        }  
		    };    	     
	     var zNodes=[];  
	     
	     var  zTreeObj  =  $.fn.zTree.init($("#menuTree"), setting, zNodes); 
	     
}

/**
 * @param event
 * @param treeId
 * @param treeNode  代表的是一个树形结构的节点
 * @param clickFlag
 * @returns
 */
function  ClickQueryMenuTreeNodeFunc(event, treeId, treeNode,clickFlag){
		//alert(treeNode+"---"+treeNode.id+"---"+treeNode.name+"----"+treeNode.isParent);
	//执行ajax成功后的回调函数
	//把用户点击的这个节点的值  --赋值给 输入框
	$("#tmp_menuId").val(treeNode.id);
	$("#tmp_menuName").val(treeNode.name);
	
}


//点击添加时，把该角色菜单插入到  sys_role_menu_rel表
function saveMenu(){
	var param = new Object();
	param.roleId = $("#sys_role").val();//角色编号
	param.menuId = $("#tmp_menuId").val();//菜单编号
	//把角色菜单关联关系插入到数据库的sys_role_menu_rel 表
	
	$.getJSON(path+"/authorization/addRoleMenuRel",param,function(responseData){
		if (responseData.isSuccess) {
			layer.alert("授权成功",1,function(){
				layer.closeAll();
				//加载授权菜单
				loadMenuReturnPageData(0);
			});
		}else{
			layer.alert("授权失败",5,function(){
				layer.closeAll();
			});
		}
	});
	
	
	
	
	
	
}

	
	
	


