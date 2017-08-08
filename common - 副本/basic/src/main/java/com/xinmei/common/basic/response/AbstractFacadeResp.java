package com.xinmei.common.basic.response;

/**
* @ClassName: AbstractFacadeResp
* @Description: 通用返回信息类
* @author ZHY
* @date 2016年11月15日 下午3:51:42
*
*/ 
public abstract class AbstractFacadeResp {

    /**
    * @Fields success : 成功标志，默认失败
    */ 
    private boolean   success = false;

    /**
    * @Fields errorCode : 错误码
    */ 
    private String errorCode;

    /**
    * @Fields resultMsg : 内部错误描述
    */ 
    private String    resultMsg;

    /**
    * @Fields requestId : 这次操作请求的id
    */ 
    private String    requestId;

    /**
     * 用户承载在rest facade层的异常携带，在filter层获取并将exception卸载
     */
    private Exception exception;

    public AbstractFacadeResp() {
    }

    public AbstractFacadeResp(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
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

    public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }
}
