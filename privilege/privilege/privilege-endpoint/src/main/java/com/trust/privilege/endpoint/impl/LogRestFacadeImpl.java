package com.trust.privilege.endpoint.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.trust.privilege.endpoint.facade.LogRestFacade;
import com.trust.privilege.endpoint.response.RestSampleFacadeResp;
import com.trust.privilege.facade.LogServerFacade;
import com.trust.privilege.facade.model.LogDetailVO;
/**
 * @ClassName: LogRestFacadeImpl 
 * @Description: 日志模块Rest接口实现
 * @author zyn161616@163.com
 * @version 创建时间：2017年3月23日 下午3:44:21
 */
public class LogRestFacadeImpl implements LogRestFacade{

	/**创建日志对象*/
	private static final Logger logger = LogManager.getLogger(LogRestFacadeImpl.class.getName());

	/**依赖入住日志facade*/
	@Resource
	private LogServerFacade logServerFacade;
	
	/**
	 * @Title: getLogMsg 
	 * @Description: 查看日志信息
	 * @param @param logDetailVO
	 * @param @return   
	 * @return RestSampleFacadeResp<Map<String,Object>>
	 */
	@Override
	public RestSampleFacadeResp<Map<String, Object>> getLogMsg(LogDetailVO logDetailVO, String userName) {
		//创建返回对象
		RestSampleFacadeResp<Map<String, Object>> resp = new RestSampleFacadeResp<>(false);
		
		try {
			//设置返回数据
			resp.setData(logServerFacade.getLogMsg(logDetailVO));
			//成功返回true
			resp.setSuccess(true);
		} catch (Exception e) {
			//出现异常，日志记录
			logger.info("查询日志信息失败", e);
			//设置返回码
			resp.setErrorCode("201");
			//设置返回信息
			resp.setResultMsg("系统内部异常，查询日志信息失败");
		}
			return resp;
	}

}
