package com.trust.privilege.dao.complexMapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.trust.privilege.model.UserRoleInstance;

/**
 * 操作用户角色实例表dao
 * 
 * @author luozhenyu
 *
 */
public interface UserRoleInstanceDao {

	/**
	 * 查看用户组中是否存在用户
	 * 
	 * @param groupId
	 * @return
	 */
	public int countUserInGroup(@Param("groupId") String groupId);

	/**
	 * 查看是否有用户属于某角色
	 * 
	 * @param roleID
	 * @return
	 */
	public int countRoleInUse(@Param("roleId") String roleID);

	/**
	 * 从用户组中删除某用户
	 * 
	 * @param map
	 * @return
	 *//*
	public int deleteUserFromGroup(Map<String, String> map);
*/
	/**
	 * 根据用户id，角色id，删除用户角色
	 * 
	 * @param map
	 * @return
	 *//*
	public int delRoleFromUser(Map<String, String> map);
*/
	/**	 * 根据用户组id，角色id，查看用户组的某角色是否有用户使用
	 * 
	 * @param map
	 * @return
	 */
	public int countRoleInGroupInUse(Map<String, String> map);

	/**
	 * 根据用户组id，查询角色id
	 * 
	 * @param groupId
	 * @return
	 */
	public List<String> selectRoleInGroup(String groupId);

	public Integer addRoleToUser(@Param("insUUID") String insUUID,
			@Param("userId") String userId, @Param("roleId") String roleId,
			@Param("list") List<String> list, @Param("stateCd") String stateCd);
	
	public Integer addRoleToUserNoGroup(@Param("insUUID") String insUUID,@Param("userId") String userId,@Param("roleId") String roleId,@Param("stateCd") String stateCd);

	/**根据用户ID查询数据*/
	public UserRoleInstance selectUserRoleInstanceBySysUserId(String sysUserId);
	
}
