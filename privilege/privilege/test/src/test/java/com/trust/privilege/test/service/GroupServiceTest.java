package com.trust.privilege.test.service;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.trust.privilege.facade.model.GroupQueryVo;
import com.trust.privilege.model.UserGroup;
import com.trust.privilege.model.complex.GroupDo;
import com.trust.privilege.service.GroupService;
import com.trust.privilege.test.base.AbstractTestBase;

/**
 * @ClassName: GroupServiceTest 
 * @Description: 用户组Service单测
 * @author zyn161616@163.com
 * @version 创建时间：2017年4月17日 下午7:19:42
 */
@SuppressWarnings("unchecked")
public class GroupServiceTest extends AbstractTestBase {
	
	/**IOC容器获取groupService实例*/
	private GroupService groupService = 
		   			(GroupService) this.applicationContext.getBean("groupService");
	
	/**全局变量，查询Map*/
	Map<String, Object> queryMap = new HashMap<>();
	
	/**
	 * @Title: testInsertGroup 
	 * @Description: 单测，新增用户组(用户组Model为空)
	 * @param    
	 * @return void
	 */
	@Test
	public void testInsertGroup(){
		//创建用户组对象
		GroupDo groupDo = new GroupDo();
		//调用service方法
		Map<String,Object> resultMap = groupService.insertGroup(groupDo);
		//断言
		assertEquals("用户组名为空,请重新填写！", resultMap.get("fail"));
	}
	
	
	/**
	 * @Title: testInsertGroup 
	 * @Description: 单测，新增用户组(用户组名不为空)
	 * @param    
	 * @return void
	 */
	@Test
	public void testInsertGroup1(){
		//创建用户组对象
		GroupDo groupDo = new GroupDo();
		//设置用户组名
		groupDo.setGroupName("测试用户组");
		//设置角色
		groupDo.setRoleId("a0867bbbb28946cfb758d61ddfc603ab");
		//设置状态
		groupDo.setStateCd("1");
		//设置创建人
		groupDo.setCreateUserid("testUser");
		//调用service方法
		Map<String,Object> resultMap = groupService.insertGroup(groupDo);
		//断言
		assertEquals("添加用户组成功!", resultMap.get("success"));
	}
	
	
	/**
	 * @Title: testSelectGroupBySuper 
	 * @Description: 单测根据条件查询用户组(当前身份为超管)
	 * @param    
	 * @return void
	 */
	@Test
	public void testSelectGroupBySuper(){
		//创建查询用户组实例
		GroupQueryVo groupQueryVo = new GroupQueryVo();
		//设置条件
		groupQueryVo.setUserName("admin");
		//进行查询,获取用户组
		List<Map<String, Object>> groups = (List<Map<String, Object>>) 
							groupService.selectGroup(groupQueryVo).get("resultList");
		//断言
		assertEquals(true, groups.size() >= 1);
    }
	
	
	/**
	 * @Title: testSelectGroupBytestUser 
	 * @Description: 单测根据条件查询用户组(当前身份为非超管)
	 * @param    
	 * @return void
	 */
	@Test
	public void testSelectGroupBytestUser(){
		//创建查询用户组实例
		GroupQueryVo groupQueryVo = new GroupQueryVo();
		//设置条件
		groupQueryVo.setUserName("testUser");
		//进行查询,获取用户组
		List<Map<String, Object>> groups = (List<Map<String, Object>>) 
							groupService.selectGroup(groupQueryVo).get("resultList");
		//断言
		assertEquals(true, groups.size() >= 1);				
	}
	
	
	/**
	 * @Title: testSelectGroupDetail 
	 * @Description: 单测通过用户组Id,查看用户组详情(用户组Id是存在的)
	 * @param    
	 * @return void
	 */
	@Test
	public void testSelectGroupDetail(){
		//设置查询条件
		queryMap.put("groupName", "测试用户组");
		//通过用户组名得到该用户组详情
		UserGroup userGroup = groupService.getUserGroupByQuery(queryMap).get(0);
		//通过用户组ID,获取该用户组详情
		Map<String,Object> resultMap = groupService.selectGroupDetail(userGroup.getGroupId());
		//断言
		assertEquals(false, resultMap.isEmpty());
	}
	
	/**
	 * @Title: testSelectGroupDetail 
	 * @Description: 单测通过用户组Id,查看用户组详情(用户组Id是不存在的)
	 * @param    
	 * @return void
	 */
	@Test
	public void testSelectGroupDetail1(){
		//通过用户组ID,查询该用户组拥有的角色
		List<String> groupRoles = (List<String>) 
				groupService.selectGroupDetail("0000000000000000000000000").get("groupRoles");
		//断言
		assertEquals(true, groupRoles.isEmpty());
	}
	
