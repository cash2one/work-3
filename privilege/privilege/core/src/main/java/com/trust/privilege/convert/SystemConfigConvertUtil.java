package com.trust.privilege.convert;

import com.trust.privilege.facade.model.SystemConfigVO;
import com.trust.privilege.model.SystemConfig;
import com.trust.privilege.util.CommonUtil;
import com.trust.privilege.util.JavaBeanUtil;
/**
 * @ClassName: SystemConfigConvertUtil 
 * @Description: 系统配置实例转换
 * @author zyn161616@163.com
 * @version 创建时间：2017年4月28日 下午3:04:45
 */
public class SystemConfigConvertUtil {
	
	/**
	 * @Title: convertVOToDo 
	 * @Description: 系统配置前台Vo转换后台Do
	 * @param @param systemConfigVO
	 * @param @return   
	 * @return SystemConfig
	 */
	public static SystemConfig convertVOToDo(SystemConfigVO systemConfigVO){
		//如果参数不为空
		if (CommonUtil.isNotNull(systemConfigVO)) {
			//创建返回对象
			SystemConfig systemConfig = new SystemConfig();
			//进行复制
			JavaBeanUtil.copyBean(systemConfigVO, systemConfig);
			//返回
			return systemConfig;
		}
		return null;
	}
	
}
