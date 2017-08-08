package com.xinmei.common.basic.response;

import com.xinmei.common.basic.AppContext;

/**
 * @ClassName: com.xinmei.common.basic.response.DefaultFacadeResponse
 * @Description: 返回消息包装
 * @Author xbzhu
 * @Date 2017年03月23日 10:34
 */
public class DefaultFacadeResponse<T> extends AbstractFacadeResp{

    private T data;


    private static final String DEFAULT_ERROR_MSG="系统繁忙";

    public static DefaultFacadeResponse getErrorDefaultResponse(){
        DefaultFacadeResponse response=new DefaultFacadeResponse(false);
        response.setRequestId(AppContext.getRequestId());
        response.setResultMsg(DEFAULT_ERROR_MSG);
        return response;
    }

    public DefaultFacadeResponse() {
    }

    public DefaultFacadeResponse(boolean success) {
        super(success);
    }

    public DefaultFacadeResponse(boolean success,T data){
        this(success,data,null,null);
    }

    public DefaultFacadeResponse(boolean success,T data,String message){
        this(success,data,message,null);
    }

    public DefaultFacadeResponse(boolean success,T data,Exception e){
        this(success,data,null,e);
    }

    public DefaultFacadeResponse(boolean success,T data,String message,Exception e){
        this(success);
        this.setData(data);
        this.setException(e);
        this.setResultMsg(message);
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
