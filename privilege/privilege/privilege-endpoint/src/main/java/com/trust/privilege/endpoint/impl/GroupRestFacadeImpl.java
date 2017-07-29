package com.trust.privilege.endpoint.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;

import com.trust.privilege.endpoint.facade.GroupRestFacade;
import com.trust.privilege.endpoint.response.RestSampleFacadeResp;
import com.trust.privilege.facade.GroupFacade;
import com.trust.privilege.facade.model.GroupModel;
import com.trust.privilege.facade.model.GroupQueryVo;

/**
 * @ClassName: GroupRestFacade 
 * @Description: 用户组模块Rest接口实现
 * @author zyn161616@163.com
 * @version 创建时间：2017年3月20日 下午7:53:17
 */
public class GroupRestFacadeImpl implements GroupRestFacade {
	
	/**创建日志对象*/
	private static org.apache.log4j.Logger logger = LogManager.getLogger(GroupRestFacadeImpl.class.getName());
	
	/**依赖注入用户组facade*/
	@Resource
	private GroupFacade groupFacade;
	
	/**
	 * @Title: selectGroup 
	 * @Description: 多条件查询用户组
	 * @param @param vo
	 * @param @param userName
	 * @param @return   
	 * @return RestSampleFacadeResp<Map<String,Object>>
	 */
	@Override
	public RestSampleFacadeResp<Map<String, Object>> selectGroup(GroupQueryVo groupQueryVo,String userName) {
		//创建返回对象
		RestSampleFacadeResp<Map<String, Object>> resp = new RestSampleFacadeResp<>(false);
							
		try {
			//设置用户名
			groupQueryVo.setUserName(userName);
			//设置返回结果
			resp.setData(groupFacade.selectGroup(groupQueryVo));
			//成功返回true
			resp.setSuccess(true);
		} catch (Exception e) {
			//初夏异常，日志记录
			logger.error("系统错误，查询用户组信息失败", e);
			//设置返回码
			resp.setErrorCode("201");
			//设置返回信息
			resp.setResultMsg("查询失败");
		}
			return resp;
	}
	
	/**
	 * @Title: selectGroupDetail 
	 * @Description: 通过用户组Id查看用户组详情
	 * @param @param groupid
	 * @param @return   
	 * @return RestSampleFacadeResp<Map<String,Object>>
	 */
	@Override
	public RestSampleFacadeResp<Map<String, Object>> selectGroupDetail(String groupId) {
			
		//创建返回对象
		RestSampleFacadeResp<Map<String, Object>> resp = new RestSampleFacadeResp<>(false);
		
		try {
			//设置返回数据
			resp.setData(groupFacade.selectGroupDetail(groupId));
			//成功返回true
			resp.setSuccess(true);
		} catch (Exception e) {
			//出现异常，日志记录
			logger.error("查询失败", e);
			//设置返回码
			resp.setErrorCode("201");
			//设置返回信息
			resp.setResultMsg("查询失败");
		}
			return resp;
	}
	
	/**
	 * @Title: selectOptGroup 
	 * @Description: 查询用户可选用户组
	 * @param @param userName
	 * @param @return   
	 * @return RestSampleFacadeResp<List<Map<String,Object>>>
	 */
	@Override
	public RestSampleFacadeResp<List<Map<String, Object>>> selectOptGroup(String userName) {
		//创建返回对象	
		RestSampleFacadeResp<List<Map<String, Object>>> resp =  new RestSampleFacadeResp<>(false);
		try {
			//设置返回数据
			resp.setData(groupFacade.selectOptGroup(userName));
			//成功返回true
			resp.setSuccess(true);
		} catch (Exception e) {
			//如果异常，日志记录
			logger.error("查询用户组失败", e);
			//设置错误码返回
			resp.setErrorCode("201");
			//设置错误信息返回
			resp.setResultMsg("查询用户组失败");
		}
			return resp;
	}
	
