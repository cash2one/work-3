package com.trust.privilege.endpoint.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.trust.privilege.endpoint.facade.ShowPortalRestFacade;
import com.trust.privilege.endpoint.response.RestSampleFacadeResp;
import com.trust.privilege.facade.InitPortalMenuFacade;
import com.trust.privilege.facade.SystemUserServerFacade;
import com.trust.privilege.facade.model.complex.MenuResVO;

/***
 * @ClassName: ShowPortalRestFacadeImpl 
 * @Description: 门户网站接口实现
 * @author zyn161616@163.com
 * @version 创建时间：2017年1月18日 下午7:44:09
 */
public class ShowPortalRestFacadeImpl implements ShowPortalRestFacade {

	/**创建日志记录对象*/
	private static final Logger logger = 
				LoggerFactory.getLogger(ShowPortalRestFacadeImpl.class.getName());
	
	/**注入facade层*/
	@Resource
	private InitPortalMenuFacade initPortalMenuFacade;
	@Resource
	private SystemUserServerFacade sysUserFacade;
	
	
	/**
	 * SSO登录之后，渲染该用户所拥有的左侧菜单栏数据
	 */
	@Override
	public RestSampleFacadeResp<List<MenuResVO>> showPortalMenu(String userName) {
		//创建返回对象
		RestSampleFacadeResp<List<MenuResVO>> resp = new RestSampleFacadeResp<>(false);
		try {
			
			//返回数据
			resp.setData(sysUserFacade.getInitMsg(userName, "4"));
			//成功返回true
			resp.setSuccess(true);
		} catch (Exception e) {
			//日志记录错误
			logger.info("获取权限列表失败",e);
			resp.setErrorCode("201");
			resp.setResultMsg("获取权限列表失败");
		}
			//返回这个数据
			return resp;
	}
	
	/**
	 * @Title: showPortal 
	 * @Description: SSO登录之后，渲染门户网站的数据接口实现
	 * @param @param QueryPrivilegeVO
	 * @param @param userName
	 * @param @return   
	 * @return RestSampleFacadeResp<Map<String,Object>>
	 */
	@Override
	public RestSampleFacadeResp<Map<String,Object>> showPortal(String userName) {
		//创建返回对象
		RestSampleFacadeResp<Map<String,Object>> resp = new  RestSampleFacadeResp<>(false);
		try {
			//通过用户名，查询该用户可访问的系统,以及常用功能列表,返回前台数据
			resp.setData(initPortalMenuFacade.getProtMenuData4UserName(userName,"4"));
			resp.setSuccess(true);
		} catch (Exception e) {
			//日志记录
			logger.info("获取用户左侧菜单列表失败",e);
			resp.setErrorCode("201");
			resp.setResultMsg("获取用户左侧菜单列表失败");
		}
			//返回数据
			return resp;
	}

}
