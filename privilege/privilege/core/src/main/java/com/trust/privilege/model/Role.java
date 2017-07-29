package com.trust.privilege.model;

import java.util.Date;

public class Role {
    private String roleId;

    private String systemPlatformCd;

    private String roleName;

    private Date createDt;

    private Date updateDt;

    private String stateCd;

    private String roleDesc;

    private String createUserId;

    private Integer roleLevel;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }

    public String getSystemPlatformCd() {
        return systemPlatformCd;
    }

    public void setSystemPlatformCd(String systemPlatformCd) {
        this.systemPlatformCd = systemPlatformCd == null ? null : systemPlatformCd.trim();
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public Date getCreateDt() {
        return createDt;
    }

    public void setCreateDt(Date createDt) {
        this.createDt = createDt;
    }

    public Date getUpdateDt() {
        return updateDt;
    }

    public void setUpdateDt(Date updateDt) {
        this.updateDt = updateDt;
    }

    public String getStateCd() {
        return stateCd;
    }

    public void setStateCd(String stateCd) {
        this.stateCd = stateCd == null ? null : stateCd.trim();
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc == null ? null : roleDesc.trim();
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId == null ? null : createUserId.trim();
    }

    public Integer getRoleLevel() {
        return roleLevel;
    }

    public void setRoleLevel(Integer roleLevel) {
        this.roleLevel = roleLevel;
    }

	public Role() {
		super();
	}

	public Role(String roleId, String systemPlatformCd, String roleName, Date createDt, Date updateDt, String stateCd,
			String roleDesc, String createUserId, Integer roleLevel) {
		super();
		this.roleId = roleId;
		this.systemPlatformCd = systemPlatformCd;
		this.roleName = roleName;
		this.createDt = createDt;
		this.updateDt = updateDt;
		this.stateCd = stateCd;
		this.roleDesc = roleDesc;
		this.createUserId = createUserId;
		this.roleLevel = roleLevel;
	}
    
	
    
}