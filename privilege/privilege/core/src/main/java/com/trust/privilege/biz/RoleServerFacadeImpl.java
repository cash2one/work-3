package com.trust.privilege.biz;

import java.util.Map;

import javax.annotation.Resource;

import com.trust.privilege.convert.RoleConvertUtil;
import com.trust.privilege.facade.RoleServerFacade;
import com.trust.privilege.facade.model.RoleVO;
import com.trust.privilege.facade.model.complex.QueryRoleVO;
import com.trust.privilege.service.RoleService;
/**
 * @ClassName: RoleServerFacadeImpl 
 * @Description: 角色模块facade接口实现
 * @author zyn161616@163.com
 * @version 创建时间：2017年3月14日 上午10:36:34
 */
public class RoleServerFacadeImpl implements RoleServerFacade{
	
	/**依赖注入角色Service*/
	@Resource
	private RoleService roleService;
	
	/**
	 * @Title: getAllRoles 
	 * @Description: 根据条件查询角色信息
	 * @param @param queryRoleVO
	 * @param @param user
	 * @param @return   
	 * @return Map<String,Object>
	 */
	@Override
	public Map<String,Object> getAllRoles(QueryRoleVO queryRoleVO) {
		return roleService.getAllRoles(
				RoleConvertUtil.queryRoleVOConvert(queryRoleVO));
	}
	
	/**
	 * @Title: insertRole 
	 * @Description: 新增角色信息
	 * @param @param roleVO
	 * @param @return   
	 * @return Map<String,Object>
	 */
	@Override
	public Map<String,Object> insertRole(RoleVO roleVO) {
		return roleService.insertRole(RoleConvertUtil.convertRoleDoToVO(roleVO));
	}
	
	/**
	 * @Title: deleteRoleByRoleId 
	 * @Description: 通过角色Id删除该角色数据
	 * @param @param roleId
	 * @param @param user
	 * @param @return   
	 * @return Map<String,Object>
	 */
	@Override
	public Map<String,Object> deleteRoleByRoleId(String roleId,String user) {
		return roleService.deleteRoleByRoleId(roleId,user);
	}
	
	/**
	 * @Title: getAllResourceByRoleId 
	 * @Description: 获取当前角色下的所有菜单和页面资源(预修改)
	 * @param @param roleId
	 * @param @return   
	 * @return Map<String,Object>
	 */
	@Override
	public Map<String,Object> getAllResourceByRoleId(String roleId) {
		return  roleService.getAllResourceByRoleId(roleId);
	}
	
	/**
	 * @Title: updateRole 
	 * @Description: 修改角色信息
	 * @param @param roleVO
	 * @param @return   
	 * @return Map<String,Object>
	 */
	@Override
	public Map<String,Object> updateRole(RoleVO roleVO) {
		return roleService.updateRole(RoleConvertUtil.convertRoleDoToVO(roleVO));
	}
	
	/**
	 * @Title: selectRole4UserNameAndSystemID 
	 * @Description: 通过用户名和系统CD获取该用户在该系统所拥有的所有角色
	 * @param @param queryMap
	 * @param @return   
	 * @return String
	 */
	@Override
	public String selectRole4UserNameAndSystemID(Map<String,Object> queryMap){
		return roleService.selectRole4UserName(queryMap).toString();
	}
	
	/**
	 * @Title: getRoleByRoleId 
	 * @Description: 根据主键查询该角色信息
	 * @param @param roleId
	 * @param @return   
	 * @return RoleVO
	 */
	@Override
	public RoleVO getRoleByRoleId(String roleId) {
		return RoleConvertUtil.convertRoleDoToModel(roleService.getRoleByRoleId(roleId));
	}
	
}
