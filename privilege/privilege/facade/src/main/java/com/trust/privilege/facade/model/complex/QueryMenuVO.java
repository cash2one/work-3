package com.trust.privilege.facade.model.complex;

public class QueryMenuVO {
	/** 当前页 */
	private Integer page;
	/** 当前页显示记录条数 */
	private Integer pageSize;//查询界面左侧菜单列表的时候，不需要分页，如果不传值，就将page，pageSize设置为0,1000
	/** 取得查询总记录数 */
	private Integer count;
	/** 总页数 */
	private Integer totalPage;
	
	private String menuName;
	
	private String systemPlatformCd;
	
	private String stateCd;
	
	private String userId;
	
	private String systemPlatformName;
	
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

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		if(page == null){
			this.page = 1;
		}
		this.page = page;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		if(pageSize == null){
			this.pageSize = 1000;
		}
		this.pageSize = pageSize;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
	
	
}
