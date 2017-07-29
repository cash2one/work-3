package com.trust.privilege.convert;

import com.trust.privilege.facade.model.GroupModel;
import com.trust.privilege.model.complex.GroupDo;
import com.trust.privilege.util.CommonUtil;
import com.trust.privilege.util.JavaBeanUtil;
/**
 * @ClassName: GroupConvertUtils 
 * @Description: 用户组模块实例转换
 * @author zyn161616@163.com
 * @version 创建时间：2017年4月28日 下午3:17:15
 */
public class GroupConvertUtil {
	
	/**
	 * @Title: convertVOlToModel 
	 * @Description: 前台VO转后台DO
	 * @param @param groupModel
	 * @param @return   
	 * @return GroupDo
	 */
	public static GroupDo convertVOlToModel(GroupModel groupModel){
		//如果参数不为空
		if (CommonUtil.isNotNull(groupModel)) {
			//创建返回对象
			GroupDo groupDo = new GroupDo();
			//赋值
			JavaBeanUtil.copyBean(groupModel, groupDo);
			return groupDo;
		}
			return null;
	}
	
	
}
