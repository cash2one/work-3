package com.trust.privilege.test.service;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import com.trust.privilege.facade.model.PrivilegeResVO;
import com.trust.privilege.model.PrivilegeRes;
import com.trust.privilege.model.PrivilegeResType;
import com.trust.privilege.model.complex.PrivilegeResDo;
import com.trust.privilege.model.complex.QueryPrivilege;
import com.trust.privilege.service.PrivilegeService;
import com.trust.privilege.test.base.AbstractTestBase;
/**
 * @ClassName: PrivilegeServiceTest 
 * @Description: 资源模块service单测	
 * @author zyn161616@163.com
 * @version 创建时间：2017年4月24日 下午3:22:55
 */
@SuppressWarnings("unchecked")
public class PrivilegeServiceTest extends AbstractTestBase{

	/**IOC依赖注入privilegeService实例*/
	private PrivilegeService privilegeService = (PrivilegeService) this.applicationContext.getBean("privilegeService");

	/**
	 * @Title: selectSysPrivilegeTestQueryIsNull 
	 * @Description: 单测,根据用户名，菜单Id 查询权限资源(参数为空)
	 * @param    
	 * @return void
	 */
	@Test
	public void selectSysPrivilegeTestQueryIsNull(){
		//调用方法
		Map<String,Object> resultMap = privilegeService.selectSysPrivilege("", "admin");	
		//断言
		assertEquals(true, resultMap.isEmpty());
	}

	/**
	 * @Title: selectSysPrivilegeTest 
	 * @Description: 单测,根据用户名，菜单Id 查询权限资源
	 * @param    
	 * @return void
	 */
	@Test
	public void selectSysPrivilegeTest(){
		//调用方法
		Map<String,Object> resultMap = privilegeService.selectSysPrivilege("059b6be17b2b4f93a0e62174a25fd4dd","admin");	
		//断言
		assertEquals(false, resultMap.isEmpty());
	}
	
	/**
	 * @Title: getAllprivelegesTestIsSuper 
	 * @Description: 单测,获取系统资源列表(超管)
	 * @param    
	 * @return void
	 */
	@Test
	public void getAllprivelegesTestIsSuper(){
		//创建Model
		QueryPrivilege queryPrivilege = new QueryPrivilege();
		//设置参数
		queryPrivilege.setSystemPlatformName("权限系统");
		//设置用户名
		queryPrivilege.setUserName("admin");
		//调用方法
		Map<String,Object> resultMap = privilegeService.getAllpriveleges(queryPrivilege);
		//获取资源列表
		List<Map<String, Object>> privilegeRess = (List<Map<String, Object>>) resultMap.get("list");
		//断言
		assertEquals(false, privilegeRess.isEmpty());
	}

	/**
	 * @Title: getAllprivilegesTestIsTestUser 
	 * @Description: 单测,获取系统资源列表(非超管)
	 * @param    
	 * @return void
	 */
	@Test
	public void getAllprivilegesTestIsTestUser(){
		//创建Model
		QueryPrivilege queryPrivilege = new QueryPrivilege();
		//设置参数
		queryPrivilege.setSystemPlatformName("权限系统");
		//设置用户名
		queryPrivilege.setUserName("testUser");
		//调用方法
		Map<String,Object> resultMap = privilegeService.getAllpriveleges(queryPrivilege);
		//获取资源列表
		List<Map<String, Object>> privilegeRess = (List<Map<String, Object>>) resultMap.get("list");
		//断言
		assertEquals(false, privilegeRess.isEmpty());
	}

	/**
	 * @Title: getAllResTypeTest 
	 * @Description: 单测,查询所有的页面资源类型
	 * @param    
	 * @return void
	 */
	@Test
	public void getAllResTypeTest(){
		//调用方法
		List<PrivilegeResType> privilegeResType = privilegeService.getAllResType();
		//断言
		assertEquals(false, privilegeResType.isEmpty());
	}

	/**
	 * @Title: selectSysValidatePrivilegeTestQueryIsNull 
	 * @Description: 单测,根据用户名，系统CD，查询需要拦截的url(参数为空)
	 * @param    
	 * @return void
	 */
	@Test
	public void selectSysValidatePrivilegeTestQueryIsNull(){
		//调用方法
		Set<String> resultUrls = privilegeService.selectSysValidatePrivilege("", "admin");
		//断言
		assertEquals(true, resultUrls.isEmpty());
	}
	