	/**
	 * http://local.trust.com:8341/etheta/management/privilege/group/selectSys
	 * @Title: selectSys 
	 * @Description: 新建用户组时获取系统列表
	 * @param @param userName
	 * @param @return   
	 * @return RestSampleFacadeResp<List<Map<String,Object>>>
	 */
	@Override
	public RestSampleFacadeResp<List<Map<String, Object>>> selectSys(String userName) {
		//创建返回对象
		RestSampleFacadeResp<List<Map<String, Object>>> resp =new RestSampleFacadeResp<>(false);
		try {
			//设置返回数据
			resp.setData(groupFacade.selectSys(userName));
			//成功返回true
			resp.setSuccess(true);
		} catch (Exception e) {
			//如果出现异常，那么日志记录
			logger.error("查询系统失败", e);
			//设置错误码返回
			resp.setErrorCode("201");
			//设置错误信息返回
			resp.setResultMsg("查询系统列表失败");
		}
			return resp;
	}
	
	/**
	 * @Title: selectRoleFromGroup 
	 * @Description: 查询用户组拥有的角色
	 * @param @param groupId
	 * @param @return   
	 * @return RestSampleFacadeResp<Map<String,Object>>
	 */
	@Override
	public RestSampleFacadeResp<Map<String, Object>> selectRoleFromGroup(String groupId) {
		//创建返回对象	
		RestSampleFacadeResp<Map<String, Object>> resp = new RestSampleFacadeResp<>(false);
		
		try {
			//设置返回数据
			resp.setData(groupFacade.selectRoleFromGroup(groupId));
			//成功返回true
			resp.setSuccess(true);
		} catch (Exception e) {
			//出现异常，日志记录
			logger.info("查询用户组所拥有角色失败",e);
			//设置返回码
			resp.setErrorCode("201");
			//设置返回信息
			resp.setResultMsg("系统内部异常，查询用户组所拥有角色失败");
		}
		return resp;
	}
	
	/**
	 * @Title: insertGroup 
	 * @Description: 添加用户组
	 * @param @param groupModel
	 * @param @param userName
	 * @param @return   
	 * @return RestSampleFacadeResp<String>
	 */
	@Override
	public RestSampleFacadeResp<String> insertGroup(GroupModel groupModel,String userName) {
			
		//创建返回对象
		RestSampleFacadeResp<String> resp =  new RestSampleFacadeResp<>(false);
		try {
			//设置用户名
			groupModel.setCreateUserid(userName);
			//获取返回数据
			Map<String,Object> result = groupFacade.insertGroup(groupModel);
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
			logger.error("系统错误，增加失败", e);
			//设置返回码
			resp.setErrorCode("201");
			//设置返回信息
			resp.setResultMsg("系统错误，增加失败");
		}
			return resp;
	}
	
	/**
	 * @Title: deleteGroup 
	 * @Description: 删除用户组
	 * @param @param groupId
	 * @param @param userName
	 * @param @return   
	 * @return RestSampleFacadeResp<Integer>
	 */
	@Override
	public RestSampleFacadeResp<String> deleteGroup(String groupId,String userName) {
		//创建返回对象
		RestSampleFacadeResp<String> resp = new RestSampleFacadeResp<>(false);
		try {
			//获取返回数据
			Map<String,Object> result = groupFacade.deleteGroup(groupId,userName);
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
			logger.error("删除用户组失败", e);
			//设置返回码
			resp.setErrorCode("201");
			//设置返回信息
			resp.setResultMsg("删除用户组失败");
		}
		return resp;
	}
	
