package com.trust.privilege.facade.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class RoleVO {
	private String roleId;

    private String systemPlatformCd;

    private String roleName;

    private Date createDt;

    private Date updateDt;

    private String stateCd;

    private String roleDesc;

    private String createUserId;

    private Integer roleLevel;
    
    private String existRoleId;
    
    private String userId;
    /**添加角色时要选择多个用户组，所以要传入用户组的id集合字符串*/
    private String groupIds;
    /**多个菜单id的字符串*/
    private String menuIds;
    /**多个资源id的集合*/
    private String privilegeResIds;
    
    /**菜单及其操作类型对象*/
    private Map<String,Object> menuMap = new HashMap<String,Object>();
    /**资源及其操作类型对象*/
//    private Map<String,Object> resMap = new HashMap<String,Object>();
    private Set<String>	resMap;
    
    public void setResMap(Set<String> resMap) {
		this.resMap = resMap;
	}

	private String userName;
    
    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }

    public String getSystemPlatformCd() {
        return systemPlatformCd;
    }

    public void setSystemPlatformCd(String systemPlatformCd) {
        this.systemPlatformCd = systemPlatformCd == null ? null : systemPlatformCd.trim();
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public Date getCreateDt() {
        return createDt;
    }

    public void setCreateDt(Date createDt) {
        this.createDt = createDt;
    }

    public Date getUpdateDt() {
        return updateDt;
    }

    public void setUpdateDt(Date updateDt) {
        this.updateDt = updateDt;
    }

    public String getStateCd() {
        return stateCd;
    }

    public void setStateCd(String stateCd) {
        this.stateCd = stateCd == null ? null : stateCd.trim();
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc == null ? null : roleDesc.trim();
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId == null ? null : createUserId.trim();
    }

    public Integer getRoleLevel() {
        return roleLevel;
    }

    public void setRoleLevel(Integer roleLevel) {
        this.roleLevel = roleLevel;
    }

	public String getExistRoleId() {
		return existRoleId;
	}

	public void setExistRoleId(String existRoleId) {
		this.existRoleId = existRoleId;
	}

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

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public RoleVO() {
		super();
	}

}