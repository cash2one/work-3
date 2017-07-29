package com.trust.privilege.convert;

import com.trust.privilege.facade.model.RoleVO;
import com.trust.privilege.facade.model.complex.QueryRoleVO;
import com.trust.privilege.model.Role;
import com.trust.privilege.model.complex.QueryRole;
import com.trust.privilege.model.complex.RoleDo;
import com.trust.privilege.util.CommonUtil;
import com.trust.privilege.util.JavaBeanUtil;
/**
 * @ClassName: RoleConvertUtil 
 * @Description: 角色模块实例转换
 * @author zyn161616@163.com
 * @version 创建时间：2017年4月27日 下午3:46:39
 */
public class RoleConvertUtil {
	
	/**
	 * @Title: convertRoleDoToModel 
	 * @Description: 后台转前台
	 * @param @param role
	 * @param @return   
	 * @return RoleVO
	 */
	public static RoleVO convertRoleDoToModel(Role role){
		//如果参数不为空
		if(CommonUtil.isNotNull(role)){
			//创建返回对象
			RoleVO roleVo = new RoleVO();
			//复制Bean
			JavaBeanUtil.copyBean(role, roleVo);
			//返回
			return roleVo;
		}
			return null;
	}
	
	/**
	 * @Title: convertRoleDoToVO 
	 * @Description: 前台Vo转换后台Do
	 * @param @param roleVO
	 * @param @return   
	 * @return RoleDo
	 */
	public static RoleDo convertRoleDoToVO(RoleVO roleVO){
		//如果参数不为空
		if (CommonUtil.isNotNull(roleVO)) {
			//创建返回对象
			RoleDo roleDo =  new RoleDo();
			//进行赋值
			JavaBeanUtil.copyBean(roleVO,roleDo);
			//返回
			return roleDo;
		}
			return null;
	}
	
	/**
	 * @Title: queryRoleVOConvert 
	 * @Description: 前台查询VO转换后台查询Model
	 * @param @param queryRoleVO
	 * @param @return   
	 * @return QueryRole
	 */
	public static QueryRole queryRoleVOConvert(QueryRoleVO queryRoleVO) {
		//如果参数不为空
		if (CommonUtil.isNotNull(queryRoleVO)) {
			//创建返回对象
			QueryRole queryRole=new QueryRole();
			//进行BeanCopy
			JavaBeanUtil.copyBean(queryRoleVO, queryRole);
			//返回结果
			return queryRole;
		}
			return null;
	}	
	
}
