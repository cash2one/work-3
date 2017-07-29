package com.trust.privilege.facade;

import java.util.Map;

import com.trust.privilege.facade.model.RoleVO;
import com.trust.privilege.facade.model.complex.QueryRoleVO;
/**
 * @ClassName: RoleServerFacadeImpl 
 * @Description: 角色模块facade接口
 * @author zyn161616@163.com
 * @version 创建时间：2017年3月14日 上午10:36:34
 */
public interface RoleServerFacade {
	
	/**
	 * @Title: insertRole 
	 * @Description: 新增角色信息
	 * @param @param roleVO
	 * @param @return   
	 * @return Map<String,Object>
	 */
	Map<String,Object> insertRole(RoleVO roleVO);
	
	/**
	 * @Title: getAllRoles 
	 * @Description: 根据条件查询角色信息
	 * @param @param queryRoleVO
	 * @param @param user
	 * @param @return   
	 * @return Map<String,Object>
	 */
	Map<String,Object> getAllRoles(QueryRoleVO queryRoleVO);
	
	/**
	 * @Title: deleteRoleByRoleId 
	 * @Description: 通过角色Id删除该角色数据
	 * @param @param roleId
	 * @param @param user
	 * @param @return   
	 * @return Map<String,Object>
	 */
	Map<String,Object> deleteRoleByRoleId(String roleId,String user);
	
	/**
	 * @Title: getAllResourceByRoleId 
	 * @Description: 获取当前角色下的所有菜单和页面资源
	 * @param @param roleId
	 * @param @return   
	 * @return Map<String,Object>
	 */
	Map<String,Object> getAllResourceByRoleId(String roleId);
	
	/**
	 * @Title: updateRole 
	 * @Description: 修改角色信息
	 * @param @param roleVO
	 * @param @return   
	 * @return Map<String,Object>
	 */
	Map<String,Object> updateRole(RoleVO roleVO);
	
	/**
	 * @Title: selectRole4UserNameAndSystemID 
	 * @Description: 通过用户名和系统CD获取该用户在该系统所拥有的所有角色
	 * @param @param queryMap
	 * @param @return   
	 * @return String
	 */
	public String selectRole4UserNameAndSystemID(Map<String,Object> queryMap);
	
	/**
	 * @Title: getRoleByRoleId 
	 * @Description: 根据主键查询该角色信息
	 * @param @param roleId
	 * @param @return   
	 * @return RoleVO
	 */
	RoleVO getRoleByRoleId(String roleId);
	
}
