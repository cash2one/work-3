package com.trust.privilege.endpoint.facade;

import java.util.List;
import java.util.Map;

import javax.annotation.security.PermitAll;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.trust.privilege.endpoint.response.RestSampleFacadeResp;
import com.trust.privilege.facade.model.complex.MenuResVO;
/***
 * @ClassName: ShowPortalRestFacade 
 * @Description: portal门户Rest接口
 * @author zyn161616@163.com
 * @version 创建时间：2017年1月18日 下午5:07:22
 */
@Path("/trust/privilege")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@PermitAll
public interface ShowPortalRestFacade {
	
	/**
	 * http://local.trust.com:8341/trust/privilege/showPortalMenu
	 * @Title: showPortalMenu 
	 * @Description: SSO通过后的门户左侧菜单数据渲染
	 * @param @return   
	 * @return RestSampleFacadeResp<List<MenuResVO>>
	 */
	@Path("/showPortalMenu")
	@GET
	public RestSampleFacadeResp<List<MenuResVO>> showPortalMenu(@HeaderParam("userName")String userName);
	
	
	/**
	 * http://local.trust.com:8341/trust/privilege/showPortal
	 * @Title: showPortal 
	 * @Description: SSO验证通过后的门户右侧菜单数据渲染
	 * @param @param userName
	 * @param @return   
	 * @return RestSampleFacadeResp<Map<String,Object>>
	 */
	@Path("/showPortal")
	@GET
	public RestSampleFacadeResp<Map<String,Object>> showPortal(@HeaderParam("userName")String userName);
	
}
