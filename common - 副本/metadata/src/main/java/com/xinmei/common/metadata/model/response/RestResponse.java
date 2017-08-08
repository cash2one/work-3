package com.xinmei.common.metadata.model.response;

/**
 * Created by Toby on 2016/11/3.
 * rest调用返回实体
 */
public class RestResponse<T> extends AbstractResponse{

    private T data;

    public RestResponse() {
    }

    public RestResponse(boolean success,T data) {
        this.setSuccess(success);
        this.data = data;
    }

    public RestResponse(String resultMsg){
        this.setResultMsg(resultMsg);
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
