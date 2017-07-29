package com.trust.privilege.facade.model.complex;

public class QueryPrivilegeVO {
	
	private String menuName;
	private String systemPlatformName;
	private String userId;
	private String resName;
	private Integer page;
	private Integer pageSize;
	private String userName;
	private String systemPlatformCD;
	private String stateCd;
	
	public String getSystemPlatformCD() {
		return systemPlatformCD;
	}
	public void setSystemPlatformCD(String systemPlatformCD) {
		this.systemPlatformCD = systemPlatformCD;
	}
	public String getStateCd() {
		return stateCd;
	}
	public void setStateCd(String stateCd) {
		this.stateCd = stateCd;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getSystemPlatformName() {
		return systemPlatformName;
	}
	public void setSystemPlatformName(String systemPlatformName) {
		this.systemPlatformName = systemPlatformName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getResName() {
		return resName;
	}
	public void setResName(String resName) {
		this.resName = resName;
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
