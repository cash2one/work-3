package com.trust.privilege.test.facade;

import static org.junit.Assert.assertEquals;
import java.util.Map;
import org.junit.Test;
import com.trust.privilege.facade.SystemPlatformServerFacade;
import com.trust.privilege.facade.model.SystemConfigVO;
import com.trust.privilege.facade.model.SystemPlatformVO;
import com.trust.privilege.facade.model.complex.QuerySystemPlatformVO;
import com.trust.privilege.model.SystemPlatform;
import com.trust.privilege.service.SystemPlatFormService;
import com.trust.privilege.test.base.AbstractTestBase;

/**
 * @ClassName: SystemPlatformRestFacadeTest 
 * @Description: 系统操作facade单测
 * @author zyn161616@163.com
 * @version 创建时间：2017年4月20日 下午7:54:33
 */
public class SystemPlatformRestFacadeTest extends AbstractTestBase{
	
	/**IOC容器获取systemPlatformServerFacade实例*/
	SystemPlatformServerFacade systemPlatformServerFacade = 
				(SystemPlatformServerFacade)this.applicationContext.getBean("systemPlatformServerFacade");
	
	/**IOC容器获取systemService实例*/
	private SystemPlatFormService systemService = 
		   			(SystemPlatFormService) this.applicationContext.getBean("systemPlatFormService");
	
	/**
	 * @Title: selectSystemPlatformTestIsSuper 
	 * @Description: 单测,通过条件，查询系统列表(超管)
	 * @param    
	 * @return void
	 */
	@Test
	public void selectSystemPlatformTestIsSuper(){
		//创建查询Mdel
		QuerySystemPlatformVO querySystemPlatformVO = new QuerySystemPlatformVO();
		//设置当前为超管
		querySystemPlatformVO.setUserName("admin");
		//进行查询
		Map<String,Object> resultMap = systemPlatformServerFacade.selectSystemPlatform(querySystemPlatformVO);
		//断言
		assertEquals(false, resultMap.isEmpty());
	}
	
	/**
	 * @Title: selectSystemPlatformTestIsTestUser 
	 * @Description: 单测,通过条件，查询系统列表(非超管)
	 * @param    
	 * @return void
	 */
	@Test
	public void selectSystemPlatformTestIsTestUser(){
		//创建查询Mdel
		QuerySystemPlatformVO querySystemPlatformVO = new QuerySystemPlatformVO();
		//设置当前为测试用户
		querySystemPlatformVO.setUserName("testUser");
		//进行查询
		Map<String,Object> resultMap = systemPlatformServerFacade.selectSystemPlatform(querySystemPlatformVO);
		//断言
		assertEquals(false, resultMap.isEmpty());
	}
	
	/**
	 * @Title: getAllSystemTest 
	 * @Description: 单测,查询数据库中的系统列表，多条件查询日志时使用
	 * @param    
	 * @return void
	 */
	@Test
	public void getAllSystemTest(){
		//查询并且断言
		assertEquals(false, systemPlatformServerFacade.getAllSystem().isEmpty());
	}
	
	/**
	 * @Title: insertSystemTestModelIsNull 
	 * @Description: 单测,新增系统信息(model为空)
	 * @param    
	 * @return void
	 */
	@Test
	public void insertSystemTestModelIsNull(){
		//创建model
		SystemPlatformVO systemPlatformVO = new SystemPlatformVO();
		//进行查询
		Map<String,Object> resultMap = systemPlatformServerFacade.insertSystem(systemPlatformVO);
		//断言
		assertEquals("系统CD或者系统名称不可为空", resultMap.get("fail"));
	}
	
	/**
	 * @Title: insertSystemTestQueryIsNull 
	 * @Description: 单测,新增系统信息(系统CD为空)
	 * @param    
	 * @return void
	 */
	@Test
	public void insertSystemTestQueryIsNull(){
		//创建model
		SystemPlatformVO systemPlatformVO = new SystemPlatformVO();
		//设置系统CD
		systemPlatformVO.setSystemPlatformCd(null);
		//设置系统名称
		systemPlatformVO.setSystemPlatformName("测试系统");
		//进行查询
		Map<String,Object> resultMap = systemPlatformServerFacade.insertSystem(systemPlatformVO);
		//断言
		assertEquals("系统CD或者系统名称不可为空", resultMap.get("fail"));
	}
	
