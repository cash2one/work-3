package com.trust.privilege.dao.complexMapper;
import java.util.List;
import java.util.Map;

import com.trust.privilege.model.complex.LogDetail;
/**
 * @ClassName: LogMapper 
 * @Description: 日志dao
 * @author zyn161616@163.com
 * @version 创建时间：2017年4月24日 上午10:50:06
 */
public interface LogMapper {
	
	/**
	 * @Title: insertLog 
	 * @Description: 插入日志信息
	 * @param @param logDetail
	 * @param @return   
	 * @return Integer
	 */
	public Integer insertLog(LogDetail logDetail);
	
	/**
	 * @Title: getLogMsg 
	 * @Description: 查询日志信息
	 * @param @param logDetail
	 * @param @return   
	 * @return List<Map<String,Object>>
	 */
	public List<Map<String,Object>> getLogMsg(LogDetail logDetail);
	
}
