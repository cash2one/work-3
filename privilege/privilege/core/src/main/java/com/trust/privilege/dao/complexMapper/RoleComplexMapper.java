package com.trust.privilege.dao.complexMapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 * @ClassName: RoleComplexMapper 
 * @Description: 资源模块dao			
 * @author zyn161616@163.com
 * @version 创建时间：2017年4月24日 上午11:46:24
 */
public interface RoleComplexMapper {
	
	/**
	 * @Title: getAllPrivilegeResByRoleId 
	 * @Description: 根据角色Id查询角色下的页面资源
	 * @param @param roleId
	 * @param @return   
	 * @return List<Map<String,Object>>
	 */
	List<Map<String,Object>> getAllPrivilegeResByRoleId(@Param("roleId") String roleId);
	
	/**
	 * @Title: getAllRoles 
	 * @Description: 多条件查询角色信息
	 * @param @param queryMap
	 * @param @return   
	 * @return List<Map<String,Object>>
	 */
	List<Map<String,Object>> getAllRoles(Map<String,Object> queryMap);
	
	/**
	 * @Title: getAllRolesByCreateUserId 
	 * @Description: 根据创建人姓名查询角色，超级管理员身份
	 * @param @param queryMap
	 * @param @return   
	 * @return List<Map<String,Object>>
	 */
	List<Map<String,Object>> getAllRolesByCreateUserId(Map<String,Object> queryMap);
	
	/**
	 * @Title: getAllMemusByRoleId 
	 * @Description: 根据角色Id查询角色下的菜单
	 * @param @param roleId
	 * @param @return   
	 * @return List<Map<String,Object>>
	 */
	List<Map<String,Object>> getAllMemusByRoleId(@Param("roleId")String roleId);
	
	/**
	 * @Title: selectRoleByUserName 
	 * @Description: 通过用户名和系统查询到当前系统该用户所拥有的用户组中的角色
	 * @param @param queryMap
	 * @param @return   
	 * @return List<Map<String,Object>>
	 */
	List<Map<String,Object>> selectRoleByUserName(Map<String,Object> queryMap);
	
	/**
	 * @Title: selectOptRoleBySuperAdmin 
	 * @Description: 查询该系统下所有的角色
	 * @param @param systemId
	 * @param @return   
	 * @return List<Map<String,Object>>
	 */
	public List<Map<String, Object>> selectOptRoleBySuperAdmin(@Param("systemId")String systemId);
	
    /**
     * @Title: selectUserCreateRoleByUserName 
     * @Description: 通过角色名和系统CD获取该角色在本系统中拥有的角色
     * @param @param queryMap
     * @param @return   
     * @return List<Map<String,Object>>
     */
	List<Map<String,Object>> selectUserCreateRoleByUserName(Map<String,Object> queryMap);
	
	/**
	 * @Title: addManyMenuToRole 
	 * @Description: 为角色插入多个菜单资源
	 * @param @param list
	 * @param @param roleId
	 * @param @param stateCd
	 * @param @return   
	 * @return int
	 */
	int addManyMenuToRole(@Param("list") List<String> list,@Param("roleId") String roleId,@Param("stateCd") String stateCd);
	
}