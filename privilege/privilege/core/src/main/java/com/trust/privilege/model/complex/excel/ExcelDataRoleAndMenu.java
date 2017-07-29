package com.trust.privilege.model.complex.excel;

/***
 * @ClassName: ExcelDataRoleAndMenu 
 * @Description: Excel数据，角色对应菜单的Model
 * @author zyn161616@163.com
 * @version 创建时间：2016年12月27日 上午11:44:40
 */
public class ExcelDataRoleAndMenu extends ExcelDataBase{

	private String menuName ;			//菜单名称
	private String menuDesc	;			//菜单描述
	private String menuReqMethod;		//菜单请求方式
	private String menuReqUrl;			//菜单请求的URL
	
	/**公有属性的取值赋值方法*/
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
	public String getMenuReqMethod() {
		return menuReqMethod;
	}
	public void setMenuReqMethod(String menuReqMethod) {
		this.menuReqMethod = menuReqMethod;
	}
	public String getMenuReqUrl() {
		return menuReqUrl;
	}
	public void setMenuReqUrl(String menuReqUrl) {
		this.menuReqUrl = menuReqUrl;
	}
	
	/**有参构造器*/
	public ExcelDataRoleAndMenu(String roleName, String roleDesc, String systemPlatFormCd, String menuName,
			String menuDesc, String menuReqMethod, String menuReqUrl) {
		super(roleName, roleDesc, systemPlatFormCd);
		this.menuName = menuName;
		this.menuDesc = menuDesc;
		this.menuReqMethod = menuReqMethod;
		this.menuReqUrl = menuReqUrl;
	}
	
	
	/**无参构造器*/
	public ExcelDataRoleAndMenu() {}
	
}