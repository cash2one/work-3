package com.trust.privilege.model;

import java.util.Date;

public class MenuRole {
    private String menuId;

    private String roleId;

    private String stateCd;

    private Date createDt;

    private String stateType;

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId == null ? null : menuId.trim();
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
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

	public MenuRole(String menuId, String roleId, String stateCd, Date createDt, String stateType) {
		super();
		this.menuId = menuId;
		this.roleId = roleId;
		this.stateCd = stateCd;
		this.createDt = createDt;
		this.stateType = stateType;
	}

	public MenuRole() {
		super();
	}
    
    
}