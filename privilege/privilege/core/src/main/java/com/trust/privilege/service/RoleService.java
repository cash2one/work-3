package com.trust.privilege.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.trust.privilege.model.Role;
import com.trust.privilege.model.complex.QueryRole;
import com.trust.privilege.model.complex.RoleDo;
/**
 * @ClassName: RoleService 
 * @Description: 角色Service接口
 * @author zyn161616@163.com
 * @version 创建时间：2017年3月14日 上午10:28:23
 */
public interface RoleService {
	
	/**
	 * @Title: valdiateSuper 
	 * @Description: 验证是否为超级管理员
	 * @param @param userName
	 * @param @return   
	 * @return boolean
	 */
	public boolean valdiateSuper(String userName);
	
	/**
	 * @Title: getAllRoles 
	 * @Description: 根据条件查询角色信息
	 * @param @param queryRoleVO
	 * @param @return   
	 * @return Map<String,Object>
	 */
	public Map<String,Object> getAllRoles(QueryRole queryRole);

	/**
	 * @Title: insertRole 
	 * @Description: 新增角色信息
	 * @param @param role
	 * @param @return   
	 * @return Map<String,Object>
	 */
	public Map<String,Object> insertRole(RoleDo role);
	
	/**
	 * @Title: deleteRoleByRoleId 
	 * @Description: 通过角色Id删除该角色数据
	 * @param @param roleId
	 * @param @param userName
	 * @param @return   
	 * @return Map<String,Object>
	 */
	public Map<String,Object> deleteRoleByRoleId(String roleId,String userName);
	
	/**
	 * @Title: updateRole 
	 * @Description: 修改角色
	 * @param @param roleDo
	 * @param @return   
	 * @return Map<String,Object>
	 */
	public Map<String,Object> updateRole(RoleDo roleDo);
	
	/**
	 * @Title: getAllResourceByRoleId 
	 * @Description: 获取当前角色下的所有菜单和页面资源
	 * @param @param roleId
	 * @param @return   
	 * @return Map<String,Object>
	 */
	public Map<String,Object> getAllResourceByRoleId(String roleId);
	
	/**
	 * @Title: selectRole4UserName 
	 * @Description: 通过用户名和系统CD查询当前系统该用户所拥有的角色
	 * @param @param queryMap
	 * @param @return   
	 * @return String
	 */
	public Set<String> selectRole4UserName(Map<String,Object> queryMap);
	
	/**
	 * @Title: getRoleByRoleId 
	 * @Description: 根据主键查询该角色信息
	 * @param @param roleId
	 * @param @return   
	 * @return Role
	 */
	public Role getRoleByRoleId(String roleId);
	
	/**
	 * @Title: getRoleByExample 
	 * @Description: 通过条件获取数据库中的角色数据
	 * @param @param roleName
	 * @param @param systemPlatformCD
	 * @param @return   
	 * @return List<Role>
	 */
	public List<Role> getRoleByExample(String roleName,String systemPlatformCD);
	
}
