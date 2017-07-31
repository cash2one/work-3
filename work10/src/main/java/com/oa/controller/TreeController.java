package com.oa.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.oa.entity.Menu;
import com.oa.entity.Org;
import com.oa.service.MenuService;
import com.oa.service.OrgService;

@Controller
@RequestMapping("/tree")
public class TreeController extends BaseController{
	
	@Resource
	private OrgService orgService;
	
	//注入menuService
	@Resource
	private MenuService menuService;
	
	
	@RequestMapping("/orgSubList")
	public void  subList(HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> map = getParam(request);
		System.out.println("id:"+map.get("id")+"  name:"+map.get("name"));
		String id = (String) map.get("id");
		if (id==null||"".equals(id)) {
			id = "-1";
		}
		int parentId = Integer.parseInt(id);
		List<Org> list =  orgService.selectOrgListByParentId(parentId);
		List<Map<String , Object>> resList = new ArrayList<Map<String,Object>>();
		for (Org org : list) {
			Map<String, Object> map2 = new HashMap<String, Object>();
			map2.put("id", org.getOrgId());
			map2.put("name", org.getOrgName());
			map2.put("orgPath", org.getOrgPath());
			map2.put("isParent", true);//是否为父节点   zTree
			resList.add(map2);
		}
		System.out.println(resList);
		Gson gson = new Gson();
		String json =  gson.toJson(resList);
		super.printJSON(response, json);
		
		
	}
	/**
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("/menuSubList")
	public  void  menuSubList(HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> map = getParam(request);
		System.out.println("menuSubList:map--------------"+map);
		Object parentId = map.get("id");//这里的id 对应页面的 autoParam:["id", "name"],的id
		//null --->"null"
		if (parentId==null||"".equals(parentId)) {
			parentId=-1;
		}
		//System.out.println(null=="null");
		
		
		List<Menu> list = menuService.selectDirByParentId(Long.parseLong(parentId+""));
		List<Map<String, Object>> list2 = new ArrayList<Map<String,Object>>();
		for (Menu menu : list) {
			Map<String, Object> map2 = new HashMap<String,Object>();
			map2.put("id", menu.getMenuId());
			map2.put("name", menu.getMenuName());
			//是否是父目录
			map2.put("isParent", true);//
			list2.add(map2);
		}
		
		super.printJSON(response, new Gson().toJson(list2));
		
	}
	
	@RequestMapping("/menuSubList1")
	public  void  menuSubList1(HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> map = getParam(request);
		System.out.println("menuSubList:map--------------"+map);
		Object parentId = map.get("id");//这里的id 对应页面的 autoParam:["id", "name"],的id
		//null --->"null"
		if (parentId==null||"".equals(parentId)) {
			parentId=-1;
		}		
		List<Menu> list = menuService.selectMenuByParentId(Integer.parseInt(parentId+""));
		List<Map<String, Object>> list2 = new ArrayList<Map<String,Object>>();
		for (Menu menu : list) {
			Map<String, Object> map2 = new HashMap<String,Object>();
			map2.put("id", menu.getMenuId());
			map2.put("name", menu.getMenuName());
			//是否是父目录
			if (menu.getMenuType()==1) {
				map2.put("isParent", true);//  getMenuType==1 ;代表是目录，zTree的每个节点都有isParent属性  true 代表是目录
			}else{
				map2.put("isParent", false);//
			}
			
			list2.add(map2);
		}
		
		super.printJSON(response, new Gson().toJson(list2));
		
	}
	
	

}
