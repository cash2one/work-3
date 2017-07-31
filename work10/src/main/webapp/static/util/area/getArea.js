/*
*显示三级联动
1）加载省份信息
2）加载市级信息
3)加载县区信息
*/
var  getArea = new getArea();

function Cascade(oprovinceId,ocityId,ocountryId){
	
	getArea.getProvinces(1,oprovinceId,ocityId,ocountryId);	
	
	/**
	 * 下拉列表中的值发生改变时执行的函数
	 * qry_provinceId
	 */
	 $("#"+oprovinceId).change(function(){
		 //#qry_cityId option
		 $("#"+ocityId+" option").remove();
		 getArea.getCity(null,oprovinceId,ocityId,ocountryId);
		 
	 });
	 
	 $("#"+ocityId+"").change(function(){
		 $("#"+ocountryId+" option").remove();
		 getArea.getCountry(ocountryId,ocityId);
	 });
	
}

function  getArea(){
  	this.getProvinces = function(parentId,oprovinceId,ocityId,ocountryId){
  		//得到省份信息的对象  select
  		var pObj = $("#"+oprovinceId);
  		//alert(pObj);
  		$.getJSON(path+"/getAreaList",{parentId:parentId},function(responseData){
  			//responseData:[{},{},{}]
  			//追加option
  			//怎么遍历json数据
  			$.each(responseData,function(index,area){
  				//alert(index+"------"+area.areaId+"-----"+area.areaName);
  				//在下拉列表的后面追加option
  				pObj.append("<option value='"+area.areaId+"'>"+area.areaName+"</option>");
  			});
  			getArea.getCity(null,oprovinceId,ocityId,ocountryId);
  		});
  		
  	}
  	
  	this.getCity = function(parentId,oprovinceId,ocityId,ocountryId){
  		$("#"+ocityId+" option").remove();
  		//得到是市区的下拉列表
  		var cObj = $("#"+ocityId);
  		// cObj.remove();
  		//得到省份的id
  		 var provinceId = $("#"+oprovinceId).val();//得到用户选择的省份id
  		 $.getJSON(path+"/getAreaList",{parentId:provinceId},function(responseData){
  			//responseData:[{},{},{}]
  			//追加option
  			//怎么遍历json数据
  			$.each(responseData,function(index,area){
  				//alert(index+"------"+area.areaId+"-----"+area.areaName);
  				//在下拉列表的后面追加option
  				cObj.append("<option value='"+area.areaId+"'>"+area.areaName+"</option>");
  				
  			});
  			
  			getArea.getCountry(ocountryId,ocityId);
  			
  		});
  		
  	}
  	
  	this.getCountry = function(ocountryId,ocityId){
  		 $("#"+ocountryId + " option ").remove();
  		var countryObj = $("#"+ocountryId);
  		//得到地市的id
  		var cityId = $("#"+ocityId).val();
  		 $.getJSON(path+"/getAreaList",{parentId:cityId},function(responseData){
   			//responseData:[{},{},{}]
   			//追加option
   			//怎么遍历json数据
   			$.each(responseData,function(index,area){
   				//alert(index+"------"+area.areaId+"-----"+area.areaName);
   				//在下拉列表的后面追加option
   				countryObj.append("<option value='"+area.areaId+"'>"+area.areaName+"</option>");
   				
   			});
   		});
  		
  	}

}

