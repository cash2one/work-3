package com.trust.privilege.convert;
import java.util.ArrayList;
import java.util.List;

import com.trust.privilege.facade.model.SystemPlatformVO;
import com.trust.privilege.model.SystemPlatform;
import com.trust.privilege.model.complex.SystemPlatformDo;
import com.trust.privilege.util.CommonUtil;
import com.trust.privilege.util.JavaBeanUtil;
/**
 * @ClassName: SystemPlatformConvertUtils 
 * @Description: 系统模块实例转换
 * @author zyn161616@163.com
 * @version 创建时间：2017年4月28日 下午3:03:00
 */
public class SystemPlatformConvertUtil {
	
	/**
	 * @Title: convertDoListToModelList 
	 * @Description: 后台DO集合转前台VO
	 * @param @param SystemPlatforms
	 * @param @return   
	 * @return List<SystemPlatformVO>
	 */
	public static List<SystemPlatformVO> convertDoListToModelList(List<SystemPlatform> SystemPlatforms){
		//如果参数不为空
		if(CommonUtil.isNotNull(SystemPlatforms)){
			//创建返回对象
			List<SystemPlatformVO> SystemPlatformVOs = new ArrayList<SystemPlatformVO>();
			//循环赋值
			for (SystemPlatform SystemPlatform : SystemPlatforms) {
				//创建前台VO对象
				SystemPlatformVO systemPlatformVO = new SystemPlatformVO();
				//进行copy
				JavaBeanUtil.copyBean(SystemPlatform, systemPlatformVO);
				//放入集合
				SystemPlatformVOs.add(systemPlatformVO);
			}
			return SystemPlatformVOs;
		}
		return null;
	}
	
	/**
	 * @Title: convertSystemPlatformDoToVO 
	 * @Description: 前台Vo转后台Do
	 * @param @param systemPlatformVO
	 * @param @return   
	 * @return SystemPlatformDo
	 */
	public static SystemPlatformDo convertSystemPlatformDoToVO(SystemPlatformVO systemPlatformVO){
		//如果参数不为空
		if (CommonUtil.isNotNull(systemPlatformVO)) {
			//创建返回对象
			SystemPlatformDo systemPlatformDo =  new SystemPlatformDo();
			//进行赋值
			JavaBeanUtil.copyBean(systemPlatformVO,systemPlatformDo);
			//返回
			return systemPlatformDo;
		}
			return null;
	}
}
