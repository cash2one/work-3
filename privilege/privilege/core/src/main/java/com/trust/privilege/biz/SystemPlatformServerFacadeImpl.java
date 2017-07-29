package com.trust.privilege.biz;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.trust.privilege.convert.SystemConfigConvertUtil;
import com.trust.privilege.convert.SystemPlatformConvertUtil;
import com.trust.privilege.facade.SystemPlatformServerFacade;
import com.trust.privilege.facade.model.SystemConfigVO;
import com.trust.privilege.facade.model.SystemPlatformVO;
import com.trust.privilege.facade.model.complex.QuerySystemPlatformVO;
import com.trust.privilege.service.InitPortalPageService;
import com.trust.privilege.service.SystemPlatFormService;
/**
 * @ClassName: SystemPlatformServerFacadeImpl 
 * @Description: 系统操作facade实现
 * @author zyn161616@163.com
 * @version 创建时间：2017年3月10日 上午11:11:43
 */
public class SystemPlatformServerFacadeImpl implements SystemPlatformServerFacade{
	
	/**依赖注入系统Service层*/
	@Resource
	private SystemPlatFormService systemService;
	/**依赖注入初始化Server，用作portal接口*/
	@Resource
	private InitPortalPageService initPortalPageService;
	
	/**
	 * @Title: selectSystemPlatform 
	 * @Description: 通过条件查询系统信息
	 * @param @param systemPlatFormName
	 * @param @param userName
	 * @param @return   
	 * @return Map<String,Object>
	 */
	@Override
	public Map<String,Object> selectSystemPlatform(QuerySystemPlatformVO querySystemPlatformVO) {
		return  systemService.selectSystemPlatform(querySystemPlatformVO);
	}

	/**
	 * @Title: selectSystemPlatform 
	 * @Description: 新增系统信息
	 * @param @param systemPlatformVO
	 * @return Map<String,Object>
	 */
	@Override
	public Map<String,Object> insertSystem(SystemPlatformVO systemPlatformVO) {
		return systemService.insertSystem(
				SystemPlatformConvertUtil.convertSystemPlatformDoToVO(systemPlatformVO)); 
	}

	/**
	 * @Title: deleteSystemPlateForm 
	 * @Description: 通过系统CD删除该系统信息
	 * @param @param systemPlatformVO
	 * @return Map<String,Object>
	 */
	@Override
	public Map<String,Object> deleteSystemPlateForm(String systemPlatformCd,String user) {
		return systemService.deleteSystem(systemPlatformCd,user);
	}

	/**
	 * @Title: upadateSystemPlatform 
	 * @Description: 修改系统信息
	 * @param @param systemPlatformVO
	 * @return Map<String,Object>
	 */
	@Override
	public Map<String,Object> upadateSystemPlatform(SystemPlatformVO systemPlatformVO) {
		return systemService.updateSystem(
				SystemPlatformConvertUtil.convertSystemPlatformDoToVO(systemPlatformVO));
	}

	/**
	 * @Title: getAllSystem 
	 * @Description: 查询日志时候，所属系统的接口
	 * @param @return   							
	 * @return List<SystemPlatformVO>
	 */
	@Override
	public List<SystemPlatformVO> getAllSystem() {
		return 	SystemPlatformConvertUtil
					.convertDoListToModelList(systemService.getAllSystem());
	}
	
	//-----------------------------------------------------------------
	
	/**
	 * @Title: getAllOffenUseOption 
	 * @Description: 查询系统常用功能列表
	 * @param @param systemConfigVO
	 * @param @return   
	 * @return Map<String,Object>
	 */
	@Override
	public Map<String,Object> getAllOffenUseOption(SystemConfigVO systemConfigVO) {
		return systemService.getAllOffenUseOption(systemConfigVO.getUserName());
	}
	
	/**
	 * @Title: insertOffenUseOption 
	 * @Description: 插入系统常用功能
	 * @param @param systemConfigVO
	 * @param @return   
	 * @return Integer
	 */
	@Override
	public String insertOffenUseOption(SystemConfigVO systemConfigVO) {
		return systemService.insertOffenUseOption(SystemConfigConvertUtil.convertVOToDo(systemConfigVO));
	}
	
	/**
	 * @Title: deleteOffenUseOption 
	 * @Description: 通过常用系统功能的ID删除系统常用功能
	 * @param @param systemConfigId
	 * @param @return   
	 * @return String
	 */
	@Override
	public String deleteOffenUseOption(String systemConfigId) {
		return systemService.deleteOffenUseOption(systemConfigId);
	}
	
}
