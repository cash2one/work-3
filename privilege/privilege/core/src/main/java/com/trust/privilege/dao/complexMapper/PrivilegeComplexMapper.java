package com.trust.privilege.dao.complexMapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.trust.privilege.model.PrivilegeRes;
/**
 * @ClassName: PrivilegeComplexMapper 
 * @Description: 资源模块dao
 * @author zyn161616@163.com
 * @version 创建时间：2017年4月24日 上午11:28:28
 */
public interface PrivilegeComplexMapper {

	/**
	 * @Title: getAllPrivilege 
	 * @Description:  非超管身份根据条件查询所有的权限资源（除菜单外的资源）
	 * @param @param queryMap
	 * @param @return   
	 * @return List<Map<String,Object>>
	 */
	List<Map<String, Object>> getAllPrivilege(Map<String, Object> queryMap);
	
	/**
	 * @Title: getAllPrivilegeBySuperAdmin 
	 * @Description: 超级管理员的身份查询页面权限资源
	 * @param @param queryMap
	 * @param @return   
	 * @return List<Map<String,Object>>
	 */
	List<Map<String, Object>> getAllPrivilegeBySuperAdmin(Map<String, Object> queryMap);
			
	/**
	 * @Title: getPrivilegeByMenuIds 
	 * @Description: 根据多个菜单Id查询菜单下的资源
	 * @param @param list
	 * @param @param stateCd
	 * @param @return   
	 * @return List<PrivilegeRes>
	 */
	public List<PrivilegeRes> getPrivilegeByMenuIds(@Param("list") List<String> list, @Param("stateCd") String stateCd);
	
	/**
	 * @Title: selectSysPrivilege 
	 * @Description: 根据用户名，菜单Id查询资源
	 * @param @param map
	 * @param @return   
	 * @return List<Map<String,Object>>
	 */
	public List<Map<String, Object>> selectSysPrivilege(Map<String, Object> map);
	
	/**
	 * @Title: selectValidUrlByUserName 
	 * @Description: 通过用户名获取该用户可访问ur
	 * @param @param Name
	 * @param @param systemId
	 * @param @return   
	 * @return List<String>
	 */
	public List<String> selectValidUrlByUserName(@Param("name") String Name,@Param("systemId") String systemId);
	
	/**
	 * @Title: selectAllUrlBySys 
	 * @Description: 跟据系统CD查询所有的url
	 * @param @param systemId
	 * @param @return   
	 * @return List<String>
	 */
	public List<String> selectAllUrlBySys(String systemId);
	
	/**
	 * @Title: selectSystemceByUserName 
	 * @Description: 通过条件得到该用户所拥有的系统（将系统配置成资源，用作portal页面）
	 * @param @param queryMap
	 * @param @return   
	 * @return List<Map<String,Object>>
	 */
	public List<Map<String,Object>> selectSystemceByUserName(Map<String,Object> queryMap);

}
