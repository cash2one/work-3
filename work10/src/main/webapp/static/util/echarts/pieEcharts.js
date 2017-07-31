/*生成一个饼图
 * 1），初始化echarts  echarts.init(dom对象);
 * 2），定义option 
 * 3），设置option
 */

$(function(){
	

	var  myEcharts = echarts.init(document.getElementById("myPieDiv"));

	var option =
	{
		    title : {
		        text: '千锋员工统计',
		        subtext: '真实数据',
		        x:'center'
		    },
		    tooltip : {
		        trigger: 'item',
		        formatter: "{a} <br/>{b} : {c} ({d}%)"
		    },
		    legend: {
		        orient: 'vertical',
		        left: 'left',
		        data: []
		    },
		    series : [
		        {
		            name: '员工户籍',
		            type: 'pie',
		            radius : '55%',
		            center: ['50%', '60%'],
		            data:[
		            ],
		            itemStyle: {
		                emphasis: {
		                    shadowBlur: 10,
		                    shadowOffsetX: 0,
		                    shadowColor: 'rgba(0, 0, 0, 0.5)'
		                }
		            }
		        }
		    ]
		};

	  $.getJSON(path+"/echarts/pieEcharts",{},function(responseData){
		//  alert(responseData.isSuccess);
		  //把ajax响应的内容赋值给echarts的option
		  option.legend.data = responseData.legendData;//map的键
		  option.series[0].data = responseData.series0Data;
		  
		  //设置option
		  myEcharts.setOption(option);
		  
	  });
	
});




 