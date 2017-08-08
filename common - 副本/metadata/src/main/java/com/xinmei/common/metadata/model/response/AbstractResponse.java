package com.xinmei.common.metadata.model.response;

/**
 * Created by Toby on 2016/11/3.
 * 返回信息抽象实体，用于定义结果、错误码等相关信息
 */
public abstract class AbstractResponse {


    /**成功标志，默认失败。*/
    private boolean success = false;
    /**错误码*/
    private String errorCode;
    /**错误描述*/
    private String resultMsg;
    /**这次操作请求的id*/
    private String requestId;


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