	/**
	 * @Title: testSelectOptGroupBySuper 
	 * @Description: 单测查询当前操作人可选用户组(超管)
	 * @param    
	 * @return void
	 */
	@Test
	public void testSelectOptGroupBySuper(){
		//查询当前操作人可选择用户组
		List<Map<String,Object>> groups = groupService.selectOptGroup("admin");
		//断言
		assertEquals(false, groups.isEmpty());
	}
	
	/**
	 * @Title: testSelectOptGroupByTestUser 
	 * @Description: 单测查询当前操作人可选用户组(非超管)
	 * @param    
	 * @return void
	 */
	@Test
	public void testSelectOptGroupByTestUser(){
		//查询当前操作人可选择用户组
		List<Map<String,Object>> groups = groupService.selectOptGroup("testUser");
		//断言
		assertEquals(false, groups.isEmpty());
	}
	
	/**
	 * @Title: testSelectSysBySuper
	 * @Description: 单测,通过当前操作人，查询用户组所拥有系统(超管)
	 * @param    
	 * @return void
	 */
	@Test
	public void testSelectSysBySuper(){
		//通过当前操作人，查询用户组所拥有系统
		List<Map<String,Object>> systemPlatFroms = groupService.selectSys("admin");
		//断言
		assertEquals(false, systemPlatFroms.isEmpty());
	}
	
	/**
	 * @Title: testSelectSysByTestUser
	 * @Description: 单测,通过当前操作人，查询用户组所拥有系统(非超管)
	 * @param    
	 * @return void
	 */
	@Test
	public void testSelectSysByTestUser(){
		//通过当前操作人，查询用户组所拥有系统
		List<Map<String,Object>> systemPlatFroms = groupService.selectSys("testUser");
		//断言
		assertEquals(false, systemPlatFroms.isEmpty());
	}
	
	/**
	 * @Title: testSelectRoleFromGroup 
	 * @Description: 单测,通过用户组Id,查询用户组拥有的角色
	 * @param    
	 * @return void
	 */
	@Test
	public void testSelectRoleFromGroup(){
		//设置查询条件
		queryMap.put("groupName", "测试用户组");
		//通过用户组名得到该用户组详情
		UserGroup userGroup = groupService.getUserGroupByQuery(queryMap).get(0);
		//单测,通过用户组Id,查询用户组拥有的角色
		Map<String,Object> roles = groupService.selectRoleFromGroup(userGroup.getGroupId());
		//断言
		assertEquals(false, roles.isEmpty());
	}
	
	/**
	 * @Title: testGetUserGroupByQuery 
	 * @Description: 单测,通过条件查询用户组信息
	 * @param    
	 * @return void
	 */
	@Test
	public void testGetUserGroupByQuery(){
		//设置查询条件
		queryMap.put("groupName", "测试用户组");
		//通过用户组名得到该用户组详情
		UserGroup userGroup = groupService.getUserGroupByQuery(queryMap).get(0);
		//断言
		assertEquals(true, userGroup != null);
	}
	
	/**
	 * @Title: testSelectRoleBySysSystemPlatfromCDIsNull 
	 * @Description: 单测,新建用户组时，通过所属系统CD获取该系统的角色(系统CD为空)
	 * @param    
	 * @return void
	 */
	@Test
	public void testSelectRoleBySysSystemPlatfromCDIsNull(){
		//设置当前操作人
		String userName = "admin";
		//设置系统CD为空
		String systemPlatfromCD = null;
		//调用方法
		List<Map<String,Object>> resultList = groupService.selectRoleBySys(systemPlatfromCD, userName);
		//断言
		assertEquals(true, resultList.isEmpty());
		
	}
	
	/**
	 * @Title: testSelectRoleBySysIsSuper 
	 * @Description: 单测,新建用户组时，通过所属系统CD获取该系统的角色(超管)
	 * @param    
	 * @return void
	 */
	@Test
	public void testSelectRoleBySysIsSuper(){
		//设置当前操作人
		String userName = "admin";
		//设置系统
		String systemIds = "1,2,3,4,5";
		//调用方法
		List<Map<String,Object>> resultList = groupService.selectRoleBySys(systemIds, userName);
		//断言
		assertEquals(false, resultList.isEmpty());
	}
	
	/**
	 * @Title: testSelectRoleBySysIsTestUser 
	 * @Description: 单测,新建用户组时，通过所属系统CD获取该系统的角色(非超管)
	 * @param    
	 * @return void
	 */
	@Test
	public void testSelectRoleBySysIsTestUser(){
		//设置当前操作人
		String userName = "testUser";
		//设置系统
		String systemIds = "1,2,3,4,5";
		//调用方法
		List<Map<String,Object>> resultList = groupService.selectRoleBySys(systemIds, userName);
		//断言
		assertEquals(false, resultList.isEmpty());				
	}
	
