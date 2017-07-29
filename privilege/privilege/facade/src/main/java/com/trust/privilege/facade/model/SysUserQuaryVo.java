package com.trust.privilege.facade.model;

public class SysUserQuaryVo {
	private String userId;
	private String page;
	private String userName; 
	private String state;
	private String queryUserName;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getQueryUserName() {
		return queryUserName;
	}
	public void setQueryUserName(String queryUserName) {
		this.queryUserName = queryUserName;
	}
	@Override
	public String toString() {
		return "SysUserQuaryVo [userId=" + userId + ", page=" + page + ", userName=" + userName + ", state=" + state
				+ ", queryUserName=" + queryUserName + "]";
	}
	
}
