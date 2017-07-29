package com.trust.privilege.endpoint.facade;

import java.util.List;
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
import com.trust.privilege.facade.model.SystemConfigVO;
import com.trust.privilege.facade.model.SystemPlatformVO;
import com.trust.privilege.facade.model.complex.QuerySystemPlatformVO;
/**
 * @ClassName: SystemPlatformRestFacade 
 * @Description: 系统模块Rest接口
 * @author zyn161616@163.com
 * @version 创建时间：2017年3月14日 上午10:40:58
 */
@Path(URLConstants.REST_API_PEFFIX + "/xinmei/button/systemPlatform")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@PermitAll
public interface SystemPlatformRestFacade {
	
	/**
	 * http://local.trust.com:8341/etheta/management/xinmei/button/systemPlatform/addSystem
	 * @Title: insertSystem 
	 * @Description: 新增系统信息
	 * @param @param systemPlatformVO
	 * @param @param userName
	 * @param @return   
	 * @return RestSampleFacadeResp<String>
	 */
	@POST
	@Path("/addSystem")
	public RestSampleFacadeResp<String> insertSystem(SystemPlatformVO systemPlatformVO,@HeaderParam("userName") String userName);
	
	/**
	 * http://local.trust.com:8341/etheta/management/xinmei/button/systemPlatform/deleteSystem/{systemPlatformCd}
	 * @Title: deleteSystem 
	 * @Description: 删除系统平台
	 * @param @param systemPlatformCd
	 * @param @param user
	 * @param @return   
	 * @return RestSampleFacadeResp<String>
	 */
	@GET
	@Path("/deleteSystem/{systemPlatformCd}")
	public RestSampleFacadeResp<String> deleteSystem(
			@PathParam("systemPlatformCd")String systemPlatformCd,@HeaderParam("userName") String userName);
	
	/**
	 * http://local.trust.com:8341/etheta/management/xinmei/button/systemPlatform/UpadateSystemPlatform
	 * @Title: updateSystem 
	 * @Description: 修改系统信息
	 * @param @param systemPlatformVO
	 * @param @param String userName
	 * @param @return   
	 * @return RestSampleFacadeResp<String>
	 */
	@POST
	@Path("/UpadateSystemPlatform")
	public RestSampleFacadeResp<String> updateSystem(SystemPlatformVO systemPlatformVO,@HeaderParam("userName") String userName);
	
	/**
	 * http://local.trust.com:8341/etheta/management/xinmei/button/systemPlatform/selectSystemPlatform
	 * @Title: selectSystemPlatform 
	 * @Description: 查询系统平台列表
	 * @param @param systemPlatformName
	 * @param @param userName
	 * @param @return   
	 * @return RestSampleFacadeResp<Map<String,Object>>
	 */
	@POST
	@Path("/selectSystemPlatform")
	public RestSampleFacadeResp<Map<String,Object>> selectSystemPlatform(
					QuerySystemPlatformVO querySystemPlatformVO ,@HeaderParam("userName") String userName);
	
	//-----------------------------------------------------------------
	/**
	 * http://local.trust.com:8341/etheta/management/xinmei/button/systemPlatform/getAllSystem
	 * @Title: getAllSystem 
	 * @Description: 查询日志时候，所属系统的接口
	 * @param @return   
	 * @return RestSampleFacadeResp<List<SystemPlatformVO>>
	 */
	@POST
	@Path("/getAllSystem")
	public RestSampleFacadeResp<List<SystemPlatformVO>> getAllSystem();
	
	/**
	 *  http://local.trust.com:8341/etheta/management/xinmei/button/systemPlatform/getAllOffenUseOption
	 * @Title: getAllOffenUseOption 
	 * @Description: 查询系统常用功能
	 * @param @param systemConfigVO
	 * @param @return   
	 * @return RestSampleFacadeResp<Map<String,Object>>
	 */
	@POST
	@Path("/getAllOffenUseOption")
	public RestSampleFacadeResp<Map<String,Object>> getAllOffenUseOption(SystemConfigVO systemConfigVO);
	
	/**
	 * http://local.trust.com:8341/etheta/management/xinmei/button/systemPlatform/insertOffenUseOption
	 * @Title: insertOffenUseOption 
	 * @Description: 系统配置，插入系统常用功能
	 * @param @param systemConfigVO
	 * @param @param user
	 * @param @return   
	 * @return RestSampleFacadeResp<String>
	 */
	@POST
	@Path("/insertOffenUseOption/{user}")
	public RestSampleFacadeResp<String> insertOffenUseOption(SystemConfigVO systemConfigVO,String user);
	
	/**
	 * http://local.trust.com:8341/etheta/management/xinmei/button/systemPlatform/deleteOffenUserOption
	 * @Title: deleteOffenUserOption 
	 * @Description: 通过常用系统功能的ID删除系统常用功能
	 * @param @param systemConfigId
	 * @param @return   
	 * @return RestSampleFacadeResp<String>
	 */
	@POST
	@Path("/deleteOffenUserOption/{systemConfigId}")
	public RestSampleFacadeResp<String> deleteOffenUserOption(@PathParam("systemConfigId") String systemConfigId);
	//-----------------------------------------------------------------
	
//	/**
//	 * 根据主键查询系统
//	 * http://localhost:8341/etheta/management/xinmei/button/systemPlatform/getSystemPlatformByPrimaryKey/{systemPlatformCd}
//	 * @param systemPlatformCd
//	 * @return
//	 */
//	@GET
//	@Path("/getSystemPlatformByPrimaryKey/{systemPlatformCd}/{user}")
//	public RestSampleFacadeResp<SystemPlatformVO> getSystemPlatformByPrimaryKey(@PathParam("systemPlatformCd")String systemPlatformCd,@PathParam("user")String user);
//	
//	/**
//	 * 查询用户下的系统配置
//	 * http://localhost:8341/etheta/management/xinmei/button/systemPlatform/getAllSystemConfigByUserName
//	 * @param userName
//	 * @return
//	 */
//	@POST
//	@Path("/getAllSystemConfigByUserName/{userName}")
//	public RestSampleFacadeResp<List<Map<String,Object>>> getAllSystemConfigByUserName(@PathParam("userName") String userName);
	

}
