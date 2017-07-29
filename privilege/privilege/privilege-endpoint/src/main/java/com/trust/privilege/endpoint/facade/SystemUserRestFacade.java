package com.trust.privilege.endpoint.facade;

import java.util.Map;

import javax.annotation.security.PermitAll;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.trust.privilege.endpoint.constants.URLConstants;
import com.trust.privilege.endpoint.response.RestSampleFacadeResp;
import com.trust.privilege.facade.model.SysUserModel;
import com.trust.privilege.facade.model.SysUserQuaryVo;
/**
 * @ClassName: SystemUserRestFacade 
 * @Description: 用户模块Rest接口
 * @author zyn161616@163.com
 * @version 创建时间：2017年3月17日 下午2:33:09
 */
@Path(value =URLConstants.REST_API_PEFFIX+ "/privilege/user")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@PermitAll
public interface SystemUserRestFacade {
	
	/**
	 * http://local.trust.com:8341/etheta/management/privilege/user/select
	 * @Title: selectUser 
	 * @Description: 按条件查询用户信息
	 * @param @param quaryVo
	 * @param @param userName
	 * @param @return   
	 * @return RestSampleFacadeResp<Map<String,Object>>
	 */
	@POST
	@Path("/select")
	public RestSampleFacadeResp<Map<String, Object>> selectUser(SysUserQuaryVo quaryVo,@HeaderParam("userName")String userName);
	
	/**
	 * http://local.trust.com:8341/etheta/management/privilege/user/add
	 * @Title: insertUser 
	 * @Description: 新增用户
	 * @param @param sysUserModel
	 * @param @param userName
	 * @param @return   
	 * @return RestSampleFacadeResp<String>
	 */
	@POST
	@Path("/add")
	public RestSampleFacadeResp<String> insertUser(SysUserModel sysUserModel,@HeaderParam("userName")String userName);
	
	/**
	 * http://local.trust.com:8341/etheta/management/privilege/user/delete
	 * @Title: deleteUserById 
	 * @Description: 根据用户Id删除该用户信息
	 * @param @param sysUserId
	 * @param @param userName
	 * @param @return   
	 * @return RestSampleFacadeResp<String>
	 */
	@GET
	@Path("/delete/{sysUserId}")
	public RestSampleFacadeResp<String> deleteUserById(@PathParam("sysUserId") String sysUserId,@HeaderParam("userName")String userName);
	
	/**
	 * http://local.trust.com:8341/etheta/management/privilege/user/selectUpdDis/{userId}
	 * @Title: selectUpdDis 
	 * @Description: 修改用户时查询所需的数据（预修改）
	 * @param @param userId
	 * @param @param userName
	 * @param @return  
	 * @return RestSampleFacadeResp<Map<String,Object>>
	 */
	@GET
	@Path("/selectUpdDis/{userId}")
	public RestSampleFacadeResp<Map<String, Object>> selectUpdDis(@PathParam("userId") String userId,@HeaderParam("userName") String userName );
	
	/**
	 * http://local.trust.com:8341/etheta/management/privilege/user/update
	 * @Title: updateUser 
	 * @Description: 修改用户信息
	 * @param @param sysUserModel
	 * @param @param userName
	 * @param @return   
	 * @return RestSampleFacadeResp<String>
	 */
	@POST
	@Path("/update")
	public RestSampleFacadeResp<String> updateUser(SysUserModel sysUserModel,@HeaderParam("userName")String userName);
	
	//-----------------------------------------------------------------
	
	/**
	 * http://local.trust.con:8341/etheta/management/privilege/user/getUserInfo
	 * @Title: getUserInfo 
	 * @Description: potal页面获取个人信息
	 * @param @param userName
	 * @param @return   
	 * @return RestSampleFacadeResp<SysUserModel>
	 */
	@GET
	@Path("/getUserInfo")
	public RestSampleFacadeResp<SysUserModel> getUserInfo(@HeaderParam("userName")String userName);
	
	/**
	 * http://local.trust.com:8341/etheta/management/privilege/user/modifyPassword
	 * @Title: modifyPassword 
	 * @Description: portal页面根据用户名修改密码
	 * @param @param model
	 * @param @return   
	 * @return RestSampleFacadeResp<String>
	 */
	@POST
	@Path("/modifyPassword")
	public RestSampleFacadeResp<String> modifyPassword(SysUserModel model);

}


