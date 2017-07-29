package com.trust.privilege.test.facade;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.trust.privilege.facade.RoleServerFacade;
import com.trust.privilege.facade.model.RoleVO;
import com.trust.privilege.facade.model.complex.QueryRoleVO;
import com.trust.privilege.model.Role;
import com.trust.privilege.service.RoleService;
import com.trust.privilege.test.base.AbstractTestBase;
import com.trust.privilege.util.CommonUtil;

/**
 * @ClassName: RoleServerFacadeTest 
 * @Description: 角色模块facade单测
 * @author zyn161616@163.com
 * @version 创建时间：2017年4月27日 下午3:21:03
 */
@SuppressWarnings("unchecked")
public class RoleServerFacadeTest extends AbstractTestBase{
	
	/**IOC依赖注入roleServerFacade实例*/
	private RoleServerFacade roleServerFacade = (RoleServerFacade) this.applicationContext.getBean("roleServerFacade");
	
	/**IOC依赖注入roleService实例*/
	RoleService roleService = (RoleService) this.applicationContext.getBean("roleService");
	
	/**
	 * @Title: getAllRolesTestIsSuper 
	 * @Description: 单测,根据条件查询角色信息(超管)
	 * @param    
	 * @return void
	 */
	@Test
	public void getAllRolesTestIsSuper(){
		//创建model
		QueryRoleVO queryRoleVO = new QueryRoleVO();
		//设置当前操作人
		queryRoleVO.setUserName("admin");
		//设置系统CD
		queryRoleVO.setSystemPlatformCd("1");
		//调用方法
		Map<String,Object> resultMap = roleServerFacade.getAllRoles(queryRoleVO);
		//获取角色信息
		List<Map<String,Object>> roles = (List<Map<String, Object>>) resultMap.get("list");
		//断言
		assertEquals(false, roles.isEmpty());
	}
	
	/**
	 * @Title: getAllRolesTestIsTestUser 
	 * @Description: 单测,根据条件查询角色信息(非超管)
	 * @param    
	 * @return void
	 */
	@Test
	public void getAllRolesTestIsTestUser(){
		//创建model
		QueryRoleVO queryRoleVO = new QueryRoleVO();
		//设置当前操作人
		queryRoleVO.setUserName("testUser");
		//设置系统CD
		queryRoleVO.setSystemPlatformCd("1");
		//调用方法
		Map<String,Object> resultMap = roleServerFacade.getAllRoles(queryRoleVO);
		//获取角色信息
		List<Map<String,Object>> roles = (List<Map<String, Object>>) resultMap.get("list");
		//断言
		assertEquals(false, roles.isEmpty());
	}
	
	/**
	 * @Title: getAllRolesTestParameterIsNull 
	 * @Description: 单测,根据条件查询角色信息(参数为空)
	 * @param    
	 * @return void
	 */
	@Test
	public void getAllRolesTestParameterIsNull(){
		//创建model
		QueryRoleVO queryRoleVO = new QueryRoleVO();
		//调用方法
		Map<String,Object> resultMap = roleServerFacade.getAllRoles(queryRoleVO);
		//获取角色信息
		List<Map<String,Object>> roles = (List<Map<String, Object>>) resultMap.get("list");
		//断言
		assertEquals(true, roles.isEmpty());		
	}
	
	/**
	 * @Title: getAllRolesTest 
	 * @Description: 单测,根据条件查询角色信息
	 * @param    
	 * @return void
	 */
	@Test
	public void getAllRolesTest(){
		//创建model
		QueryRoleVO queryRoleVO = new QueryRoleVO();
		//设置当前操作人
		queryRoleVO.setUserName("testUser");
		//设置系统CD
		queryRoleVO.setSystemPlatformCd("1");
		//设置角色名称
		queryRoleVO.setRoleName("测试角色");
		//调用方法
		Map<String,Object> resultMap = roleServerFacade.getAllRoles(queryRoleVO);
		//获取角色信息
		List<Map<String,Object>> roles = (List<Map<String, Object>>) resultMap.get("list");
		//断言
		assertEquals(false, roles.isEmpty());
	}
	