	/**
	 * @Title: selectSysValidatePrivilegeTestQueryIsNull 
	 * @Description: 单测,根据用户名，系统CD，查询需要拦截的url
	 * @param    
	 * @return void
	 */
	@Test
	public void selectSysValidatePrivilegeTest(){
		//调用方法
		Set<String> resultUrls = privilegeService.selectSysValidatePrivilege("1", "admin");
		//断言
		assertEquals(false, resultUrls.isEmpty());
	}
	
	/**
	 * @Title: getPrivilegeByMenuIdsTest 
	 * @Description: 单测,在新建角色时，根据多个id查询页面有效资源
	 * @param    
	 * @return void
	 */
	@Test
	public void getPrivilegeByMenuIdsTest(){
		//创建对象
		PrivilegeResVO privilegeResVO = new PrivilegeResVO();
		//设置菜单Id
		privilegeResVO.setMenuIds("059b6be17b2b4f93a0e62174a25fd4dd");
		//设置状态
		privilegeResVO.setStateCd("1");
		//调用方法
		List<PrivilegeRes> privilegeRess = privilegeService.getPrivilegeByMenuIds(privilegeResVO);
		//断言
		assertEquals(false, privilegeRess.isEmpty());
	}
	
	/**
	 * @Title: getPrivilegeTestParameterIsNull 
	 * @Description: 单测,通过条件获取资源数据(参数为空)
	 * @param    
	 * @return void
	 */
	@Test
	public void getPrivilegeTestParameterIsNull(){
		//调用方法
		List<PrivilegeRes> privilegeRess = privilegeService.getPrivilege(null, null, null);
		//断言
		assertEquals(false, privilegeRess.isEmpty());
	}
	
	/**
	 * @Title: getPrivilegeTestResNameIsNull 
	 * @Description: 单测,通过条件获取资源数据(资源名称为空)
	 * @param    
	 * @return void
	 */
	@Test
	public void getPrivilegeTestResNameIsNull(){
		//调用方法
		List<PrivilegeRes> privilegeRess = privilegeService.getPrivilege(null, "/etheta/management/privilege/group/delete", "1");
		//断言
		assertEquals(false, privilegeRess.isEmpty());
	}
	
	/**
	 * @Title: getPrivilegeTestPrivilegeUrlIsNull 
	 * @Description: 单测,通过条件获取资源数据(资源Url为空)
	 * @param    
	 * @return void
	 */
	@Test
	public void getPrivilegeTestPrivilegeUrlIsNull(){
		//调用方法
		List<PrivilegeRes> privilegeRess = privilegeService.getPrivilege("删除用户组", null, "1");
		//断言
		assertEquals(false, privilegeRess.isEmpty());
	}
	
	/**
	 * @Title: getPrivilegeTestSystemPlatformCDIsNull 
	 * @Description: 单测,通过条件获取资源数据(系统CD为空)
	 * @param    
	 * @return void
	 */
	@Test
	public void getPrivilegeTestSystemPlatformCDIsNull(){
		//调用方法
		List<PrivilegeRes> privilegeRess = privilegeService.getPrivilege("删除用户组", "/etheta/management/privilege/group/delete", null);
		//断言
		assertEquals(false, privilegeRess.isEmpty());
	}
	
	/**
	 * @Title: getPrivilegeTest 
	 * @Description: 单测,通过条件获取资源数据
	 * @param    
	 * @return void
	 */
	@Test
	public void getPrivilegeTest(){
		//调用方法
		List<PrivilegeRes> privilegeRess = privilegeService.getPrivilege("删除用户组", "/etheta/management/privilege/group/delete", "1");
		//断言
		assertEquals(false, privilegeRess.isEmpty());
	}
	
	/**
	 * @Title: insertPrivilegeTestModelIsNull 
	 * @Description: 单测, 新增资源信息(参数为空)
	 * @param    
	 * @return void
	 */
	@Test
	public void insertPrivilegeTestModelIsNull(){
		//创建资源Model
		PrivilegeResDo privilege = new PrivilegeResDo();
		//调用方法
		Map<String,Object> resultMap = privilegeService.insertPrivilege(privilege);
		//断言
		assertEquals(true, resultMap.isEmpty());
	}
	
	/**
	 * @Title: insertPrivilegeTestValidateParameter
	 * @Description:  单测, 新增资源信息(验证参数)
	 * @param    
	 * @return void
	 */
	@Test
	public void insertPrivilegeTestValidateParameter(){
		//创建资源Model
		PrivilegeResDo privilege = new PrivilegeResDo();
		//设置当前操作人
		privilege.setCreateUserId("admin");
		//设置资源名称
		privilege.setResName("删除用户组");
		//设置系统CD
		privilege.setSystemPlatformCd("1");
		//设置资源Url
		privilege.setUrl("/etheta/management/privilege/group/delete");
		//调用方法
		Map<String,Object> resultMap = privilegeService.insertPrivilege(privilege);
		//断言
		assertEquals("当前系统下资源名称重复,当前资源所属的系统下已存在此url,请重新填写！", resultMap.get("fail"));
	}
	
