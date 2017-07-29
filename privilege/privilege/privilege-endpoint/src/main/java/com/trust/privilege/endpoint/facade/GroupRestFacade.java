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
import com.trust.privilege.facade.model.GroupModel;
import com.trust.privilege.facade.model.GroupQueryVo;
/**
 * @ClassName: GroupRestFacade 
 * @Description: 用户组模块Rest接口
 * @author zyn161616@163.com
 * @version 创建时间：2017年3月20日 下午7:53:17
 */
@Path(URLConstants.REST_API_PEFFIX+"/privilege/group")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@PermitAll
public interface GroupRestFacade {
	
	/**
	 * http://local.trust.com:8341/etheta/management/privilege/group/selectGroup
	 * @Title: selectGroup 
	 * @Description: 多条件查询用户组					
	 * @param @param groupQuaryVo
	 * @param @param userName
	 * @param @return   
	 * @return RestSampleFacadeResp<Map<String,Object>>
	 */
	@POST
	@Path("/selectGroup")
	public RestSampleFacadeResp<Map<String, Object>> selectGroup(GroupQueryVo groupQuaryVo,@HeaderParam("userName")String userName);
	
	/**
	 * http://local.trust.com:8341/etheta/management/privilege/group/detail/{groupId}
	 * @Title: selectGroupDetail 
	 * @Description: 通过用户组Id查看用户组详情
	 * @param @param groupid
	 * @param @return   
	 * @return RestSampleFacadeResp<Map<String,Object>>
	 */
	@GET
	@Path("/detail/{groupId}")
	public RestSampleFacadeResp<Map<String, Object>> selectGroupDetail(@PathParam("groupId")String groupid);

	/**
	 * http://local.trust.com:8341/etheta/management/privilege/group/selectOptGroup
	 * @Title: selectOptGroup 
	 * @Description: 查询用户可选用户组
	 * @param @param userName
	 * @param @return   
	 * @return RestSampleFacadeResp<List<Map<String,Object>>>
	 */
	@POST
	@Path("/selectOptGroup")
	public RestSampleFacadeResp<List<Map<String, Object>>> selectOptGroup(@HeaderParam("userName")String userName);
	
	/**
	 * http://local.trust.com:8341/etheta/management/privilege/group/selectSys
	 * @Title: selectSys 
	 * @Description: 查询用户所拥有系统
	 * @param @param userName
	 * @param @return   
	 * @return RestSampleFacadeResp<List<Map<String,Object>>>
	 */
	@GET
	@Path("/selectSys")
	public RestSampleFacadeResp<List<Map<String, Object>>> selectSys(@HeaderParam("userName")String userName);
	
	/**
	 * http://local.trust.com:8341/etheta/management/privilege/group/selectRoleFromGroup/{groupId}
	 * @Title: selectRoleFromGroup 
	 * @Description: 查询用户组拥有的角色
	 * @param @param groupId
	 * @param @return   
	 * @return RestSampleFacadeResp<Map<String,Object>>
	 */
	@GET
	@Path("/selectRoleFromGroup/{groupId}")
	public RestSampleFacadeResp<Map<String, Object>> selectRoleFromGroup(@PathParam("groupId")String groupId);
	
	/**
	 * http://localhost:8341/etheta/management/privilege/group/add
	 * @Title: insertGroup 
	 * @Description: 添加用户组
	 * @param @param groupModel
	 * @param @param userName
	 * @param @return   
	 * @return RestSampleFacadeResp<String>
	 */
	@POST
	@Path("/add")
	public RestSampleFacadeResp<String> insertGroup(GroupModel groupModel,@HeaderParam("userName")String userName);
	
	/**
	 * http://localhost:8341/etheta/management/privilege/group/delete/{groupId}
	 * @Title: deleteGroup 
	 * @Description: 删除用户组
	 * @param @param groupId
	 * @param @param userName
	 * @param @return   
	 * @return RestSampleFacadeResp<String>
	 */
	@GET
	@Path("/delete/{groupId}")
	public RestSampleFacadeResp<String> deleteGroup(@PathParam("groupId") String groupId,@HeaderParam("userName") String userName);
	
	/**
	 * http://localhost:8341/etheta/management/privilege/group/updateDis/{groupId}
	 * @Title: updateDis 
	 * @Description: 修改用户组时需要的数据(预修改)
	 * @param @param groupId
	 * @param @param userName
	 * @param @return   
	 * @return RestSampleFacadeResp<Map<String,Object>>
	 */
	@GET
	@Path("/updateDis/{groupId}")
	public RestSampleFacadeResp<Map<String, Object>> updateDis(@PathParam("groupId")String groupId,@HeaderParam("userName")String userName); 
	
	/**
	 * http://local.trust.com:8341/etheta/management/privilege/group/update
	 * @Title: updateGroup 
	 * @Description: 修改用户组信息
	 * @param @param groupModel
	 * @param @param userName
	 * @param @return   
	 * @return RestSampleFacadeResp<String>
	 */
	@POST
	@Path("/update")
	public RestSampleFacadeResp<String> updateGroup(GroupModel groupModel,@HeaderParam("userName")String userName);
	
	/**
	 * http://local.trust.com:8341/etheta/management/privilege/group/selectRoleBySys/{systemId}
	 * @Title: selectRoleBySys 
	 * @Description: 新建用户组时，通过所属系统CD获取该系统的角色
	 * @param @param systemId
	 * @param @param userName
	 * @param @return   
	 * @return RestSampleFacadeResp<List<Map<String,Object>>>
	 */
	@GET
	@Path("/selectRoleBySys/{systemId}")
	public RestSampleFacadeResp<List<Map<String, Object>>> selectRoleBySys(@PathParam("systemId")String systemIds,@HeaderParam("userName")String userName);
	
//--------------------------------------------分界线---------------------------------------------------------------

	/**
	 * 根据用户id查询用户组-sw
	 * http://localhost:8341/etheta/management/privilege/group/selectGroupByCondition
	 * @param userId
	 * @return
	 */
//	@GET
//	@Path("/selectGroupByCondition/{userName}")
//	public RestSampleFacadeResp<List<GroupModel>> selectGroupByCondition(@PathParam("userName") String userName);
	
	/**查询用户可以访问的角色
	 * http://localhost:8341/etheta/management/privilege/group/selectOptRole
	 * @param userId
	 * @return
	 */
//	@GET
//	@Path("/selectOptRole")
//	public RestSampleFacadeResp<List<String>> selectOptRole(@HeaderParam("userName")String userName);
	
	
}
