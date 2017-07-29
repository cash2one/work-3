package com.trust.privilege.biz;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import com.trust.privilege.convert.PrivilegeConvertUtil;
import com.trust.privilege.convert.ResTypeConvertUtil;
import com.trust.privilege.facade.PrivilegeServerFacade;
import com.trust.privilege.facade.model.PrivilegeResTypeVO;
import com.trust.privilege.facade.model.PrivilegeResVO;
import com.trust.privilege.facade.model.complex.QueryPrivilegeVO;
import com.trust.privilege.service.PrivilegeService;

/**
 * @ClassName: PrivilegeServerFacade 
 * @Description: 页面资源facade接口实现
 * @author zyn161616@163.com
 * @version 创建时间：2017年3月14日 下午3:57:04
 */
public class PrivilegeServerFacadeImpl implements PrivilegeServerFacade{
	
	/**注入资源service*/
	@Resource
	private PrivilegeService privilegeService;
	
	/**
	 * @Title: selectSysPrivilege 
	 * @Description: 根据用户名，菜单CD 查询权限资源
	 * @param @param menuId
	 * @param @param userName
	 * @param @return   
	 * @return Map<String,Object>
	 */
	@Override
	public Map<String, Object> selectSysPrivilege( String menuId,String userName) {
		return privilegeService.selectSysPrivilege( menuId,userName);
	}
	
	/**
	 * @Title: getAllPrivileges 
	 * @Description: 获取系统资源列表
	 * @param queryPrivilegeVO  
	 * @param @return   
	 * @return Map<String,Object>
	 */
	@Override
	public Map<String,Object> getAllPrivileges(QueryPrivilegeVO queryPrivilegeVO) {
		return privilegeService.getAllpriveleges(
				PrivilegeConvertUtil.queryPrivilegeVOConvert(queryPrivilegeVO));
	}
	
	/**
	 * @Title: addPrivilegeRes 
	 * @Description: 新增资源信息
	 * @param @param privilegeResVO
	 * @param @return   
	 * @return Map<String,Object>
	 */
	@Override
	public Map<String,Object> addPrivilegeRes(PrivilegeResVO privilegeResVO) {
		return privilegeService.insertPrivilege(
				PrivilegeConvertUtil.convertPrivilegeResDoToVO(privilegeResVO));
	}

	/**
	 * @Title: deleteprivilegeRes 
	 * @Description: 删除一个资源
	 * @param @param privilegeResId
	 * @param @param userName
	 * @param @return   
	 * @return Map<String,Object>
	 */
	@Override
	public Map<String,Object> deleteprivilegeRes(String privilegeResId,String userName) {
		return privilegeService.deletePrivilege(privilegeResId,userName);
	}

	/**
	 * @Title: updatePrivilegeRes 
	 * @Description: 修改权限信息						
	 * @param @param privilegeResVO
	 * @param @param userName
	 * @param @return   
	 * @return Map<String,Object>
	 */
	@Override
	public Map<String,Object> updatePrivilegeRes(PrivilegeResVO privilegeResVO){
		return privilegeService.updatePrivilege(
				PrivilegeConvertUtil.convertPrivilegeResDoToVO(privilegeResVO));
	}
	
	/**
	 * @Title: getAllResType 
	 * @Description: 查询所有的页面资源类型
	 * @param @return   
	 * @return List<PrivilegeResTypeVO>
	 */
	@Override
	public List<PrivilegeResTypeVO> getAllResType() {
		return ResTypeConvertUtil.convertListDoToListVo(
								privilegeService.getAllResType());
	}
	
	/**
	 * @Title: getPrivilegeByMenuIds 
	 * @Description: 在新建角色时，根据多个id查询页面有效资源
	 * @param @param privilegeResVO
	 * @param @return   
	 * @return List<PrivilegeResVO	
	 */
	@Override
	public List<PrivilegeResVO> getPrivilegeByMenuIds(PrivilegeResVO privilegeResVO) {
		return PrivilegeConvertUtil.convertDOListToVOList(
				privilegeService.getPrivilegeByMenuIds(privilegeResVO));
		
	}
	
	/**
	 * @Title: selectSysValidatePrivilege 
	 * @Description: 根据用户名，系统CD，查询需要拦截的url
	 * @param systemId
     * @param userId
	 * @param @return   
	 * @return Set<String>
	 */
	@Override
	public Set<String> selectSysValidatePrivilege(String systemId, String userName) {
		return privilegeService.selectSysValidatePrivilege(systemId, userName);
	}
	//-----------------------------------------------------------------
	
	

//	@Override
//	public PrivilegeResourceVo getPrivilegeWithRole(String userName,String systemId) {
//		return null;
//	}

//	@Override
//	public Integer insertResType(PrivilegeResTypeVO privilegeResTypeVO) {
//		return privilegeService.insertResType(ResTypeConvertUtil.convertVOToDo(privilegeResTypeVO));
//	}
	
}
