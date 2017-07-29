package com.trust.privilege.facade.model.complex;

import java.util.List;

import com.trust.privilege.facade.model.PrivilegeResVO;

public class MenuResVO {
	private List<MenuResVO> menus;
	private String menuId;
	private String menuName;
	private String roleId;
	private String stateType;
	private String url;
	private List<PrivilegeResVO> list;
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getStateType() {
		return stateType;
	}
	public void setStateType(String stateType) {
		this.stateType = stateType;
	}
	
	public List<MenuResVO> getMenus() {
		return menus;
	}
	public void setMenus(List<MenuResVO> menus) {
		this.menus = menus;
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
	public List<PrivilegeResVO> getList() {
		return list;
	}
	public void setList(List<PrivilegeResVO> list) {
		this.list = list;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	@Override
	public String toString() {
		return "MenuResVO [menus=" + menus + ", menuId=" + menuId
				+ ", menuName=" + menuName + ", roleId=" + roleId
				+ ", stateType=" + stateType + ", url=" + url + ", list="
				+ list + "]";
	}
	
	
	
	
	
	
	
}
