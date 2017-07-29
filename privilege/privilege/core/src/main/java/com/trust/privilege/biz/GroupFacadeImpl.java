package com.trust.privilege.biz;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.trust.privilege.convert.GroupConvertUtil;
import com.trust.privilege.facade.GroupFacade;
import com.trust.privilege.facade.model.GroupModel;
import com.trust.privilege.facade.model.GroupQueryVo;
import com.trust.privilege.service.GroupService;
/**
 * @ClassName: GroupRestFacade 
 * @Description: 用户组模块Facade接口实现
 * @author zyn161616@163.com
 * @version 创建时间：2017年3月20日 下午7:53:17
 */
public class GroupFacadeImpl implements GroupFacade{
	
	/**依赖注入用户组Service*/
	@Resource
	private GroupService groupService;
	
	/**
	 * @Title: selectGroup 
	 * @Description: 多条件查询用户组
	 * @param @param groupQuaryVo
	 * @param @return   
	 * @return Map<String, Object>
	 */
	@Override
	public Map<String, Object> selectGroup(GroupQueryVo groupQuaryVo) {
		return groupService.selectGroup(groupQuaryVo);
	}
	
	/**
	 * @Title: selectGroupDetail 
	 * @Description: 查看用户组详情
	 * @param @param groupid
	 * @param @return   
	 * @return Map<String, Object>
	 */
	@Override
	public Map<String, Object> selectGroupDetail(String groupId) {
		return groupService.selectGroupDetail(groupId);
	}
	
	/**
	 * @Title: selectOptGroup 
	 * @Description: 查询用户可选用户组
	 * @param @param userName
	 * @param @return   
	 * @return List<Map<String, Object>>
	 */
	@Override
	public List<Map<String, Object>> selectOptGroup(String userName) {
		return groupService.selectOptGroup(userName);
	}
	
	/**
	 * @Title: selectSys 
	 * @Description: 查询用户所拥有系统
	 * @param @param userName
	 * @param @return   
	 * @return List<Map<String, Object>>
	 */
	@Override
	public List<Map<String, Object>> selectSys(String userName) {
		return groupService.selectSys(userName);
	}
	
	/**
	 * @Title: selectRoleFromGroup 
	 * @Description: 查询用户组拥有的角色
	 * @param @param groupId
	 * @param @return   
	 * @return Map<String,Object>
	 */
	@Override
	public Map<String, Object> selectRoleFromGroup(String groupId) {
		return groupService.selectRoleFromGroup(groupId);
	}
	
	/**
	 * @Title: insertGroup 
	 * @Description: 添加用户组
	 * @param @param groupModel
	 * @param @return   
	 * @return Map<String,Object>
	 */
	public Map<String,Object> insertGroup(GroupModel groupModel){
		return groupService.insertGroup(
					GroupConvertUtil.convertVOlToModel(groupModel));
	}
	
	/**
	 * @Title: deleteGroup 
	 * @Description: 删除用户组
	 * @param @param groupId
	 * @param @param userName
	 * @param @return   
	 * @return Map<String,Object>
	 */
	@Override
	public Map<String,Object> deleteGroup(String groupId,String userName) {
		return groupService.deleteGroup(groupId,userName);
	}
	
	/**
	 * @Title: selectUpdDis 
	 * @Description: 修改用户组时需要的数据(预修改)
	 * @param @param groupId
	 * @param @param userName
	 * @param @return   
	 * @return Map<String, Object>
	 */
	@Override
	public Map<String, Object> selectUpdDis(String groupId, String userName) {
		return groupService.groupUpdDis(groupId, userName);
	}
	
	/**
	 * @Title: updateGroup 
	 * @Description: 修改用户组信息
	 * @param @param groupModel
	 * @param @return   
	 * @return Map<String,Object>
	 */
	@Override
	public Map<String,Object> updateGroup(GroupModel groupModel) {
		return groupService.updateGroup(GroupConvertUtil.convertVOlToModel(groupModel));
	}
	
	/**
	 * @Title: selectRoleBySys 
	 * @Description: 新建用户组时，通过所属系统CD获取该系统的角色
	 * @param @param systemId
	 * @param @param userName
	 * @param @return   
	 * @return List<Map<String, Object>>
	 */
	@Override
	public List<Map<String, Object>> selectRoleBySys(String systemIds,String userName) {
		return groupService.selectRoleBySys(systemIds,userName);
	}
	
}
