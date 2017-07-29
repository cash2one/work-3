package com.trust.privilege.dao.complexMapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.trust.privilege.model.complex.GroupDo;

/**
 * @ClassName: GroupDao 
 * @Description: 用户组dao
 * @author zyn161616@163.com
 * @version 创建时间：2017年4月19日 下午3:00:17	
 */
public interface GroupDao{
	
	/**
	 * @Title: selectGroupDetail 
	 * @Description: 通过用户组Id,查询用户组详情
	 * @param @param groupId
	 * @param @return   
	 * @return List<Map<String,Object>>
	 */
	public List<Map<String, Object>> selectGroupDetail(String groupId);
	
	/**
	 * @Title: selectRoleFromGroup 
	 * @Description: 通过用户组Id查询用户组所拥有的角色
	 * @param @param groupId
	 * @param @return   
	 * @return List<Map<String,Object>>
	 */
	public List<Map<String, Object>> selectRoleFromGroup(@Param("groupId")String groupId);

	/**
	 * @Title: selectUserInGroup 
	 * @Description: 通过用户组ID,查询该用户组所拥有的用户
	 * @param @param groupId
	 * @param @return   
	 * @return List<String>
	 */
	public List<String> selectUserInGroup(String groupId);
	
	/**
	 * @Title: selectGroupByGroupName 
	 * @Description: 通过用户组名称的得到该用户组数据
	 * @param @param groupName
	 * @param @return   
	 * @return GroupDo
	 */
	public GroupDo selectGroupByGroupName(String groupName);
	
	/**
	 * @Title: selectRoleBySys 
	 * @Description: 通过用户名和系统CD得到该用户所拥有的角色
	 * @param @param systemId
	 * @param @param userName
	 * @param @return   
	 * @return List<Map<String,Object>>
	 */
	public List<Map<String, Object>> selectRoleBySys(@Param("systemId")String systemId,@Param("userName")String userName );
	
	/**
	 * @Title: selectOptGroupBySuperAdmin 
	 * @Description: 获取所有用户组
	 * @param @return   
	 * @return List<Map<String,Object>>
	 */
	public List<Map<String, Object>> selectOptGroupBySuperAdmin();
	
	/**
	 * @Title: selectUserGroupBySuperAdmin 
	 * @Description: 超级管理员查询用户组
	 * @param @param queryMap
	 * @param @return   
	 * @return List<Map<String,Object>>
	 */
	public List<Map<String, Object>> selectUserGroupBySuperAdmin(Map<String, Object> queryMap);

	/**
	 * @Title: selectGroupByUserName 
	 * @Description: 通过用户名得到该用户所属用户组
	 * @param @param queryMap
	 * @param @return   
	 * @return List<Map<String,Object>>
	 */
	public List<Map<String,Object>> selectGroupByUserName(Map<String,Object> queryMap);
	
}
