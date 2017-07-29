package com.trust.privilege.model;

public class SystemConfig {
    private String systemConfigId;

    private String privilegeResId;

    private String userName;

    private String resourceType;

    private Integer showOrder;

    private String resourceName;

    private String picturePath;

    private String url;

    public String getSystemConfigId() {
        return systemConfigId;
    }

    public void setSystemConfigId(String systemConfigId) {
        this.systemConfigId = systemConfigId == null ? null : systemConfigId.trim();
    }

    public String getPrivilegeResId() {
        return privilegeResId;
    }

    public void setPrivilegeResId(String privilegeResId) {
        this.privilegeResId = privilegeResId == null ? null : privilegeResId.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType == null ? null : resourceType.trim();
    }

    public Integer getShowOrder() {
        return showOrder;
    }

    public void setShowOrder(Integer showOrder) {
        this.showOrder = showOrder;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName == null ? null : resourceName.trim();
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath == null ? null : picturePath.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }
}