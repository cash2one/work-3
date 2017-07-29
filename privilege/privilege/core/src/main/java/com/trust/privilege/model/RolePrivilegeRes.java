package com.trust.privilege.model;

import java.util.Date;

public class RolePrivilegeRes {
    private String roleId;

    private String privilegeResId;

    private String stateCd;

    private Date createDt;

    private String stateType;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }

    public String getPrivilegeResId() {
        return privilegeResId;
    }

    public void setPrivilegeResId(String privilegeResId) {
        this.privilegeResId = privilegeResId == null ? null : privilegeResId.trim();
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

    public String getStateType() {
        return stateType;
    }

    public void setStateType(String stateType) {
        this.stateType = stateType == null ? null : stateType.trim();
    }

	public RolePrivilegeRes(String roleId, String privilegeResId, String stateCd, Date createDt, String stateType) {
		super();
		this.roleId = roleId;
		this.privilegeResId = privilegeResId;
		this.stateCd = stateCd;
		this.createDt = createDt;
		this.stateType = stateType;
	}

	public RolePrivilegeRes() {
		super();
	}
    
}