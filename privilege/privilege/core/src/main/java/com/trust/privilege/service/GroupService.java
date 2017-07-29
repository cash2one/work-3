package com.trust.privilege.service;

import java.util.List;
import java.util.Map;

import com.trust.privilege.facade.model.GroupQueryVo;
import com.trust.privilege.model.UserGroup;
import com.trust.privilege.model.complex.GroupDo;

/**
 * @ClassName: GroupRestFacade 
 * @Description: 用户组模块Service接口
 * @author zyn161616@163.com
 * @version 创建时间：2017年3月20日 下午7:53:17
 */
public interface GroupService {
	
	/**
	 * @Title: selectGroup 
	 * @Description: 根据条件查询用户组						 
	 * @param @param vo
	 * @param @return   
	 * @return Map<String, Object>
	 */
	public Map<String, Object> selectGroup(GroupQueryVo groupQueryVo);
	
	/**
	 * @Title: selectGroupDetail 
	 * @Description: 查看用户组详情							 
	 * @param @param groupid
	 * @param @return   
	 * @return Map<String, Object>
	 */
	public Map<String, Object> selectGroupDetail(String groupId);
	
	/**
	 * @Title: selectOptGroup 
	 * @Description: 查询用户可选用户组						 
	 * @param @param userName
	 * @param @return   
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> selectOptGroup(String userName);

	/**
	 * @Title: selectSys 
	 * @Description: 查询用户所拥有系统						 
	 * @param @param userName
	 * @param @return   
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> selectSys(String userName);
	
	/**
	 * @Title: selectRoleFromGroup 
	 * @Description: 查询用户组拥有的角色					 
	 * @param @param groupId
	 * @param @return   
	 * @return Map<String,Object>
	 */
	public Map<String, Object> selectRoleFromGroup(String groupId);
	
	/**
	 * @Title: getUserGroupByEquery 
	 * @Description: 通过条件查询用户组信息					 
	 * @param @param queryMap
	 * @param @return   
	 * @return List<UserGroup>
	 */
	public List<UserGroup> getUserGroupByQuery(Map<String,Object> queryMap);
	
	/**
	 * @Title: insertGroup 
	 * @Description: 添加用户组
	 * @param @param groupModel							 
	 * @param @return   
	 * @return Map<String,Object>
	 */
	public Map<String,Object> insertGroup(GroupDo groupDo);
	
	/**
	 * @Title: selectRoleBySys 
	 * @Description: 新建用户组时，通过所属系统CD获取该系统的角色   
	 * @param @param systemIds
	 * @param @param userName
	 * @param @return   
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> selectRoleBySys(String systemIds,String userName);
	
	/**
	 * @Title: groupUpdDis 
	 * @Description: 修改用户组时需要的数据(预修改)
	 * @param @param groupId
	 * @param @param userName
	 * @param @return   
	 * @return Map<String, Object>
	 */
	public Map<String, Object> groupUpdDis(String groupId, String userName);
	
	/**
	 * @Title: updateGroup 
	 * @Description: 修改用户组信息
	 * @param @param groupDo
	 * @param @return   
	 * @return Map<String,Object>
	 */
	public Map<String,Object> updateGroup(GroupDo groupDo);
	
	/**
	 * @Title: deleteGroup 
	 * @Description: 删除用户组
	 * @param @param groupId
	 * @param @param userName
	 * @param @return   
	 * @return Map<String,Object>
	 */
	public Map<String,Object> deleteGroup(String groupId,String userName);

	
	
}
