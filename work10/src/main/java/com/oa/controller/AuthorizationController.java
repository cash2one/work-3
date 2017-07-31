package com.oa.controller;


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
import com.mchange.v2.c3p0.stmt.GooGooStatementCache;
import com.oa.entity.Role;
import com.oa.entity.RoleMenuRel;
import com.oa.service.MenuService;
import com.oa.service.OrgService;
import com.oa.service.RoleMenuRelService;
import com.oa.service.RoleOrgRelService;
import com.oa.service.RoleService;
import com.oa.service.UserService;

@Controller
@RequestMapping("/authorization")
public class AuthorizationController extends BaseController {
	
	@Resource
	private RoleService roleService;
	
	//注入组织
	@Resource
	private OrgService orgService;
	
	//角色组织
	@Resource
	private RoleOrgRelService roleOrgRelService;
	
	//注入userService
	@Resource
	private  UserService userService;
	
	//注入MenuService
	@Resource
	private MenuService menuService;
	//注入RoleMenuService
	@Resource
	private RoleMenuRelService roleMenuRelService;
	
	
	
	@RequestMapping("/authorizationMana")
	public String authorizationMana(){
		return "authorization/authorizationMana";
	}
	
	/**
	 * 查询所有角色
	 * @param response
	 */
	@RequestMapping("/roleList")
	public   void roleList( HttpServletResponse response){
		List<Role> list = roleService.selectAllRoleList();
		
		super.printJSON(response, new Gson().toJson(list));
	}
	
	
	/**
	 * 查询角色下的组织信息
	 */
	@RequestMapping("/orgList")
	public ModelAndView orgList(HttpServletRequest request,HttpServletResponse response){
		//组织信息
		Map<String,Object> paramMap = super.getParam(request);
		Map<String,Object> map =  orgService.selectRoleOrgList(paramMap);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("orgList", map.get("orgList"));
		modelAndView.addObject("total", map.get("total"));
		modelAndView.setViewName("authorization/orgList");//orgList.ftl
		
		return modelAndView;
	}
	
	/**
	 * 组织列表  页码部门
	 */
	@RequestMapping("/orgPageNumber")
	public ModelAndView orgPageNumber(HttpServletRequest request){
		int total = Integer.parseInt(request.getParameter("total"));
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));
		int startIndex = Integer.parseInt(request.getParameter("startIndex"));
		ModelAndView result = new ModelAndView();
		result = super.getPageNumberInfo(total, startIndex, pageSize, result);
		 result.setViewName("authorization/orgPageNumber");
		return result;
	}
	
	/**
	 * 删除角色 组织关联关系信息
	 */
	@RequestMapping("/deleteOrgRel")
	public void deleteOrgRel(int orgId,HttpServletResponse res){
		int i = roleOrgRelService.deleteRoleOrgRel(orgId);
		Map<String,Object> resMap = new HashMap<String,Object>();
		if (i>0) {
			resMap.put("isSuccess", true);
		}else{
			resMap.put("isSuccess", false);

		}
		
		super.printJSON(res, new Gson().toJson(resMap));
	}
	
	/**
	 * 用户 角色列表
	 */
	@RequestMapping("/userList")
	public ModelAndView userList(HttpServletRequest request){
		Map<String, Object> paramMap = super.getParam(request);
		//查询 Sys_user sys_role_user_rel
		Map<String,Object> resMap = userService.selectRoleUserList(paramMap);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("authorization/userList");//userList.ftl
		modelAndView.addObject("userList", resMap.get("userList"));
		modelAndView.addObject("total", resMap.get("total"));

		return modelAndView;
	}
	
	/**
	 * 用户 角色 列表 的页码部分
	 */
	@RequestMapping("/userPageNumber")
	public ModelAndView userPageNumber(HttpServletRequest request){
		int total = Integer.parseInt(request.getParameter("total"));
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));
		int startIndex = Integer.parseInt(request.getParameter("startIndex"));
		ModelAndView result = new ModelAndView();
		result =  super.getPageNumberInfo(total, startIndex, pageSize, result);
		result.setViewName("authorization/userPageNumber");
		return result;
	}
	
	/**
	 * 菜单 角色列表
	 */
	@RequestMapping("/menuList")
	public ModelAndView menuList(HttpServletRequest request){
		Map<String, Object> paramMap = getParam(request);
		Map<String, Object> resMap = menuService .selectRoleMenuList(paramMap);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("authorization/menuList");
		modelAndView.addObject("menuList",resMap.get("menuList"));
		modelAndView.addObject("total",resMap.get("total"));
		
		return modelAndView;
	}
	
	/**
	 * 菜单 角色 列表 的页码部分
	 */
	@RequestMapping("/menuPageNumber")
	public ModelAndView menuPageNumber(HttpServletRequest request){
		int total = Integer.parseInt(request.getParameter("total"));
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));
		int startIndex = Integer.parseInt(request.getParameter("startIndex"));
		ModelAndView result = new ModelAndView();
		result =  super.getPageNumberInfo(total, startIndex, pageSize, result);
		result.setViewName("authorization/menuPageNumber");
		return result;
	}
	
	
	/**
	 * 给角色授权  
	 * 给角色添加访问某个菜单的权限
	 */
  @RequestMapping("/addRoleMenuRel")
  public  void addRoleMenuRel(RoleMenuRel roleMenuRel,HttpServletResponse response){
	System.out.println("roleMenuRel----------------"+roleMenuRel);	
	  Map<String, Object> resMap  = new HashMap<String,Object>();
	  //新增角色菜单
	  int i = roleMenuRelService.insert(roleMenuRel);
	  if (i>0) {
		resMap.put("isSuccess", true);
	  }else{
		resMap.put("isSuccess", false);

	  }
	  
	  printJSON(response, new Gson().toJson(resMap));
	  
  }
  
	
	
	

}
