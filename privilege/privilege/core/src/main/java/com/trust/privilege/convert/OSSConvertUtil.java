package com.trust.privilege.convert;

import com.trust.privilege.facade.model.OssRequestVO;
import com.trust.privilege.facade.model.OssResponseVO;
import com.trust.privilege.util.CommonUtil;
import com.trust.privilege.util.JavaBeanUtil;
import com.xinmei.cms.oss.model.STSRequestModel;
import com.xinmei.cms.oss.model.STSResponseModel;

/***
 * @ClassName: OSSConvertUtils 
 * @Description: OSS对象转换
 * @author zyn161616@163.com
 * @version 创建时间：2017年1月20日 下午7:37:48
 */
public class OSSConvertUtil {
	
	/**
	 * @Title: VoToModel 
	 * @Description: 前台Vo转后台Model，从OSS获取上传凭证
	 * @param @param ossRequestVO   
	 * @return void
	 */
	public static STSRequestModel VoToModel(OssRequestVO ossRequestVO){
		//如果参数不为空
		if (CommonUtil.isNotNull(ossRequestVO)) {
			//创建返回对象
			STSRequestModel sTSRequestModel = new STSRequestModel();
			//类型转换
			JavaBeanUtil.copyBean(ossRequestVO, sTSRequestModel);
			//返回数据
			return sTSRequestModel;
		}
		return null;
	}
	
	/**
	 * @Title: ModelToVo 
	 * @Description: 后台得到的返回对象转换前台需要的model
	 * @param @param ossRequestVO
	 * @param @return   
	 * @return STSRequestModel
	 */
	public static OssResponseVO ModelToVo(STSResponseModel sTSResponsetModel){
		//如果参数不为空
		if (CommonUtil.isNotNull(sTSResponsetModel)) {
			//创建访问OSS服务器对象
			OssResponseVO ossResponseVO = new OssResponseVO();
			//类型转换
			JavaBeanUtil.copyBean(sTSResponsetModel, ossResponseVO);
			//返回转换后的对象
			return ossResponseVO;
		}
		return null;
	}
	
}
