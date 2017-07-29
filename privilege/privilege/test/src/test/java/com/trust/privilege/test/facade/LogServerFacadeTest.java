package com.trust.privilege.test.facade;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.trust.privilege.facade.LogServerFacade;
import com.trust.privilege.facade.model.LogDetailVO;
import com.trust.privilege.test.base.AbstractTestBase;
/**
 * @ClassName: LogServerFacadeTest 
 * @Description: 日志facade单测
 * @author zyn161616@163.com
 * @version 创建时间：2017年4月24日 上午11:05:13
 */
@SuppressWarnings("unchecked")
public class LogServerFacadeTest extends AbstractTestBase{
	
	/**IOC依赖注入logServerFacade实例*/
	private LogServerFacade logServerFacade = (LogServerFacade) this.applicationContext.getBean("logServerFacade");


	/**
	 * @Title: getLogMsgQueryIsNull 
	 * @Description: 单测,多条件查询日志信息(参数为空)
	 * @param    
	 * @return void
	 */
	@Test
	public void getLogMsgQueryIsNull(){
		//调用方法
		Map<String,Object> resultMap = logServerFacade.getLogMsg(new LogDetailVO());
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
		LogDetailVO logDetailVO = new LogDetailVO();
		//设置参数
		logDetailVO.setSystemPlatformName("权限系统");
		//调用方法
		Map<String,Object> resultMap = logServerFacade.getLogMsg(logDetailVO);
		//获取日志信息
		List<Map<String,Object>> logs = (List<Map<String, Object>>) resultMap.get("list");
		//断言
		assertEquals(false, logs.isEmpty());
	}
}
