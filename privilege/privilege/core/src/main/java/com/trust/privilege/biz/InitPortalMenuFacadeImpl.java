package com.trust.privilege.biz;

import java.util.Map;

import javax.annotation.Resource;

import com.trust.privilege.facade.InitPortalMenuFacade;
import com.trust.privilege.service.InitPortalPageService;

/***
 * @ClassName: InitPortalPageData 
 * @Description: 初始化门户菜单数据
 * @author zyn161616@163.com
 * @version 创建时间：2017年1月18日 下午8:41:15
 */
public class InitPortalMenuFacadeImpl implements InitPortalMenuFacade{
	
	/**注入service层*/
	@Resource
	private InitPortalPageService initPortalPageService;
	
	
	/**
	 * @Title: getProtMenuData4UserName 
	 * @Description: 通过条件获取该用户门户页可访问的系统菜单
	 * @param @param userName
	 * @param @param systemPlatformCD
	 * @param @return   
	 * @return Map<String,Object>
	 */
	@Override
	public  Map<String,Object> getProtMenuData4UserName(String userName,String systemPlatformCD) {
		//转换之后直接返回这个对象
		return initPortalPageService.getPortalInitMenuData(	userName,systemPlatformCD);
				
	}

}
