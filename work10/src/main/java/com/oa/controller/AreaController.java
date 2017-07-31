package com.oa.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.oa.service.AreaService;

@Controller
public class AreaController extends BaseController {
	
	@Resource
	private AreaService  areaService;
	
	@RequestMapping("/getAreaList")
	public void getAreaList(String parentId,HttpServletResponse response){
		List<Map<String, Object>> list =  areaService.selectAreaByParentId(parentId);
		System.out.println(list);
	  super.printJSON(response, new Gson().toJson(list));
	}
	
	

}
