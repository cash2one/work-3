package com.trust.privilege.util;

import com.trust.privilege.model.GroupRole;
import com.trust.privilege.model.MemBer;
import com.trust.privilege.model.Menu;
import com.trust.privilege.model.MenuRole;
import com.trust.privilege.model.PrivilegeRes;
import com.trust.privilege.model.Role;
import com.trust.privilege.model.RolePrivilegeRes;
import com.trust.privilege.model.SystemUser;
import com.trust.privilege.model.UserRoleInstance;
import com.trust.privilege.model.complex.GroupDo;
import com.trust.privilege.model.complex.excel.ExcelDataRoleAndMenu;
import com.trust.privilege.model.complex.excel.ExcelDataRoleAndPrivilegeRes;
import com.trust.privilege.model.complex.excel.ExcelDataUserAndRoleAndGroup;

/***
 * @ClassName: DBInstanceUtil 
 * @Description: 数据库实例工具
 * @author zyn161616@163.com
 * @version 创建时间：2016年12月28日 下午5:34:09
 */
public class DBInstanceUtil {

	/**插入角色表之前的的准备，获取角色实例*/
	public static Role getRole(ExcelDataRoleAndMenu excelDataRoleAndMenu){
		//创建用户表的model
		Role role = new Role();
		//设置角色ID
		role.setRoleId(CommonUtil.getUUID());
		//设置所属系统平台
		role.setSystemPlatformCd(excelDataRoleAndMenu.getSystemPlatFormCd());
		//设置角色名
		role.setRoleName(excelDataRoleAndMenu.getRoleName());
		//设置当前角色状态，0为不可用，1为可用
		role.setStateCd("1");
		//设置角色描述
		role.setRoleDesc(excelDataRoleAndMenu.getRoleDesc());
		//返回这个对象
		return role;
	}
	
	/**插入菜单资源表之前的准备，获取菜单实例*/
	public static Menu getMenu(ExcelDataRoleAndMenu excelDataRoleAndMenu){
		//创建菜单实例
		Menu menu = new Menu();
		//设置菜单Id
		menu.setMenuId(CommonUtil.getUUID());
		//设置所属平台
		menu.setSystemPlatformCd(excelDataRoleAndMenu.getSystemPlatFormCd());
		//设置菜单名称
		menu.setMenuName(excelDataRoleAndMenu.getMenuName());
		//设置菜单描述
		menu.setMenuDesc(excelDataRoleAndMenu.getMenuDesc());
		//设置菜单的请求方式
		menu.setReqeustMethed(excelDataRoleAndMenu.getMenuReqMethod());
		//设置惨淡的请求URL
		menu.setUrl(excelDataRoleAndMenu.getMenuReqUrl());
		//设置菜单的当前状态,0为不可用，1为正常
		menu.setStateCd("1");
		//返回这个对象
		return menu ;
	}
	
	/**插入菜单角色关联表之前的准备，获取菜单角色关联表实例*/
	public static MenuRole getMenuAndRoleTable(String roleId,String menuId){
		//创建菜单角色关联表实例
		MenuRole menuRole = new MenuRole();
		//设置角色id
		menuRole.setRoleId(roleId);
		//设置菜单id
		menuRole.setMenuId(menuId);
		//设置菜单状态类型,默认为可见
		menuRole.setStateType("1");
		//设置状态，0为不可用，1为正常
		menuRole.setStateCd("1");
		//返回这个实例
		return menuRole;
	}
	
	/**插入权限资源表之前的准备，获取权限资源表的实例*/
	public static PrivilegeRes getPrivilegeRes(ExcelDataRoleAndPrivilegeRes excelDataRoleAndPrivilegeRes){
		//创建权限资源表实例
		PrivilegeRes privilegeRes = new PrivilegeRes();
		//设置权限资源id
		privilegeRes.setPrivilegeResId(CommonUtil.getUUID());
		//设置系统平台
		privilegeRes.setSystemPlatformCd(excelDataRoleAndPrivilegeRes.getSystemPlatFormCd());
		//设置资源名称
		privilegeRes.setResName(excelDataRoleAndPrivilegeRes.getResName());
		//设置资源描述
		privilegeRes.setResDesc(excelDataRoleAndPrivilegeRes.getResDesc());
		//设置权限资源的请求方式
		privilegeRes.setReqMethed(excelDataRoleAndPrivilegeRes.getResReqMethod());
		//设置权限资源的url
		privilegeRes.setUrl(excelDataRoleAndPrivilegeRes.getResReqUrl());
		//资源状态0为不可用，1为整场
		privilegeRes.setStateCd("1");
		//设置当前资源所属菜单
		privilegeRes.setMenuId(excelDataRoleAndPrivilegeRes.getMenuId());
		//设置当前资源的标识
		privilegeRes.setMark(excelDataRoleAndPrivilegeRes.getResMark());
		//设置当前资源的类型
		privilegeRes.setResTypeCd(excelDataRoleAndPrivilegeRes.getResTypeCd());
		//返回这个实例
		return privilegeRes;
		
	}
	
