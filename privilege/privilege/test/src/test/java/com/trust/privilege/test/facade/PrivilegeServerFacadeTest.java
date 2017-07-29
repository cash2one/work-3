package com.trust.privilege.test.facade;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import com.trust.privilege.facade.PrivilegeServerFacade;
import com.trust.privilege.facade.model.PrivilegeResTypeVO;
import com.trust.privilege.facade.model.PrivilegeResVO;
import com.trust.privilege.facade.model.complex.QueryPrivilegeVO;
import com.trust.privilege.model.PrivilegeRes;
import com.trust.privilege.service.PrivilegeService;
import com.trust.privilege.test.base.AbstractTestBase;
/**
 * @ClassName: PrivilegeServerFacadeTest 
 * @Description: 资源模块facade单测
 * @author zyn161616@163.com
 * @version 创建时间：2017年4月26日 下午2:07:53
 */
@SuppressWarnings("unchecked")
public class PrivilegeServerFacadeTest extends AbstractTestBase{

	/**IOC依赖注入实例*/
	PrivilegeServerFacade privilegeServerFacade = (PrivilegeServerFacade) this.applicationContext.getBean("privilegeServerFacade");
	
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
		Map<String,Object> resultMap = privilegeServerFacade.selectSysPrivilege("", "admin");	
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
		Map<String,Object> resultMap = privilegeServerFacade.selectSysPrivilege("059b6be17b2b4f93a0e62174a25fd4dd","admin");	
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
		QueryPrivilegeVO queryPrivilegeVO = new QueryPrivilegeVO();
		//设置参数
		queryPrivilegeVO.setSystemPlatformName("权限系统");
		//设置用户名
		queryPrivilegeVO.setUserName("admin");
		//调用方法
		Map<String,Object> resultMap = privilegeServerFacade.getAllPrivileges(queryPrivilegeVO);
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
		QueryPrivilegeVO queryPrivilegeVO = new QueryPrivilegeVO();
		//设置参数
		queryPrivilegeVO.setSystemPlatformName("权限系统");
		//设置用户名
		queryPrivilegeVO.setUserName("testUser");
		//调用方法
		Map<String,Object> resultMap = privilegeServerFacade.getAllPrivileges(queryPrivilegeVO);
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
		List<PrivilegeResTypeVO> privilegeResType = privilegeServerFacade.getAllResType();
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
		Set<String> resultUrls = privilegeServerFacade.selectSysValidatePrivilege("", "admin");
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
		Set<String> resultUrls = privilegeServerFacade.selectSysValidatePrivilege("1", "admin");
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
		List<PrivilegeResVO> privilegeRess = privilegeServerFacade.getPrivilegeByMenuIds(privilegeResVO);
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
		PrivilegeResVO privilegeResVO = new PrivilegeResVO();
		//调用方法
		Map<String,Object> resultMap = privilegeServerFacade.addPrivilegeRes(privilegeResVO);
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
		PrivilegeResVO privilegeResVO = new PrivilegeResVO();
		//设置当前操作人
		privilegeResVO.setCreateUserId("admin");
		//设置资源名称
		privilegeResVO.setResName("删除用户组");
		//设置系统CD
		privilegeResVO.setSystemPlatformCd("1");
		//设置资源Url
		privilegeResVO.setUrl("/etheta/management/privilege/group/delete");
		//调用方法
		Map<String,Object> resultMap = privilegeServerFacade.addPrivilegeRes(privilegeResVO);
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
		PrivilegeResVO privilegeResVO = new PrivilegeResVO();
		//设置当前操作人
		privilegeResVO.setCreateUserId("admin");
		//设置资源名称
		privilegeResVO.setResName("测试资源01");
		//设置系统CD
		privilegeResVO.setSystemPlatformCd("1");
		//设置资源Url
		privilegeResVO.setUrl("/test01");
		//调用方法
		Map<String,Object> resultMap = privilegeServerFacade.addPrivilegeRes(privilegeResVO);
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
		PrivilegeResVO privilegeResVO = new PrivilegeResVO();
		//调用方法
		Map<String,Object> resultMap = privilegeServerFacade.updatePrivilegeRes(privilegeResVO);
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
		PrivilegeRes privilegeRes = privilegeService.getPrivilege("测试资源01", "", "1").get(0);
		//创建资源Model
		PrivilegeResVO privilegeResVO = new PrivilegeResVO();
		//设置主键
		privilegeResVO.setPrivilegeResId(privilegeRes.getPrivilegeResId());
		//设置资源名称
		privilegeResVO.setResName("删除用户组");
		//设置系统CD
		privilegeResVO.setSystemPlatformCd("1");
		//设置资源Url
		privilegeResVO.setUrl("/test01");
		//调用方法
		Map<String,Object> resultMap = privilegeServerFacade.updatePrivilegeRes(privilegeResVO);
		//断言
		assertEquals("当前系统下已存在此资源名称,当前系统下已存在此请求Url,请重新填写！", resultMap.get("fail"));
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
		PrivilegeRes privilegeRes = privilegeService.getPrivilege("测试资源01", "", "1").get(0);
		//创建资源Model
		PrivilegeResVO privilegeResVO = new PrivilegeResVO();
		//设置主键
		privilegeResVO.setPrivilegeResId(privilegeRes.getPrivilegeResId());
		//设置资源名称
		privilegeResVO.setResName("testPrivilegeResource01");
		//设置系统CD
		privilegeResVO.setSystemPlatformCd("1");
		//设置资源Url
		privilegeResVO.setUrl("/testPrivilegeResource01");
		//调用方法
		Map<String,Object> resultMap = privilegeServerFacade.updatePrivilegeRes(privilegeResVO);
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
		Map<String,Object> resultMap = privilegeServerFacade.deleteprivilegeRes(null, "admin");
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
		PrivilegeRes privilegeRes = privilegeService.getPrivilege("testPrivilegeResource01", "", "1").get(0);
		//调用方法
		Map<String,Object> resultMap = privilegeServerFacade.deleteprivilegeRes(privilegeRes.getPrivilegeResId(), "admin");
		//断言
		assertEquals("已有角色使用这个资源，不可删除", resultMap.get("fail"));
	}
}
