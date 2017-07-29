package com.trust.privilege.endpoint.impl;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import com.trust.privilege.endpoint.facade.PrivilegeRestFacade;
import com.trust.privilege.endpoint.response.RestSampleFacadeResp;
import com.trust.privilege.facade.PrivilegeServerFacade;
import com.trust.privilege.facade.model.PrivilegeResTypeVO;
import com.trust.privilege.facade.model.PrivilegeResVO;
import com.trust.privilege.facade.model.complex.QueryPrivilegeVO;

/**
 * @ClassName: PrivilegeRestFacade 
 * @Description: 页面资源Rest接口实现
 * @author zyn161616@163.com
 * @version 创建时间：2017年3月14日 下午3:56:03
 */
public class PrivilegeRestFacadeImpl implements PrivilegeRestFacade {
	
	/**创建日志对象*/
	private static final Logger logger =LogManager.getLogger(PrivilegeRestFacadeImpl.class.getName());

	/**注入页面资源facade*/
	@Resource
	private PrivilegeServerFacade privilegeServerFacade;
	
	/**
	 * @Title: selectSysPrivilege 
	 * @Description: 根据用户名，菜单CD 查询权限资源
	 * @param @param menuId
	 * @param @param userName
	 * @param @return   
	 * @return RestSampleFacadeResp<Map<String,Object>>
	 */
	@Override
	public RestSampleFacadeResp<Map<String, Object>> selectSysPrivilege(String menuId,String userName) {
		//创建返回对象
		RestSampleFacadeResp<Map<String, Object>> resp = new RestSampleFacadeResp<>(false);
				
		try {
			//设置返回信息
			resp.setData(privilegeServerFacade.selectSysPrivilege( menuId,userName));
			//成功返回true
			resp.setSuccess(true);
		} catch (Exception e) {
			//出现问题，日志记录
			logger.info("查询权限资源信息失败", e);
			//设置错误码
			resp.setErrorCode("201");
			//设置错误信息
			resp.setResultMsg("系统内部异常，查询权限资源信息失败");
		}
			return resp;
	}
	
	/**
	 * @Title: getAllPrivilege 
	 * @Description: 获取系统资源列表
	 * @param @return   
	 * @return restSamleFacadeResp<Map<String,Object>>
	 */
	@Override
	public RestSampleFacadeResp<Map<String,Object>> getAllprivileges(QueryPrivilegeVO queryPrivilegeVO,String userName) {
		//创建返回对象
		RestSampleFacadeResp<Map<String,Object>> resp = new RestSampleFacadeResp<>(false);
		
		try {
			//设置用户名
			queryPrivilegeVO.setUserName(userName);
			//设置返回数据
			resp.setData(privilegeServerFacade.getAllPrivileges(queryPrivilegeVO));
			//成功返回true
			resp.setSuccess(true);
			
		} catch (Exception e) {
			//出现异常，日志记录
			logger.info("查询资源失败", e);
			//设置返回码
			resp.setErrorCode("201");
			//设置返回信息
			resp.setResultMsg("系统内部异常，查询权限分页失败");
		}
			return resp;
	}

	/**
	 * @Title: insertPrivilege 
	 * @Description: 新增资源信息
	 * @param @param privilegeResVO
	 * @param @param userName
	 * @param @return   
	 * @return RestSampleFacadeResp<String>
	 */
	@Override
	public RestSampleFacadeResp<String> insertPrivilege(PrivilegeResVO privilegeVO,String userName) {
		//创建返回对象
		RestSampleFacadeResp<String> resp = new RestSampleFacadeResp<>(false);
		try {
			//设置创建人
			privilegeVO.setCreateUserId(userName);
			//获取返回数据
			Map<String,Object> result = privilegeServerFacade.addPrivilegeRes(privilegeVO);
			//如果存在错误数据
			if(result.get("fail") != null){
				//设置返回信息
				resp.setResultMsg((String)result.get("fail"));
			}else{
				//设置返回信息
				resp.setData((String)result.get("success"));
				//成功返回true
				resp.setSuccess(true);
			}
		} catch (Exception e) {
			//出现异常，日志记录
			logger.info("添加权限信息失败", e);
			//设置错误码
			resp.setErrorCode("201");
			//设置返回信息
			resp.setResultMsg("系统内部异常，添加权限信息失败");
		}
			return resp;
	}

	/**
	 * @Title: deletePrivilege 
	 * @Description: 删除一个资源
	 * @param @param privilegeResId
	 * @param @param userName
	 * @param @return   
	 * @return RestSampleFacadeResp<String>
	 */
	public RestSampleFacadeResp<String> deletePrivilege(String privilegeResId,String userName) {
		//创建返回对象
		RestSampleFacadeResp<String> resp = new RestSampleFacadeResp<>(false);
		try {
			//获取返回数据
			Map<String,Object> result = privilegeServerFacade.deleteprivilegeRes(privilegeResId,userName);
			//如果存在错误数据
			if(result.get("fail") != null){
				//设置返回信息
				resp.setResultMsg((String)result.get("fail"));
			}else{
				//设置返回信息
				resp.setData((String)result.get("success"));
				//成功返回true
				resp.setSuccess(true);
			}
			
		} catch (Exception e) {
			//出现异常，日志记录
			logger.info("删除资源信息失败", e);
			//设置错误码
			resp.setErrorCode("201");
			//设置返回信息
			resp.setResultMsg("系统内部异常，删除资源信息失败");
		}
			return resp;
	}