	/**
	 * @Title: testGroupUpdDisGroupIdIsNull 
	 * @Description: 单测，通过用户组Id,得到该用户组详情(用户组Id为空)
	 * @param    
	 * @return void
	 */
	@Test
	public void testGroupUpdDisGroupIdIsNull(){
		//设置当前操作人
		String userName = "admin";
		//设置用户组Id
		String groupId = null;
		//调用方法
		Map<String,Object> resultMap = groupService.groupUpdDis(groupId, userName);
		//断言
		assertEquals("用户组Id不可为空", resultMap.get("fail"));
	}
	
	/**
	 * @Title: testGroupUpdDisIsSuper 
	 * @Description: 单测，通过用户组Id,得到该用户组详情(超管)
	 * @param    
	 * @return void
	 */
	@Test
	public void testGroupUpdDisIsSuper(){
		//设置查询条件
		queryMap.put("groupName", "测试用户组");
		//通过用户组名得到该用户组详情
		UserGroup userGroup = groupService.getUserGroupByQuery(queryMap).get(0);
		//设置当前操作人
		String userName = "admin";
		//调用方法
		Map<String,Object> resultMap = groupService.groupUpdDis(userGroup.getGroupId(), userName);
		//获取该用户组拥有的角色
		List<Map<String, Object>> roles = (List<Map<String, Object>>) resultMap.get("roles");
		//断言
		assertEquals(false, roles.isEmpty());
	}
	
	/**
	 * @Title: testGroupUpdDisIsTestUser 
	 * @Description: 单测，通过用户组Id,得到该用户组详情(非超管)
	 * @param    
	 * @return void
	 */
	@Test
	public void testGroupUpdDisIsTestUser(){
		//设置查询条件
		queryMap.put("groupName", "测试用户组");
		//通过用户组名得到该用户组详情
		UserGroup userGroup = groupService.getUserGroupByQuery(queryMap).get(0);
		//设置当前操作人
		String userName = "testUser";
		//调用方法
		Map<String,Object> resultMap = groupService.groupUpdDis(userGroup.getGroupId(), userName);
		//获取该用户组拥有的角色
		List<Map<String, Object>> roles = (List<Map<String, Object>>) resultMap.get("roles");
		//断言
		assertEquals(true,!roles.isEmpty());			
	}
	
	/**
	 * @Title: testUpdateGroup 
	 * @Description: 单测，修改用户组信息(用户组Id为空)
	 * @param    
	 * @return void
	 */
	@Test
	public void testUpdateGroupIsNull(){
		//创建用户组对象
		GroupDo groupDo = new GroupDo();
		//设置当前操作人
		groupDo.setCreateUserid("admin");
		//设置用户组Id
		groupDo.setGroupId(null);
		//设置用户组名
		groupDo.setGroupName("测试组");
		//调用方法
		Map<String,Object> resultMap = groupService.updateGroup(groupDo);
		//断言
		assertEquals("用户组Id不可为空", resultMap.get("fail"));
	}
	
	/**
	 * @Title: testUpdateGroup 
	 * @Description: 单测，修改用户组信息
	 * @param    
	 * @return void
	 */
	@Test
	public void testUpdateGroup(){
		//设置查询条件
		queryMap.put("groupName", "测试用户组");
		//通过用户组名得到该用户组详情
		UserGroup userGroup = groupService.getUserGroupByQuery(queryMap).get(0);
		//创建用户组对象
		GroupDo groupDo = new GroupDo();
		//设置当前操作人
		groupDo.setCreateUserid("admin");
		//设置用户组Id
		groupDo.setGroupId(userGroup.getGroupId());
		//设置用户组名
		groupDo.setGroupName("测试组");
		//调用方法
		Map<String,Object> resultMap = groupService.updateGroup(groupDo);
		//断言
		assertEquals("修改用户组成功！", resultMap.get("success"));		
	}
	
	/**
	 * @Title: testDeleteGroupGroupIdIsNull 
	 * @Description: 单测，删除用户组(用户组Id为空)
	 * @param    
	 * @return void
	 */
	@Test
	public void testDeleteGroupGroupIdIsNull(){
		//设置当前操作人
		String userName = "admin";
		//调用方法
		Map<String,Object> resultMap = groupService.deleteGroup(null, userName);
		//断言
		assertEquals("用户组Id不可为空",resultMap.get("fail"));
	}
	
	/**
	 * @Title: testDeleteGroup 
	 * @Description: 单测，删除用户组
	 * @param    
	 * @return void
	 */
	@Test
	public void testDeleteGroup(){
		//设置查询条件
		queryMap.put("groupName", "测试组");
		//通过用户组名得到该用户组详情
		UserGroup userGroup = groupService.getUserGroupByQuery(queryMap).get(0);				
		//设置当前操作人
		String userName = "admin";
		//调用方法
		Map<String,Object> resultMap = groupService.deleteGroup(userGroup.getGroupId(), userName);
		//断言
		assertEquals(false, resultMap.isEmpty());
	}
	
}
