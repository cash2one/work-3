package com.trust.privilege.facade;

import java.util.List;
import java.util.Map;

import com.trust.privilege.facade.model.SystemConfigVO;
import com.trust.privilege.facade.model.SystemPlatformVO;
import com.trust.privilege.facade.model.complex.QuerySystemPlatformVO;
/**
 * @ClassName: SystemPlatformServerFacade 
 * @Description: 系统操作facade接口
 * @author zyn161616@163.com
 * @version 创建时间：2017年3月10日 上午11:11:11
 */
public interface SystemPlatformServerFacade {
	
	/**
	 * @Title: selectSystemPlatform 
	 * @Description: 通过条件查询系统信息
	 * @param @param querySystemPlatformVO
	 * @param @return   
	 * @return Map<String,Object>
	 */
	public Map<String,Object> selectSystemPlatform(QuerySystemPlatformVO querySystemPlatformVO);
	
	/**
	 * @Title: getAllSystem 
	 * @Description: 查询数据库中的系统列表，多条件查询日志时使用
	 * @param @return   
	 * @return List<SystemPlatformVO>
	 */
	public List<SystemPlatformVO> getAllSystem();
	
    /**
     * @Title: addSys 
     * @Description: 新增系统信息
     * @param @param systemPlatformVO
     * @param @return   
     * @return Map<String,Object>
     */
    public Map<String,Object> insertSystem(SystemPlatformVO systemPlatformVO);
    
    /**
     * @Title: upadateSystemPlatform 
     * @Description: 修改系统信息
     * @param @param systemPlatformVO
     * @param @return   
     * @return Map<String,Object> 
     */
    public Map<String,Object> upadateSystemPlatform(SystemPlatformVO systemPlatformVO);
    
    /**
     * @Title: deleteSystemPlateForm 
     * @Description: 根据系统CD删除该系统信息
     * @param @param systemId
     * @param @param userName
     * @param @return   
     * @return Map<String,Object> 
     */
    public Map<String,Object> deleteSystemPlateForm(String systemId,String userName);

    //-----------------------------------------------------------------
	
	/**
	 * @Title: getAllOffenUseOption 
	 * @Description: 查询系统常用功能列表
	 * @param @param systemConfigVO
	 * @param @return   
	 * @return Map<String,Object>
	 */
	public Map<String,Object> getAllOffenUseOption(SystemConfigVO systemConfigVO);
	
	/**
	 * @Title: insertOffenUseOption 
	 * @Description: 插入系统常用功能
	 * @param @param systemConfigVO
	 * @param @return   
	 * @return String
	 */
	public String insertOffenUseOption(SystemConfigVO systemConfigVO);
	
	/**
	 * @Title: deleteOffenUseOption 
	 * @Description: 通过常用系统功能的ID删除系统常用功能
	 * @param @param systemConfigId
	 * @param @return   
	 * @return String
	 */
	public String deleteOffenUseOption(String systemConfigId);
	
}
