package com.trust.privilege.endpoint.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.trust.privilege.endpoint.facade.SystemUserRestFacade;
import com.trust.privilege.endpoint.response.RestSampleFacadeResp;
import com.trust.privilege.facade.MenuServerFacade;
import com.trust.privilege.facade.SystemUserServerFacade;
import com.trust.privilege.facade.model.SysUserModel;
import com.trust.privilege.facade.model.SysUserQuaryVo;
/**
 * @ClassName: SystemUserRestFacade 
 * @Description: 用户模块Rest接口实现
 * @author zyn161616@163.com
 * @version 创建时间：2017年3月17日 下午2:33:09
 */
public class SystemUserRestFacadeImpl implements SystemUserRestFacade {
	
	/**创建日志对象*/
	private static final Logger logger = LogManager.getLogger(SystemUserRestFacadeImpl.class.getName());

	/**依赖注入用户facade*/
	@Resource
	private SystemUserServerFacade sysUserFacade;
	
	/**依赖注入菜单facade*/
	@Resource
	private MenuServerFacade menuServerFacade;
	

	/**
	 * @Title: selectUser 
	 * @Description: 按条件查询用户信息
	 * @param @param quaryVo
	 * @param @param userName
	 * @param @return   
	 * @return RestSampleFacadeResp<Map<String,Object>>
	 */
	@Override
	public RestSampleFacadeResp<Map<String, Object>> selectUser(SysUserQuaryVo queryVo,String userName) {
		//创建返回对象
		RestSampleFacadeResp<Map<String, Object>> resp = new RestSampleFacadeResp<>(false);
		
		try {
			//设置当前操作人
			queryVo.setUserName(userName);
			//设置返回数据
			resp.setData(sysUserFacade.selectUser(queryVo));
			//成功返回true
			resp.setSuccess(true);
		} catch (Exception e) {
			//出现异常，日志记录
			logger.error("查询用户信息失败", e);
			//设置返回码
			resp.setErrorCode("201");
			//设置返回信息
			resp.setResultMsg("系统错误，查询用户失败！");
		}
			return resp;
	}
	
	/**
	 * @Title: insertUser 
	 * @Description: 新增用户
	 * @param @param sysUserModel
	 * @param @param userName
	 * @param @return   
	 * @return RestSampleFacadeResp<String>
	 */
	@Override
	public RestSampleFacadeResp<String> insertUser(SysUserModel sysUserModel,String userName) {
		//创建返回对象
		RestSampleFacadeResp<String> resp = new RestSampleFacadeResp<>(false);
		
		try {
			//将创建人名字放入
			sysUserModel.setCreateUserId(userName);
			//获取返回数据
			Map<String,Object> result = sysUserFacade.insertUser(sysUserModel);
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
			logger.error("添加用户失败", e);
			//设置返回码
			resp.setErrorCode("201");
			//设置返回信息
			resp.setResultMsg("添加用户信息失败");
		}
		return resp;
	}
	
	/**
	 * @Title: deleteUserById 
	 * @Description: 根据用户Id删除该用户信息
	 * @param @param sysUserId
	 * @param @param userName
	 * @param @return   
	 * @return RestSampleFacadeResp<String>
	 */
	@Override
	public RestSampleFacadeResp<String> deleteUserById(String sysUserId,String userName) {
		//创建返回对象
		RestSampleFacadeResp<String> resp =  new RestSampleFacadeResp<>(false);
		
		try {
			//获取返回数据
			Map<String,Object> result = sysUserFacade.deleteUserById(sysUserId,userName);
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
			logger.error("删除该用户信息失败", e);
			//设置返回码
			resp.setErrorCode("201");
			//设置返回信息
			resp.setResultMsg("删除该用户信息失败");
		}
			return resp;
	}
	
	/**
	 * @Title: selectUpdDis 
	 * @Description: 修改用户时查询所需的数据（预修改）
	 * @param @param userId
	 * @param @param userName
	 * @param @return  
	 * @return RestSampleFacadeResp<Map<String,Object>>
	 */
	@Override
	public RestSampleFacadeResp<Map<String, Object>> selectUpdDis(String userId,String userName) {
		//创建返回对象
		RestSampleFacadeResp<Map<String, Object>> resp = new RestSampleFacadeResp<>(false);
		try {
			//设置返回数据
			resp.setData(sysUserFacade.selectUpdDis(userId,userName));
			//成功返回true
			resp.setSuccess(true);
		} catch (Exception e) {
			//出现异常，日志记录
			logger.error("查询用户信息失败", e);
			//设置返回码
			resp.setErrorCode("201");
			//设置返回信息
			resp.setResultMsg("系统错误，查询用户信息失败！");
		}
			return resp;
	}

	/**
	 * @Title: updateUser 
	 * @Description: 修改用户信息
	 * @param @param sysUserModel
	 * @param @param userName
	 * @param @return   
	 * @return RestSampleFacadeResp<String>
	 */
	@Override
	public RestSampleFacadeResp<String> updateUser(SysUserModel sysUserModel,String userName){
		//创建返回对象
		RestSampleFacadeResp<String> resp = new RestSampleFacadeResp<>(false);

		try {
			//设置当前操作人
			sysUserModel.setCreateUserId(userName);
			//获取返回数据
			Map<String,Object> result = sysUserFacade.updateUser(sysUserModel);
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
			logger.error("修改用户失败", e);
			//设置返回码
			resp.setErrorCode("201");
			//设置返回错误信息
			resp.setResultMsg("修改用户信息失败");
		}
			return resp;
	}

	//-----------------------------------------------------------------
	/**
	 * @Title: getUserInfo 
	 * @Description: potal页面获取个人信息
	 * @param @param userName
	 * @param @return   
	 * @return RestSampleFacadeResp<SysUserModel>
	 */
	@Override
	public RestSampleFacadeResp<SysUserModel> getUserInfo(String userName) {
		//创建返回数据
		RestSampleFacadeResp<SysUserModel> resp = new RestSampleFacadeResp<>(false);
		try {
			//设置返回数据
			resp.setData(sysUserFacade.getUserInfo(userName));
			//成功返回true
			resp.setSuccess(true);
		} catch (Exception e) {
			//日志记录
			logger.info("查询个人信息失败",e);
			resp.setErrorCode("0");
			resp.setResultMsg("查询个人信息失败");
		}
			//返回数据
			return resp;
	}
	
	/**
	 * @Title: modifyPassword 
	 * @Description: portal页面根据用户名修改密码
	 * @param @param model
	 * @param @return   
	 * @return RestSampleFacadeResp<String>
	 */
	@Override
	public RestSampleFacadeResp<String> modifyPassword(SysUserModel model) {
		//创建返回对象
		RestSampleFacadeResp<String> resp = new RestSampleFacadeResp<>(false);;
		
		try{
			//返回数据
			resp.setResultMsg(sysUserFacade.modifyPassword(model));
			//成功返回true
			resp.setSuccess(true);
		}catch(Exception e){
			//出现问题，日志记录
			logger.error("修改密码失败", e);
			resp.setErrorCode("201");
			resp.setResultMsg("系统错误，修改密码失败!");
		}
		return resp ;
	}
	
}
