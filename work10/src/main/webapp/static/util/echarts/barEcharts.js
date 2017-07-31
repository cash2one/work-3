
/**
 * 1)初始化echarts
 * 2)定义option  
 * 3)ajax请求数据
 * 4)把ajax响应内容赋值给option的相关属性
 * 5)设置option
 */
$(function(){
	
	var myBarEcharts = echarts.init(document.getElementById("myBarDiv"));

	var option = {
	    tooltip: {
	        trigger: 'axis',
	        axisPointer: {
	            type: 'cross',
	            crossStyle: {
	                color: '#999'
	            }
	        }
	    },
	    toolbox: {
	        feature: {
	            dataView: {show: true, readOnly: false},
	            magicType: {show: true, type: ['line', 'bar']},
	            restore: {show: true},
	            saveAsImage: {show: true}
	        }
	    },
	    legend: {
	        data:['男','女','保密']
	    },
	    xAxis: [
	        {
	            type: 'category',
	            data: [],
	            axisPointer: {
	                type: 'shadow'
	            }
	        }
	    ],
	    yAxis: [
	        {
	            type: 'value',
	            name: '人数',
	            min: 0,
	            max: 8,
	            interval: 1,
	            axisLabel: {
	                formatter: '{value} 人'
	            }
	        }
	    ],
	    series: [
	        {
	            name:'男',
	            type:'bar',
	            data:[]
	        },
	        {
	            name:'女',
	            type:'bar',
	            data:[]
	        },
	        {
	            name:'保密',
	            type:'bar',
	            data:[]
	        }
	    ]
	};

	//ajax
	$.getJSON(path+"/echarts/barEcharts",function(responseData){
		//赋值给option
		
		option.xAxis[0].data = responseData.legendData;//x坐标的内容
		option.series[0].data = responseData.boyData;
		option.series[1].data = responseData.girlData;
		option.series[2].data = responseData.securytData;
		
		//设置option
		myBarEcharts.setOption(option);
	});
	
});