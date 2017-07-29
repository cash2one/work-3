package com.trust.privilege.model.complex;

import java.text.SimpleDateFormat;
import java.util.List;

import com.trust.privilege.model.SystemUser;

public class UserDo extends SystemUser {
	 private String groupId;
	 private String roleId;
	 private String realName;
	 private String newPassword;
	 private List<String> roleNames;
	 private String createUserName;
	 private String roleNameStr;
	 private String dateStr;
	 
	 
	public String getDateStr() {
		dateStr=new SimpleDateFormat("yyyy-MM-dd").format(getCreateDt()).toString();
		return dateStr;
	}
	public void setDateStr(String dateStr){
		this.dateStr=dateStr;
	}
	public String getRoleNameStr() {
		return roleNameStr;
	}
	public void setRoleNameStr(String roleNameStr) {
		this.roleNameStr = roleNameStr;
	}
	public String getCreateUserName() {
		return createUserName;
	}
	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}
	public List<String> getRoleNames() {
		return roleNames;
	}
	public void setRoleNames(List<String> roleNames) {
		this.roleNames = roleNames;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
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
		return "UserDo [groupId=" + groupId + ", roleId=" + roleId
				+ ", realName=" + realName + ", newPassword=" + newPassword
				+ ", roleNames=" + roleNames + ", createUserName="
				+ createUserName + ", roleNameStr=" + roleNameStr
				+ ", dateStr=" + dateStr + ", toString()=" + super.toString()
				+ "]";
	}
	public UserDo(String groupId, String roleId, String realName, String newPassword, List<String> roleNames,
			String createUserName, String roleNameStr, String dateStr) {
		super();
		this.groupId = groupId;
		this.roleId = roleId;
		this.realName = realName;
		this.newPassword = newPassword;
		this.roleNames = roleNames;
		this.createUserName = createUserName;
		this.roleNameStr = roleNameStr;
		this.dateStr = dateStr;
	}
	public UserDo() {
		super();
	}
	
}
