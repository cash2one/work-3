package com.trust.privilege.endpoint.facade;

import java.util.Map;

import javax.annotation.security.PermitAll;
import javax.ws.rs.Consumes;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.trust.privilege.endpoint.constants.URLConstants;
import com.trust.privilege.endpoint.response.RestSampleFacadeResp;
import com.trust.privilege.facade.model.LogDetailVO;

/**
 * @ClassName: LogRestFacade 
 * @Description: 日志模块Rest接口
 * @author zyn161616@163.com
 * @version 创建时间：2017年3月23日 下午3:40:32
 */
@Path(URLConstants.REST_API_PEFFIX+"/xinmei")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@PermitAll
public interface LogRestFacade {
	
	/**
	 * http://local.trust.com:8341/etheta/management/xinmei/button/log/getLogMsg
	 * @Title: getLogMsg 
	 * @Description: 查看日志信息
	 * @param @param logDetailVO
	 * @param @return   
	 * @return RestSampleFacadeResp<Map<String,Object>>
	 */
	@POST
	@Path("/button/log/getLogMsg")
	public RestSampleFacadeResp<Map<String,Object>> getLogMsg(LogDetailVO logDetailVO,@HeaderParam("userName")String userName);
	
}
