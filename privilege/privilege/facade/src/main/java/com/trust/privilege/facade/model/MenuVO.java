package com.trust.privilege.facade.model;

import java.util.Date;
import java.util.List;

public class MenuVO {
	
	private String menuId;
	/**系统平台id*/
    private String systemPlatformCd;
    /***/
    private String menuName;
    /***/
    private String menuDesc;
    /***/
    private String reqeustMethed;
    /***/
    private String url;
    /***/				
    private String parentId;
    /***/			
    private String menuLevel;
    /***/				
    private Date createDt;
    private Date updateDt;
    /***/			
    private String isValidate;
    /***/			
    private String showOrder;
    /***/
    private String createUserId;
    /***/
    private String roleIds;
    private List<MenuVO> childs;
    
    private String stateCd;
    
    private String userId;
    
    private String userName;
    
    public Date getUpdateDt() {
		return updateDt;
	}

	public void setUpdateDt(Date updateDt) {
		this.updateDt = updateDt;
	}

	public List<MenuVO> getChilds() {
		return childs;
	}

	public void setChilds(List<MenuVO> childs) {
		this.childs = childs;
	}

	public String getMenuId() {
        return menuId;
    }

	public String getSystemPlatformCd() {
		return systemPlatformCd;
	}

	public void setSystemPlatformCd(String systemPlatformCd) {
		this.systemPlatformCd = systemPlatformCd;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuDesc() {
		return menuDesc;
	}

	public void setMenuDesc(String menuDesc) {
		this.menuDesc = menuDesc;
	}

	public String getReqeustMethed() {
		return reqeustMethed;
	}

	public void setReqeustMethed(String reqeustMethed) {
		this.reqeustMethed = reqeustMethed;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getMenuLevel() {
		return menuLevel;
	}

	public void setMenuLevel(String menuLevel) {
		this.menuLevel = menuLevel;
	}

	public Date getCreateDt() {
		return createDt;
	}

	public void setCreateDt(Date createDt) {
		this.createDt = createDt;
	}

	public String getIsValidate() {
		return isValidate;
	}

	public void setIsValidate(String isValidate) {
		this.isValidate = isValidate;
	}

	public String getShowOrder() {
		return showOrder;
	}

	public void setShowOrder(String showOrder) {
		this.showOrder = showOrder;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public String getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
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

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
    				
   
	
}
