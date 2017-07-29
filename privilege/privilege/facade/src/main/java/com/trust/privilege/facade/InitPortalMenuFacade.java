package com.trust.privilege.facade;

import java.util.Map;

/***
 * @ClassName: InitPortalMenuFacade 
 * @Description: 初始化门户菜单数据facade
 * @author zyn161616@163.com
 * @version 创建时间：2017年1月18日 下午8:42:50
 */
public interface InitPortalMenuFacade {

	/**
	 * @Title: getProtMenuData4UserName 
	 * @Description: 通过条件获取该用户门户页显示可访问的系统
	 * @param @param userName
	 * @param @param systemPlatformCD
	 * @param @return   
	 * @return Map<String,Object>
	 */
	public  Map<String,Object> getProtMenuData4UserName(String userName,String systemPlatformCD);
}
