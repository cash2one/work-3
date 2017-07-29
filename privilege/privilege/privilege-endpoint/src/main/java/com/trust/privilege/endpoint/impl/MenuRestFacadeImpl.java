package com.trust.privilege.endpoint.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.trust.privilege.endpoint.facade.MenuRestFacade;
import com.trust.privilege.endpoint.response.RestSampleFacadeResp;
import com.trust.privilege.facade.MenuServerFacade;
import com.trust.privilege.facade.model.MenuVO;
import com.trust.privilege.facade.model.complex.MenuResVO;
import com.trust.privilege.facade.model.complex.QueryMenuVO;
/**
 * @ClassName: MenuRestFacadeImpl 
 * @Description: 菜单模块Rest接口的实现
 * @author zyn161616@163.com
 * @version 创建时间：2017年3月6日 下午5:42:26
 */
public class MenuRestFacadeImpl implements MenuRestFacade{
	
	/**创建日志对象*/
	private static final Logger logger = LoggerFactory.getLogger(MenuRestFacadeImpl.class);

	/**注入菜单facade层*/
	@Resource
	private MenuServerFacade menuServerFacade;
	
	/**
	 * @Title: insertMenu 
	 * @Description: 新增菜单信息
	 * @param @param querySystemPlatformVO
	 * @param @param userName
	 * @param @return   
	 * @return RestSampleFacadeResp<String>
	 */
	@Override
	public RestSampleFacadeResp<String> insertMenu(MenuVO menuVO, String userName) {
		//创建返回对象
		RestSampleFacadeResp<String> resp = new RestSampleFacadeResp<>(false);
				
		try {
			//设置创建人
			menuVO.setCreateUserId(userName);
			//获取返回数据
			Map<String,Object> result = menuServerFacade.insertMenu(menuVO);
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
			//出现错误，日志记录
			logger.info("系统内部异常，添加菜单失败", e);
			//设置返回码
			resp.setErrorCode("201");
			//设置返回消息
			resp.setResultMsg("系统内部异常，添加菜单失败");
		}
			return resp;
	}

	/**
	 * @Title: insertMenu 
	 * @Description: 删除菜单信息
	 * @param @param menuId
	 * @param @param userName
	 * @param @return   
	 * @return RestSampleFacadeResp<String>
	 */
	@Override
	public RestSampleFacadeResp<String> deleteMenu(String menuId, String userName) {
		//创建返回对象
		RestSampleFacadeResp<String> resp = new RestSampleFacadeResp<>(false);
		
		try {
			//获取返回数据
			Map<String,Object> result = menuServerFacade.deleteMenu(menuId,userName);
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
			logger.info("删除菜单失败", e); 
			//设置返回码
			resp.setErrorCode("201");
			//设置返回信息
			resp.setResultMsg("系统内部异常，删除菜单失败");
		}
			return resp;
	}

	/**
	 * @Title: getAllParentMenus 
	 * @Description: 修改菜单时查询菜单的父级菜单列表的接口
	 * @param @param queryMenuVO
	 * @param @return   
	 * @return RestSampleFacadeResp<Map<String,Object>>
	 */
	@Override
	public RestSampleFacadeResp<Map<String, Object>> getAllParentMenus(	QueryMenuVO queryMenuVO,String userName) {
		//创建返回对象
		RestSampleFacadeResp<Map<String, Object>> resp = new RestSampleFacadeResp<>(false);
		
		try {
			queryMenuVO.setUserName(userName);
			//获取所有上级菜单，设置返回数据
			resp.setData(menuServerFacade.getAllParentMenus(queryMenuVO));
			//成功返回true
			resp.setSuccess(true);
		} catch (Exception e) {
			//出现异常，日志记录
			logger.info("查询父级菜单失败", e);
			//设置返回码
			resp.setErrorCode("201");
			//设置返回信息
			resp.setResultMsg("系统内部异常，查询父级菜单失败");
		}
			return resp;
	}
	
	/**
	 * @Title: updateMenu 
	 * @Description: 修改菜单信息
	 * @param @param menuVO
	 * @param @param userName
	 * @param @return   
	 * @return RestSampleFacadeResp<String>
	 */
	@Override
	public RestSampleFacadeResp<String> updateMenu(MenuVO menuVO,String userName) {
		//创建返回对象
		RestSampleFacadeResp<String> resp = new RestSampleFacadeResp<>(false);
		
		try {
			//设置当前操作人
			menuVO.setCreateUserId(userName);
			//获取返回数据
			Map<String,Object> result = menuServerFacade.updateMenu(menuVO);
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
			//出现异常日志记录
			logger.info("修改菜单失败", e);
			//设置返回码
			resp.setErrorCode("201");
			//设置返回信息
			resp.setResultMsg("系统内部异常，修改菜单失败");
		}
			return resp;
	}

