package com.trust.privilege.facade.model;

import java.util.Date;

public class GroupModel {
	private String groupId;
    private String groupName;

    private String parentGroupId;

    private String stateCd;

    private Date updateDt;

    private Date createDt;

    private String groupDesc;

    private String createUserid;
    private String roleId;
    


	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	
	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getParentGroupId() {
		return parentGroupId;
	}

	public void setParentGroupId(String parentGroupId) {
		this.parentGroupId = parentGroupId;
	}

	public String getStateCd() {
		return stateCd;
	}

	public void setStateCd(String stateCd) {
		this.stateCd = stateCd;
	}

	public Date getUpdateDt() {
		return updateDt;
	}

	public void setUpdateDt(Date updateDt) {
		this.updateDt = updateDt;
	}

	public Date getCreateDt() {
		return createDt;
	}

	public void setCreateDt(Date createDt) {
		this.createDt = createDt;
	}

	public String getGroupDesc() {
		return groupDesc;
	}

	public void setGroupDesc(String groupDesc) {
		this.groupDesc = groupDesc;
	}

	public String getCreateUserid() {
		return createUserid;
	}

	public void setCreateUserid(String createUserid) {
		this.createUserid = createUserid;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	@Override
	public String toString() {
		return "GroupModel [groupId=" + groupId + ", groupName=" + groupName
				+ ", parentGroupId=" + parentGroupId + ", stateCd=" + stateCd + ", updateDt=" + updateDt + ", createDt="
				+ createDt + ", groupDesc=" + groupDesc + ", createUserid=" + createUserid + ", roleId=" + roleId + "]";
	}
	
}
