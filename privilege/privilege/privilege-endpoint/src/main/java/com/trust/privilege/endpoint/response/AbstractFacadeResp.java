package com.trust.privilege.endpoint.response;
/***
 * 通用响应数据封装
 * @author Administrator
 *
 */
public abstract class AbstractFacadeResp {
	
	/**成功标志，默认失败。*/
    private boolean   success = false;

    /**错误码*/
    private String	errorCode;
    
    /**九州内部错误描述*/
    private String    resultMsg;

    /**这次操作请求的id*/
    private String    requestId;


    /**有参构造器*/
	public AbstractFacadeResp(boolean success) {
		this.success = success;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getResultMsg() {
		return resultMsg;
	}

	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
    
}
