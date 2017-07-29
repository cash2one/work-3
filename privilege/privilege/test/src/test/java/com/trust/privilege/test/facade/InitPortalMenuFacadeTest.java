package com.trust.privilege.test.facade;

import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.trust.privilege.facade.InitPortalMenuFacade;
import com.trust.privilege.model.SystemConfig;
import com.trust.privilege.model.complex.PortalInitDo;
import com.trust.privilege.test.base.AbstractTestBase;

/***
 * @ClassName: InitPortalMenuFacadeTest 
 * @Description: 初始化门户菜单数据facade单测
 * @author zyn161616@163.com
 * @version 创建时间：2017年1月19日 上午11:56:16
 */
public class InitPortalMenuFacadeTest extends AbstractTestBase {
	
	InitPortalMenuFacade initPortalMenuFacade = (InitPortalMenuFacade)
						this.applicationContext.getBean("initPortalMenuFacade");
	
	
	
	/**单测通过用户名获取该用户门户页可访问的系统菜单*/
	@SuppressWarnings("unchecked")
	@Test
	public void getProtMenuData4UserNameTest(){
		Map<String,Object> resultMap = initPortalMenuFacade.getProtMenuData4UserName("admin","4");
	
		//获取该用户所拥有的系统
		List<PortalInitDo> systemList = (List<PortalInitDo>) resultMap.get("SystemList");
		//获得该用户经常访问的功能
		Map<String,Object> oftenFunctions = (Map<String, Object>) resultMap.get("oftenFunction");
		//获得常访问的系统
		List<SystemConfig> oftenSystems = (List<SystemConfig>) oftenFunctions.get("oftenSystemlist");
		
		System.out.println("该用户所拥有"+systemList.size()+"个，    常用系统有"+oftenSystems.size()+"个");
	}
	
	
	
}
