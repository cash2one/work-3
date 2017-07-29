package com.trust.privilege.facade;

import com.trust.privilege.facade.model.OssRequestVO;
import com.trust.privilege.facade.model.OssResponseVO;

/***
 * @ClassName: ImportFile4OSSFacade 
 * @Description:  通过OSS导入文件
 * @author zyn161616@163.com
 * @version 创建时间：2017年1月22日 下午8:01:37
 */
public interface ImportFile4OSSFacade {
	
	/**
	 * @Title: getSTSSignature4Get 
	 * @Description: 向OSS索取访问凭证
	 * @param @param OssRequestVO
	 * @param @return   
	 * @return STSResponseModelVO
	 */
	public OssResponseVO getSTSSignature4Get(OssRequestVO OssRequestVO) ;
	
	/**
	 * @Title: bulkInsertIniDataToDB 
	 * @Description: 通过OSS下载文件,批量插入初始化数据到数据库
	 * @param @param ossRequestVO
	 * @param @param userNmae
	 * @param @return   
	 * @return String
	 */
	public String bulkInsertIniDataToDBbyOss(OssRequestVO ossRequestVO,String userNmae);
	
}
