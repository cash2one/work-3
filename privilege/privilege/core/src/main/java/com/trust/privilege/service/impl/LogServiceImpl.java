package com.trust.privilege.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.trust.privilege.dao.SystemPlatformMapper;
import com.trust.privilege.dao.complexMapper.LogMapper;
import com.trust.privilege.model.complex.LogDetail;
import com.trust.privilege.service.LogService;

/**
 * @ClassName: LogService 
 * @Description: 日志模块Service接口实现
 * @author zyn161616@163.com
 * @version 创建时间：2017年3月16日 下午8:42:36
 */
public class LogServiceImpl implements LogService{
	
	/**依赖注入日志Mapper*/
	@Resource
	private LogMapper logMapper;
	
	/**依赖注入系统Mapper*/
	@Resource
	private SystemPlatformMapper systemPlatformMapper;

	/**
	 * @Title: getLogMsg 
	 * @Description: 多条件查询日志信息
	 * @param @param logDetailVO
	 * @param @return   
	 * @return Map<String, Object>
	 */
	@Override
	public Map<String, Object> getLogMsg(LogDetail logDetail) {
		//创建返回Map
		Map<String, Object> reuseMap = new HashMap<>();
		//查询日志列表
		List<Map<String,Object>> logs = logMapper.getLogMsg(logDetail);
		//将查询到的日志列表放进map中返回
		reuseMap.put("list", logs); 
		//返回数据
		return reuseMap;
	}
	
	
}
