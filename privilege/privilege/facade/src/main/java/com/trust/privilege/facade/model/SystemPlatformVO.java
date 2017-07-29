package com.trust.privilege.facade.model;


public class SystemPlatformVO {
	private String systemPlatformCd;

    private String systemPlatformName;

    private String systemPlatformDesc;

    private String stateCd;
    
    /**此处将日期类型为字符串类型*/
    private String createDt;

    private String createUserId;
    
    private String userName;

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

   
    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId == null ? null : createUserId.trim();
    }

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCreateDt() {
		return createDt;
	}

	public void setCreateDt(String createDt) {
		this.createDt = createDt;
	}
}