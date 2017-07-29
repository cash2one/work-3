package com.trust.privilege.service.impl;

import java.io.IOException;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.trust.privilege.service.BulkInitDataService;
import com.trust.privilege.service.OssService;
import com.trust.privilege.util.CommonUtil;
import com.trust.privilege.util.ExcelUtil;
import com.trust.privilege.util.OSSUtil;
import com.xinmei.cms.oss.facade.OSSFacade;
import com.xinmei.cms.oss.model.STSRequestModel;
import com.xinmei.cms.oss.model.STSResponseModel;
/***
 * @ClassName: OssServiceImpl 
 * @Description: OSS对象存储服务实现
 * @author zyn161616@163.com
 * @version 创建时间：2017年1月22日 下午7:52:53
 */
public class OssServiceImpl implements OssService {

	/**创建日志对象*/
	private static final Logger logger = LoggerFactory.getLogger(BulkInitDataServiceImpl.class);
	
	/**注入Sofa-Rpc OssFacade*/
	@Resource
	private OSSFacade oSSFacade;
	/**注入批量化导入数据Service*/
	@Resource
	private BulkInitDataService BulkInitDataService;
	
	
	/**
	 * @Title: bulkImportSystemInitData4OSS 
	 * @Description: 通过OSS得到文件批量导入系统初始化数据
	 * @param @param stsRequestModel
	 * @return String
	 */
	@Override
	public String bulkImportSystemInitData4OSS(STSRequestModel stsRequestModel,String userName) {
		//声明返回对象
		String result = null;
		try {
			//如果对象不为空
			if(CommonUtil.isNotNull(stsRequestModel)){
				//调用RPC服务，请求OSS服务器
				STSResponseModel response = oSSFacade.getSTSSignature4Get(stsRequestModel);
				//解析流文件转换成二维数组存储
				Map<Integer,String[][]> ExcelMap = ExcelUtil.getAllExcelData(OSSUtil.readFile4Oss(response));
				//然后在插入数据库
				return BulkInitDataService.bulkInsertDataToDB(ExcelMap,userName);
			}
		} catch (IOException e) {
			logger.info("批量导入系统初始化Excel数据失败",e);
		}
			return result;
	}

}
