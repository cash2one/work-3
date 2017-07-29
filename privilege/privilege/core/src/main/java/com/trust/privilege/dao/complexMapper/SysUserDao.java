package com.trust.privilege.dao.complexMapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.trust.privilege.model.complex.UserDo;

/**
 * @ClassName: SysUserDao 
 * @Description: 用户模块dao
 * @author zyn161616@163.com
 * @version 创建时间：2017年4月24日 上午11:50:44
 */
public interface SysUserDao {

	/**
	 * @Title: selectUser 
	 * @Description: 多条件查询用户信息(非管理员查询用户信息)
	 * @param @param map
	 * @param @return   
	 * @return List<UserDo>
	 */
	public List<UserDo> selectUser(Map<String, Object> map);
	
	/**
	 * @Title: selectUserBySuperAdmin 
	 * @Description: 多条件查询用户信息(超级管理员查询用户)
	 * @param @param map
	 * @param @return   
	 * @return List<UserDo>
	 */
	public List<UserDo> selectUserBySuperAdmin(Map<String, Object> map);
	
	/**
	 * @Title: selectUserDetail 
	 * @Description: 通过用户Id,得到该用户详情
	 * @param @param userId
	 * @param @return   
	 * @return List<Map<String,Object>>
	 */
	public List<Map<String, Object>> selectUserDetail(String userId);
	
	/**
	 * @Title: selectUserByIdCard 
	 * @Description: 
	 * @param @param userName
	 * @param @return   
	 * @return UserDo
	 */
	public UserDo selectUserByIdCard(String userName);
	
	/**
	 * @Title: selectUserInfo 
	 * @Description: 通过用户名查询该用户信息
	 * @param @param userName
	 * @param @return   
	 * @return List<UserDo>
	 */
	public List<UserDo> selectUserInfo(String userName);
	
	/**
	 * @Title: modifyPassWord 
	 * @Description: 根据用户名，修改密码
	 * @param @param userName
	 * @param @param password
	 * @param @return   
	 * @return int
	 */
	public int modifyPassWord(@Param("userName")String userName,@Param("password")String password);
	
	/**
	 * @Title: selectRoleByUserName 
	 * @Description: 通过用户名查找该用户拥有的角色
	 * @param @param userName
	 * @param @param systemId
	 * @param @return   
	 * @return List<Map<String,Object>>
	 */
	public List<Map<String, Object>> selectRoleByUserName(@Param("userName")String userName,@Param("systemId")String systemId);

	/**
	 * @Title: selectUserIdByUserName 
	 * @Description: 通过用户名得到该用户Id
	 * @param @param userName
	 * @param @return   
	 * @return String
	 */
	public String selectUserIdByUserName(String userName);

}