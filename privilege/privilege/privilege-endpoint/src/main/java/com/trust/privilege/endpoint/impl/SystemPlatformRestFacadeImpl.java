package com.trust.privilege.endpoint.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.trust.privilege.endpoint.facade.SystemPlatformRestFacade;
import com.trust.privilege.endpoint.response.RestSampleFacadeResp;
import com.trust.privilege.facade.SystemPlatformServerFacade;
import com.trust.privilege.facade.model.SystemConfigVO;
import com.trust.privilege.facade.model.SystemPlatformVO;
import com.trust.privilege.facade.model.complex.QuerySystemPlatformVO;
/**
 * @ClassName: SystemPlatformRestFacade 
 * @Description: 系统模块Rest接口实现
 * @author zyn161616@163.com
 * @version 创建时间：2017年3月14日 上午10:40:58
 */
public class SystemPlatformRestFacadeImpl implements SystemPlatformRestFacade{
	
	/**创建日志对象*/
	private static final Logger logger = LoggerFactory.getLogger(SystemPlatformRestFacadeImpl.class);
			
	/**依赖注入系统facade层*/
	@Resource
	private SystemPlatformServerFacade systemPlatformServerFacade; 
	
	/**
	 * @Title: insertSystem 
	 * @Description: 新增系统信息
	 * @param @param querySystemPlatformVO
	 * @param @param userName
	 * @param @return   
	 * @return RestSampleFacadeResp<String>
	 */
	@Override
	public RestSampleFacadeResp<String> insertSystem(SystemPlatformVO systemPlatformVO, String userName) {
		//创建返回对象
		RestSampleFacadeResp<String> resp=new RestSampleFacadeResp<>(false);
		try{
			//创建人改为当前登录名
			systemPlatformVO.setCreateUserId(userName);
			//获取返回数据
			Map<String,Object> result = systemPlatformServerFacade.insertSystem(systemPlatformVO);
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
		}catch(Exception e){
			//出现异常，日志记录
			logger.info("添加系统失败", e);
			//设置返回码
			resp.setErrorCode("201");
			//设置返回信息
			resp.setResultMsg("系统内部异常，添加系统失败");
		}
			return resp;
	}
	
	/**
	 * @Title: deleteSystem 
	 * @Description: 通过系统CD删除该系统信息
	 * @param @param systemId
	 * @param @param userName
	 * @param @return   
	 * @return RestSampleFacadeResp<String>
	 */
	@Override
	public RestSampleFacadeResp<String> deleteSystem(String systemId,String userName) {
		//创建返回对象
		RestSampleFacadeResp<String> resp = new RestSampleFacadeResp<>(false);
		try {
			//获取返回数据
			Map<String,Object> result = systemPlatformServerFacade.deleteSystemPlateForm(systemId,userName);
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
			logger.info("删除系统信息失败", e);
			//设置返回码
			resp.setErrorCode("201");
			//设置返回信息
			resp.setResultMsg("系统内部异常，删除系统信息失败");
		}
			return resp;
	}
	
	/**
	 * @Title: updateSystem 
	 * @Description: 修改系统信息
	 * @param @param systemPlatformVO
	 * @param @param userName
	 * @param @return   
	 * @return RestSampleFacadeResp<String>
	 */
	@Override
	public RestSampleFacadeResp<String> updateSystem(SystemPlatformVO systemPlatformVO,String userName) {
		//创建返回对象
		RestSampleFacadeResp<String> resp = new RestSampleFacadeResp<>(false);
		try {
			//设置当前操作人
			systemPlatformVO.setCreateUserId(userName);
			//获取返回数据
			Map<String,Object> result = systemPlatformServerFacade.upadateSystemPlatform(systemPlatformVO);
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
			logger.info("修改系统信息失败", e);
			//设置返回码
			resp.setErrorCode("201");
			//设置返回信息
			resp.setResultMsg("系统内部异常，修改系统信息失败");
		}
			return resp;
	}
	
	/**
	 * @Title: selectSystemPlatform 
	 * @Description: 查询所有系统平台
	 * @param @param querySystemPlatformVO
	 * @param @param userName
	 * @param @return   
	 * @return RestSampleFacadeResp<Map<String,Object>>
	 */
	@Override
	public RestSampleFacadeResp<Map<String,Object>> selectSystemPlatform(QuerySystemPlatformVO querySystemPlatformVO,String userName){
		//创建返回对象
		RestSampleFacadeResp<Map<String,Object>> resp = new RestSampleFacadeResp<>(false);
									
		try {
			//设置用户名
			querySystemPlatformVO.setUserName(userName);
			//设置返回数据
			resp.setData(systemPlatformServerFacade.selectSystemPlatform(querySystemPlatformVO));
			//成功返回true
			resp.setSuccess(true);
		} catch (Exception e) {
			//出现异常，日志记录
			logger.info("查询系统平台失败", e);
			//设置返回码
			resp.setErrorCode("201");
			//设置返回信息
			resp.setResultMsg("系统内部异常，查询系统平台失败");
		}
			return resp;
	}
	