	/**插入角色权限资源关联表之前的准备，获取角色权限资源关联表实例*/
	public static RolePrivilegeRes getRoleAndPrivilegeResTable(String roleId,String privilegeResId){
		//创建角色权限资源关联表实例
		RolePrivilegeRes rolePrivilegeRes = new RolePrivilegeRes();
		//设置角色id
		rolePrivilegeRes.setRoleId(roleId);
		//设置权限资源的id
		rolePrivilegeRes.setPrivilegeResId(privilegeResId);
		//设置资源状态类型,默认为可见的
		rolePrivilegeRes.setStateType("1");
		//设置当前状态0为不可用，1为正常
		rolePrivilegeRes.setStateCd("1");
		//返回这个实例
		return rolePrivilegeRes;
	}
	
	/**插入用户组表之前的准备，获取用户组表的实例*/
	public static GroupDo getUserGroup(ExcelDataUserAndRoleAndGroup excelDataUserAndRoleAndGroup){
		//创建用户组表的实例
		GroupDo groupDo = new GroupDo();
		//设置用户组id
		groupDo.setGroupId(CommonUtil.getUUID());
		//设置用户组名称
		groupDo.setGroupName(excelDataUserAndRoleAndGroup.getGroupName());
		//设置当前用户组的父id
		groupDo.setParentGroupId(excelDataUserAndRoleAndGroup.getParent_Group_Id());
		//设置当前用户组的状态0为不可用，1为正常
		groupDo.setStateCd("1");
		//设置当前用户组的描述
		groupDo.setGroupDesc(excelDataUserAndRoleAndGroup.getGroupDesc());
		//返回这个实例
		return groupDo;
	}
	
	/**插入用户组与角色关联表之前的准备，获取用户组与角色关联表的实例*/
	public static GroupRole getGroupAndRoleTable(String roleId,String groupId){
		//创建用户组与角色关联表的实例
		GroupRole groupRole = new GroupRole();
		//设置用户组id
		groupRole.setGroupId(groupId);
		//设置角色id
		groupRole.setRoleId(roleId);
		//设置当前数据状态0为不可用，1为正常
		groupRole.setStateCd("1");
		//返回这个实例
		return groupRole;
	}
	
	/**插入用户表之前的准备，获取用户表实例*/
	public static SystemUser getSystemUser(ExcelDataUserAndRoleAndGroup excelDataUserAndRoleAndGroup){
		//创建用户表的实例
		SystemUser user = new SystemUser();
		//设置当前用户的Id
		user.setSystemUserId(CommonUtil.getUUID());
		//设置用户名
		user.setUserName(excelDataUserAndRoleAndGroup.getUserName());
		//设置证件类型
		user.setMemberType(excelDataUserAndRoleAndGroup.getMemeberType());
		//设置电话号码
		user.setTelNumber(excelDataUserAndRoleAndGroup.getPhoneNumber());
		//设置当前用户状态0为无效，1为正常
		user.setStateCd("1");
		//设置描述
		user.setUserDesc(excelDataUserAndRoleAndGroup.getUserDesc());
		//设置身份证号
		user.setIdCard(excelDataUserAndRoleAndGroup.getIdCard());
		//返回这个实例
		return user;
	}
	
	/**插入用户角色实例表之前的准备，获取用户角色实例表的实例*/
	public static UserRoleInstance getUserRoleInstanceTable(String systemUserId,String groupId){
		//创建用户角色实例表的实例
		UserRoleInstance userRoleInstance = new UserRoleInstance();
		//设置用户id
		userRoleInstance.setSystemUserId(systemUserId);
		//设置用户组id
		userRoleInstance.setGroupId(groupId);
		//设置当前数据状态0为不可用，1为正常
		userRoleInstance.setStateCd("1");
		//返回这个实例
		return userRoleInstance;
	}
	
	/**插入成员表之前的的准备，获取成员实例*/
	public static MemBer getMember(ExcelDataUserAndRoleAndGroup excelDataUserAndRoleAndGroup){
		//创建成员表实例
		MemBer memBer = new MemBer();
		//设置成员id
		memBer.setMemberId(excelDataUserAndRoleAndGroup.getIdCard());
		//设置成员类型
		memBer.setMemberType(excelDataUserAndRoleAndGroup.getMemeberType());
		//设置成员姓名
		memBer.setMemberName(excelDataUserAndRoleAndGroup.getRealName());
		//返回这个对象
		return memBer;
	}
	
}
