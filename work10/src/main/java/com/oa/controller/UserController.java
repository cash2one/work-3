package com.oa.controller;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.oa.common.MD5Test;
import com.oa.common.excel.CalendarHelper;
import com.oa.entity.Menu;
import com.oa.entity.User;
import com.oa.service.MenuService;
import com.oa.service.RoleMenuRelService;
import com.oa.service.RoleOrgRelService;
import com.oa.service.RoleUserRelService;
import com.oa.service.UserService;
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {
	
	//注入service
	@Resource
	private UserService userService;
	@Resource
	private RoleUserRelService roleUserRelService;
	@Resource
	private RoleOrgRelService roleOrgRelService;
	@Resource
	private MenuService menuService;
	
	@RequestMapping("/toLogin")
	public  String toLogin(){
		return "login";
	}
	 
	@RequestMapping("/login")
	public  String login(User user,HttpSession session){
		if (user!=null) {
			System.out.println("明文："+user.getUserPassword());
			user.setUserPassword(MD5Test.md5(user.getUserPassword()));
			System.out.println("密文："+user.getUserPassword());
	
		
		User user2=userService.login(user);
		if (user2!=null) {
			session.setAttribute("u", user2);
			long id =  user2.getUserId();
			//1.查询该用户的角色编号
			String roleIds1 = roleUserRelService.selectRoleIdByUserId(id);
			//2.查询该用户的组织的角色编号
			String roleIds2 = roleOrgRelService.selectRoleIdByOrgId(user2.getOrgId());
			
			System.out.println(roleIds1+"--"+roleIds2);
			// 1,9---11
			String roleIds ="";
			if (roleIds1==null) {
				if (roleIds2==null) {
					return "login";
				}else  {
					roleIds = roleIds2;
				}	
			}else {
				if (roleIds2==null) {
					roleIds = roleIds1;
				}else  {
					roleIds = roleIds1.concat(",").concat(roleIds2);
				}	
			}
			
			//如果是周末，天气好就去游泳   天气不好在家看电影
			//如果是工作日，天气好，就去上班，天气不好，请假
			
			String []idStrings = roleIds.split(",");
			List list = Arrays.asList(idStrings);
			//3.根据角色编号查询菜单
			List<Menu> list2 =  menuService.selectMenuByRoleId(list);
			System.out.println(list2);
	        for (Menu menu : list2) {
				System.out.println(menu);
			}
			session.setAttribute("menuList", list2);
			return "main";
		}
		}
		return "login";
	}
	
	
	@RequestMapping("/userMana")
	public String userMana(){
		return "user/userMana";
	}
	
	@RequestMapping("/userList")
	public ModelAndView  userList(HttpServletRequest request){
		Map<String, Object> paramMap = getParam(request);
		System.out.println("paramMap----------------"+paramMap);
		Map<String, Object> resMap = userService.selectUserListByPage(paramMap);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("user/userList");
		modelAndView.addObject("userList",resMap.get("userList"));//request.setAttibute();
		modelAndView.addObject("total", resMap.get("total"));
		
		return modelAndView;
		
	}
	
	
	@RequestMapping("/userPageNumber")
	public ModelAndView userPageNumber(HttpServletRequest request){
		int total = Integer.parseInt(request.getParameter("total"));
		int startIndex = Integer.parseInt(request.getParameter("startIndex"));
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));
		
		ModelAndView result = new ModelAndView();
		result = super.getPageNumberInfo(total, startIndex, pageSize, result);
		result.setViewName("user/userPageNumber");
		return result;
		
	}
	
	@RequestMapping("/queryUser")
	public  void queryUser(long userId,HttpServletResponse response){
		User user =  userService.selectByPrimaryKey(userId);
		super.printJSON(response, new Gson().toJson(user));
	}
	
	@RequestMapping("/updateUser")
	public  void updateUser(User user,HttpServletResponse response){
		int i = userService.updateByPrimaryKeySelective(user);
		Map<String, Object> resMap = new HashMap<>();
		if (i>0) {
			resMap.put("isSuccess", true);
		}
		else {
			resMap.put("isSuccess", false);
		}
		super.printJSON(response, new Gson().toJson(resMap));

		
	}
	
	@RequestMapping("/addUser")
	public void addUser(User user,String birthday,HttpServletResponse response){
		try {
			Date date = CalendarHelper.parseDate(birthday);
			user.setUserBirthday(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int i = userService.insert(user);
		Map<String, Object> resMap = new HashMap<>();
		if (i>0) {
			resMap.put("isSuccess", true);
		}
		else {
			resMap.put("isSuccess", false);
		}
		
		super.printJSON(response, new Gson().toJson(resMap));
	}
	/**
	 *1） 邮件批量发送
	 *2） md5处理密码  admin --->  392skadhsfhsdkbvfjkdbvjdczcatyx   md5('admin')
	 *3） 数据用正则校验
	 *4)  修改密码
	 *5)  退出
	 */
	

}
