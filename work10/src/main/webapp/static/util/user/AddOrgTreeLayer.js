/*
 * 新增的查询父组织
 */

/**
 * 点击选择时  弹出的框  展示所有组织
 */

function  AddOrgTreeLayer(){

	//弹出的内容
	  var htmlStr = "";
	    htmlStr += '<div style="width:300px;height:200px" >';
		htmlStr += '<div style="display:block; padding-bottom:5px;" align="center"  >';
		htmlStr += '<div><table><tr><td><input type="hidden" id="tmp_orgParentPath" width="50px"/><input type="hidden" id="tmp_orgParentId" width="50px"/><input type="text" id="tmp_orgParentName" width="50px"><button onclick="SetAddOrgTreeParam();">确定</button></td></tr></table></div>';
		htmlStr += '<div id="AddOrgTree" class="ztree"></div>';
		htmlStr += '</div>';
		htmlStr += '</div>';
		
	$.layer({
			type : 1,
			title : false,
			area : [ 'auto', 'auto' ],
			border : [ 0 ], 
			closeBtn : [ 0, true ], 
			shift : 'center', 
			page : {
				html : htmlStr
			}
		  });
	
	addOrgTreeObj();//树形结构   //zTree
}

/**
 * 加载树形结构
 * zTree
 * @returns
 */
function addOrgTreeObj(){
	
	 var setting = {   
		        data: {    
		            simpleData: {    
		                enable: true  //使用简单 Array格式的数据 
		            }    
		        },
		        async: {     //异步加载数据   ajax
		            enable: true, //开启异步加载处理   
		            url:path+"/tree/orgSubList",    
		            autoParam:["id", "name"], //异步加载时需要自动提交父节点属性的参    当前节点的节点id  节点名称   
		            //otherParam:{"otherParam":"zTreeAsyncTest"},//向后台传入其他参数
		            dataType: "json",//默认text  
		            type:"get"//默认post  
		        }  
		        ,callback:{  //回调函数   success:function()
		            onClick:ClickAddOrgTreeNodeFunc
		        }  
		    };    	     
	     var zNodes=[];  
	     
	     var  zTreeObj  =  $.fn.zTree.init($("#AddOrgTree"), setting, zNodes); 
	     
}

function  ClickAddOrgTreeNodeFunc(event, treeId, treeNode,clickFlag){
		//alert(treeNode+"---"+treeNode.id+"---"+treeNode.name);
	//执行ajax成功后的回调函数
	//把用户点击的这个节点的值  --赋值给 输入框
	//alert(treeNode.orgPath);
	$("#tmp_orgParentPath").val(treeNode.orgPath);
	$("#tmp_orgParentId").val(treeNode.id);
	$("#tmp_orgParentName").val(treeNode.name);
	
}

//把弹出框中的值 赋值给页面用于条件查询
function SetAddOrgTreeParam(){ 
	alert($("#tmp_orgParentName").val());
	$("#orgName").val($("#tmp_orgParentName").val());
	$("#orgId").val($("#tmp_orgParentId").val());
	 //关闭当前层
	 var index = layer.index; //获取当前弹层的索引号
	 layer.close(index); //关闭当前弹层
	 
} 


