package com.trust.privilege.test.service;

import java.util.Map;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

import com.trust.privilege.facade.model.complex.QuerySystemPlatformVO;
import com.trust.privilege.model.SystemConfig;
import com.trust.privilege.model.SystemPlatform;
import com.trust.privilege.service.SystemPlatFormService;
import com.trust.privilege.test.base.AbstractTestBase;
/**
 * @ClassName: SystemServiceTest 
 * @Description: 系统平台Service单测
 * @author zyn161616@163.com
 * @version 创建时间：2017年4月18日 下午5:15:12
 */
public class SystemServiceTest extends AbstractTestBase {
	
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
		Map<String,Object> resultMap = systemService.selectSystemPlatform(querySystemPlatformVO);
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
		Map<String,Object> resultMap = systemService.selectSystemPlatform(querySystemPlatformVO);
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
		assertEquals(false, systemService.getAllSystem().isEmpty());
	}

	/**
	 * @Title: getSystemTest 
	 * @Description: 单测，通过条件查询(系统名称为空)
	 * @param    
	 * @return void
	 */
	@Test
	public void getSystemTest(){
		//查询
		SystemPlatform systemPlatform = systemService.getSystem(null, "1").get(0);
		//断言
		assertEquals(true, systemPlatform != null );
	}
	
	/**
	 * @Title: getSystemTest 
	 * @Description: 单测，通过条件查询(查询条件为空)
	 * @param    
	 * @return void
	 */
	@Test
	public void getSystemTestQueryIsNull(){
		//查询
		SystemPlatform systemPlatform = systemService.getSystem(null, null).get(0);
		//断言
		assertEquals(true, systemPlatform != null );
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
		SystemPlatform systemPlatform = new SystemPlatform();
		//进行查询
		Map<String,Object> resultMap = systemService.insertSystem(systemPlatform);
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
		SystemPlatform systemPlatform = new SystemPlatform();
		//设置系统CD
		systemPlatform.setSystemPlatformCd(null);
		//设置系统名称
		systemPlatform.setSystemPlatformName("测试系统");
		//进行查询
		Map<String,Object> resultMap = systemService.insertSystem(systemPlatform);
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
		SystemPlatform systemPlatform = new SystemPlatform();
		//设置系统CD
		systemPlatform.setSystemPlatformCd("007");
		//设置系统名称
		systemPlatform.setSystemPlatformName("测试系统");
		//进行查询
		Map<String,Object> resultMap = systemService.insertSystem(systemPlatform);
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
		SystemPlatform systemPlatform = new SystemPlatform();
		//进行查询
		Map<String,Object> resultMap = systemService.updateSystem(systemPlatform);
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
		SystemPlatform systemPlatform = new SystemPlatform();
		//设置系统CD
		systemPlatform.setSystemPlatformCd(null);
		//设置系统名称
		systemPlatform.setSystemPlatformName("测试系统");
		//进行修改
		Map<String,Object> resultMap = systemService.updateSystem(systemPlatform);
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
		SystemPlatform systemPlatform = new SystemPlatform();
		//设置系统CD
		systemPlatform.setSystemPlatformCd(system.getSystemPlatformCd());
		//设置系统名称
		systemPlatform.setSystemPlatformName("测试系统的测试数据");
		
		//进行修改
		Map<String,Object> resultMap = systemService.updateSystem(systemPlatform);
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
		Map<String,Object> resultMap = systemService.deleteSystem(null, null);
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
		Map<String,Object> resultMap = systemService.deleteSystem(null, "admin");
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
		Map<String,Object> resultMap = systemService.deleteSystem(system.getSystemPlatformCd(), "admin");
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
		//查询
		Map<String,Object> resultMap = systemService.getAllOffenUseOption(null);
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
		//查询
		Map<String,Object> resultMap = systemService.getAllOffenUseOption("admin");
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
		//创建对象
		SystemConfig systemConfig = new SystemConfig();
		//调用方法,并断言
		assertEquals("资源名称不可为空", systemService.insertOffenUseOption(systemConfig));
	}
	
	/**
	 * @Title: insertOffenUserOptionTestQuery 
	 * @Description:  单测,插入系统常用功能(参数，数据库中存在)
	 * @param    
	 * @return void
	 */
	@Test
	public void insertOffenUserOptionTestQuery(){
		//创建对象
		SystemConfig systemConfig = new SystemConfig();
		//设置操作人
		systemConfig.setUserName("admin");
		//设置资源名称 
		systemConfig.setResourceName("任务平台系统");
		//调用方法并断言
		assertEquals("名称不可重复", systemService.insertOffenUseOption(systemConfig));
		
	}

	/**
	 * @Title: insertOffenUseOptionTest 
	 * @Description: 单测,插入系统常用功能
	 * @param    
	 * @return void
	 */
	@Test
	public void insertOffenUseOptionTest(){
		//创建对象
		SystemConfig systemConfig = new SystemConfig();
		//设置资源Id
		systemConfig.setPrivilegeResId("90ab3e308cfa4da480ebb8199acd4bb8");
		//设置操作人
		systemConfig.setUserName("admin");
		//设置资源名称 
		systemConfig.setResourceName("云客服系统");	
		//调用方法并断言
		assertEquals("插入常用功能成功", systemService.insertOffenUseOption(systemConfig));
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
		assertEquals(false, systemService.deleteOffenUseOption(null) != null);
	}
	
}
