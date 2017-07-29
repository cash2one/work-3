package com.trust.privilege.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.trust.privilege.dao.complexMapper.MenuComplexMapper;
import com.trust.privilege.dao.complexMapper.PrivilegeComplexMapper;
import com.trust.privilege.service.InitPortalPageService;
import com.trust.privilege.service.SystemPlatFormService;
import com.trust.privilege.util.CommonUtil;
/***
 * @ClassName: InitPortalPageImpl 
 * @Description: 初始化门户网站首页
 * @author zyn161616@163.com
 * @version 创建时间：2017年1月18日 下午8:35:56
 */
public class InitPortalPageServiceImpl implements InitPortalPageService {
	/**注入菜单dao层*/
	@Resource
	private MenuComplexMapper menuComplexMapper;
	/**注入资源dao层*/
	@Resource
	private PrivilegeComplexMapper privilegeComplexMapper;
	/**注入service层*/
	@Resource
	private SystemPlatFormService systemService;
	
	/**
	 * @Title: getPortalInitMenuData 
	 * @Description: 通过条件取门户首页初始化数据（将系统配置成资源，用作portal页面）
	 * @param @param queryPrivilege
	 * @param @param systemPlatformCD
	 * @param @return   
	 * @return Map<String,Object>
	 */
	@Override
	public Map<String,Object> getPortalInitMenuData(String userName,String systemPlatformCD) {
		//创建Map用来存储返回值
		Map<String,Object> map = new HashMap<>(); ;
		//如果参数不为空
		if(CommonUtil.isNotNull(systemPlatformCD)){
			//设置查询条件
			map.put("userName", userName);
			map.put("stateCd", "1");
			map.put("systemPlatformCD", systemPlatformCD);
			//获取该用户所用户的系统数据
			List<Map<String,Object>> userSystems = 
					privilegeComplexMapper.selectSystemceByUserName(map);
			//使用完清空下
			map.clear();
			
			//调用service查询该用户常用功能列表
			Map<String,Object> oftenFunctionMap = systemService.getAllOffenUseOption(userName);
			
			//将得到的数据放入map中 
			map.put("SystemList", userSystems);
			//将该用户常用的功能列表放入Map
			map.put("oftenFunction", oftenFunctionMap);
		}
		//返回这个Map
		return map;
	}

}
