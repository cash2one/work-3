package com.trust.privilege.model.complex;
/***
 * @ClassName: PortalInitDo 
 * @Description: 门户需要初始化的model
 * @author zyn161616@163.com
 * @version 创建时间：2017年1月19日 上午11:19:38
 */
public class PortalInitDo {
	//定义的系统id
	private String menuId;
	//各系统名称
	private String systemName;
	//各系统跳转首页的url
	private String systemUrl;
	//访问方式
	private String requestMethod;
	
	/**公有属性的取值赋值方法*/
	public String getSystemName() {
		return systemName;
	}
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}
	public String getSystemUrl() {
		return systemUrl;
	}
	public void setSystemUrl(String systemUrl) {
		this.systemUrl = systemUrl;
	}
	public String getRequestMethod() {
		return requestMethod;
	}
	public void setRequestMethod(String requestMethod) {
		this.requestMethod = requestMethod;
	}
	public PortalInitDo() {}
	
}
