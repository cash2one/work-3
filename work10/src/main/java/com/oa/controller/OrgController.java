package com.oa.controller;

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
import com.oa.entity.Org;
import com.oa.entity.User;
import com.oa.service.OrgService;
import com.oa.service.UserService;

/**
 * 组织管理
 * @author liliting
 *
 */
@Controller
@RequestMapping("/org")
public class OrgController extends BaseController{
	
	//注入service
	@Resource
	private OrgService orgService;
	
	@Resource
	private UserService userService;
	
	
	@RequestMapping("/orgMana")
	public String  toOrgMana(){
		return "org/orgMana";//跳转到orgMana页面
	}
	
	
	/**
	 * 查询组织列表
	 * @param request
	 * @return
	 */
	@RequestMapping("/orgList")
	public String orgList(HttpServletRequest request){
		//参数是一个Map
		Map<String, Object> paramMap = getParam(request);
		 System.out.println("paramMap----------------"+paramMap);
		Map<String, Object> resMap =  orgService.selectOrgList(paramMap);
		HttpSession session = request.getSession();
		session.setAttribute("orgList", resMap.get("orgList"));
		session.setAttribute("total", resMap.get("total"));
		
		return "org/orgList";//返回orgList.ftl视图
	}
	
	@RequestMapping("/orgNumber")
	public  ModelAndView  orgNumber(HttpServletRequest request){
		Map<String, Object> paramMap = getParam(request);
		 System.out.println("orgNumber:paramMap----------------"+paramMap);
		int total = Integer.parseInt(paramMap.get("total")+"");
		int startIndex = Integer.parseInt(paramMap.get("startIndex")+"");
		int pageSize = Integer.parseInt(paramMap.get("pageSize")+"");
		ModelAndView result = new ModelAndView();
	   result = super.getPageNumberInfo(total, startIndex, pageSize, result);
	   result.setViewName("org/orgPageNumber");
	   return result;
	}
	
	
	@RequestMapping("/addOrg")
	public void addOrg(HttpServletRequest request,HttpServletResponse response){
		
		/**
		 * orgPath=orgParentPath+orgId
		 */
		Map<String, Object> paramMap = super.getParam(request);
		paramMap.put("orgPath", paramMap.get("orgParentOrgPath")+""+(orgService.selectOrgId()+1));
		
		Map<String , Object> map = new HashMap<String, Object>();
		try {
			orgService.insert(paramMap);//新增
			map.put("isSuccess", true);

		} catch (Exception e) {
			map.put("isSuccess", false);
		}
		
		
		super.printJSON(response,new Gson().toJson(map) );//{"isSuccess":true}
	}
	
	@RequestMapping("/queryOrg")
	public  void queryOrg(HttpServletResponse response,HttpServletRequest request,long orgId){
		 System.out.println("queryOrg------"+orgId);
		 
		 Org org = orgService.selectOrgAndParentByOrgId(orgId);
		 
		 //对象 json
		 super.printJSON(response, new Gson().toJson(org));
		 
	}
	
	
	@RequestMapping("/updateOrg")
	public void updateOrg(Org org,HttpServletResponse response){
		System.out.println("updateOrg-------"+org);
		int i =  orgService.updateByPrimaryKeySelective(org);
		Map<String , Object> map = new HashMap<String, Object>();
		if (i>0) {
			map.put("isSuccess", true); 
		}else {
			map.put("isSuccess", false);
		}
		
		super.printJSON(response, new Gson().toJson(map));
		
	}
	
	@RequestMapping("/delete")
	public  void  delete(long orgId,HttpServletResponse response){
		//分析：组织能不能随便删除
		//1,组织下有用户不能删除
		List<User> userList = userService.selectUserListByOrgId(orgId);
		//2.组织下有子组织不能删除
         List<Org> orgList = orgService.selectOrgListByParentId(orgId);
         Map<String , Object> map = new HashMap<String, Object>();
        if (orgList!=null&&orgList.size()>0) {
        	   map.put("isSuccess", "hashSubOrg");
		}else if (userList!=null&&userList.size()>0) {
		  map.put("isSuccess", "hashUsers");
		}else {
			orgService.deleteByPrimaryKey(orgId);
			map.put("isSuccess",true);
		}
         
        super.printJSON(response, new Gson().toJson(map));
	}
	

}