	/**
	 * @Title: getAllSystem 
	 * @Description: 查询日志时候，所属系统的接口
	 * @param @return   
	 * @return RestSampleFacadeResp<List<SystemPlatformVO>>
	 */
	@Override
	public RestSampleFacadeResp<List<SystemPlatformVO>> getAllSystem() {
		//创建返回对象
		RestSampleFacadeResp<List<SystemPlatformVO>> resp = new RestSampleFacadeResp<>(false);
				
		try {
			//设置返回数据
			resp.setData(systemPlatformServerFacade.getAllSystem());
			//成功返回true
			resp.setSuccess(true);
		} catch (Exception e) {
			//出现异常，日志记录
			logger.info("加载日志页面时，查询系统列表失败", e);
			//设置返回码
			resp.setErrorCode("201");
			//设置返回信息
			resp.setResultMsg("系统内部异常，查询系统列表失败");
		}
			return resp;
	}
	
	//-----------------------------------------------------------------
	/**
	 * @Title: getAllOffenUseOption 
	 * @Description: 查询系统常用功能列表
	 * @param @param systemConfigVO
	 * @param @return   
	 * @return RestSampleFacadeResp<Map<String,Object>>
	 */
	@Override
	public RestSampleFacadeResp<Map<String,Object>> getAllOffenUseOption(SystemConfigVO systemConfigVO) {
			
		//创建返回对象
		RestSampleFacadeResp<Map<String,Object>> resp = new RestSampleFacadeResp<>(false);
				
		try {
			//设置返回数据
			resp.setData(systemPlatformServerFacade.getAllOffenUseOption(systemConfigVO));
			//成功返回true
			resp.setSuccess(true);
		} catch (Exception e) {
			//出现异常，日志记录
			logger.info(this.getClass().getName(), e);
			//设置返回码
			resp.setErrorCode("201");
			//设置返回信息
			resp.setResultMsg("系统内部异常，查询页面失败");
		}
			return resp;
	}
	
	/**
	 * @Title: insertOffenUseOption 
	 * @Description: 系统配置，插入系统常用功能
	 * @param @param systemConfigVO
	 * @param @param user
	 * @param @return   
	 * @return RestSampleFacadeResp<String>
	 */
	@Override
	public RestSampleFacadeResp<String> insertOffenUseOption(SystemConfigVO systemConfigVO,String user) {
		//创建返回独享
		RestSampleFacadeResp<String> resp = new RestSampleFacadeResp<>(false);
		
		try {
			//设置返回信息
			resp.setResultMsg(systemPlatformServerFacade.insertOffenUseOption(systemConfigVO));
			//成功返回true
			resp.setSuccess(true);
		} catch (Exception e) {
			//出现异常，日志记录
			logger.info(this.getClass().getName(), e);
			//设置返回码
			resp.setErrorCode("201");
			//设置返回信息
			resp.setResultMsg("系统内部异常，修改系统失败");
		}
		return resp;
	}
	
	/**
	 * @Title: deleteOffenUserOption 
	 * @Description: 通过常用系统功能的ID删除系统常用功能
	 * @param @param systemConfigId
	 * @param @return   
	 * @return RestSampleFacadeResp<Stirng>
	 */
	@Override
	public RestSampleFacadeResp<String> deleteOffenUserOption(String systemConfigId) {
		//创建返回对象
		RestSampleFacadeResp<String> resp = new RestSampleFacadeResp<>(false);
		try {
			//设置返回数据
			resp.setResultMsg(systemPlatformServerFacade.deleteOffenUseOption(systemConfigId));
			//成功返回true
			resp.setSuccess(true);
		} catch (Exception e) {
			//出现异常，日志记录
			logger.info(this.getClass().getName(), e);
			//设置返回码
			resp.setErrorCode("201");
			//设置返回信息
			resp.setResultMsg("系统内部异常，修改系统失败");
		}
			return resp;
	}
	//-----------------------------------------------------------------
	
//	@Override
//	public RestSampleFacadeResp<SystemPlatformVO> getSystemPlatformByPrimaryKey(
//			String systemPlatformCd,String user) {
//		RestSampleFacadeResp<SystemPlatformVO> resp = new RestSampleFacadeResp<>(
//				false);
//		SystemPlatformVO data = new SystemPlatformVO();
//		try {
//			data = systemPlatformServerFacade
//					.getSystemPlatformByPrimaryKey(systemPlatformCd);
//			resp.setSuccess(true);
//			resp.setData(data);
//		} catch (Exception e) {
//			resp.setErrorCode("201");
//			resp.setResultMsg("权限id错误，查询不到此的用户");
//			logger.info(this.getClass().getName(), e);
//		}
//		return resp;
//	}
//	
//	
//	@Override
//	public RestSampleFacadeResp<List<Map<String, Object>>> getAllSystemConfigByUserName(
//			String userName) {
//		RestSampleFacadeResp<List<Map<String,Object>>> resp = new RestSampleFacadeResp<List<Map<String,Object>>>(
//				false);
//		try {
//			resp.setSuccess(true);
//			resp.setData(systemPlatformServerFacade.getAllSystemConfigByUserName(userName));
//		} catch (Exception e) {
//			resp.setErrorCode("201");
//			resp.setResultMsg("系统内部异常，查询用户下的系统配置失败");
//			logger.info(this.getClass().getName(), e);
//		}
//		return resp;
//	}

}
