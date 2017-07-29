package com.trust.privilege.facade.model;

import java.util.Date;

public class SysUserModel {
	private String systemUserId;
	private String idCard;

	private String userName;

    private String password;

    private Date createDt;

	private Date updateDt;

    private String stateCd;

    private String userDesc;
    private String memberType;
    private String createUserId;
    private String groupId;
    private String roleId;
    private String realName;
    private String telNumber;
    private String newPassword;
    
	public String getMemberType() {
		return memberType;
	}

	public void setMemberType(String memberType) {
		this.memberType = memberType;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getTelNumber() {
		return telNumber;
	}

	public void setTelNumber(String telNumber) {
		this.telNumber = telNumber;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getSystemUserId() {
		return systemUserId;
	}

	public void setSystemUserId(String systemUserId) {
		this.systemUserId = systemUserId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
		this.stateCd = stateCd;
	}

	public String getUserDesc() {
		return userDesc;
	}

	public void setUserDesc(String userDesc) {
		this.userDesc = userDesc;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	@Override
	public String toString() {
		return "SysUserModel [systemUserId=" + systemUserId + ", idCard="
				+ idCard + ", userName=" + userName + ", password=" + password
				+ ", createDt=" + createDt + ", updateDt=" + updateDt
				+ ", stateCd=" + stateCd + ", userDesc=" + userDesc
				+ ", memberType=" + memberType + ", createUserId="
				+ createUserId + ", groupId=" + groupId + ", roleId=" + roleId
				+ ", realName=" + realName + ", telNumber=" + telNumber
				+ ", newPassword=" + newPassword + "]";
	}

	
	

	

	

	

	

	

	
	
}
