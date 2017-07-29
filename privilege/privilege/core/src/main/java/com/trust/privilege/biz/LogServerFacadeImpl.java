package com.trust.privilege.biz;

import java.util.Map;

import javax.annotation.Resource;

import com.trust.privilege.convert.LogDetailConvertUtil;
import com.trust.privilege.facade.LogServerFacade;
import com.trust.privilege.facade.model.LogDetailVO;
import com.trust.privilege.service.LogService;
/**
 * @ClassName: LogServerFacadeImpl 
 * @Description: 日志模块facade接口实现
 * @author zyn161616@163.com
 * @version 创建时间：2017年3月16日 下午8:41:37
 */
public class LogServerFacadeImpl implements LogServerFacade{
	
	/**依赖注入日志Service*/
	@Resource
	private LogService logService;
	
	/**
	 * @ClassName: LogServerFacade 
	 * @Description: 日志facade接口
	 * @author zyn161616@163.com
	 * @version 创建时间：2017年3月16日 下午8:41:10
	 */
	@Override
	public Map<String, Object> getLogMsg(LogDetailVO logDetailVO) {
		return logService.getLogMsg(LogDetailConvertUtil.comverModelToDo(logDetailVO));
	}
	
}