	/**
	 * @Title: getAllResourceByRoleIdTestParameterIsNull 
	 * @Description: 单测,获取当前角色下的所有菜单和页面资源(参数为空)
	 * @param    
	 * @return void
	 */
	@Test
	public void getAllResourceByRoleIdTestParameterIsNull(){
		//调用方法
		Map<String,Object> resultMap = roleServerFacade.getAllResourceByRoleId(null);
		//断言
		assertEquals(true, resultMap.isEmpty());
	}
	
	/**
	 * @Title: getAllResourceByRoleIdTest 
	 * @Description: 单测,获取当前角色下的所有菜单和页面资源(预修改角色)
	 * @param    
	 * @return void
	 */
	@Test
	public void getAllResourceByRoleIdTest(){
		//调用方法
		Map<String,Object> resultMap = roleServerFacade.getAllResourceByRoleId("a0867bbbb28946cfb758d61ddfc603ab");
		//获取菜单Id
		List<String> menuIds = (List<String>) resultMap.get("memuIdsList");
		//断言
		assertEquals(false, menuIds.isEmpty());
	}
	
	/**
	 * @Title: selectRole4UserNameTestParameterIsNull 
	 * @Description: 单测,通过用户名和系统CD获取该用户在该系统所拥有的所有角色(参数为空)
	 * @param    
	 * @return void
	 */
	@Test
	public void selectRole4UserNameTestParameterIsNull(){
		//创建查询Map
		Map<String, Object> queryMap = new HashMap<>();
		//调用方法
		String roleNames = roleServerFacade.selectRole4UserNameAndSystemID(queryMap);
		//断言
		assertEquals(false, roleNames.isEmpty());
	}
	
	/**
	 * @Title: selectRole4UserNameTest 
	 * @Description: 单测,通过用户名和系统CD获取该用户在该系统所拥有的所有角色
	 * @param    
	 * @return void
	 */
	@Test
	public void selectRole4UserNameTest(){
		//创建查询Map
		Map<String, Object> queryMap = new HashMap<>();
		//设置用户名
		queryMap.put("userName", "admin");
		//设置系统CD
		queryMap.put("systemPlatformCd", "1");
		//调用方法
		String roleNames = roleServerFacade.selectRole4UserNameAndSystemID(queryMap);
		//断言
		assertEquals(false, roleNames.isEmpty());
	}
	
	/**
	 * @Title: getRoleByRoleIdTestParameterIsNull 
	 * @Description: 单测,根据主键查询该角色信息(参数为空)
	 * @param    
	 * @return void
	 */
	@Test
	public void getRoleByRoleIdTestParameterIsNull(){
		//调用方法
		RoleVO role = roleServerFacade.getRoleByRoleId(null);
		//断言
		assertEquals(true, CommonUtil.isNull(role));
	}

	/**
	 * @Title: getRoleByRoleIdTest 
	 * @Description: 单测,根据主键查询该角色信息
	 * @param    
	 * @return void
	 */
	@Test
	public void getRoleByRoleIdTest(){
		//调用方法
		RoleVO role = roleServerFacade.getRoleByRoleId("a0867bbbb28946cfb758d61ddfc603ab");
		//断言
		assertEquals(true, CommonUtil.isNotNull(role));
	}
	//-----------------------------------------------------------------
	/**
	 * @Title: insertRoleTestParameterIsNull 
	 * @Description: 单测,新增角色信息(参数为空)
	 * @param    
	 * @return void
	 */
	@Test
	public void insertRoleTestParameterIsNull(){
		//创建Model
		RoleVO roleVO = new RoleVO();
		//调用方法
		Map<String,Object> resultMap = roleServerFacade.insertRole(roleVO);
		//断言
		assertEquals(true, resultMap.isEmpty());
	}
	
	/**
	 * @Title: insertRoleTestValidateParameter 
	 * @Description: 单测,新增角色信息(验证参数)
	 * @param    
	 * @return void
	 */
	@Test
	public void insertRoleTestValidateParameter(){
		//创建Model
		RoleVO roleVO = new RoleVO();
		//设置角色名
		roleVO.setRoleName("测试角色");
		//设置系统CD
		roleVO.setSystemPlatformCd("1");
		//调用方法
		Map<String,Object> resultMap = roleServerFacade.insertRole(roleVO);
		//断言
		assertEquals("所属系统下角色名称重复,请重复填写", resultMap.get("fail"));
	}
	
