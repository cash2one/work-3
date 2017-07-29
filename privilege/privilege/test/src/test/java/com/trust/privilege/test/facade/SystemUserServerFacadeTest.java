package com.trust.privilege.test.facade;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.trust.privilege.facade.SystemUserServerFacade;
import com.trust.privilege.facade.model.SysUserModel;
import com.trust.privilege.facade.model.SysUserQuaryVo;
import com.trust.privilege.facade.model.complex.MenuResVO;
import com.trust.privilege.model.SystemUser;
import com.trust.privilege.service.SystemUserServcie;
import com.trust.privilege.test.base.AbstractTestBase;
/**
 * @ClassName: SystemUserServerFacadeTest 
 * @Description: 用户模块facade单测
 * @author zyn161616@163.com
 * @version 创建时间：2017年4月28日 上午11:48:32
 */
public class SystemUserServerFacadeTest extends AbstractTestBase{
	
	/**IOC依赖注入systemUserServerFacade实例*/
	private SystemUserServerFacade systemUserServerFacade = (SystemUserServerFacade) this.applicationContext.getBean("sysUserFacade");
	
	/**IOC依赖注入systemUserService实例*/
	private SystemUserServcie systemUserService = (SystemUserServcie) this.applicationContext.getBean("sysUserService");
	
	
	/**
	 * @Title: selectUserTestIsSuper 
	 * @Description: 单测,按条件查询用户信息(超管)
	 * @param    
	 * @return void
	 */
	@Test
	public void selectUserTestIsSuper(){
		//创建查询Model
		SysUserQuaryVo queryVo = new SysUserQuaryVo();
		//设置当前操作人
		queryVo.setUserName("admin");
		//调用方法
		Map<String,Object> resultMap = systemUserServerFacade.selectUser(queryVo);
		//断言
		assertEquals(false, resultMap.isEmpty());
	}
	
	/**
	 * @Title: selectUserTestIsTestUser 
	 * @Description: 单测,按条件查询用户信息(非超管)
	 * @param    
	 * @return void
	 */
	@Test
	public void selectUserTestIsTestUser(){
		//创建查询Model
		SysUserQuaryVo queryVo = new SysUserQuaryVo();
		//设置当前操作人
		queryVo.setUserName("testUser");
		//调用方法
		Map<String,Object> resultMap = systemUserServerFacade.selectUser(queryVo);
		//断言
		assertEquals(false, resultMap.isEmpty());
	}
	
	/**
	 * @Title: selectUpdDisTestParameterIsNull 
	 * @Description: 单测, 修改用户时查询所需的数据（参数为空）
	 * @param    
	 * @return void
	 */
	@Test
	public void selectUpdDisTestParameterIsNull(){
		//调用方法
		Map<String,Object> resultMap = systemUserServerFacade.selectUpdDis("", "admin");
		//断言
		assertEquals(true, resultMap.isEmpty());
	}
	
	/**
	 * @Title: selectUpdDisTestIsSuper 
	 * @Description: 单测,修改用户时查询所需的数据（超管）
	 * @param    
	 * @return void
	 */
	@Test
	public void selectUpdDisTestIsSuper(){
		//通过用户名得到该用户
		SystemUser user = systemUserService.getUserByQuery("admin", null).get(0);
		//调用方法
		Map<String,Object> resultMap = systemUserServerFacade.selectUpdDis(user.getSystemUserId(), "admin");
		//断言
		assertEquals(false, resultMap.isEmpty());
	}
	
	/**
	 * @Title: selectUpdDisTestIsTestUser 
	 * @Description: 单测,修改用户时查询所需的数据（非超管）
	 * @param    
	 * @return void
	 */
	@Test
	public void selectUpdDisTestIsTestUser(){
		//通过用户名得到该用户
		SystemUser user = systemUserService.getUserByQuery("testUser", null).get(0);
		//调用方法
		Map<String,Object> resultMap = systemUserServerFacade.selectUpdDis(user.getSystemUserId(), "testUser");
		//断言
		assertEquals(false, resultMap.isEmpty());
	}
	
