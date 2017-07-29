package com.trust.privilege.facade;

import java.util.Map;

import com.trust.privilege.facade.model.LogDetailVO;
/**
 * @ClassName: LogServerFacade 
 * @Description: 日志facade接口
 * @author zyn161616@163.com
 * @version 创建时间：2017年3月16日 下午8:41:10
 */
public interface LogServerFacade {
	
	/**
	 * @Title: getLogMsg 
	 * @Description: 查看日志信息
	 * @param @param logDetailVO
	 * @param @return   
	 * @return Map<String,Object>
	 */
	public Map<String,Object> getLogMsg(LogDetailVO logDetailVO);
}
