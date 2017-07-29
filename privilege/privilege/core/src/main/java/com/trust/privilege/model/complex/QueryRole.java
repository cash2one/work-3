package com.trust.privilege.model.complex;

import com.trust.privilege.model.Role;

/**
 * Title: 
 *  Description:查询出来除角色实体类外的 数据信息 
 *  Company:xinmei 
 *  @author sunwei
 *  @date 上午10:16:29
 *  @version
 */
public class QueryRole extends Role{
	
	
	/** 当前页 */
	private Integer page;
	/** 当前页显示记录条数 */
	private Integer pageSize;
	/**角色名字*/
	private String roleName;
	/**系统名称*/
	private String systemPlatformName;
	/**用户id*/
	private String userId;
	/**状态值*/
	private String stateCd;
	/**系统id*/
	private String systemPlatformCd;
	/**用户名*/
	private String userName;
	
	
	
	
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
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
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
	public String getStateCd() {
		return stateCd;
	}
	public void setStateCd(String stateCd) {
		this.stateCd = stateCd;
	}
	public String getSystemPlatformCd() {
		return systemPlatformCd;
	}
	public void setSystemPlatformCd(String systemPlatformCd) {
		this.systemPlatformCd = systemPlatformCd;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
}
