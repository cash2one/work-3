package com.trust.privilege.service;

import com.xinmei.cms.oss.model.STSRequestModel;

/***
 * @ClassName: OssService 
 * @Description: OSS的Service
 * @author zyn161616@163.com
 * @version 创建时间：2017年1月22日 下午7:52:02
 */
public interface OssService {
	
	/**
	 * @Title: bulkImportSystemInitData4OSS 
	 * @Description: 通过OSS得到文件批量导入系统初始化数据
	 * @param @param stsRequestModel
	 * @param @param userName
	 * @param @return   
	 * @return String
	 */
	public String bulkImportSystemInitData4OSS(STSRequestModel stsRequestModel,String userName);
}
