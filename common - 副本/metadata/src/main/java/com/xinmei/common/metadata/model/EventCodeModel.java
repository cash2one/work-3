package com.xinmei.common.metadata.model;

/**
 * Created by Toby on 2016/11/24.
 */
public class EventCodeModel {

    private String eventCode;
    private String eventMethod;
    private String eventPackageUrl;
    private String sysCode;


    public String getSysCode() {
        return sysCode;
    }

    public void setSysCode(String sysCode) {
        this.sysCode = sysCode;
    }

    public String getEventCode() {
        return eventCode;
    }

    public void setEventCode(String eventCode) {
        this.eventCode = eventCode;
    }

    public String getEventMethod() {
        return eventMethod;
    }

    public void setEventMethod(String eventMethod) {
        this.eventMethod = eventMethod;
    }

    public String getEventPackageUrl() {
        return eventPackageUrl;
    }

    public void setEventPackageUrl(String eventPackageUrl) {
        this.eventPackageUrl = eventPackageUrl;
    }
}
