package com.trust.privilege.service;

import java.util.List;
import java.util.Map;

import com.trust.privilege.facade.model.SysUserModel;
import com.trust.privilege.facade.model.SysUserQuaryVo;
import com.trust.privilege.model.SystemUser;
import com.trust.privilege.model.complex.MenuDo;
import com.trust.privilege.model.complex.UserDo;

/**
 * @ClassName: SystemUserRestFacade 
 * @Description: 用户模块Service接口
 * @author zyn161616@163.com
 * @version 创建时间：2017年3月17日 下午2:33:09
 */
public interface SystemUserServcie {
	
	/**
	 * @Title: getUserByQuery 
	 * @Description: 通过条件获取该用户信息
	 * @param @param userName
	 * @param @param password
	 * @param @return   
	 * @return List<SystemUser>
	 */
	public List<SystemUser> getUserByQuery(String userName,String password);
	
	/**
	 * @Title: selectUser 
	 * @Description: 按条件查询用户信息
	 * @param @param queryVo
	 * @param @return   
	 * @return Map<String,Object>
	 */
	public Map<String, Object> selectUser(SysUserQuaryVo queryVo);
	
	/**
	 * @Title: insertUser 
	 * @Description: 新增用户信息
	 * @param @param sysUserDo
	 * @param @return   
	 * @return Map<String,Object>
	 */
	public Map<String,Object> insertUser(UserDo sysUserDo);
	
	/**
	 * @Title: deleteUserById 
	 * @Description: 根据用户Id删除该用户信息
	 * @param @param sysUserId
	 * @param @param userName
	 * @param @return   
	 * @return Map<String,Object>
	 */
	public Map<String,Object> deleteUserById(String sysUserId, String userName);
	
	/**
	 * @Title: selectUpdDis 
	 * @Description: 修改用户时查询所需的数据（预修改）
	 * @param @param userId
	 * @param @param userName
	 * @param @return  
	 * @return Map<String, Object>
	 */
	public Map<String, Object> selectUpdDis(String userId,String userName);
	
	/**
	 * @Title: updateUser 
	 * @Description: 修改用户信息
	 * @param @param sysUserDo
	 * @param @return   
	 * @return Map<String,Object>
	 */
	public Map<String,Object> updateUser(UserDo sysUserDo);
	
	/**
	 * @Title: getInitMsg 
	 * @Description: 通过id获取该用户详情
	 * @param @param userName
	 * @param @param systemId
	 * @param @return   
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> selectUserDetail(String userId);

//-----------------------------------------------------------------	
	/**
	 * @Title: getInitMsg 
	 * @Description: 通过条件取门户首页初始化数据（将系统配置成资源，用作portal页面）
	 * @param @param userName
	 * @param @param systemId
	 * @param @return   
	 * @return List<MenuResVO>
	 */
	public List<MenuDo> getInitMsg(String userName, String systemId);
	
	/**
	 * @Title: getUserInfo 
	 * @Description: portal页面，左侧获取个人信息的接口
	 * @param @return   
	 * @return UserDo
	 */
	public UserDo getUserInfo(String userName);

	/**
	 * @Title: modifyPassword 
	 * @Description: 根据用户名修改密码
	 * @param @param sysUserModel
	 * @param @return   
	 * @return String
	 */
	public String modifyPassword(SysUserModel sysUserModel);

}