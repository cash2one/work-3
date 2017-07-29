package com.trust.privilege.model.complex.excel;

/***
 * @ClassName: ExcelDataRoleAndPrivilegeRes 
 * @Description: Excel数据，角色对应权限资源的Model
 *	此资源指button，或者文本等资源而非菜单资源
 * @author zyn161616@163.com
 * @version 创建时间：2016年12月27日 上午11:50:16
 */
public class ExcelDataRoleAndPrivilegeRes extends ExcelDataBase{

	private String resName	;			//资源名称
	private String resDesc	;			//资源描述
	private String resReqMethod;		//资源请求的方式
	private String resReqUrl;			//资源请求的URL
	private String resTypeCd;			//当前类型是按钮还是其他资源
	private String resMark;				//资源标识
	private String menuName	;			//当前资源所属菜单的id
	
	/**公有属性的取值赋值方法*/
	public String getMenuName() {
		return menuName;
	}
	public String getResMark() {
		return resMark;
	}
	public void setResMark(String resMark) {
		this.resMark = resMark;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getResName() {
		return resName;
	}
	public void setResName(String resName) {
		this.resName = resName;
	}
	public String getResDesc() {
		return resDesc;
	}
	public void setResDesc(String resDesc) {
		this.resDesc = resDesc;
	}
	public String getResReqMethod() {
		return resReqMethod;
	}
	public void setResReqMethod(String resReqMethod) {
		this.resReqMethod = resReqMethod;
	}
	public String getResReqUrl() {
		return resReqUrl;
	}
	public void setResReqUrl(String resReqUrl) {
		this.resReqUrl = resReqUrl;
	}
	public String getResTypeCd() {
		return resTypeCd;
	}
	public void setResTypeCd(String resTypeCd) {
		this.resTypeCd = resTypeCd;
	}
	public String getMenuId() {
		return menuName;
	}
	public void setMenuId(String menuName) {
		this.menuName = menuName;
	}
	
	/**有参构造器*/
	public ExcelDataRoleAndPrivilegeRes(String roleName, String roleDesc, String systemPlatFormCd, String resName,
			String resDesc, String resReqMethod, String resReqUrl, String resTypeCd, String resMark, String menuName) {
		super(roleName, roleDesc, systemPlatFormCd);
		this.resName = resName;
		this.resDesc = resDesc;
		this.resReqMethod = resReqMethod;
		this.resReqUrl = resReqUrl;
		this.resTypeCd = resTypeCd;
		this.resMark = resMark;
		this.menuName = menuName;
	}
	
	/**无参构造器*/
	public ExcelDataRoleAndPrivilegeRes(){}
	
	
}