	/**
	 * @Title: getInitMsgTestParameterIsNull 
	 * @Description: 单测, 通过条件取门户首页初始化数据(参数为空)
	 * @param    
	 * @return void
	 */
	@Test
	public void getInitMsgTestParameterIsNull(){
		//调用方法
		List<MenuResVO> menus = systemUserServerFacade.getInitMsg("admin", "");
		//断言
		assertEquals(true, menus.isEmpty());
	}
	
	/**
	 * @Title: getInitMsgTest 
	 * @Description: 单测, 通过条件取门户首页初始化数据
	 * @param    
	 * @return void
	 */
	@Test
	public void getInitMsgTest(){
		//调用方法
		List<MenuResVO> menus = systemUserServerFacade.getInitMsg("admin", "1");
		//断言
		assertEquals(false, menus.isEmpty());
	}
	
	/**
	 * @Title: getUserInfoTestParameterIsNull 
	 * @Description: 单测,portal页面，左侧获取个人信息的接口(参数为空)
	 * @param    
	 * @return void
	 */
	@Test
	public void getUserInfoTestParameterIsNull(){
		//调用方法
		SysUserModel userDo = systemUserServerFacade.getUserInfo("");
		//断言
		assertEquals(false, userDo.getUserName() != null);
	}
	
	/**
	 * @Title: getUserInfoTest 
	 * @Description: 单测,portal页面，左侧获取个人信息的接口
	 * @param    
	 * @return void
	 */
	@Test
	public void getUserInfoTest(){
		//调用方法
		SysUserModel userDo = systemUserServerFacade.getUserInfo("admin");
		//断言
		assertEquals(true, userDo.getUserName() != null);
	}

	/**
	 * @Title: modifyPasswordTestParameterIsNull 
	 * @Description: 单测,portal页面根据用户名修改密码(参数为空)
	 * @param    
	 * @return void
	 */
	@Test
	public void modifyPasswordTestParameterIsNull(){
		//创建model
		SysUserModel sysUserModel = new SysUserModel();
		//调用方法
		String result = systemUserServerFacade.modifyPassword(sysUserModel);
		//断言
		assertEquals("修改密码失败！", result);
	}
	
	/**
	 * @Title: modifyPasswordTest 
	 * @Description: 单测,portal页面根据用户名修改密码
	 * @param    
	 * @return void
	 */
	@Test
	public void modifyPasswordTest(){
		//创建model
		SysUserModel sysUserModel = new SysUserModel();
		//设置用户名
		sysUserModel.setUserName("admin");
		//设置原密码
		sysUserModel.setPassword("123456");
		//设置新密码
		sysUserModel.setNewPassword("123456");
		//调用方法
		String result = systemUserServerFacade.modifyPassword(sysUserModel);
		//断言
		assertEquals("修改密码成功！", result);
	}
	
	/**
	 * @Title: insertUserTestParameterIsNull 
	 * @Description: 单测,新增用户信息(参数为空)
	 * @param    
	 * @return void
	 */
	@Test
	public void insertUserTestParameterIsNull(){
		//创建Model
		SysUserModel sysUserModel = new SysUserModel();
		//调用方法
		Map<String,Object> resultMap = systemUserServerFacade.insertUser(sysUserModel);
		//断言
		assertEquals(true, resultMap.isEmpty());
	}
	
	/**
	 * @Title: insertUserTestValidateParameter 
	 * @Description: 单测,新增用户信息(验证参数)
	 * @param    
	 * @return void
	 */
	@Test
	public void insertUserTestValidateParameter(){
		//创建Model
		SysUserModel sysUserModel = new SysUserModel();
		//设置当前操作人
		sysUserModel.setCreateUserId("admin");
		//设置用户名
		sysUserModel.setUserName("testUser");
		//调用方法
		Map<String,Object> resultMap = systemUserServerFacade.insertUser(sysUserModel);
		//断言
		assertEquals("该用户名已经存在,用户组或者角色为空,证件号码不可为空,请重新填写!", resultMap.get("fail"));
	}
	