	/**
	 * @Title: insertSystemTest 
	 * @Description: 单测,新增系统信息
	 * @param    
	 * @return void
	 */
	@Test
	public void insertSystemTest(){
		//创建model
		SystemPlatformVO systemPlatformVO = new SystemPlatformVO();
		//设置系统CD
		systemPlatformVO.setSystemPlatformCd("007");
		//设置系统名称
		systemPlatformVO.setSystemPlatformName("测试系统");
		//进行查询
		Map<String,Object> resultMap = systemPlatformServerFacade.insertSystem(systemPlatformVO);
		//断言
		assertEquals("系统信息插入成功！", resultMap.get("success"));
	}
	
	/**
	 * @Title: updateSystemTestModelIsNull 
	 * @Description: 单测,修改系统信息(model为空)
	 * @param    
	 * @return void
	 */
	@Test
	public void updateSystemTestModelIsNull(){
		//创建model
		SystemPlatformVO systemPlatformVO = new SystemPlatformVO();
		//进行查询
		Map<String,Object> resultMap = systemPlatformServerFacade.upadateSystemPlatform(systemPlatformVO);
		//断言
		assertEquals("系统CD或者系统名称不可为空", resultMap.get("fail"));
	}
	
	/**
	 * @Title: updateSystemTestQueryIsNull 
	 * @Description: 单测,修改系统信息(系统CD为空)
	 * @param    
	 * @return void
	 */
	@Test
	public void updateSystemTestQueryIsNull(){
		//创建model
		SystemPlatformVO systemPlatformVO = new SystemPlatformVO();
		//设置系统CD
		systemPlatformVO.setSystemPlatformCd(null);
		//设置系统名称
		systemPlatformVO.setSystemPlatformName("测试系统");
		//进行修改
		Map<String,Object> resultMap = systemPlatformServerFacade.upadateSystemPlatform(systemPlatformVO);
		//断言
		assertEquals("系统CD或者系统名称不可为空", resultMap.get("fail"));	
	}
	
	/**
	 * @Title: updateSystemTest 
	 * @Description: 单测,修改系统信息
	 * @param    
	 * @return void
	 */
	@Test
	public void updateSystemTest(){
		//通过系统名称查询该系统信息
		SystemPlatform system = systemService.getSystem("测试系统", "1").get(0);
		//创建model
		SystemPlatformVO systemPlatformVO = new SystemPlatformVO();
		//设置系统CD
		systemPlatformVO.setSystemPlatformCd(system.getSystemPlatformCd());
		//设置系统名称
		systemPlatformVO.setSystemPlatformName("测试系统的测试数据");
		
		//进行修改
		Map<String,Object> resultMap = systemPlatformServerFacade.upadateSystemPlatform(systemPlatformVO);
		//断言
		assertEquals("修改系统信息成功", resultMap.get("success"));
	}
	
	/**
	 * @Title: deleteSystemTestQueryIsNull 
	 * @Description: 单测,根据系统CD删除该系统数据(参数为空)
	 * @param    
	 * @return void
	 */
	@Test
	public void deleteSystemTestQueryIsNull(){
		//进行删除
		Map<String,Object> resultMap = systemPlatformServerFacade.deleteSystemPlateForm(null, null);
		//断言
		assertEquals(true, resultMap.isEmpty());
	}

	/**
	 * @Title: deleteSystemTestSystemPlatformCDIsNull 
	 * @Description: 单测,根据系统CD删除该系统数据(系统CD为空)
	 * @param    
	 * @return void
	 */
	@Test
	public void deleteSystemTestSystemPlatformCDIsNull(){
		//进行删除
		Map<String,Object> resultMap = systemPlatformServerFacade.deleteSystemPlateForm(null, "admin");
		//断言
		assertEquals(true, resultMap.isEmpty());
	}

