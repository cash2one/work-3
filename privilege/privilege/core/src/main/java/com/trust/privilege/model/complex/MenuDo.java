package com.trust.privilege.model.complex;

import java.io.Serializable;
import java.util.List;

import com.trust.privilege.model.Menu;

public class MenuDo extends Menu implements Serializable {
	// 版本号
	private static final long serialVersionUID = 1L;

	private List<MenuDo> childs;
	private String roleIds;
	private String userId;
	private List<PrivilegeResDo> privileges;
	private String stateType;
	private String userName; 

	public String getStateType() {
		return stateType;
	}

	public void setStateType(String stateType) {
		this.stateType = stateType;
	}

	public List<MenuDo> getChilds() {
		return childs;
	}

	public void setChilds(List<MenuDo> childs) {
		this.childs = childs;
	}

	public String getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<PrivilegeResDo> getPrivileges() {
		return privileges;
	}

	public void setPrivileges(List<PrivilegeResDo> privileges) {
		this.privileges = privileges;
	}

	@Override
	public String toString() {
		return "MenuDo [childs=" + childs + ", roleIds=" + roleIds
				+ ", userId=" + userId + ", privileges=" + privileges
				+ ", stateType=" + stateType + ", toString()="
				+ super.toString() + "]";
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public MenuDo() {
		super();
	}

}
