package com.trust.privilege.model.complex;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.trust.privilege.model.Role;

public class RoleDo extends Role {
	/**用户Id*/
    private String userId;
    /**用户组的id拼接字符串*/
    private String groupIds;
    /**多个菜单id的字符串*/
    private String menuIds;
    /**多个资源id的集合*/
    private String privilegeResIds;
    
    /**菜单及其操作类型对象*/
    private Map<String,Object> menuMap = new HashMap<String,Object>();
    /**资源及其操作类型对象*/
//    private Map<String,Object> resMap = new HashMap<String,Object>();
    private Set<String> resMap;
    /**用户名*/
    
	private String userName;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getGroupIds() {
		return groupIds;
	}
	public void setGroupIds(String groupIds) {
		this.groupIds = groupIds;
	}
	public String getMenuIds() {
		return menuIds;
	}
	public void setMenuIds(String menuIds) {
		this.menuIds = menuIds;
	}
	public String getPrivilegeResIds() {
		return privilegeResIds;
	}
	public void setPrivilegeResIds(String privilegeResIds) {
		this.privilegeResIds = privilegeResIds;
	}
	public Map<String, Object> getMenuMap() {
		return menuMap;
	}
	public void setMenuMap(Map<String, Object> menuMap) {
		this.menuMap = menuMap;
	}
	public Set<String> getResMap() {
		return resMap;
	}
	public void setResMap(Set<String> resMap) {
		this.resMap = resMap;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public RoleDo(String userId, String groupIds, String menuIds, String privilegeResIds, Map<String, Object> menuMap,
			Set<String> resMap, String userName) {
		super();
		this.userId = userId;
		this.groupIds = groupIds;
		this.menuIds = menuIds;
		this.privilegeResIds = privilegeResIds;
		this.menuMap = menuMap;
		this.resMap = resMap;
		this.userName = userName;
	}
	public RoleDo() {
		super();
	}
	
}