package com.trust.privilege.model.complex;

import com.trust.privilege.model.PrivilegeRes;

public class PrivilegeResDo extends PrivilegeRes{
	/**角色id拼接的字符串，用处：当添加新资源时为资源选择对个可见角色*/
    private String roleIds;
    
    /**添加角色时，选择页面下的资源需要这个参数*/
    private String menuIds;        
    private String stateType;
    private String userName;
	public String getStateType() {
		return stateType;
	}

	public void setStateType(String stateType) {
		this.stateType = stateType;
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
		return "PrivilegeResDo [roleIds=" + roleIds + ", menuIds=" + menuIds
				+ ", stateType=" + stateType + "]";
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public PrivilegeResDo() {
		super();
	}

    
}
