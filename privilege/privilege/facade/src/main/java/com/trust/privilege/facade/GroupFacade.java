package com.trust.privilege.facade;

import java.util.List;
import java.util.Map;

import com.trust.privilege.facade.model.GroupModel;
import com.trust.privilege.facade.model.GroupQueryVo;

/**
 * @ClassName: GroupRestFacade 
 * @Description: 用户组模块Facade接口
 * @author zyn161616@163.com
 * @version 创建时间：2017年3月20日 下午7:53:17
 */
public interface GroupFacade {
	
	/**
	 * @Title: selectGroup 
	 * @Description: 多条件查询用户组
	 * @param @param groupQueryVo
	 * @param @return   
	 * @return Map<String, Object>
	 */
	public Map<String, Object> selectGroup(GroupQueryVo groupQueryVo);
	
	/**
	 * @Title: selectGroup 
	 * @Description: 通过用户组Id查询用户组详情
	 * @param @param vo
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
	public List<Map<String, Object>> selectOptGroup(String userId);
	
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
	 * @return Map<String, Object>
	 */
	public Map<String, Object> selectRoleFromGroup(String groupid);
	
	/**
	 * @Title: insertGroup 
	 * @Description: 添加用户组
	 * @param @param groupModel
	 * @param @return   
	 * @return Map<String,Object>
	 */
	public Map<String,Object> insertGroup(GroupModel groupModel);
	
	/**
	 * @Title: deleteGroup 
	 * @Description: 删除用户组
	 * @param @param groupId
	 * @param @param userName
	 * @param @return   
	 * @return Map<String,Object>
	 */
	public Map<String,Object> deleteGroup(String groupId,String userName);
	
	/**
	 * @Title: selectUpdDis 
	 * @Description: 修改用户组时需要的数据(预修改)
	 * @param @param groupId
	 * @param @param userName
	 * @param @return   
	 * @return Map<String, Object>
	 */
	public Map<String, Object> selectUpdDis(String groupId, String userName);
	
	/**
	 * @Title: updateGroup 
	 * @Description: 修改用户组信息
	 * @param @param groupModel
	 * @param @return   
	 * @return Map<String,Object>
	 */
	public Map<String,Object> updateGroup(GroupModel groupModel);
	
	/**
	 * @Title: selectRoleBySys 
	 * @Description: 新建用户组时，通过所属系统CD获取该系统的角色
	 * @param @param systemIds
	 * @param @param userName
	 * @param @return   
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> selectRoleBySys(String systemIds,String userId);

}
