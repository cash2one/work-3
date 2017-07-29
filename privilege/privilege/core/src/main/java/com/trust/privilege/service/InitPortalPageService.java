package com.trust.privilege.service;

import java.util.Map;

/***
 * @ClassName: InitPortalPage 
 * @Description: 初始化门户页面
 * @author zyn161616@163.com
 * @version 创建时间：2017年1月18日 下午8:33:33
 */
public interface InitPortalPageService {
	
	/**
	 * @Title: getPortalInitMenuData 
	 * @Description: 通过用户名获取当前用户门户初始化菜单数据
	 * @param @param userName
	 * @param @param systemPlaformCD
	 * @param @return   
	 * @return Map<String,Object>
	 */
	public Map<String,Object> getPortalInitMenuData(String userName,String systemPlaformCD);
	
}
