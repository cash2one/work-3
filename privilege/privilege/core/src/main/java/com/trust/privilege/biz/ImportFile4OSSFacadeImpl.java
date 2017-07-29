package com.trust.privilege.biz;

import javax.annotation.Resource;

import com.trust.privilege.convert.OSSConvertUtil;
import com.trust.privilege.facade.ImportFile4OSSFacade;
import com.trust.privilege.facade.model.OssRequestVO;
import com.trust.privilege.facade.model.OssResponseVO;
import com.trust.privilege.service.OssService;
import com.xinmei.cms.oss.facade.OSSFacade;

/***
 * @ClassName: ImportFile4OSSFacadeImpl 
 * @Description: 通过OSS导入文件
 * @author zyn161616@163.com
 * @version 创建时间：2017年1月22日 下午8:02:28
 */
public class ImportFile4OSSFacadeImpl implements ImportFile4OSSFacade{

	/**注入Service层*/
	@Resource
	private OssService ossService;
	/**注入Sofa-Rpc 服务*/
	@Resource
	private OSSFacade oSSFacade;
	
	/**向OSS服务器获取凭证*/
	@Override
	public OssResponseVO getSTSSignature4Get(OssRequestVO ossRequestVO) {
		return OSSConvertUtil.ModelToVo(
					oSSFacade.getSTSSignature4Put(
								OSSConvertUtil.VoToModel(ossRequestVO)));
	}
	
	/**批量插入初始化数据*/
	@Override
	public String bulkInsertIniDataToDBbyOss(OssRequestVO ossRequestVO,String userName) {
		//先前后台数据转换，然后调用service方法进行批量化Excel数据导入数据库
		return ossService
				.bulkImportSystemInitData4OSS(
						OSSConvertUtil.VoToModel(ossRequestVO),userName);
	}
	
}
