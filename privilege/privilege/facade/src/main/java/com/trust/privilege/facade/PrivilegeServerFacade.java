package com.trust.privilege.facade;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.trust.privilege.facade.model.PrivilegeResTypeVO;
import com.trust.privilege.facade.model.PrivilegeResVO;
import com.trust.privilege.facade.model.complex.QueryPrivilegeVO;
/**
 * @ClassName: PrivilegeServerFacade 
 * @Description: 页面资源facade接口
 * @author zyn161616@163.com
 * @version 创建时间：2017年3月14日 下午3:57:04
 */
public interface PrivilegeServerFacade {
	
	/**
	 * @Title: selectSysPrivilege 
	 * @Description: 根据用户名，菜单CD 查询权限资源
	 * @param @param menuId
	 * @param @param userName
	 * @param @return   
	 * @return Map<String,Object>
	 */
	Map<String, Object> selectSysPrivilege(String userName,String menuId);
	
	/**
	 * @Title: getAllPrivileges 
	 * @Description: 获取系统资源列表
	 * @param queryPrivilegeVO  
	 * @param @return   
	 * @return Map<String,Object>
	 */
	Map<String,Object> getAllPrivileges(QueryPrivilegeVO queryPrivilegeVO);
	
	/**
	 * @Title: addPrivilegeRes 
	 * @Description: 新增资源信息
	 * @param @param privilegeResVO
	 * @param @return   
	 * @return Map<String,Object>
	 */
	Map<String,Object> addPrivilegeRes(PrivilegeResVO privilegeVO);
	
	/**
	 * @Title: deleteprivilegeRes 
	 * @Description: 删除一个资源
	 * @param @param privilegeResId
	 * @param @param userName
	 * @param @return   
	 * @return Map<String,Object>
	 */
	Map<String,Object> deleteprivilegeRes(String privilegeResId,String user);
	
	/**
	 * @Title: updatePrivilegeRes 
	 * @Description: 修改权限信息						
	 * @param @param privilegeResVO
	 * @param @return   
	 * @return Map<String,Object>
	 */
	Map<String,Object> updatePrivilegeRes(PrivilegeResVO privilegeResVO);
	
	/**
	 * @Title: getAllResType 
	 * @Description: 查询所有的页面资源类型
	 * @param @return   
	 * @return List<PrivilegeResTypeVO>
	 */
	List<PrivilegeResTypeVO> getAllResType();
	 
	/**
	 * @Title: getPrivilegeByMenuIds 
	 * @Description: 在新建角色时，根据多个id查询页面有效资源
	 * @param @param privilegeResVO
	 * @param @return   
	 * @return List<PrivilegeResVO
	 */
	List<PrivilegeResVO> getPrivilegeByMenuIds(PrivilegeResVO privilegeResVO);	
	
	/**
	 * @Title: selectSysValidatePrivilege 
	 * @Description: 根据用户名，系统CD，查询需要拦截的url
	 * @param systemId
     * @param userId
	 * @param @return   
	 * @return Set<String>
	 */
	Set<String> selectSysValidatePrivilege(String systemId,String userName);
	
	//-----------------------------------------------------------------
    
	/**根据用户名，系统id查询
	 * @param userName
	 * @param systemId
	 * @return
	 */
//	PrivilegeResourceVo  getPrivilegeWithRole(String userName,String systemId);
	
	/**插入页面资源类型*/
//	Integer insertResType(PrivilegeResTypeVO privilegeResTypeVO);
	
}