	/**
	 * @Title: insertPrivilegeTest 
	 * @Description:  单测, 新增资源信息
	 * @param    
	 * @return void
	 */
	@Test
	public void insertPrivilegeTest(){
		//创建资源Model
		PrivilegeResDo privilege = new PrivilegeResDo();
		//设置当前操作人
		privilege.setCreateUserId("admin");
		//设置资源名称
		privilege.setResName("测试资源");
		//设置系统CD
		privilege.setSystemPlatformCd("1");
		//设置资源Url
		privilege.setUrl("/test");
		//调用方法
		Map<String,Object> resultMap = privilegeService.insertPrivilege(privilege);
		//断言
		assertEquals("添加资源成功！", resultMap.get("success"));
	}
	
	/**
	 * @Title: updatePrivilegeTestModelIsNull 
	 * @Description: 单测, 修改资源信息(参数为空)
	 * @param    
	 * @return void
	 */
	@Test
	public void updatePrivilegeTestModelIsNull(){
		//创建资源Model
		PrivilegeResDo privilege = new PrivilegeResDo();
		//调用方法
		Map<String,Object> resultMap = privilegeService.updatePrivilege(privilege);
		//断言
		assertEquals(true, resultMap.isEmpty());
	}
	
	/**
	 * @Title: updatePrivilegeTestValidateParameter
	 * @Description: 单测, 修改资源信息(验证参数)
	 * @param    
	 * @return void
	 */
	@Test
	public void updatePrivilegeTestValidateParameter(){
		//通过系统CD和资源名称获取该资源信息
		PrivilegeRes privilegeRes = privilegeService.getPrivilege("测试资源", "", "1").get(0);
		//创建资源Model
		PrivilegeResDo privilege = new PrivilegeResDo();
		//设置主键
		privilege.setPrivilegeResId(privilegeRes.getPrivilegeResId());
		//设置资源名称
		privilege.setResName("删除用户组");
		//设置系统CD
		privilege.setSystemPlatformCd("1");
		//设置资源Url
		privilege.setUrl("/test");
		//调用方法
		Map<String,Object> resultMap = privilegeService.updatePrivilege(privilege);
		//断言
		assertEquals("当前系统下资源名称重复,当前资源所属的系统下已存在此url,请重新填写！", resultMap.get("fail"));
	}
	
	/**
	 * @Title: updatePrivilegeTest 
	 * @Description: 单测, 修改资源信息
	 * @param    
	 * @return void
	 */
	@Test
	public void updatePrivilegeTest(){
		//通过系统CD和资源名称获取该资源信息
		PrivilegeRes privilegeRes = privilegeService.getPrivilege("测试资源", "", "1").get(0);
		//创建资源Model
		PrivilegeResDo privilege = new PrivilegeResDo();
		//设置主键
		privilege.setPrivilegeResId(privilegeRes.getPrivilegeResId());
		//设置资源名称
		privilege.setResName("testPrivilegeResource");
		//设置系统CD
		privilege.setSystemPlatformCd("1");
		//设置资源Url
		privilege.setUrl("/testPrivilegeResource");
		//调用方法
		Map<String,Object> resultMap = privilegeService.updatePrivilege(privilege);
		//断言
		assertEquals("修改资源信息成功！", resultMap.get("success"));
	}
	
	/**
	 * @Title: deletePrivilegeTestParameterIsNull 
	 * @Description: 单测,通过资源Id 删该资源(参数为空)
	 * @param    
	 * @return void
	 */
	@Test
	public void deletePrivilegeTestParameterIsNull(){
		//调用方法
		Map<String,Object> resultMap = privilegeService.deletePrivilege(null, "admin");
		//断言
		assertEquals(true, resultMap.isEmpty());
	}
	
	/**
	 * @Title: deletePrivilegeTest 
	 * @Description: 单测,通过资源Id 删除该资源
	 * @param    
	 * @return void
	 */
	@Test
	public void deletePrivilegeTest(){
		//通过系统CD和资源名称获取该资源信息
		PrivilegeRes privilegeRes = privilegeService.getPrivilege("testPrivilegeResource", "", "1").get(0);
		//调用方法
		Map<String,Object> resultMap = privilegeService.deletePrivilege(privilegeRes.getPrivilegeResId(), "admin");
		//断言
		assertEquals("已有角色使用这个资源，不可删除", resultMap.get("fail"));
	}
	
}
