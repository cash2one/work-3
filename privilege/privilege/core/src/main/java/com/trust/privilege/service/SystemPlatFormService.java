package com.trust.privilege.service;

import java.util.List;
import java.util.Map;

import com.trust.privilege.facade.model.complex.QuerySystemPlatformVO;
import com.trust.privilege.model.SystemConfig;
import com.trust.privilege.model.SystemPlatform;
/**
 * @ClassName: SystemService 
 * @Description: 系统操作Service接口
 * @author zyn161616@163.com
 * @version 创建时间：2017年3月10日 上午11:13:03
 */
public interface SystemPlatFormService {
	
	/**
	 * @Title: selectSystemPlatform 
	 * @Description: 多条件查询所有的系统
	 * @param @param systemPlatFormName
	 * @param @param userName
	 * @param @return   
	 * @return Map<String,Object>
	 */
	public Map<String,Object> selectSystemPlatform(QuerySystemPlatformVO querySystemPlatformVO); 	
	
	/**
	 * @Title: insertSystem 
	 * @Description: 新增系统信息
	 * @param @param systemDo
	 * @param @param user
	 * @param @return   
	 * @return Map<String,Object>
	 */
	public Map<String,Object> insertSystem(SystemPlatform systemDo);
	
	/**
	 * @Title: deleteSystem 
	 * @Description: 根据系统CD删除该系统信息
	 * @param @param systemId
	 * @param @param userName
	 * @param @return   
	 * @return Map<String,Object>
	 */
	public Map<String,Object> deleteSystem(String systemId,String userName);
	
	/**
	 * @Title: updateSystem 
	 * @Description: 修改系统信息
	 * @param @param systemPlatform
	 * @param @return   
	 * @return Map<String,Object>
	 */
	public Map<String,Object> updateSystem(SystemPlatform systemPlatform);
	
	/**
	 * @Title: getAllSystem 
	 * @Description: 查询数据库中的系统列表，多条件查询日志时使用
	 * @param @param systemPlatform
	 * @param @return   
	 * @return List<SystemPlatform>
	 */
	public List<SystemPlatform> getAllSystem();
	
	/**
	 * @Title: getSystem 
	 * @Description: 通过条件获取系统数据
	 * @param @param systemName
	 * @param @param stateCD
	 * @param @return   
	 * @return List<SystemPlatform>
	 */
	public List<SystemPlatform> getSystem(String systemName,String stateCD);
	
//-------------------------------------------------------------------
	
	/**
	 * @Title: getAllOffenUseOption 
	 * @Description: 获取常用系统功能列表(portal页面)
	 * @param @param userName
	 * @param @return   
	 * @return Map<String,Object>
	 */
	public Map<String,Object> getAllOffenUseOption(String userName);

	/**
	 * @Title: insertOffenUseOption 
	 * @Description: 插入系统配置常用功能
	 * @param @param systemConfig
	 * @param @return   
	 * @return String
	 */
	public String insertOffenUseOption(SystemConfig systemConfig);
	
	/**
	 * @Title: deleteOffenUseOption 
	 * @Description: 通过常用功能ID删除系统常用功能
	 * @param @param systemConfigId
	 * @param @return   
	 * @return String
	 */
	public String deleteOffenUseOption(String systemConfigId);
	
}
