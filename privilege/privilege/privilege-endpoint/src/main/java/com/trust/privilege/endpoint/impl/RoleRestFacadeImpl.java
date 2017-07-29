package com.trust.privilege.endpoint.impl;

import java.util.Map;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.trust.privilege.endpoint.facade.RoleRestFacade;
import com.trust.privilege.endpoint.response.RestSampleFacadeResp;
import com.trust.privilege.facade.RoleServerFacade;
import com.trust.privilege.facade.model.RoleVO;
import com.trust.privilege.facade.model.complex.QueryRoleVO;

/**
 * @ClassName: RoleRestFacade 
 * @Description: 角色模块Rest接口实现
 * @author zyn161616@163.com
 * @version 创建时间：2017年3月14日 上午10:35:12
 */
public class RoleRestFacadeImpl implements RoleRestFacade {

	/**创建日志对象*/
	private static final Logger logger = LoggerFactory.getLogger(RoleRestFacadeImpl.class);
			
	/**依赖注入角色facade*/
	@Resource
	private RoleServerFacade roleServerFacade;
	
	/**
	 * @Title: getAllRoles 
	 * @Description: 根据条件查询角色信息
	 * @param @param queryRoleVO
	 * @param @param user
	 * @param @return   
	 * @return RestSampleFacadeResp<Map<String,Object>>
	 */
	@Override
	public RestSampleFacadeResp<Map<String,Object>> getAllRoles(QueryRoleVO queryRoleVO,String userName) {
		//创建返回独享
		RestSampleFacadeResp<Map<String,Object>> resp = new RestSampleFacadeResp<>(false);
				
		try {
			//设置用户名称
			queryRoleVO.setUserName(userName);
			//设置返回数据
			resp.setData(roleServerFacade.getAllRoles(queryRoleVO));
			//成功返回ture
			resp.setSuccess(true);
		} catch (Exception e) {
			//出现异常日志记录
			logger.info("查询角色信息失败", e);
			//设置错误码
			resp.setErrorCode("201");
			//设置错误信息
			resp.setResultMsg("系统内部异常，查询角色信息失败");
		}
			return resp;
	}
	
	/**
	 * @Title: insertRole 
	 * @Description: 新增角色信息
	 * @param @param roleVO
	 * @param @param user
	 * @param @return   
	 * @return RestSampleFacadeResp<String>
	 */
	@Override
	public RestSampleFacadeResp<String> insertRole(RoleVO roleVO,String userName) {
		//创建返回对象
		RestSampleFacadeResp<String> resp = new RestSampleFacadeResp<>(false);
		
		try {
			//设置创建人
			roleVO.setCreateUserId(userName);
			//获取返回数据
			Map<String,Object> result = roleServerFacade.insertRole(roleVO);
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
			logger.info("系统内部异常，新增角色失败", e);
			//设置返回码
			resp.setErrorCode("201");
			//设置返回信息
			resp.setResultMsg("系统内部异常，插入角色失败");
		}
			return resp;
	}
	
	/**
	 * @Title: deleteRoleByRoleId 
	 * @Description: 通过角色Id删除该角色数据
	 * @param @param roleId
	 * @param @param user
	 * @param @return   
	 * @return RestSampleFacadeResp<String>
	 */
	@Override
	public RestSampleFacadeResp<String> deleteRoleByRoleId(String roleId,String userName) {
		//创建返回对象
		RestSampleFacadeResp<String> resp = new RestSampleFacadeResp<>(false);
				
		try {
			//获取返回数据
			Map<String,Object> result = roleServerFacade.deleteRoleByRoleId(roleId,userName);
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
			logger.info("删除角色失败", e);
			//设置返回码
			resp.setErrorCode("201");
			//设置返回数据
			resp.setResultMsg("系统内部异常，删除角色失败");
		}
			return resp;
	}
	
	/**
	 * @Title: getAllResourceByRoleId 
	 * @Description: 获取当前角色下的所有菜单和页面资源(预修改)
	 * @param @param roleId
	 * @param @return   
	 * @return RestSampleFacadeResp<Map<String,Object>>
	 */
	@Override
	public RestSampleFacadeResp<Map<String,Object>> getAllResourceByRoleId(String roleId) {
		//创建返回对象
		RestSampleFacadeResp<Map<String,Object>> resp = new RestSampleFacadeResp<>(false);
				
		try {
			//设置返回数据
			resp.setData(roleServerFacade.getAllResourceByRoleId(roleId));
			//成功返回true
			resp.setSuccess(true);
		} catch (Exception e) {
			logger.info("查询该角色信息失败", e);
			resp.setErrorCode("201");
			resp.setResultMsg("系统内部异常，查询该角色信息失败");
		}
		return resp;
	}
	
	/**
	 * @Title: updateRole 
	 * @Description: 修改角色信息
	 * @param @param roleVO
	 * @param @param userName
	 * @param @return   
	 * @return RestSampleFacadeResp<>
	 */
	@Override
	public RestSampleFacadeResp<String> updateRole(RoleVO roleVO,String userName) {
		//创建返回独享
		RestSampleFacadeResp<String> resp = new RestSampleFacadeResp<>(false);
				
		try {
			//设置当前操作人
			roleVO.setCreateUserId(userName);
			//获取返回数据
			Map<String,Object> result = roleServerFacade.updateRole(roleVO);
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
			resp.setErrorCode("201");
			resp.setResultMsg("系统内部异常，修改角色信息失败");
			logger.info(this.getClass().getName(), e);
		}
		return resp;

	}
	
	/**
	 * @Title: getRoleByRoleId 
	 * @Description: 根据主键查询该角色信息
	 * @param @param roleId
	 * @param @return   
	 * @return RestSampleFacadeResp<RoleVO>
	 */
	@Override
	public RestSampleFacadeResp<RoleVO> getRoleByRoleId(String roleId) {
		//创建返回对象
		RestSampleFacadeResp<RoleVO> resp = new RestSampleFacadeResp<>(false);
		try {
			//设置返回数据
			resp.setData(roleServerFacade.getRoleByRoleId(roleId));
			//成功返回true
			resp.setSuccess(true);
		} catch (Exception e) {
			//出现异常，日志记录
			logger.info("通过主键获取该角色信息失败", e);
			//设置错误返回码
			resp.setErrorCode("201");
			//设置返回信息
			resp.setResultMsg("通过主键获取该角色信息失败");
		}
			return resp;
	}

}
