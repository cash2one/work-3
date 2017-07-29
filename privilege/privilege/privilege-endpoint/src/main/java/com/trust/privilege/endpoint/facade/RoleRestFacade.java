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
import com.trust.privilege.facade.model.RoleVO;
import com.trust.privilege.facade.model.complex.QueryRoleVO;
/**
 * @ClassName: RoleRestFacade 
 * @Description: 角色模块Rest接口
 * @author zyn161616@163.com
 * @version 创建时间：2017年3月14日 上午10:35:12
 */
@Path(URLConstants.REST_API_PEFFIX + "/xinmei")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@PermitAll
public interface RoleRestFacade {
	
	/**
	 * http://local.trust.com:8341/etheta/management/xinmei/getAllRoles
	 * @Title: getAllRoles 
	 * @Description: 根据条件查询所有的角色
	 * @param @param queryRoleVO
	 * @param @param user
	 * @param @return   
	 * @return RestSampleFacadeResp<Map<String,Object>>
	 */
	@POST
	@Path("/getAllRoles")
	public RestSampleFacadeResp<Map<String,Object>> getAllRoles(
							QueryRoleVO queryRoleVO,@HeaderParam("userName") String userName);
	
	/**
	 * http://local.trust.com:8341/etheta/management/xinmei/insertRole
	 * @Title: insertRole 
	 * @Description: 新增角色信息
	 * @param @param roleVO
	 * @param @param user
	 * @param @return   
	 * @return RestSampleFacadeResp<String>
	 */
	@POST
	@Path("/insertRole")
	public RestSampleFacadeResp<String> insertRole(RoleVO roleVO,@HeaderParam("userName") String userName);
	
	/**
	 * http://local.trust.com:8341/etheta/management/xinmei/deleteRoleByRoleId/{roleId}
	 * @Title: deleteRoleByRoleId 
	 * @Description: 通过角色id删除角色数据
	 * @param @param roleId
	 * @param @param user
	 * @param @return   
	 * @return RestSampleFacadeResp<String>
	 */
	@GET
	@Path("/deleteRoleByRoleId/{roleId}")
	public RestSampleFacadeResp<String> deleteRoleByRoleId(@PathParam("roleId") String roleId,@HeaderParam("userName") String userName);
	
	/**
	 * http://local.trust.com:8341/etheta/management/xinmei/getAllResourceByRoleId
	 * @Title: getAllResourceByRoleId 
	 * @Description: 获取当前角色下的所有菜单和页面资源
	 * @param @param roleId
	 * @param @return   
	 * @return RestSampleFacadeResp<Map<String,Object>>
	 */
	@GET
	@Path("/getAllResourceByRoleId/{roleId}")
	public RestSampleFacadeResp<Map<String,Object>> getAllResourceByRoleId(@PathParam("roleId") String roleId);
	
	/**
	 * http://local.trust.com:8341/etheta/management/xinmei/updateRole
	 * @Title: updateRole 
	 * @Description: 修改角色信息
	 * @param @param roleVO
	 * @param @param userName
	 * @param @return   
	 * @return RestSampleFacadeResp<String>
	 */
	@POST
	@Path("/updateRole")
	public RestSampleFacadeResp<String> updateRole(RoleVO roleVO,@HeaderParam("userName")String userName);
	
	/**
	 * http://local.trust.com:8341/etheta/management/xinmei/getRoleByRoleId
	 * @Title: getRoleByRoleId 
	 * @Description: 根据主键查询该角色信息
	 * @param @param roleId
	 * @param @return   
	 * @return RestSampleFacadeResp<RoleVO>
	 */
	@GET
	@Path("/getRoleByRoleId/{roleId}/{user}")
	public RestSampleFacadeResp<RoleVO> getRoleByRoleId(@PathParam("roleId") String roleId);
	
}
