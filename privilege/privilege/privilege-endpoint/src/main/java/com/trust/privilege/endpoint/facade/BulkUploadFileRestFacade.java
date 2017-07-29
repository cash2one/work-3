package com.trust.privilege.endpoint.facade;

import javax.annotation.security.PermitAll;
import javax.ws.rs.Consumes;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.trust.privilege.endpoint.constants.URLConstants;
import com.trust.privilege.endpoint.response.RestSampleFacadeResp;
import com.trust.privilege.facade.model.OssRequestVO;
import com.trust.privilege.facade.model.OssResponseVO;

/***
 * @ClassName: BulkUploadFileRestFacade 
 * @Description: 批量上传文件接口
 * @author zyn161616@163.com
 * @version 创建时间：2017年1月18日 下午7:09:36
 */
@Path(URLConstants.REST_API_PEFFIX+"/upload")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@PermitAll
public interface BulkUploadFileRestFacade {
	
	/**						 
	 * http://localhost:8341/etheta/management/upload/getSignature
	 * @Title: getSTSSignature4Get 
	 * @Description: 向OSS服务器获取上传凭证
	 * @param @param OssRequestVO
	 * @param @return   
	 * @return RestSampleFacadeResp<OssResponseVO>
	 */
	@POST
	@Path("/getSignature")
	public RestSampleFacadeResp<OssResponseVO> getSTSSignature4Get(OssRequestVO OssRequestVO);
	
	/**
	 * http://localhost:8341/privilege/management/upload/uploadInitData
	 * @Title: uploadInitData 
	 * @Description: 批量上传初始化Excel数据
	 * @param @param ossRequestVO
	 * @param @param userName
	 * @param @return   
	 * @return RestSampleFacadeResp<String>
	 */
	@POST
	@Path("/uploadInitData")
	public RestSampleFacadeResp<String> uploadInitData(OssRequestVO ossRequestVO,
			@HeaderParam("userName")String userName);
				
}
