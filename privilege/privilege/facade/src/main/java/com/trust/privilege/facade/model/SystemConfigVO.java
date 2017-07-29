package com.trust.privilege.facade.model;

public class SystemConfigVO {
	
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
		this.systemConfigId = systemConfigId;
	}

	public String getPrivilegeResId() {
		return privilegeResId;
	}

	public void setPrivilegeResId(String privilegeResId) {
		this.privilegeResId = privilegeResId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getResourceType() {
		return resourceType;
	}

	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
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
		this.resourceName = resourceName;
	}

	public String getPicturePath() {
		return picturePath;
	}

	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

   
   
}