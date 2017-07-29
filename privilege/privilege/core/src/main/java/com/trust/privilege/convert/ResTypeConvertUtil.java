package com.trust.privilege.convert;

import java.util.ArrayList;
import java.util.List;

import com.trust.privilege.facade.model.PrivilegeResTypeVO;
import com.trust.privilege.model.PrivilegeResType;
import com.trust.privilege.util.CommonUtil;
import com.trust.privilege.util.JavaBeanUtil;
/**
 * @ClassName: ResTypeConvertUtil 
 * @Description: 资源类型实例转换
 * @author zyn161616@163.com
 * @version 创建时间：2017年4月28日 下午3:10:54
 */
public class ResTypeConvertUtil {
	
	/**
	 * @Title: convertDoToVO 
	 * @Description: 后台Do转换前台Vo
	 * @param @param privilegeResType
	 * @param @return   
	 * @return PrivilegeResTypeVO
	 */
	public static PrivilegeResTypeVO convertDoToVO(PrivilegeResType privilegeResType){
		//如果参数不为空
		if (CommonUtil.isNotNull(privilegeResType)) {
			//创建返回对象
			PrivilegeResTypeVO privilegeResTypeVO = new PrivilegeResTypeVO();
			//进行赋值
			JavaBeanUtil.copyBean(privilegeResType, privilegeResTypeVO);
			//返回
			return privilegeResTypeVO;
		}
			return null;
	}
	
	/**
	 * @Title: convertListDoToListVo 
	 * @Description: 后台DO集合转换前台VO集合
	 * @param @param PrivilegeResType
	 * @param @return   
	 * @return List<PrivilegeResTypeVO>
	 */
	public static List<PrivilegeResTypeVO> convertListDoToListVo(List<PrivilegeResType> PrivilegeResTypes){
		//如果参数不为空
		if (CommonUtil.isNotNull(PrivilegeResTypes)) {
			//创建返回List
			List<PrivilegeResTypeVO> PrivilegeResTypeVOs=new ArrayList<>();
			//循环取值
			for(PrivilegeResType PrivilegeResType : PrivilegeResTypes){
				//类型转换并加入返回集合中
				PrivilegeResTypeVOs.add(ResTypeConvertUtil.convertDoToVO(PrivilegeResType));
			}
			//返回这个集合
			return PrivilegeResTypeVOs;
		}
		return null;
	}
	
}