	/**
	 * @Title: updatePrivilege 
	 * @Description: 修改权限信息						
	 * @param @param privilegeResVO
	 * @param @param userName
	 * @param @return   
	 * @return RestSampleFacadeResp<String>
	 */
	@Override
	public RestSampleFacadeResp<String> updatePrivilege(PrivilegeResVO privilegeResVO,String userName) {
		//创建返回对象
		RestSampleFacadeResp<String> resp = new RestSampleFacadeResp<>(false);
		try {
			//设置当前操作人
			privilegeResVO.setCreateUserId(userName);
			//获取返回数据
			Map<String,Object> result = privilegeServerFacade.updatePrivilegeRes(privilegeResVO);
			//如果存在错误数据
			if(result.get("fail") != null){
				//设置返回信息
				resp.setResultMsg((String)result.get("fail"));
			}else{
				//设置返回信息
				resp.setData((String)result.get("success"));
				//成功返回true
				resp.setSuccess(true);
			}
		} catch (Exception e) {
			//出现异常，日志记录
			logger.info("更新资源信息失败", e);
			//设置返回码
			resp.setErrorCode("201");
			//设置返回信息
			resp.setResultMsg("系统内部异常，更新资源信息失败");
		}
			return resp;
	}
	
	/**
	 * @Title: getAllResType 
	 * @Description: 查询所有的页面资源类型
	 * @param @return   
	 * @return RestSampleFacadeResp<List<PrivilegeResTypeVO>>
	 */
	@Override
	public RestSampleFacadeResp<List<PrivilegeResTypeVO>> getAllResType() {
		//创建返回对象
		RestSampleFacadeResp<List<PrivilegeResTypeVO>> resp = new RestSampleFacadeResp<>(false);
				
		try {
			//设置返回数据
			resp.setData(privilegeServerFacade.getAllResType());
			//成功返回true
			resp.setSuccess(true);
		} catch (Exception e) {
			//出现问题，日志记录
			logger.info("查询页面资源类型失败", e);
			//设置错误返回码
			resp.setErrorCode("201");
			//设置返回信息
			resp.setResultMsg("系统内部异常，查询页面资源类型失败");
		}
			return resp;
	}

	/**
	 * @Title: getPrivilegeByMenuIds 
	 * @Description: 在新建角色时，根据多个id查询页面有效资源	
	 * @param @param privilegeResVO
	 * @param @return   
	 * @return RestSampleFacadeResp<List<PrivilegeResVO>>
	 */
	@Override
	public RestSampleFacadeResp<List<PrivilegeResVO>> getPrivilegeByMenuIds(PrivilegeResVO privilegeResVO) {
		//创建返回对象	
		RestSampleFacadeResp<List<PrivilegeResVO>> resp = new RestSampleFacadeResp<>(false);
				
		try {
			//设置返回数据
			resp.setData(privilegeServerFacade.getPrivilegeByMenuIds(privilegeResVO));
			//成功返回ture
			resp.setSuccess(true);
		} catch (Exception e) {
			//出现异常日志记录
			logger.info("查询权限资源失败", e);
			//设置返回码
			resp.setErrorCode("201");
			//设置返回信息
			resp.setResultMsg("系统内部异常，查询权限资源信息失败");
		}
			return resp;
	}
	//-----------------------------------------------------------------
	
	
//	@Override
//	public RestSampleFacadeResp<List<String>> selectUserSysPrivilege(String userName, String systemId) {
//		RestSampleFacadeResp<List<String>> validateUrls=null;
//		try{
//		List<String> urlList=privilegeServerFacade.selectSysValidatePrivilege(systemId, userName);
//		validateUrls=new RestSampleFacadeResp<List<String>>(urlList, true);
//		}catch(Exception e){
//			logger.info(this.getClass().getName(),e);
//			validateUrls=new RestSampleFacadeResp<>(false);
//			validateUrls.setErrorCode("201");;
//			validateUrls.setResultMsg("系统异常，查询失败");
//		}
//		return validateUrls;
//	}
	
//	@Override
//	public RestSampleFacadeResp<Integer> insertResType(
//			PrivilegeResTypeVO privilegeResTypeVO,String user) {
//		RestSampleFacadeResp<Integer> resp = new RestSampleFacadeResp<>(false);
//		try {
//			Integer psId = privilegeServerFacade.insertResType(privilegeResTypeVO);
//			if (null != psId && psId > 0) {
//				resp.setData(psId);
//				resp.setSuccess(true);
//				resp.setResultMsg("插入资源类型信息成功");
//			}
//		} catch (Exception e) {
//			resp.setErrorCode("201");
//			resp.setResultMsg("系统内部异常，更新权限失败");
//			logger.info(this.getClass().getName(), e);
//		}
//		return resp;
//		
//	}

}
