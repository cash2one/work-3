package com.trust.privilege.convert;

import java.util.ArrayList;
import java.util.List;

import com.trust.privilege.facade.model.PrivilegeResVO;
import com.trust.privilege.facade.model.complex.QueryPrivilegeVO;
import com.trust.privilege.model.PrivilegeRes;
import com.trust.privilege.model.complex.PrivilegeResDo;
import com.trust.privilege.model.complex.QueryPrivilege;
import com.trust.privilege.util.CommonUtil;
import com.trust.privilege.util.JavaBeanUtil;

/**
 * @ClassName: PrivilegeConvertUtil 
 * @Description: 资源模块实例转换
 * @author zyn161616@163.com
 * @version 创建时间：2017年4月28日 下午3:26:13
 */
public class PrivilegeConvertUtil {   
	
	/**
	 * @Title: convertDOListToVOList 
	 * @Description: 后台DO集合转前台VO集合
	 * @param @param PrivilegeRess
	 * @param @return   
	 * @return List<PrivilegeResVO>
	 */
	public static List<PrivilegeResVO> convertDOListToVOList(List<PrivilegeRes> PrivilegeRess){
		//如果参数不为空
		if (CommonUtil.isNotNull(PrivilegeRess)) {
			//创建返回对象
			List<PrivilegeResVO> PrivilegeResVOs = new ArrayList<>();
			
			for (PrivilegeRes privilegeRes : PrivilegeRess) {
				//创建转换独享
				PrivilegeResVO privilegeResVO = new PrivilegeResVO();
				//进行类型转换
				JavaBeanUtil.copyBean(privilegeRes, privilegeResVO);
				//转换之后加入集合
				PrivilegeResVOs.add(privilegeResVO);
			}
			//返回
			return PrivilegeResVOs;
		}
			return null;
	}
	
	/**
	 * @Title: privilegeResToPrivilegeResVo 
	 * @Description: 后台Model转换前台Vo
	 * @param @param privilegeResDo
	 * @param @return   
	 * @return PrivilegeResVO
	 */
	public static PrivilegeResVO privilegeResToPrivilegeResVo(PrivilegeResDo privilegeResDo){
		//如果参数不为空
		if (CommonUtil.isNotNull(privilegeResDo)) {
			//创建返回对象
			PrivilegeResVO privilegeResVO = new PrivilegeResVO();
			//进行赋值
			JavaBeanUtil.copyBean(privilegeResDo, privilegeResVO);
			//返回
			return privilegeResVO;
		}
		return null;
	}
	
	/**
	 * @Title: queryPrivilegeVOConvert 
	 * @Description: 前台查询Vo转后台查询Model
	 * @param @param queryPrivilegeVO
	 * @param @return   
	 * @return QueryPrivilege
	 */
	public static QueryPrivilege queryPrivilegeVOConvert(QueryPrivilegeVO queryPrivilegeVO) {
		//如果参数不为空
		if (CommonUtil.isNotNull(queryPrivilegeVO)) {
			//黄建返回对象
			QueryPrivilege queryPrivilege=new QueryPrivilege();
			//进行赋值
			JavaBeanUtil.copyBean(queryPrivilegeVO, queryPrivilege);
			//返回 
			return queryPrivilege;
		}
			return null;
	}
	
	/**
	 * @Title: convertPrivilegeResDoToVO 
	 * @Description: 前台VO转后台DO
	 * @param @param privilegeResVO
	 * @param @return   
	 * @return PrivilegeResDo
	 */
	public static PrivilegeResDo convertPrivilegeResDoToVO(PrivilegeResVO privilegeResVO){
		//如果参数不为空
		if (CommonUtil.isNotNull(privilegeResVO)) {
			//创建返回对象
			PrivilegeResDo privilegeResDo = new PrivilegeResDo();
			//进行赋值
			JavaBeanUtil.copyBean(privilegeResVO,privilegeResDo);
			//返回
			return privilegeResDo;
		}
			return null;
	}
}
