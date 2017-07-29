package com.trust.privilege.convert;

import com.trust.privilege.facade.model.SysUserModel;
import com.trust.privilege.model.complex.UserDo;
import com.trust.privilege.util.CommonUtil;
import com.trust.privilege.util.JavaBeanUtil;
/**
 * @ClassName: SysUserConvertUtils 
 * @Description: 用户模块实例转换
 * @author zyn161616@163.com
 * @version 创建时间：2017年4月28日 下午3:01:57
 */
public class SysUserConvertUtil {
	
	/**
	 * @Title: sysUserDo 
	 * @Description: 前台VO转换后台model
	 * @param @param sysUserModel
	 * @param @return   
	 * @return UserDo
	 */
	public static UserDo userVOToModel(SysUserModel sysUserModel){
		//如果参数不为空
		if(CommonUtil.isNotNull(sysUserModel)){
			//创建后台Do对象
			UserDo sysUserDo=new UserDo();
			//进行复制
			JavaBeanUtil.copyBean(sysUserModel, sysUserDo);
			//返回结果
			return sysUserDo;
		}
		return null;
	}

	/**
	 * @Title: sysUserDoToUserModel 
	 * @Description: 后台Do转换前台Model
	 * @param @param sysUserDo
	 * @param @return   
	 * @return SysUserModel
	 */
	public static SysUserModel sysUserDoToUserModel(UserDo sysUserDo){
		//如果参数不为空
		if(CommonUtil.isNotNull(sysUserDo)){
			//创建返回对象
			SysUserModel sysUserModel=new SysUserModel();
			//进行赋值Bean
			JavaBeanUtil.copyBean(sysUserDo, sysUserModel);
			//返回这个对象
			return sysUserModel;
		}
		return null;
	}
}
