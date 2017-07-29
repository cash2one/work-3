package com.trust.privilege.facade;

import java.util.List;
import java.util.Map;

import com.trust.privilege.facade.model.SysUserModel;
import com.trust.privilege.facade.model.SysUserQuaryVo;
import com.trust.privilege.facade.model.complex.MenuResVO;

/**
 * @ClassName: SystemUserRestFacade 
 * @Description: 用户模块facade接口
 * @author zyn161616@163.com
 * @version 创建时间：2017年3月17日 下午2:33:09
 */
public interface SystemUserServerFacade {
	
	/**
	 * @Title: selectUser 
	 * @Description: 按条件查询用户信息
	 * @param @param quaryVo
	 * @param @return   
	 * @return Map<String,Object>
	 */
	public Map<String, Object> selectUser(SysUserQuaryVo quaryVo);
	
	/**
	 * @Title: insertUser 
	 * @Description: 新增用户
	 * @param @param sysUserModel
	 * @param @return   
	 * @return Map<String,Object>
	 */
	public Map<String,Object> insertUser(SysUserModel sysUserModel);
	
	/**
	 * @Title: deleteUserById 
	 * @Description: 根据用户Id删除该用户信息
	 * @param @param sysUserId
	 * @param @param userName
	 * @param @return   
	 * @return Map<String,Object>
	 */
	public Map<String,Object> deleteUserById(String sysUserId,String user);
	
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
	 * @param @param sysUserModel
	 * @param @return   
	 * @return Map<String,Object>
	 */
	public Map<String,Object> updateUser(SysUserModel sysUserModel);

//-----------------------------------------------------------------
	/**
	 * @Title: getInitMsg 
	 * @Description: 通过条件取门户首页初始化数据（将系统配置成资源，用作portal页面）
	 * @param @param userName
	 * @param @param systemId
	 * @param @return   
	 * @return List<MenuResVO>
	 */
	public List<MenuResVO> getInitMsg(String userName,String systemId);

	/**
	 * @Title: getUserInfo 
	 * @Description: portal页面，左侧获取个人信息的接口
	 * @param @return   
	 * @return SysUserModel
	 */
	public SysUserModel getUserInfo(String userName);

	/**
	 * @Title: modifyPassword 
	 * @Description: portal页面通过用户名修改密码
	 * @param @param model
	 * @param @return   
	 * @return String
	 */
	public String modifyPassword(SysUserModel model);

}
