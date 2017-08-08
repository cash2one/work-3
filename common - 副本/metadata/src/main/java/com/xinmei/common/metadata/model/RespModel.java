package com.xinmei.common.metadata.model;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Toby on 2016/11/2.
 * 元数据平台返回报文实体类
 */
public class RespModel {

    private boolean success;
    private String time;

    private List<MetaDataRespModel> data=new ArrayList<MetaDataRespModel>();//直接预初始化对象，避免null判断


    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List<MetaDataRespModel> getData() {
        return data;
    }

    public void setData(List<MetaDataRespModel> data) {
        this.data = data;
    }
}
