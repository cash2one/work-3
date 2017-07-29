package com.trust.privilege.model.complex;

import com.trust.privilege.model.Menu;

public class QueryMenu extends Menu{
	
	private String menuName;
	
	private String systemPlatformCd;
	
	private String stateCd;
	
	private String userId;
	
	private String systemPlatformName;
	
	private Integer page;
	private Integer pageSize;
	
	private String menuId;
	
	private String childMenuIds;
	private String userName;
	
	public String getChildMenuIds() {
		return childMenuIds;
	}
	public void setChildMenuIds(String childMenuIds) {
		this.childMenuIds = childMenuIds;
	}
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getSystemPlatformCd() {
		return systemPlatformCd;
	}
	public void setSystemPlatformCd(String systemPlatformCd) {
		this.systemPlatformCd = systemPlatformCd;
	}
	public String getStateCd() {
		return stateCd;
	}
	public void setStateCd(String stateCd) {
		this.stateCd = stateCd;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getSystemPlatformName() {
		return systemPlatformName;
	}
	public void setSystemPlatformName(String systemPlatformName) {
		this.systemPlatformName = systemPlatformName;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
	
	
}
