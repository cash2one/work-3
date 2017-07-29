package com.trust.privilege.service;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.trust.privilege.facade.model.PrivilegeResVO;
import com.trust.privilege.model.PrivilegeRes;
import com.trust.privilege.model.PrivilegeResType;
import com.trust.privilege.model.complex.PrivilegeResDo;
import com.trust.privilege.model.complex.QueryPrivilege;
/**
 * @ClassName: PrivilegeService 
 * @Description: 页面资源Service接口
 * @author zyn161616@163.com
 * @version 创建时间：2017年3月14日 下午3:58:45
 */
public interface PrivilegeService {
	
	/**
	 * @Title: selectSysPrivilege 
	 * @Description: 根据用户名，菜单Id 查询权限资源
	 * @param @param menuId
	 * @param @param userName
	 * @param @return   
	 * @return Map<String,Object>
	 */
	public Map<String, Object> selectSysPrivilege(String userName,String menuId);
	
	/**
	 * @Title: getAllpriveleges 
	 * @Description: 获取系统资源列表
	 * @param @param queryPrivilege
	 * @param @return   
	 * @return Map<String,Object>
	 */
	public Map<String,Object> getAllpriveleges(QueryPrivilege queryPrivilege);
	
	/**
	 * @Title: insertPrivilege 
	 * @Description: 新增资源信息
	 * @param @param privilegeResVO
	 * @param @return   
	 * @return Map<String, Object>
	 */
	public Map<String, Object> insertPrivilege(PrivilegeResDo privilege);
	
	/**
	 * @Title: deletePrivilege 
	 * @Description: 通过资源Id 删除该资源
	 * @param @param privilegeResId
	 * @param @param userName
	 * @param @return   
	 * @return Map<String, Object>
	 */
	public Map<String, Object> deletePrivilege(String privilegeResId,String userName);
	
	/**
	 * @Title: updatePrivilege 
	 * @Description: 修改资源信息
	 * @param @param Privilege
	 * @param @return   
	 * @return Map<String, Object>
	 */
	public Map<String, Object> updatePrivilege(PrivilegeResDo Privilege);
	
	/**
	 * @Title: getAllResType 
	 * @Description: 查询所有的页面资源类型
	 * @param @return   
	 * @return List<PrivilegeResTypeVO>
	 */
	public List<PrivilegeResType> getAllResType();
	
	/**
	 * @Title: selectSysValidatePrivilege 
	 * @Description: 根据用户名，系统CD，查询需要拦截的url
	 * @param systemId
     * @param userName
	 * @param @return   
	 * @return Set<String>
	 */
	public Set<String> selectSysValidatePrivilege(String systemId,String userName);
	
	/**
	 * @Title: getPrivilege 
	 * @Description: 通过条件获取资源数据
	 * @param @param resName
	 * @param @param privilegeUrl
	 * @param @param systemPlatformCD
	 * @param @return   
	 * @return List<PrivilegeRes>
	 */
	public List<PrivilegeRes> getPrivilege(String resName,String privilegeUrl,String systemPlatformCD);
	
	/**
	 * @Title: getPrivilegeByMenuIds 
	 * @Description: 在新建角色时，根据多个id查询页面有效资源
	 * @param @param privilegeResVO
	 * @param @return   
	 * @return List<PrivilegeRes>
	 */
	public List<PrivilegeRes> getPrivilegeByMenuIds(PrivilegeResVO privilegeResVO);
	//-----------------------------------------------------------------
	
	/**插入页面资源类型*/
//	public Integer insertResType(PrivilegeResType privilegeResType);

	
}
