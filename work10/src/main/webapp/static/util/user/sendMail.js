
/**
 * mail 收件人
 * @param mail
 * @returns
 */
function sendMail(mail){
	//弹框
	  var htmlStr ="";	 
		htmlStr += '<div style="width:600px;" >';
		htmlStr += '<div style="display:block; padding-bottom:20px;" align="center"  >';		
		htmlStr += '<table border="0" >';		
		htmlStr += '<tr>';
		htmlStr += '<td>';
		htmlStr += '<span>收件人:</span>';
		htmlStr += '<input type="text"  id="sendMail" style="width:220px;">';
		htmlStr += '</td>';
		
		htmlStr += '</tr>';
		htmlStr += '<tr>';
		htmlStr += '<td>';
		htmlStr += '<span>标题:</span>';
		htmlStr += '<input type="text"  id="title" style="width:220px;">';
		htmlStr += '</td>';
		
		htmlStr += '</tr>';
		htmlStr += '<tr>';
		htmlStr += '<td align="center">';	 
		htmlStr += '<span>内容:</span>';
		htmlStr += '<textarea rows="3"  id="content" style="width:500px;"></textarea>';
		htmlStr += '</td>';
		htmlStr += '</tr>';
		htmlStr += '<tr><td colspan="2" align="center">';
		htmlStr += '<input id="saveButton"  type="button" onclick="sendMailTo()"   value="发送"  />';		
		htmlStr += '</td></tr>';
		htmlStr += '</table>';
		htmlStr += '</div>';
		htmlStr += '</div>';

	$.layer({
		type : 1,
		title : '发送邮件',
		area : [ 'auto', 'auto' ],
		page : {
			html : htmlStr
		}
	});
	$("#sendMail").val(mail);
	
}

	function sendMailTo(){
		var param = new Object();
		param.sendMail = $("#sendMail").val();
		param.title = $("#title").val();
		param.content = $("#content").val();
		
		$.ajax({
			url:path+"/sendMail",
			data:param,
			dataType:"json",
			type:"post",
			success:function(responseData){
				if (responseData.isSuccess) {
					var alerts= layer.alert("发送成功",1,function(){
						 layer.closeAll();
		  			   });
					
				}
			}
		});
		
		
		
	}