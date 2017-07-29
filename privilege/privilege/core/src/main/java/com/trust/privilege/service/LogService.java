package com.trust.privilege.service;

import java.util.Map;

import com.trust.privilege.model.complex.LogDetail;
/**
 * @ClassName: LogService 
 * @Description: 日志模块Service接口
 * @author zyn161616@163.com
 * @version 创建时间：2017年3月16日 下午8:42:36
 */
public interface LogService {
	
	/**
	 * @Title: getLogMsg 
	 * @Description: 多条件查询日志信息
	 * @param @param logDeail
	 * @param @return   
	 * @return Map<String,Object>
	 */
	public Map<String, Object> getLogMsg(LogDetail logDeail);
	
}
