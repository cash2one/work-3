package com.trust.privilege.dao.complexMapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
/**
 * @ClassName: SystemPlatformComplexMapper 
 * @Description: 系统操作dao
 * @author zyn161616@163.com
 * @version 创建时间：2017年4月20日 下午2:57:43
 */
public interface SystemPlatformComplexMapper {
	
	/**
	 * @Title: selectSystemPlatform 
	 * @Description: 根据用户所拥有的角色来查询用户所拥有的系统列表
	 * @param @param map
	 * @param @return   
	 * @return List<Map<String,Object>>
	 */
	public List<Map<String,Object>> selectSystemPlatform(Map<String,Object> map);
	
	/**
	 * @Title: selectSystemPlatformByCreateUserId 
	 * @Description: 如果是超级管理员，那么就可以不用根据角色来查询，直接根据创建人来查询系统，只有超级管理员可以添加系统
	 * @param @param map
	 * @param @return   
	 * @return List<Map<String,Object>>
	 */
	public List<Map<String,Object>> selectSystemPlatformByCreateUserId(Map<String,Object> map);
	
	/**
	 * @Title: getRoleIdsBySystemPlatformCd 
	 * @Description: 获取当前系统下所有角色id的集合
	 * @param @param systemPlatformCd
	 * @param @return   
	 * @return List<String>
	 */
	public List<String> getRoleIdsBySystemPlatformCd(@Param("systemPlatformCd") String systemPlatformCd);
	
	/**
	 * @Title: updateStateCdOfRoleIds 
	 * @Description: 修改多个角色的状态
	 * @param @param list
	 * @param @param stateCd
	 * @param @return   
	 * @return Integer
	 */
	public Integer updateStateCdOfRoleIds(@Param("list") List<String> list,@Param("stateCd") String stateCd);
	
	/**
	 * @Title: updateMenuBySys 
	 * @Description: 修改当前系统下菜单的状态
	 * @param @param stateCd
	 * @param @param systemPlatformCd
	 * @param @return   
	 * @return Integer
	 */
	public Integer updateMenuBySys(@Param("stateCd") String stateCd,@Param("systemPlatformCd") String systemPlatformCd);
	
	/**
	 * @Title: updateResBySys 
	 * @Description: 修改该系统下权限资源的状态
	 * @param @param stateCd
	 * @param @param systemPlatformCd
	 * @param @return   
	 * @return Integer
	 */
	public Integer updateResBySys(@Param("stateCd") String stateCd,@Param("systemPlatformCd") String systemPlatformCd);
	
	/**
	 * @Title: selectRoleInSys 
	 * @Description: 查询系统CD下是否拥有角色
	 * @param @param systemPlatformCd
	 * @param @return   
	 * @return int
	 */
	public int selectRoleInSys(String systemPlatformCd);

	/**
	 * @Title: selectMenuInSys 
	 * @Description: 查询系统CD下是否拥有菜单
	 * @param @param systemId
	 * @param @return   
	 * @return int
	 */
	public int selectMenuInSys(String systemId);

	/**
	 * @Title: selectPrivilegeInSys 
	 * @Description: 查询系统CD下是否拥有资源
	 * @param @param systemId
	 * @param @return   
	 * @return int
	 */
	public int selectPrivilegeInSys(String systemId);
	
	/**
	 * @Title: selectAllSys 
	 * @Description: 查询有效的系统列表
	 * @param @return   
	 * @return List<Map<String,Object>>
	 */
	public List<Map<String, Object>> selectAllSys();
	
	/**
	 * @Title: selectSysByUser 
	 * @Description: 通过当前操作人和用户组Id得到可操作系统
	 * @param @param userName
	 * @param @param groupId
	 * @param @return   
	 * @return List<Map<String,Object>>
	 */
	public List<Map<String, Object>> selectSysByUser(@Param("userName")String userName,@Param("groupId")String groupId);
	
	/**
	 * @Title: selectSysByGroup 
	 * @Description: 通过用户组ID得到这个用户组所拥有的系统
	 * @param @param groupId
	 * @param @return   
	 * @return List<Map<String,Object>>
	 */
	public List<Map<String, Object>> selectSysByGroup(String groupId);
	
}