	/**
	 * @Title: insertUserTest 
	 * @Description: 单测,新增用户信息
	 * @param    
	 * @return void
	 */
	@Test 
	public void insertUserTest(){
		//创建Model
		SysUserModel sysUserModel = new SysUserModel();
		//设置当前操作人
		sysUserModel.setCreateUserId("admin");
		//设置用户名
		sysUserModel.setUserName("testData");
		//设置用户组Id
		sysUserModel.setGroupId("7e13c789179048eabdc8b34c6030c8c3");
		//设置证件号
		sysUserModel.setIdCard("000000000000000000");
		//设置角色
		sysUserModel.setRoleId("ac7a69b92d744460a19ef6c010337b16");
		//调用方法
		Map<String,Object> resultMap = systemUserServerFacade.insertUser(sysUserModel);
		//断言
		assertEquals("新增用户信息成功", resultMap.get("success"));
	}
	
	/**
	 * @Title: updateUserTestParameterIsNull 
	 * @Description: 单测,修改用户信息(参数为空)
	 * @param    
	 * @return void
	 */
	@Test
	public void updateUserTestParameterIsNull(){
		//创建Model
		SysUserModel sysUserModel = new SysUserModel();
		//调用方法
		Map<String,Object> resultMap = systemUserServerFacade.updateUser(sysUserModel);
		//断言
		assertEquals(true, resultMap.isEmpty());
	}
	
	/**
	 * @Title: updateUserTestValidateParameter 
	 * @Description: 单测,修改用户信息(验证参数)
	 * @param    
	 * @return void
	 */
	@Test
	public void updateUserTestValidateParameter(){
		//通过用户名得到该用户
		SystemUser user = systemUserService.getUserByQuery("testData", null).get(0);
		//创建Model
		SysUserModel sysUserModel = new SysUserModel();
		//设置主键
		sysUserModel.setSystemUserId(user.getSystemUserId());
		//设置当前操作人
		sysUserModel.setCreateUserId("admin");
		//设置用户名
		sysUserModel.setUserName("testUser");
		//调用方法
		Map<String,Object> resultMap = systemUserServerFacade.updateUser(sysUserModel);
		//断言
		assertEquals("该用户名已经存在,用户组或者角色为空,证件号码不可为空,请重新填写!", resultMap.get("fail"));
	}
	
	/**
	 * @Title: updateUserTest 
	 * @Description: 单测,修改用户信息
	 * @param    
	 * @return void
	 */
	@Test
	public void updateUserTest(){
		//通过用户名得到该用户
		SystemUser user = systemUserService.getUserByQuery("testData", null).get(0);
		//创建Model
		SysUserModel sysUserModel = new SysUserModel();
		//设置主键
		sysUserModel.setSystemUserId(user.getSystemUserId());
		//设置当前操作人
		sysUserModel.setCreateUserId("admin");
		//设置用户名
		sysUserModel.setUserName("testUserData");
		//设置用户组Id
		sysUserModel.setGroupId("7e13c789179048eabdc8b34c6030c8c3");
		//设置证件号
		sysUserModel.setIdCard("000000000000000000");
		//设置角色
		sysUserModel.setRoleId("ac7a69b92d744460a19ef6c010337b16");
		//调用方法
		Map<String,Object> resultMap = systemUserServerFacade.updateUser(sysUserModel);
		//断言
		assertEquals("修改用户信息成功", resultMap.get("success"));		
	}
	
	/**
	 * @Title: deleteUserByIdTestParameterIsNull 
	 * @Description: 单测,根据用户Id删除该用户信息(参数为空)
	 * @param    
	 * @return void
	 */
	@Test
	public void deleteUserByIdTestParameterIsNull(){
		//调用方法
		Map<String,Object> resultMap = systemUserServerFacade.deleteUserById("", "admin");
		//断言
		assertEquals(true, resultMap.isEmpty());
	}
	
	/**
	 * @Title: deleteUserByIdTest 
	 * @Description: 单测,根据用户Id删除该用户信息
	 * @param    
	 * @return void
	 */
	@Test
	public void deleteUserByIdTest(){
		//通过用户名得到该用户
		SystemUser user = systemUserService.getUserByQuery("testUserData", null).get(0);
		//调用方法
		Map<String,Object> resultMap = systemUserServerFacade.deleteUserById(user.getSystemUserId(), "admin");
		//断言
		assertEquals("删除该用户成功", resultMap.get("success"));
	}
	
}
