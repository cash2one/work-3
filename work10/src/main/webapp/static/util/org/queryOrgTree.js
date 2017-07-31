/**
 * 点击选择时  弹出的框  展示所有组织
 */

function  queryOrgTree(){

	//弹出的内容
	  var htmlStr = "";
	    htmlStr += '<div style="width:300px;height:200px" >';
		htmlStr += '<div style="display:block; padding-bottom:5px;" align="center"  >';
		htmlStr += '<div><table><tr><td><input type="hidden" id="tmp_orgPath" width="50px"/><input type="text" id="tmp_orgName" width="50px"><button onclick="SetQueryOrgTreeParam();">确定</button></td></tr></table></div>';
		htmlStr += '<div id="QueryOrgTree" class="ztree"></div>';
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
	
	QueryOrgTreeObj();//树形结构   //zTree
}

/**
 * 加载树形结构
 * zTree
 * @returns
 */
function QueryOrgTreeObj(){
	
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
		            onClick:ClickQueryOrgTreeNodeFunc
		        }  
		    };    	     
	     var zNodes=[];  
	     
	     var  zTreeObj  =  $.fn.zTree.init($("#QueryOrgTree"), setting, zNodes); 
	     
}

function  ClickQueryOrgTreeNodeFunc(event, treeId, treeNode,clickFlag){
		//alert(treeNode+"---"+treeNode.id+"---"+treeNode.name);
	//执行ajax成功后的回调函数
	//把用户点击的这个节点的值  --赋值给 输入框
	$("#tmp_orgPath").val(treeNode.id);
	$("#tmp_orgName").val(treeNode.name);
	
}

//把弹出框中的值 赋值给页面用于条件查询
function SetQueryOrgTreeParam(){ 
	 $("#qry_orgId").val($("#tmp_orgPath").val());
	 $("#qry_orgName").val( $("#tmp_orgName").val());
	 layer.closeAll();
} 


