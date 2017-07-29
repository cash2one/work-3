package com.trust.privilege.endpoint.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.trust.privilege.endpoint.facade.BulkUploadFileRestFacade;
import com.trust.privilege.endpoint.response.RestSampleFacadeResp;
import com.trust.privilege.facade.ImportFile4OSSFacade;
import com.trust.privilege.facade.model.OssRequestVO;
import com.trust.privilege.facade.model.OssResponseVO;
/***
 * @ClassName: BulkUploadFileRestFacadeImpl 
 * @Description: 批量上传文件接口实现
 * @author zyn161616@163.com
 * @version 创建时间：2017年1月18日 下午7:16:21
 */
public class BulkUploadFileRestFacadeImpl implements BulkUploadFileRestFacade {
	/**创建日志对象*/
	private static final Logger logger = LoggerFactory
							.getLogger(BulkUploadFileRestFacadeImpl.class.getName());
	/**注入facade层*/
	@Resource
	private ImportFile4OSSFacade importFile4OSSFacade;
	
	/**向OSS服务器获取凭证*/
	@Override
	public RestSampleFacadeResp<OssResponseVO> getSTSSignature4Get(OssRequestVO OssRequestVO) {
		//创建返回对象
		RestSampleFacadeResp<OssResponseVO> resp = new RestSampleFacadeResp<>(false);
		
		try {
			//调用facade服务得到访问OSS凭证
			resp.setData(importFile4OSSFacade.getSTSSignature4Get(OssRequestVO));
			resp.setSuccess(true);
		} catch (Exception e) {
			//日志记录下错误
			logger.info("获取OSS凭证失败",e);
			resp.setResultMsg("获取OSS凭证失败");
			resp.setErrorCode("0");
		}
			//向前端返回这段数据
			return resp;
	}
	
	/**批量上传初始化数据(Excel导入)*/
	@Override
	public RestSampleFacadeResp<String> uploadInitData(OssRequestVO ossRequestVO,String userName) {
		//创建返回对象
		RestSampleFacadeResp<String> resp = new RestSampleFacadeResp<>(false);
		
		try {
			//设置返回参数
			resp.setData(importFile4OSSFacade.bulkInsertIniDataToDBbyOss(ossRequestVO,userName));
			resp.setSuccess(true);
		}catch(Exception e){
			logger.info("Excel批量导入数据失败",e);
			//如果异常
			resp.setErrorCode("201");
			resp.setResultMsg("Excel批量导入数据失败");
		}
			//向前端返回这段数据
			return resp;
	}
	

}
