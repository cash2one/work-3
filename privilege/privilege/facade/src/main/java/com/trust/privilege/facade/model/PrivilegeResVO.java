package com.trust.privilege.facade.model;

import java.util.Date;

public class PrivilegeResVO {
	
    private String privilegeResId;

    private String resTypeCd;
    
    private String resName;
    
    private String resDesc;

    private String reqMethed;

    private String url;

    private String stateCd;

    private Date createDate;

    private Date updateDate;

    private Date version;
    
    private String isValidate;

    private String createUserId;
    private String mark;
    private String systemPlatformCd;
    private String menuId;
    private String menuIds;
    private String stateType;
    private String userName;
    
    
    public String getStateType() {
		return stateType;
	}

	public void setStateType(String stateType) {
		this.stateType = stateType;
	}

	/**添加资源时，传进来拼接的角色id的字符串*/
    private String roleIds;
    
    public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}
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
		this.menuId = menuId;
	}

	public String getIsValidate() {
		return isValidate;
	}

	public void setIsValidate(String isValidate) {
		this.isValidate = isValidate;
	}

	public String getSystemPlatformCd() {
		return systemPlatformCd;
	}

	public void setSystemPlatformCd(String systemPlatformCd) {
		this.systemPlatformCd = systemPlatformCd;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public String getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}

	public String getMenuIds() {
		return menuIds;
	}

	public void setMenuIds(String menuIds) {
		this.menuIds = menuIds;
	}

	@Override
	public String toString() {
		return "PrivilegeResVO [privilegeResId=" + privilegeResId
				+ ", resTypeCd=" + resTypeCd + ", resName=" + resName
				+ ", resDesc=" + resDesc + ", reqMethed=" + reqMethed
				+ ", url=" + url + ", stateCd=" + stateCd  + ","
				+ " createDate=" + createDate + ", updateDate="
				+ updateDate + ", version=" + version + ", isValidate="
				+ isValidate + ", createUserId=" + createUserId + ", mark="
				+ mark + ", systemPlatformCd=" + systemPlatformCd + ", menuId="
				+ menuId + ", menuIds=" + menuIds + ", stateType=" + stateType
				+ ", roleIds=" + roleIds + "]";
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
    
    
}