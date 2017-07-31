package com.oa.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.oa.entity.Menu;
import com.oa.entity.RoleMenuRel;
import com.oa.service.MenuService;
import com.oa.service.RoleMenuRelService;

@Controller
@RequestMapping("/menu")
public class MenuController extends BaseController {
	
	@Resource
	private MenuService menuService;
	@Resource
	private RoleMenuRelService roleMenuRelService;
	
	
	@RequestMapping("/menuMana")
	public String menuMane(){
		return "/menu/menuMana";//menu/menuMana.ftl
	}
	
	/**
	 * 加载菜单数据
	 */
	@RequestMapping("/menuList")
	public ModelAndView  menuList(HttpServletRequest request){
		Map<String, Object> paramMap = super.getParam(request);
		System.out.println("paramMap------>"+paramMap);
		
		Map<String, Object> resMap = menuService.selectMenuListByPage(paramMap);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("menusList", resMap.get("menuList"));
		modelAndView.addObject("total", resMap.get("total"));
		//request.setAttribute();
		modelAndView.setViewName("/menu/menuList");
		return modelAndView;
	}
	
	@RequestMapping("/menuPageNumber")
	public ModelAndView menuPageNumber(HttpServletRequest request){
		Map<String, Object> paramMap = super.getParam(request);
		System.out.println("paramMap------>"+paramMap);
		ModelAndView modelAndView = new ModelAndView();
		int  total = Integer.parseInt((paramMap.get("total")+""));
		int  startIndex = Integer.parseInt((paramMap.get("startIndex")+""));
		int  pageSize = Integer.parseInt((paramMap.get("pageSize")+""));
		
		modelAndView =  super.getPageNumberInfo(total, startIndex, pageSize, modelAndView);
		modelAndView.setViewName("/menu/menuPageNumber");

		return modelAndView;
	}
	
	
	//新增
	@RequestMapping("/addMenu")
	public void addMenu(Menu menu,HttpServletResponse response){
		
		System.out.println("addMenu-------------"+menu);
		//如果是get请求  处理乱码
//		try {
//			menu.setMenuName(new String(menu.getMenuName().getBytes("iso-8859-1"),"utf-8"));
//			menu.setDescContent(new String(menu.getDescContent().getBytes("iso-8859-1"),"utf-8"));
//			System.out.println(menu);
//		} catch (UnsupportedEncodingException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		
		int i  = 0;
		
		try {
			i= menuService.insert(menu);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Map<String, Object> resMap = new HashMap<String, Object>();
		if (i>0) {
			resMap.put("isSuccess", true);
		}else{
			resMap.put("isSuccess", false);
		}
		
		super.printJSON(response, new Gson().toJson(resMap));
	}
	
	
	/**
	 * 根据id查看菜单
	 */
	@RequestMapping("/queryMenuById")
	public void queryMenuById(long menuId,HttpServletResponse response){
		
		Menu menu = menuService.selectMenuAndParenMenuByMenuId(menuId);
		
		super.printJSON(response, new Gson().toJson(menu));
	}
	
	/**
	 * 修改updateMen
	 */
	@RequestMapping("/updateMenu")
	public  void updateMenu(Menu menu,HttpServletResponse response){
		int i = menuService.updateByPrimaryKeySelective(menu);
		Map<String, Object> resMap = new HashMap<String, Object>();
		if (i>0) {
			resMap.put("isSuccess", true);
		}else{
			resMap.put("isSuccess", false);
		}
		
		super.printJSON(response, new Gson().toJson(resMap));
	}
	
	@RequestMapping("/deleteMenu")
	public void deleteMenu(long menuId,HttpServletResponse response){
		//1，当前菜单下有子菜单 不能删除  页面管控  后台可以不处理
		//2，该菜单下有角色 不能删除
		
		List<RoleMenuRel> list = roleMenuRelService.selectMenuRoleRelByMenuId(menuId);
		Map<String, Object> resMap = new HashMap<String, Object>();
		
		if (list!=null&&list.size()>0) {
			resMap.put("isSuccess", false);
		}else{
			int i = menuService.deleteByPrimaryKey(menuId);
			if (i>0) {
				resMap.put("isSuccess", true);
			}else{
				resMap.put("isSuccess", false);
			}
			
		}
		
		super.printJSON(response, new Gson().toJson(resMap));
		
	}
	
}
