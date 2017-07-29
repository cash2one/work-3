package com.trust.privilege.test.service;
import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import com.trust.privilege.model.Role;
import com.trust.privilege.model.complex.QueryRole;
import com.trust.privilege.model.complex.RoleDo;
import com.trust.privilege.service.RoleService;
import com.trust.privilege.test.base.AbstractTestBase;
import com.trust.privilege.util.CommonUtil;
/**
 * @ClassName: RoleServiceTest 
 * @Description: 角色模块Service单测
 * @author zyn161616@163.com
 * @version 创建时间：2017年4月26日 下午3:54:57
 */
@SuppressWarnings("unchecked")
public class RoleServiceTest extends AbstractTestBase {

	/**IOC依赖注入roleService实例*/
	RoleService roleService = (RoleService) this.applicationContext.getBean("roleService");

	/**
	 * @Title: valdiateSuperTest 
	 * @Description: 单测,验证是否为超级管理员
	 * @param    
	 * @return void
	 */
	@Test
	public void valdiateSuperTest(){
		//调用方法,并断言
		assertEquals(true, roleService.valdiateSuper("admin"));
	}
	
	/**
	 * @Title: valdiateSuperTestParameterIsNull 
	 * @Description: 单测,验证是否为超级管理员(参数为空)
	 * @param    
	 * @return void
	 */
	@Test
	public void valdiateSuperTestParameterIsNull(){
		//调用方法,并断言
		assertEquals(false, roleService.valdiateSuper(""));
	}
	
	/**
	 * @Title: getAllRolesTestIsSuper 
	 * @Description: 单测,根据条件查询角色信息(超管)
	 * @param    
	 * @return void
	 */
	@Test
	public void getAllRolesTestIsSuper(){
		//创建model
		QueryRole queryRole = new QueryRole();
		//设置当前操作人
		queryRole.setUserName("admin");
		//设置系统CD
		queryRole.setSystemPlatformCd("1");
		//调用方法
		Map<String,Object> resultMap = roleService.getAllRoles(queryRole);
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
		QueryRole queryRole = new QueryRole();
		//设置当前操作人
		queryRole.setUserName("testUser");
		//设置系统CD
		queryRole.setSystemPlatformCd("1");
		//调用方法
		Map<String,Object> resultMap = roleService.getAllRoles(queryRole);
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
		QueryRole queryRole = new QueryRole();
		//调用方法
		Map<String,Object> resultMap = roleService.getAllRoles(queryRole);
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
		QueryRole queryRole = new QueryRole();
		//设置当前操作人
		queryRole.setUserName("testUser");
		//设置系统CD
		queryRole.setSystemPlatformCd("1");
		//设置角色名称
		queryRole.setRoleName("测试角色");
		//调用方法
		Map<String,Object> resultMap = roleService.getAllRoles(queryRole);
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
		Map<String,Object> resultMap = roleService.getAllResourceByRoleId(null);
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
		Map<String,Object> resultMap = roleService.getAllResourceByRoleId("a0867bbbb28946cfb758d61ddfc603ab");
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
		Set<String> roleNames = roleService.selectRole4UserName(queryMap);
		//断言
		assertEquals(true, roleNames.isEmpty());
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
		Set<String> roleNames = roleService.selectRole4UserName(queryMap);
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
		Role role = roleService.getRoleByRoleId(null);
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
		Role role = roleService.getRoleByRoleId("a0867bbbb28946cfb758d61ddfc603ab");
		//断言
		assertEquals(true, CommonUtil.isNotNull(role));
	}
	
	/**
	 * @Title: getRoleByExampleTestParameterIsNull 
	 * @Description: 单测,通过条件获取数据库中的角色数据(参数为空)
	 * @param    
	 * @return void
	 */
	@Test
	public void getRoleByExampleTestParameterIsNull(){
		//调用方法
		List<Role> role = roleService.getRoleByExample(null, null);
		//断言
		assertEquals(false, role.isEmpty());
	}
	
	/**
	 * @Title: getRoleByExampleTest 
	 * @Description: 单测,通过条件获取数据库中的角色数据
	 * @param    
	 * @return void
	 */
	@Test
	public void getRoleByExampleTest(){
		//调用方法
		List<Role> role = roleService.getRoleByExample("测试角色", "1");
		//断言
		assertEquals(false, role.isEmpty());
	}
	
	/**
	 * @Title: insertRoleTestParameterIsNull 
	 * @Description: 单测,新增角色信息(参数为空)
	 * @param    
	 * @return void
	 */
	@Test
	public void insertRoleTestParameterIsNull(){
		//创建Model
		RoleDo role = new RoleDo();
		//调用方法
		Map<String,Object> resultMap = roleService.insertRole(role);
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
		RoleDo role = new RoleDo();
		//设置角色名
		role.setRoleName("测试角色");
		//设置系统CD
		role.setSystemPlatformCd("1");
		//调用方法
		Map<String,Object> resultMap = roleService.insertRole(role);
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
		RoleDo role = new RoleDo();
		//设置角色名
		role.setRoleName("testRole");
		//设置系统CD
		role.setSystemPlatformCd("1");
		//设置创建人
		role.setCreateUserId("admin");
		//设置状态
		role.setStateCd("1");
		//调用方法
		Map<String,Object> resultMap = roleService.insertRole(role);
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
		RoleDo role = new RoleDo();
		//调用方法
		Map<String,Object> resultMap = roleService.updateRole(role);
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
		RoleDo role = new RoleDo();
		//设置主键
		role.setRoleId(dbRole.getRoleId());
		//设置角色名
		role.setRoleName("testRole");
		//设置系统CD
		role.setSystemPlatformCd("1");
		//调用方法
		Map<String,Object> resultMap = roleService.updateRole(role);
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
		RoleDo role = new RoleDo();
		//设置主键
		role.setRoleId(dbRole.getRoleId());
		//设置角色名
		role.setRoleName("testRoleData");
		//设置系统CD
		role.setSystemPlatformCd("1");
		//调用方法
		Map<String,Object> resultMap = roleService.updateRole(role);
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
		Map<String,Object> resultMap = roleService.deleteRoleByRoleId(null, "admin");
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
		Role dbRole = roleService.getRoleByExample("testRole", "1").get(0);
		//调用方法	
		Map<String,Object> resultMap = roleService.deleteRoleByRoleId(dbRole.getRoleId(), "admin");
		//断言
		assertEquals("删除角色成功", resultMap.get("success"));		
	}
	
}
