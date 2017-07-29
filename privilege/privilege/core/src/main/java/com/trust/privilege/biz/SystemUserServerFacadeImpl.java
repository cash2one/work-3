package com.trust.privilege.biz;



import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.trust.privilege.convert.MenuConvertUtil;
import com.trust.privilege.convert.SysUserConvertUtil;
import com.trust.privilege.facade.SystemUserServerFacade;
import com.trust.privilege.facade.model.SysUserModel;
import com.trust.privilege.facade.model.SysUserQuaryVo;
import com.trust.privilege.facade.model.complex.MenuResVO;
import com.trust.privilege.service.SystemUserServcie;

/**
 * @ClassName: SystemUserRestFacade 
 * @Description: 用户模块facade接口实现
 * @author zyn161616@163.com
 * @version 创建时间：2017年3月17日 下午2:33:09
 */
public class SystemUserServerFacadeImpl implements SystemUserServerFacade {
	
	/**依赖注入用户Service*/
	@Resource
	private SystemUserServcie sysUserService;

	/**
	 * @Title: selectUser 
	 * @Description: 按条件查询用户信息
	 * @param @param quaryVo
	 * @param @return   
	 * @return Map<String,Object>
	 */
	@Override
	public Map<String, Object> selectUser(SysUserQuaryVo quaryVo) {
		return sysUserService.selectUser(quaryVo);
	}
	
	/**
	 * @Title: insertUser 
	 * @Description: 新增用户
	 * @param @param sysUserModel
	 * @param @param userName
	 * @param @return   
	 * @return Map<String,Object>
	 */
	@Override
	public Map<String,Object> insertUser(SysUserModel sysUserModel) {
		return sysUserService.insertUser(
				SysUserConvertUtil.userVOToModel(sysUserModel));
	}
	
	/**
	 * @Title: deleteUserById 
	 * @Description: 根据用户Id删除该用户信息
	 * @param @param sysUserId
	 * @param @param userName
	 * @param @return   
	 * @return Map<String,Object>
	 */
	@Override
	public Map<String,Object> deleteUserById(String sysUserId,String userName) {
		return sysUserService.deleteUserById(sysUserId,userName);
	}
	
	/**
	 * @Title: selectUpdDis 
	 * @Description: 修改用户时查询所需的数据（预修改）
	 * @param @param userId
	 * @param @param userName
	 * @param @return  
	 * @return Map<String, Object>
	 */
	@Override
	public Map<String, Object> selectUpdDis(String userId,String userName) {
		return sysUserService.selectUpdDis(userId,userName);
	}
	
	/**
	 * @Title: updateUser 
	 * @Description: 修改用户信息
	 * @param @param sysUserModel
	 * @param @return   
	 * @return Map<String,Object>
	 */
	@Override
	//修改用户
	public Map<String,Object> updateUser(SysUserModel sysUserModel) {
		return sysUserService.updateUser(
					SysUserConvertUtil.userVOToModel(sysUserModel));
	}
	//-----------------------------------------------------------------
	/**
	 * @Title: getUserInfo 
	 * @Description: potal页面获取个人信息
	 * @param @param userName
	 * @param @return   
	 * @return SysUserModel
	 */
	@Override
	public SysUserModel getUserInfo(String userName) {
		return SysUserConvertUtil.sysUserDoToUserModel(sysUserService.getUserInfo(userName));
	}
	
	/**
	 * @Title: getInitMsg 
	 * @Description: 通过条件取门户首页初始化数据（将系统配置成资源，用作portal页面）
	 * @param @param userName
	 * @param @param systemId
	 * @param @return   
	 * @return List<MenuResVO>
	 */
	@Override
	public List<MenuResVO> getInitMsg(String userName, String systemId) {
		return MenuConvertUtil.menuDoToMenuVo(
						sysUserService.getInitMsg(userName, systemId));
	}
	
	/**
	 * @Title: modifyPassword 
	 * @Description: portal页面根据用户名修改密码
	 * @param @param model
	 * @param @return   
	 * @return String
	 */
	@Override
	public String modifyPassword(SysUserModel model) {
		return sysUserService.modifyPassword(model);
	}
	
}
