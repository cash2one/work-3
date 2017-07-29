package com.trust.privilege.model.complex;

import com.trust.privilege.model.UserGroup;
/**
 * @ClassName: GroupDo 
 * @Description: 自定义用户组Model
 * @author zyn161616@163.com
 * @version 创建时间：2017年4月19日 下午3:02:47
 */
public class GroupDo extends UserGroup {
	
	private String systemId;	//系统CD
	private String roleId;		//角色ID
	
	//公有属性的取值赋值方法
	public String getSystemId() {
		return systemId;
	}
	public void setSystemId(String systemId) {
		this.systemId = systemId;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	
	public GroupDo() {}
	
}