	/**
	 * @Title: insertRoleTest 
	 * @Description: 单测,新增角色信息
	 * @param    
	 * @return void
	 */
	@Test
	public void insertRoleTest(){
		//创建Model
		RoleVO roleVO = new RoleVO();
		//设置角色名
		roleVO.setRoleName("testRole");
		//设置系统CD
		roleVO.setSystemPlatformCd("1");
		//设置创建人
		roleVO.setCreateUserId("admin");
		//设置状态
		roleVO.setStateCd("1");
		//调用方法
		Map<String,Object> resultMap = roleServerFacade.insertRole(roleVO);
		//断言
		assertEquals("新增角色成功", resultMap.get("success"));
	}
	
	/**
	 * @Title: updateRoleTestParameterIsNull 
	 * @Description: 单测,修改角色信息(参数为空)
	 * @param    
	 * @return void
	 */
	@Test
	public void updateRoleTestParameterIsNull(){
		//创建Model
		RoleVO roleVO = new RoleVO();
		//调用方法
		Map<String,Object> resultMap = roleServerFacade.updateRole(roleVO);
		//断言
		assertEquals(true, resultMap.isEmpty());
	}
	
	/**
	 * @Title: updateRoleTestValidateParameter 
	 * @Description: 单测,修改角色信息(验证参数)
	 * @param    
	 * @return void
	 */
	@Test
	public void updateRoleTestValidateParameter(){
		//通过角色名获取角色ID
		Role dbRole = roleService.getRoleByExample("testRole", "1").get(0);
		//创建Model
		RoleVO roleVO = new RoleVO();
		//设置主键
		roleVO.setRoleId(dbRole.getRoleId());
		//设置角色名
		roleVO.setRoleName("testRole");
		//设置系统CD
		roleVO.setSystemPlatformCd("1");
		//调用方法
		Map<String,Object> resultMap = roleServerFacade.updateRole(roleVO);
		//断言
		assertEquals("角色名称已存在,请重复填写", resultMap.get("fail"));
	}
	
	/**
	 * @Title: updateRoleTest 
	 * @Description: 单测,修改角色信息
	 * @param    
	 * @return void
	 */
	@Test
	public void updateRoleTest(){
		//通过角色名获取角色ID
		Role dbRole = roleService.getRoleByExample("testRole", "1").get(0);
		//创建Model
		RoleVO roleVO = new RoleVO();
		//设置主键
		roleVO.setRoleId(dbRole.getRoleId());
		//设置角色名
		roleVO.setRoleName("testRoleData");
		//设置系统CD
		roleVO.setSystemPlatformCd("1");
		//调用方法
		Map<String,Object> resultMap = roleServerFacade.updateRole(roleVO);
		//断言
		assertEquals("修改角色成功", resultMap.get("success"));
	}
	
	/**
	 * @Title: deleteRoleByRoleIdTestParameterIsNull 
	 * @Description: 单测,通过角色Id删除该角色数据(参数为空)
	 * @param    
	 * @return void
	 */
	@Test
	public void deleteRoleByRoleIdTestParameterIsNull(){
		//调用方法
		Map<String,Object> resultMap = roleServerFacade.deleteRoleByRoleId(null, "admin");
		//断言
		assertEquals(true, resultMap.isEmpty());
	}
	
	/**
	 * @Title: deleteRoleByRoleIdTest 
	 * @Description: 单测,通过角色Id删除该角色数据
	 * @param    
	 * @return void
	 */
	@Test
	public void deleteRoleByRoleIdTest(){
		//通过角色名获取角色ID
		Role dbRole = roleService.getRoleByExample("testRoleData", "1").get(0);
		//调用方法	
		Map<String,Object> resultMap = roleServerFacade.deleteRoleByRoleId(dbRole.getRoleId(), "admin");
		//断言
		assertEquals("删除角色成功", resultMap.get("success"));		
	}
	
}
