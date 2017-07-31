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
import com.oa.entity.Role;
import com.oa.entity.RoleUserRel;
import com.oa.service.RoleService;
import com.oa.service.RoleUserRelService;

@Controller
@RequestMapping("/role")
public class RoleController extends BaseController {
	
	//注入service
	@Resource
	private RoleService roleService;
	@Resource
	private RoleUserRelService roleUserRelService;
	
	
	@RequestMapping("/roleMana")
	public String roleMana(){
		return "role/roleMana";
	}
	
	@RequestMapping("/roleList")
	public ModelAndView  roleList(HttpServletRequest request){
		Map<String, Object> paramMap = super.getParam(request);
		
		Map<String, Object> resMap = roleService.selectRoleListByPage(paramMap);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("rolesList", resMap.get("rolesList"));
		modelAndView.addObject("total",resMap.get("total"));
		modelAndView.setViewName("role/roleList");
		return modelAndView;
	}
	
	@RequestMapping("/rolePageNumber")
	public ModelAndView rolePageNumber(HttpServletRequest request){
		Map<String, Object>  paramMap = super.getParam(request);
		int pageSize = Integer.parseInt(paramMap.get("pageSize")+"");
		int startIndex = Integer.parseInt(paramMap.get("startIndex")+"");
		int total = Integer.parseInt(paramMap.get("total")+"");
		ModelAndView result = new ModelAndView();
		result = super.getPageNumberInfo(total, startIndex, pageSize, result);
		
		result.setViewName("role/rolePageNumber");
		
		return result;
	}
	
	
	//新增
	@RequestMapping("/addRole")
	public void addRole(Role role,HttpServletResponse response){
		System.out.println(role+"---------------------addRole");
		int i = roleService.insert(role);
		Map<String, Object>  map  =  new HashMap<String, Object>();
		if (i>0) {
			map.put("isSuccess", true);
		}else{
			map.put("isSuccess", false);
		}
		super.printJSON(response, new Gson().toJson(map));
	}
	
	//根据id查看角色
	@RequestMapping("/queryRole")
	public void queryRole(long roleId,HttpServletResponse response){
		Role role  = roleService.selectByPrimaryKey(roleId);
		super.printJSON(response, new Gson().toJson(role));
	}
	
	@RequestMapping("/updateRole")
	public void updateRole(Role role,HttpServletResponse response){
		System.out.println("updateRole------------------"+role);
		int i  = roleService.updateByPrimaryKeySelective(role);
		Map<String, Object>  map  =  new HashMap<String, Object>();
		if (i>0) {
			map.put("isSuccess", true);
		}else{
			map.put("isSuccess", false);
		}
		super.printJSON(response, new Gson().toJson(map));
	}
	
	@RequestMapping("/deleteRole")
	public void  deleteRole(long roleId,HttpServletResponse response){
		//根据角色查询用户  如果该角色下有用户  不能删除
		List<RoleUserRel> list = roleUserRelService.selectUserByRoleId(roleId);
		Map<String, Object>  map  =  new HashMap<String, Object>();
		if (list!=null&&list.size()>0) {
			map.put("isSuccess", false);
		}else {
			int i = roleService.deleteByPrimaryKey(roleId);
			if (i>0) {
				map.put("isSuccess", true);
			}else{
				map.put("isSuccess", false);
			}
		}
		super.printJSON(response, new Gson().toJson(map));
		
	}

}
