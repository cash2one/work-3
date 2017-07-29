package com.trust.privilege.test.service;

import java.util.List;
import java.util.Map;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

import com.trust.privilege.model.complex.LogDetail;
import com.trust.privilege.service.LogService;
import com.trust.privilege.test.base.AbstractTestBase;

/**
 * @ClassName: LogServiceTest 
 * @Description: 日志Service单测
 * @author zyn161616@163.com
 * @version 创建时间：2017年4月24日 上午10:52:28
 */
@SuppressWarnings("unchecked")
public class LogServiceTest extends AbstractTestBase{
	
	/**IOC容器获取logService实例*/
	private LogService logService = (LogService)this.applicationContext.getBean("logService");
	
	/**
	 * @Title: getLogMsgQueryIsNull 
	 * @Description: 单测,多条件查询日志信息(参数为空)
	 * @param    
	 * @return void
	 */
	@Test
	public void getLogMsgQueryIsNull(){
		//调用方法
		Map<String,Object> resultMap = logService.getLogMsg(new LogDetail());
		//获取日志信息
		List<Map<String,Object>> logs = (List<Map<String, Object>>) resultMap.get("list");
		//断言
		assertEquals(false, logs.isEmpty());
	}
	
	/**
	 * @Title: getLogMsgTest 
	 * @Description: 单测,多条件查询日志信息
	 * @param    
	 * @return void
	 */
	@Test
	public void getLogMsgTest(){
		//创建日志对象
		LogDetail logDetail = new LogDetail();
		//设置参数
		logDetail.setSystemPlatformName("权限系统");
		//调用方法
		Map<String,Object> resultMap = logService.getLogMsg(logDetail);
		//获取日志信息
		List<Map<String,Object>> logs = (List<Map<String, Object>>) resultMap.get("list");
		//断言
		assertEquals(false, logs.isEmpty());
	}
	
}
