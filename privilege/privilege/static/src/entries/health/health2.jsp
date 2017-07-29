<%@ page language="java" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>预约体检</title>
	<link rel="stylesheet" type="text/css" href="birthDate.css">
	<script src="jquery-2.2.3.min.js"></script>
	<style>
      .formMain{
      	width: 80%;
      	margin: 0 auto;
      	border: 1px solid #ccc;
      }
      .formItem{
      	list-style: none;
      	margin: 15px;
      }
	</style>
</head>
<body>
	<div class="header">
		信美相互人寿
	</div>
    <div class="container">
    <h3>体检预约</h3>
    	<form action="">
    	<ul class="formMain">
    		<li class="formItem">
    			<label for="">体检机构：</label>
				<select name="tijianJG">
					<option value="news">时事新闻</option>
			        <option value="sports">体育新闻</option>
			        <option value="international">国际新闻</option>
	            </select>
    		</li>
    		<li class="formItem">
    			<label for="">体检项目：</label>
				<select name="tijianXM">
					<option value="news">时事新闻</option>
			        <option value="sports">体育新闻</option>
			        <option value="international">国际新闻</option>
	            </select>
    		</li>
    		<li class="formItem">
    		     <label for="">预约时间：</label>
    			 <input class="Wdate" name="yuyueDate" type="text" id="d17" />
    		</li>
    		<li class="formItem">
    			<label for="">消耗积分：</label><span>345</span>         
    		</li>
    		<li class="formItem">
    			<input type="submit" value="Submit" id="g" />
    		</li>
    	</ul>
    	</form>
    </div>
    
    <script type="text/javascript" src="birthDate.js"></script>
     <script>
     var currYear = new Date().getFullYear();
     var opt = {  
        theme: 'android-holo-light',    
            lang: 'zh', 
          display: 'center', 
            mode: $('#mode').val(),       
            defaultValue: new Date(new Date().setFullYear(currYear - 16)),//日历点击默认弹出日期 
            setText: '确定', //确认按钮名称
            cancelText: "取消",  
            dateFormat: 'yy-mm-dd', //返回结果格式化为年月格式  
            dateOrder: 'yymmdd', //面板中日期排列格式
            startYear:'1989',
            endYear:2036, //结束年份
            headerText: function (valueText) { //自定义弹出框头部格式  
                array = valueText.split('-'); 
                return currYear-16 +"年" + array[1] + "月"+array[2] + "日";  
            }
          };
      $('#d17').mobiscroll().date(opt); 

      

     	$('form').submit(function() {
     		var a = $(this).serialize()
     		// console.log(a)
    	    alert(a);
    	    $.ajax({
    	    	url:'',
    	    	type:'GET',
    	    	dataType:'json',
    	    	data:a,
    	    	success:function(data){
    	    	},
    	    	error:function(data){
    	    	}

    	    })
    	    
    	});
 </script>
</body>
</html>