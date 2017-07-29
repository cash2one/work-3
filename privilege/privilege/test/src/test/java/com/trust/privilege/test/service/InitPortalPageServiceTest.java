package com.trust.privilege.test.service;

import com.trust.privilege.service.InitPortalPageService;
import com.trust.privilege.test.base.AbstractTestBase;

/***
 * @ClassName: InitPortalPageServiceTest 
 * @Description: 初始化门户数据Service单测
 * @author zyn161616@163.com
 * @version 创建时间：2017年1月19日 上午11:38:52
 */
public class InitPortalPageServiceTest extends AbstractTestBase{

	InitPortalPageService initPortalPageService = (InitPortalPageService)
				this.applicationContext.getBean("initPortalPageService");
	
	/**通过用户名获得该用户的所有门户菜单资源*/
	//@SuppressWarnings({ "unused", "unchecked" })
	//@Test   
	/*public void getPortalInitMenuDataTest(){
		//获取数据
		Map<String,Object> resultMap = initPortalPageService.getPortalInitMenuData("admin");
		//获取该用户所拥有的系统
		Map<String,Object> UserSystem = (Map<String, Object>) resultMap.get("SystemList");
		//获得该用户经常访问的功能
		Map<String,Object> oftenFunctions = (Map<String, Object>) resultMap.get("oftenFunction");
		//获得常访问的系统
		List<SystemConfig> oftenSystems = (List<SystemConfig>) oftenFunctions.get("oftenSystemlist");
	
	}*/
}
