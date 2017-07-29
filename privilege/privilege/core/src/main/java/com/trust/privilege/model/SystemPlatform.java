package com.trust.privilege.model;

import java.util.Date;

public class SystemPlatform {
    private String systemPlatformCd;

    private String systemPlatformName;

    private String systemPlatformDesc;

    private String stateCd;

    private Date createDt;

    private String createUserId;

    public String getSystemPlatformCd() {
        return systemPlatformCd;
    }

    public void setSystemPlatformCd(String systemPlatformCd) {
        this.systemPlatformCd = systemPlatformCd == null ? null : systemPlatformCd.trim();
    }

    public String getSystemPlatformName() {
        return systemPlatformName;
    }

    public void setSystemPlatformName(String systemPlatformName) {
        this.systemPlatformName = systemPlatformName == null ? null : systemPlatformName.trim();
    }

    public String getSystemPlatformDesc() {
        return systemPlatformDesc;
    }

    public void setSystemPlatformDesc(String systemPlatformDesc) {
        this.systemPlatformDesc = systemPlatformDesc == null ? null : systemPlatformDesc.trim();
    }

    public String getStateCd() {
        return stateCd;
    }

    public void setStateCd(String stateCd) {
        this.stateCd = stateCd == null ? null : stateCd.trim();
    }

    public Date getCreateDt() {
        return createDt;
    }

    public void setCreateDt(Date createDt) {
        this.createDt = createDt;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId == null ? null : createUserId.trim();
    }
}