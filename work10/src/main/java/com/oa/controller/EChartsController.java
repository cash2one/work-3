package com.oa.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpRange;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.oa.service.UserService;

@Controller
@RequestMapping("/echarts")
public class EChartsController extends BaseController {
	
	//注入userService
	@Resource
	private UserService userService;
	
	@RequestMapping("/toPieGraph")
	public  String toPieGraph(){//跳转到生成饼图的页面
		return "echarts/pieGraph";
	}
	
	

	@RequestMapping("/toBarGraph")
	public  String toBarGraph(){//跳转到生成饼图的页面
		return "echarts/barGraph";
	}
	
	
	@RequestMapping("/pieEcharts")
	public void pieEcharts( HttpServletResponse response){
		
		Map<String, Object>  map = null;
		
		try {
			 	map = userService.selectProvinceNameAndCount();
			 	map.put("isSuccess", true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
		 	map.put("isSuccess", false);
		}
		
		super.printJSON(response, new Gson().toJson(map));
		
	}
	
	
	@RequestMapping("/barEcharts")
	public void barEcharts( HttpServletResponse response){
		Map<String, Object> map = null;
		
		try {
			map = userService.selectProviceAndSex();
			
			map.put("isSuccess", true);
			
		} catch (Exception e) {
			map.put("isSuccess", false);
		}
		
		super.printJSON(response, new Gson().toJson(map));
		
	}
	
	
	
	

}
