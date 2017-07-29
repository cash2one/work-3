package com.trust.privilege.model.complex.excel;
/***
 * @ClassName: ExcelDataBase 
 * @Description: 权限系统 Excel 数据的父类Model
 * @author zyn161616@163.com
 * @version 创建时间：2016年12月27日 上午11:42:13
 */
public class ExcelDataBase {
	
	private String roleName ;			//角色名
	private String roleDesc ;			//角色描述
	private String systemPlatFormCd;	//所属系统平台
	
	/**公有属性的取值赋值方法*/
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getRoleDesc() {
		return roleDesc;
	}
	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}
	public String getSystemPlatFormCd() {
		return systemPlatFormCd;
	}
	public void setSystemPlatFormCd(String systemPlatFormCd) {
		this.systemPlatFormCd = systemPlatFormCd;
	}

	/**有参构造器*/
	public ExcelDataBase(String roleName, String roleDesc, String systemPlatFormCd) {
		this.roleName = roleName;
		this.roleDesc = roleDesc;
		this.systemPlatFormCd = systemPlatFormCd;
	}
	/**无参构造器*/
	public ExcelDataBase() {}
	
}