	/**
	 * @Title: updateDis 
	 * @Description: 修改用户组时需要的数据(预修改)
	 * @param @param groupId
	 * @param @param userName
	 * @param @return   
	 * @return RestSampleFacadeResp<Map<String,Object>>
	 */
	@Override
	public RestSampleFacadeResp<Map<String, Object>> updateDis(String groupId,String userName) {
		//创建返回对象
		RestSampleFacadeResp<Map<String, Object>> resp =  new RestSampleFacadeResp<>(false);
		try {
			//设置返回数据
			resp.setData(groupFacade.selectUpdDis(groupId,userName));
			//成功返回true
			resp.setSuccess(true);
		} catch (Exception e) {
			//出现异常，日志记录
			logger.error("查询用户组失败", e);
			//设置错误码返回
			resp.setErrorCode("201");
			//设置错误信息返回
			resp.setResultMsg("查询用户组失败");
		}
			return resp;
	}
	
	/**
	 * http://local.trust.com:8341/etheta/management/privilege/group/update
	 * @Title: updateGroup 
	 * @Description: 修改用户组信息
	 * @param @param groupModel
	 * @param @param userName
	 * @param @return   
	 * @return RestSampleFacadeResp<String>
	 */
	@Override
	public RestSampleFacadeResp<String> updateGroup(GroupModel groupModel,String userName) {
		//创建返回对象
		RestSampleFacadeResp<String> resp = new RestSampleFacadeResp<>(false);
		
		try {
			//设置用户名
			groupModel.setCreateUserid(userName);
			//获取返回数据
			Map<String,Object> result = groupFacade.updateGroup(groupModel);
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
			logger.error("修改用户组失败", e);
			//设置返回码
			resp.setErrorCode("201");
			//设置返回信息
			resp.setResultMsg("修改用户组失败");
		}
			return resp;
	}
	
	/**
	 * @Title: selectRoleBySys 
	 * @Description: 新建用户组时，通过所属系统CD获取该系统的角色
	 * @param @param systemId
	 * @param @param userName
	 * @param @return   
	 * @return RestSampleFacadeResp<List<Map<String,Object>>>
	 */
	@Override
	public RestSampleFacadeResp<List<Map<String, Object>>> selectRoleBySys(String systemIds,String userName) {
		//创建发挥对象
		RestSampleFacadeResp<List<Map<String, Object>>> resp = new RestSampleFacadeResp<>(false);
		try {
			//设置返回数据
			resp.setData(groupFacade.selectRoleBySys(systemIds,userName));
			//成功返回true
			resp.setSuccess(true);
		} catch (Exception e) {
			//异常，日志记录
			logger.error("查询角色失败", e);
			//设置返回码
			resp.setErrorCode("201");
			//设置返回信息
			resp.setResultMsg("查询角色失败");
		}
			return resp;
	}

//--------------------------------------------分界线---------------------------------------------------------------

//	@Override
//	public RestSampleFacadeResp<List<GroupModel>> selectGroupByCondition(
//			String userName) {
//		List<GroupModel> groupModelList = groupFacade
//				.selectGroupByCondition(userName);
//		RestSampleFacadeResp<List<GroupModel>> groupModelListRest = null;
//		if (groupModelList.isEmpty()) {
//			groupModelListRest = new RestSampleFacadeResp<>(false);
//			groupModelListRest.setErrorCode("查询用户组失败");
//			return groupModelListRest;
//		}
//		groupModelListRest = new RestSampleFacadeResp<List<GroupModel>>(
//				groupModelList, true);
//		return groupModelListRest;
//	}

//	@Override
//	public RestSampleFacadeResp<List<String>> selectOptRole(String userName) {
//		RestSampleFacadeResp<List<String>> resultRest = null;
//		try {
//			List<String> result = groupFacade.selectOptRole(userName);
//			resultRest = new RestSampleFacadeResp<List<String>>(result, true);
//		} catch (Exception e) {
//			logger.error("查询角色", e);
//			resultRest = new RestSampleFacadeResp<>(false);
//			resultRest.setErrorCode("201");
//			resultRest.setResultMsg("查询角色失败");
//		}
//		return resultRest;
//	}

	

}
