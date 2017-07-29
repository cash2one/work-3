package com.trust.privilege.convert;

import com.trust.privilege.facade.model.LogDetailVO;
import com.trust.privilege.model.complex.LogDetail;
import com.trust.privilege.util.CommonUtil;
import com.trust.privilege.util.JavaBeanUtil;
/**
 * @ClassName: LogDetailConvertUtil 
 * @Description: 日志模块实例转换
 * @author zyn161616@163.com
 * @version 创建时间：2017年4月28日 下午3:15:59
 */
public class LogDetailConvertUtil {
	
	/**
	 * @Title: comverModelToDo 
	 * @Description: 前台VO转换后台DO
	 * @param @param logDetailVO
	 * @param @return   
	 * @return LogDetail
	 */
	public static LogDetail comverModelToDo(LogDetailVO logDetailVO){
		//如果参数不为空
		if (CommonUtil.isNotNull(logDetailVO)) {
			//创建返回对象
			LogDetail logDetail = new LogDetail();
			//进行copy
			JavaBeanUtil.copyBean(logDetailVO, logDetail);
			//返回这个对象
			return logDetail;
		}
			return null;
	}
	
}
