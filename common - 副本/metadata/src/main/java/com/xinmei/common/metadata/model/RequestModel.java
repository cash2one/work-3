package com.xinmei.common.metadata.model;

import com.xinmei.common.metadata.util.DateUtil;

/**
 * Created by Toby on 2016/11/2.
 * 请求报文实体表
 */
public class RequestModel {

    private String time;
    private String requestType;
    private String codeId;
    private String codeThemeCd;


    public static RequestModel getAllRequestModel(){
        RequestModel requestModel=new RequestModel();
        requestModel.setTime(DateUtil.getCurrentTime(DateUtil.TIME_FORMAT_YYYY_MM_DD_HH_MM_SS));
        requestModel.setCodeId("");
        requestModel.setCodeThemeCd("");
        requestModel.setRequestType("all");
        return requestModel;
    }

    public static RequestModel getRequestModel(String codeId,String codeThemeCd){
        RequestModel requestModel=new RequestModel();
        requestModel.setTime(DateUtil.getCurrentTime(DateUtil.TIME_FORMAT_YYYY_MM_DD_HH_MM_SS));
        requestModel.setCodeId(codeId);
        requestModel.setCodeThemeCd(codeThemeCd);
        requestModel.setRequestType("batch");
        return requestModel;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getCodeId() {
        return codeId;
    }

    public void setCodeId(String codeId) {
        this.codeId = codeId;
    }

    public String getCodeThemeCd() {
        return codeThemeCd;
    }

    public void setCodeThemeCd(String codeThemeCd) {
        this.codeThemeCd = codeThemeCd;
    }
}
