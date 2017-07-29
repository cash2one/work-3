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
import com.trust.privilege.facade.model.PrivilegeResTypeVO;
import com.trust.privilege.facade.model.PrivilegeResVO;
import com.trust.privilege.facade.model.complex.QueryPrivilegeVO;
/**
 * @ClassName: PrivilegeRestFacade 
 * @Description: 页面资源Rest接口
 * @author zyn161616@163.com
 * @version 创建时间：2017年3月14日 下午3:56:03
 */
@Path(URLConstants.REST_API_PEFFIX + "/xinmei/button/privilege")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@PermitAll
public interface PrivilegeRestFacade {
	/**
	 * http://local.trust.com:8341/etheta/management/xinmei/button/privilege/selectSysPrivilege/{menuId}
	 * @Title: selectSysPrivilege 
	 * @Description: 根据用户名，菜单CD 查询权限资源			
	 * @param @param menuId
	 * @param @param userName
	 * @param @return   
	 * @return RestSampleFacadeResp<Map<String,Object>>
	 */
	@GET
	@Path("/selectSysPrivilege/{menuId}")
	public RestSampleFacadeResp<Map<String, Object>> selectSysPrivilege(@PathParam("menuId")String menuId,@HeaderParam("userName")String userName);
	
	/**
	 * http://local.trust.com:8341/etheta/management/xinmei/button/privilege/privilegeList
	 * @Title: getAllPrivilege 
	 * @Description: 获取系统资源列表
	 * @param @return   
	 * @return restSamleFacadeResp<Map<String,Object>>
	 */
	@POST
	@Path("/privilegeList")
	public RestSampleFacadeResp<Map<String,Object>> getAllprivileges(QueryPrivilegeVO queryPrivilegeVO,@HeaderParam("userName")String userName);
	
	/**
	 * http://local.trust.com:8341/etheta/management/xinmei/button/privilege/addPrivilege
	 * @Title: insertPrivilege 
	 * @Description: 新增资源信息
	 * @param @param privilegeResVO
	 * @param @param userName
	 * @param @return   
	 * @return RestSampleFacadeResp<String>
	 */
	@POST
	@Path("/addPrivilege")
	public RestSampleFacadeResp<String> insertPrivilege(PrivilegeResVO privilegeResVO,@HeaderParam("userName")String userName);
	
	/**
	 * http://local.trust.com:8341/etheta/management/xinmei/button/privilege/deletePrivilege/{privilegeResId}
	 * @Title: deletePrivilege 
	 * @Description: 删除一个资源
	 * @param @param privilegeResId
	 * @param @param userName
	 * @param @return   
	 * @return RestSampleFacadeResp<String>
	 */
	@GET
	@Path("/deletePrivilege/{privilegeResId}")
	public RestSampleFacadeResp<String> deletePrivilege(@PathParam("privilegeResId") String privilegeResId,@HeaderParam("userName")String userName);
	
	/**
	 * http://local.trust.com:8341/etheta/management/xinmei/button/privilege/updatePrivilege
	 * @Title: updatePrivilege 
	 * @Description: 修改资源信息						
	 * @param @param privilegeResVO
	 * @param @param userName
	 * @param @return   
	 * @return RestSampleFacadeResp<Integer>
	 */
	@POST
	@Path("/updatePrivilege")
	public RestSampleFacadeResp<String> updatePrivilege(PrivilegeResVO privilegeResVO,@HeaderParam("userName")String userName);
	
	/**
	 * http://local.trust.com:8341/etheta/management/xinmei/button/privilege/getAllResType
	 * @Title: getAllResType 
	 * @Description: 查询所有的页面资源类型
	 * @param @return   
	 * @return RestSampleFacadeResp<List<PrivilegeResTypeVO>>
	 */
	@POST
	@Path("/getAllResType")
	public RestSampleFacadeResp<List<PrivilegeResTypeVO>> getAllResType();
	
	/**
	 * http://local.trust.com:8341/etheta/management/xinmei/button/privilege/getPrivilegeByMenuIds
	 * @Title: getPrivilegeByMenuIds 
	 * @Description: 在新建角色时，根据多个id查询页面有效资源
	 * @param @param privilegeResVO
	 * @param @return   
	 * @return RestSampleFacadeResp<List<PrivilegeResVO>>
	 */
	@POST
	@Path("/getPrivilegeByMenuIds")
	public RestSampleFacadeResp<List<PrivilegeResVO>> getPrivilegeByMenuIds(PrivilegeResVO privilegeResVO);
	
	//-----------------------------------------------------------------
	/**
	 * 插入页面资源类型
	 * http://localhost:8341/etheta/management/xinmei/button/privilege/insertResType
	 * @param privilegeResVO
	 * @return
	 */
//	@POST
//	@Path("/button/privilege/insertResType/{user}")
//	public RestSampleFacadeResp<Integer> insertResType(PrivilegeResTypeVO privilegeResTypeVO,@HeaderParam("userName")String userName);
	
	/**根据用户名和系统id，查询需要拦截的url
	 * http://localhost:8341/etheta/management/xinmei/privilege/validate
	 * @param userId
	 * @param systemId
	 * @author luozhenyu
	 * @return
	 */
//   @GET
//   @Path("/privilege/validate/{systemId}")
//   public RestSampleFacadeResp<List<String>> selectUserSysPrivilege(@HeaderParam("userName")String userName,@PathParam("systemId")String systemId);
	
}