	/**
	 * @Title: getAllMenus 
	 * @Description: 根据系统CD获取此系统下所有菜单
	 * @param @param menuVO
	 * @param @param userName
	 * @param @return   
	 * @return RestSampleFacadeResp<Integer>
	 */
	@Override
	public RestSampleFacadeResp<Map<String, Object>> getAllMenus(QueryMenuVO queryMenuVO,String userName) {
		//创建返回对象
		RestSampleFacadeResp<Map<String, Object>> resp = new RestSampleFacadeResp<>(false);
		try {
			//设置用户名
			queryMenuVO.setUserName(userName);
			//查询该系统下所有菜单，设置返回数据
			resp.setData(menuServerFacade.getAllMenus(queryMenuVO));
			//成功返回true
			resp.setSuccess(true);
		} catch (Exception e) {
			//出现异常，日志记录
			logger.info("查询菜单失败", e);
			//设置返回码
			resp.setErrorCode("201");
			//设置返回信息
			resp.setResultMsg("系统内部异常，查询系统下的菜单失败");
		}
			return resp;
	}
	
	/**
	 * @Title: selectSysMenu 
	 * @Description: 系统初始化的时候,加载左侧菜单
	 * @param @param userName
	 * @param @param systemId
	 * @param @return   
	 * @return RestSampleFacadeResp<List<MenuResVO>>
	 */
	@Override
	public RestSampleFacadeResp<List<MenuResVO>> selectSysMenu(String userName, String systemPlatformCd) {
		//创建返回对象
		RestSampleFacadeResp<List<MenuResVO>> resp = new RestSampleFacadeResp<>(false);
		
		try {
			//获取是系统初始化菜单，返回数据
			resp.setData(menuServerFacade.selectSysMenu(userName, systemPlatformCd));
			//成功返回true
			resp.setSuccess(true);
		} catch (Exception e) {
			//出现异常，日志记录
			logger.info("查询菜单列表失败", e);
			//设置返回码
			resp.setErrorCode("201");
			//设置返回信息
			resp.setResultMsg("系统内部异常，查询菜单列表失败");
		}
			return resp;
	}
	
	/**
	 * @Title: getMenuByPrimaryKey 
	 * @Description: 根据系统CD得到该菜单信息(系统上及菜单获取showOrder)
	 * @param @param menuId
	 * @param @return   
	 * @return RestSampleFacadeResp<MenuVO>
	 */
	@Override
	public RestSampleFacadeResp<MenuVO> getMenuByPrimaryKey(String menuId) {
		//创建返回对象
		RestSampleFacadeResp<MenuVO> resp = new RestSampleFacadeResp<>(false);
		
		try {
			//通过菜单id得到该菜单数据
			resp.setData(menuServerFacade.getMenuByPrimaryKey(menuId));
			//成功返回true
			resp.setSuccess(true);
		} catch (Exception e) {
			//出现错误日志记录
			logger.info("查询菜单信息失败", e);
			//设置返回码
			resp.setErrorCode("201");
			//设置返回信息
			resp.setResultMsg("系统内部异常，查询菜单失败");
		}
			return resp;
		
	}
	
	/**
	 * @Title: getRolesByMenuId 
	 * @Description: 查询该菜单信息时，获取菜单关联的角色信息
	 * @param @param menuId
	 * @param @return   
	 * @return RestSampleFacadeResp<String>
	 */
	@Override
	public RestSampleFacadeResp<String> getRolesByMenuId(String menuId) {
		//创建返回对象
		RestSampleFacadeResp<String> resp = new RestSampleFacadeResp<>(false);
		try {
			//设置返回数据
			resp.setData(menuServerFacade.getRolesByMenuId(menuId));
			//成功返回true
			resp.setSuccess(true);
		} catch (Exception e) {
			//出现问题，日志记录
			logger.info("查询该菜单关联角色失败", e);
			//设置错误码
			resp.setErrorCode("201");
			//设置错误信息
			resp.setResultMsg("系统内部异常，查询该菜单关联角色失败");
		}
			return resp;
	}

}
