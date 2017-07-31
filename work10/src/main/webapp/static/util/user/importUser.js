
/**
 * 导入
 * @returns
 */
function importUser(){
	//弹框
	var htmlStr = "";
	htmlStr += '<div style="width:600px;" >';	
	htmlStr += '<div style="display:block; padding-bottom:20px;" align="center" >';
	htmlStr += '<form  id = "userExcelFileFormId" action="'+path +'/importUsers" enctype="multipart/form-data" method="post" >';
	htmlStr += '<input type="file" id="userExcelFile" name="userExcelFile"  onchange="ValidateFileType()" >';
	htmlStr += ' <input type="submit"  value="导入"  />  ';
	htmlStr += '</form>';
	htmlStr += '</table>';
	htmlStr += '</div>';
	htmlStr += '</div>';
	$.layer({
		type : 1,
		title : '导入用户',
		area : [ 'auto', 'auto' ],
		page : {
			html : htmlStr
		}
	});
	//ajax 提交表单  jquery.form:异步提交的form表单
	SubmitFuc();

	
}


function SubmitFuc(){
	/**
	 * beforeSubmit: showRequest,  //提交前的回调函数  
     success: showResponse,      //提交后的回调函数  
   //url: url,                 //默认是form的action， 如果申明，则会覆盖  
   //type: type,               //默认是form的method（get or post），如果申明
	 */
	var options={
		beforeSubmit	:showRequest,
		success:showResponse,
		dataType : 'json'
	};
	
  $('#userExcelFileFormId').submit(function() {//提交表单的回调函数
		
		var file = document.getElementById("userExcelFile");
		if(!file.value){
			alert("请选Excel文档！");
			return false;//组织默认行为
		}
		
	$(this).ajaxSubmit(options);//异步提交
		return false;//阻止默认行为
	});

	
}

function showResponse(responseData){
	if(responseData.isSuccess){
		alert('提交成功');
		window.location =path+"/user/userMana";

	}
}

function showRequest(){}

function ValidateFileType(){
	//得到上传的文件名称
	var fileName = $("#userExcelFile").val();
	var index = fileName.lastIndexOf(".");
	var suffix = fileName.substring(index+1);
	if("xls"!=suffix&&"xlsx"!=suffix){//xls
		alert("请选择excel文件");
		$("#userExcelFile").val('');
	}	
}