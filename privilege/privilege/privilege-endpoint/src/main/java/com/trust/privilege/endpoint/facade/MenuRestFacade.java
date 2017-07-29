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
import com.trust.privilege.facade.model.MenuVO;
import com.trust.privilege.facade.model.complex.MenuResVO;
import com.trust.privilege.facade.model.complex.QueryMenuVO;
/**
 * @ClassName: MenuRestFacade 
 * @Description: 菜单模块Rest接口
 * @author zyn161616@163.com
 * @version 创建时间：2017年3月7日 下午4:58:44
 */
@Path(URLConstants.REST_API_PEFFIX + "/xinmei")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@PermitAll
public interface MenuRestFacade {
	
	/**
	 * http://local.trust.com:8341/etheta/management/xinmei/selectSysMenu/{systemId}
	 * @Title: selectSysMenu 
	 * @Description: 系统初始化的时候,加载左侧菜单列表
	 * @param @param userName
	 * @param @param systemId
	 * @param @return   
	 * @return RestSampleFacadeResp<List<MenuResVO>>
	 */
	@GET
	@Path("/selectSysMenu/{systemId}")
	public RestSampleFacadeResp<List<MenuResVO>> selectSysMenu(@HeaderParam("userName")String userName, @PathParam("systemId")String systemId);
	
	/**
	 * http://local.trust.com:8341/etheta/management/xinmei/getAllMenus
	 * @Title: getAllMenus 
	 * @Description: 根据系统id获取此系统的所有菜单（根据条件查询如分页条件，菜单名，系统id）
	 * @param @param queryMenuVO
	 * @param @param userName
	 * @param @return   
	 * @return RestSampleFacadeResp<Map<String,Object>>
	 */
	@POST
	@Path("/getAllMenus")
	public RestSampleFacadeResp<Map<String, Object>> getAllMenus(QueryMenuVO queryMenuVO,@HeaderParam("userName")String userName);
	
	/**
	 * http://local.trust.com:8341/etheta/management/xinmei/getRolesByMenuId/{menuId}
	 * @Title: getRolesByMenuId 
	 * @Description: 查询该菜单信息时，获取菜单关联的角色信息
	 * @param @param menuId
	 * @param @return   
	 * @return RestSampleFacadeResp<String>
	 */
	@GET
	@Path("/getRolesByMenuId/{menuId}")
	public RestSampleFacadeResp<String> getRolesByMenuId(@PathParam("menuId")String menuId);
	
	/**
	 * http://local.trust.com:8341/etheta/management/xinmei/addMenu
	 * @Title: insertMenu 
	 * @Description: 新增菜单
	 * @param @param menuVO
	 * @param @param userName
	 * @param @return   
	 * @return RestSampleFacadeResp<String>
	 */
	@POST
	@Path("/addMenu")
	public RestSampleFacadeResp<String> insertMenu(MenuVO menuVO,@HeaderParam("userName")String userName);
	
	/**
	 * http://local.trust.com:8341/etheta/management/xinmei/deleteMenu/{menuId}
	 * @Title: deleteMenu 
	 * @Description:  删除菜单
	 * @param @param menuId
	 * @param @param userName
	 * @param @return   
	 * @return RestSampleFacadeResp<String>
	 */
	@GET
	@Path("/deleteMenu/{menuId}")
	public RestSampleFacadeResp<String> deleteMenu(@PathParam("menuId") String menuId,
														@HeaderParam("userName")String userName);
	/**
	 * http://local.trust.com:8341/etheta/management/xinmei/getAllParentMenus
	 * @Title: getAllParentMenus 
	 * @Description: 修改菜单时查询菜单的父级菜单列表的接口
	 * @param @param queryMenuVO
	 * @param @return   
	 * @return RestSampleFacadeResp<Map<String,Object>>
	 */
	@POST
	@Path("/getAllParentMenus")
	public RestSampleFacadeResp<Map<String, Object>> getAllParentMenus(QueryMenuVO queryMenuVO,@HeaderParam("userName") String userName);			
												
	/**
	 * http://local.trust.com:8341/etheta/management/xinmei/updateMenu
	 * @Title: updateMenu 
	 * @Description: 修改菜单信息
	 * @param @param menuVO
	 * @param @param userName
	 * @param @return   
	 * @return RestSampleFacadeResp<String>
	 */
	@POST
	@Path("/updateMenu")
	public RestSampleFacadeResp<String> updateMenu(MenuVO menuVO,@HeaderParam("userName") String userName);
	
	/**
	 * http://local.trust.com:8341/etheta/management/xinmei/getMenuByPrimaryKey/{menuId}
	 * @Title: getMenuByPrimaryKey 
	 * @Description: 根据系统CD得到该系统信息(系统上及菜单获取showOrder)
	 * @param @param menuId
	 * @param @return   
	 * @return RestSampleFacadeResp<MenuVO>
	 */
	@GET
	@Path("/getMenuByPrimaryKey/{menuId}")
	public RestSampleFacadeResp<MenuVO> getMenuByPrimaryKey(@PathParam("menuId") String menuId);
	
}
