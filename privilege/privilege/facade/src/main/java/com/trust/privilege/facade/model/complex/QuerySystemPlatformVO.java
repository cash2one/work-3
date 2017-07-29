package com.trust.privilege.facade.model.complex;

import com.trust.privilege.facade.model.SystemPlatformVO;

public class QuerySystemPlatformVO extends SystemPlatformVO{
	
	/** 当前页 */
	private Integer page;
	/** 当前页显示记录条数 */
	private Integer pageSize;

	/** 取得查询总记录数 */
	private Integer count;
	/** 总页数 */
	private Integer totalPage;
	private String userId;
	private String stateCd;
	private String systemPlatformName;
	private String systemPlatformCd;
	/**用户名称*/
	private String userName;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
	public String getSystemPlatformName() {
		return systemPlatformName;
	}
	public void setSystemPlatformName(String systemPlatformName) {
		this.systemPlatformName = systemPlatformName;
	}
	public String getSystemPlatformCd() {
		return systemPlatformCd;
	}
	public void setSystemPlatformCd(String systemPlatformCd) {
		this.systemPlatformCd = systemPlatformCd;
	}
	
	
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getStateCd() {
		return stateCd;
	}
	public void setStateCd(String stateCd) {
		this.stateCd = stateCd;
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
	
}
