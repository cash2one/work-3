package com.trust.privilege.facade.model;
/**
 * @ClassName: GroupQueryVo 
 * @Description: 用户组查询Model
 * @author zyn161616@163.com
 * @version 创建时间：2017年4月17日 下午7:32:04
 */
public class GroupQueryVo {
	
	private String userName ;
	private String groupId;
	private String page;
	private String groupName; 
	private String state;
	private String userId;
	private String pageSize;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPageSize() {
		return pageSize;
	}
	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}
	public GroupQueryVo() {}
	
}
