
/**
 * 点击选择时 把菜单的父目录查询出来 以树形结构展示
 * @returns
 */
function queryMenuTree(){
	//1,layer 弹框
	//弹出的内容
	  var htmlStr = "";
	    htmlStr += '<div style="width:300px;height:200px" >';
		htmlStr += '<div style="display:block; padding-bottom:5px;" align="center"  >';
		htmlStr += '<div><table><tr><td><input type="hidden" id="tmp_menuParentId" width="50px"/><input type="text" id="tmp_menuParentName" width="50px"><button onclick="SetQueryMenuTreeParam();">确定</button></td></tr></table></div>';
		htmlStr += '<div id="QueryMenuTree" class="ztree"></div>';//QueryMenuTree 是树形结构的挂载点
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
	
	
	
	//2,zTree布局树形结构
	QueryMenuTreeObj();//树形结构   //zTree
	
}
	
function  QueryMenuTreeObj(){
	
	//异步加载  zTree封装的异步加载
	 var setting = {   
		        data: {    
		            simpleData: {    
		                enable: true  //使用简单 Array格式的数据 
		            }    
		        },
		        async: {     //异步加载数据   ajax
		            enable: true, //开启异步加载处理   
		            url:path+"/tree/menuSubList",    
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
	     
	     var  zTreeObj  =  $.fn.zTree.init($("#QueryMenuTree"), setting, zNodes); 
	     
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
	$("#tmp_menuParentId").val(treeNode.id);
	$("#tmp_menuParentName").val(treeNode.name);
	
}


function SetQueryMenuTreeParam(){
	//把选择的树形机构的值 填充到 按照所属目录查询的条件查询的输入框中
	
	$("#qry_parentMenuName").val($("#tmp_menuParentName").val());
	$("#qry_parentMenuId").val($("#tmp_menuParentId").val());
	
//	var index = layer.index;
//	
//	layer.close(index);
	
	layer.closeAll();
	
}



	
	
	