	/**
	 * @Title: deleteSystemTest 
	 * @Description: 单测,根据系统CD删除该系统数据
	 * @param    
	 * @return void
	 */
	@Test
	public void deleteSystemTest(){
		//通过系统名称查询该系统信息
		SystemPlatform system = systemService.getSystem("测试系统的测试数据", "1").get(0);
		//进行删除
		Map<String,Object> resultMap = systemPlatformServerFacade.deleteSystemPlateForm(system.getSystemPlatformCd(), "admin");
		//断言
		assertEquals("删除系统成功", resultMap.get("success"));
	}
	
	/**
	 * @Title: getAllOffenUseOptionTestQueryIsNull 
	 * @Description: 单测,通过用户名查询该用户常用功能列表（portal门户,参数为空）
	 * @param    
	 * @return void
	 */
	@Test
	public void getAllOffenUseOptionTestQueryIsNull(){
		//创建Model
		SystemConfigVO systemConfigVO = new SystemConfigVO();
		//查询
		Map<String,Object> resultMap = systemPlatformServerFacade.getAllOffenUseOption(systemConfigVO);
		//断言
		assertEquals(true, resultMap.isEmpty());
	}
	
	/**
	 * @Title: getAllOffenUseOptionTest
	 * @Description: 单测,通过用户名查询该用户常用功能列表（portal门户）
	 * @param    
	 * @return void
	 */
	@Test
	public void getAllOffenUseOptionTest(){
		//创建Model
		SystemConfigVO systemConfigVO = new SystemConfigVO();
		//设置当前从操作人
		systemConfigVO.setUserName("admin");
		//查询
		Map<String,Object> resultMap = systemPlatformServerFacade.getAllOffenUseOption(systemConfigVO);
		//断言
		assertEquals(false, resultMap.isEmpty());
	}
	
	/**
	 * @Title: insertOffenUseOptionTestQueryIsNull 
	 * @Description: 单测,插入系统常用功能(参数为空)
	 * @param    
	 * @return void
	 */
	@Test
	public void insertOffenUseOptionTestQueryIsNull(){
		//创建Model
		SystemConfigVO systemConfigVO = new SystemConfigVO();
		//调用方法,并断言
		assertEquals("资源名称不可为空", systemPlatformServerFacade.insertOffenUseOption(systemConfigVO));
	}
	
	/**
	 * @Title: insertOffenUserOptionTestQuery 
	 * @Description:  单测,插入系统常用功能(参数，数据库中存在)
	 * @param    
	 * @return void
	 */
	@Test
	public void insertOffenUserOptionTestQuery(){
		//创建Model
		SystemConfigVO systemConfigVO = new SystemConfigVO();
		//设置操作人
		systemConfigVO.setUserName("admin");
		//设置资源名称 
		systemConfigVO.setResourceName("任务平台系统");
		//调用方法并断言
		assertEquals("名称不可重复", systemPlatformServerFacade.insertOffenUseOption(systemConfigVO));
	}
	
	/**
	 * @Title: insertOffenUseOptionTest 
	 * @Description: 单测,插入系统常用功能
	 * @param    
	 * @return void
	 */
	@Test
	public void insertOffenUseOptionTest(){
		//创建Model
		SystemConfigVO systemConfigVO = new SystemConfigVO();
		//设置资源Id
		systemConfigVO.setPrivilegeResId("90ab3e308cfa4da480ebb8199acd4bb8");
		//设置操作人
		systemConfigVO.setUserName("admin");
		//设置资源名称 
		systemConfigVO.setResourceName("云客服系统");	
		//调用方法并断言
		assertEquals("插入常用功能成功", systemPlatformServerFacade.insertOffenUseOption(systemConfigVO));
	}
	
	/**
	 * @Title: deleteOffenUseOptionTestQueryIsNull 
	 * @Description: 单测, 通过常用系统功能的ID删除系统常用功能(参数为空)
	 * @param    
	 * @return void
	 */
	@Test
	public void deleteOffenUseOptionTestQueryIsNull(){
		//删除并且断言
		assertEquals(false, systemPlatformServerFacade.deleteOffenUseOption(null) != null);
	}
	
}
