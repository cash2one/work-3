package com.trust.privilege.model;

import java.util.Date;

public class PrivilegeRes {
    private String privilegeResId;

    private String resTypeCd;

    private String systemPlatformCd;

    private String resName;

    private String resDesc;

    private String reqMethed;

    private String url;

    private String stateCd;

    private Date createDate;

    private Date updateDate;

    private Date version;

    private String menuId;

    private String createUserId;

    private String mark;

    public String getPrivilegeResId() {
        return privilegeResId;
    }

    public void setPrivilegeResId(String privilegeResId) {
        this.privilegeResId = privilegeResId == null ? null : privilegeResId.trim();
    }

    public String getResTypeCd() {
        return resTypeCd;
    }

    public void setResTypeCd(String resTypeCd) {
        this.resTypeCd = resTypeCd == null ? null : resTypeCd.trim();
    }

    public String getSystemPlatformCd() {
        return systemPlatformCd;
    }

    public void setSystemPlatformCd(String systemPlatformCd) {
        this.systemPlatformCd = systemPlatformCd == null ? null : systemPlatformCd.trim();
    }

    public String getResName() {
        return resName;
    }

    public void setResName(String resName) {
        this.resName = resName == null ? null : resName.trim();
    }

    public String getResDesc() {
        return resDesc;
    }

    public void setResDesc(String resDesc) {
        this.resDesc = resDesc == null ? null : resDesc.trim();
    }

    public String getReqMethed() {
        return reqMethed;
    }

    public void setReqMethed(String reqMethed) {
        this.reqMethed = reqMethed == null ? null : reqMethed.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getStateCd() {
        return stateCd;
    }

    public void setStateCd(String stateCd) {
        this.stateCd = stateCd == null ? null : stateCd.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Date getVersion() {
        return version;
    }

    public void setVersion(Date version) {
        this.version = version;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId == null ? null : menuId.trim();
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId == null ? null : createUserId.trim();
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark == null ? null : mark.trim();
    }

	public PrivilegeRes() {
		super();
	}
    
    
}