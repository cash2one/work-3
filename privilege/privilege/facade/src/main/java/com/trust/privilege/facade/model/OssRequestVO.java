package com.trust.privilege.facade.model;
/***
 * @ClassName: OssRequestVO 
 * @Description: 请求OSS服务器的vo
 * @author zyn161616@163.com
 * @version 创建时间：2017年1月20日 下午5:53:42
 */
public class OssRequestVO {
	
	//上传该资源的应用名
	private String resFromAppName;
	//有权限访问资源的应用名
	private String resAccessAppName;
	//资源名称
	private String resName;
	//资源类型
	private String resType;
	//操作人
	private String userName ;
	
	
	/**公有属性的取值赋值方法*/
	public String getResAccessAppName() {
		return resAccessAppName;
	}
	public void setResAccessAppName(String resAccessAppName) {
		this.resAccessAppName = resAccessAppName;
	}
	public String getResFromAppName() {
		return resFromAppName;
	}
	public void setResFromAppName(String resFromAppName) {
		this.resFromAppName = resFromAppName;
	}
	public String getResName() {
		return resName;
	}
	public void setResName(String resName) {
		this.resName = resName;
	}
	public String getResType() {
		return resType;
	}
	public void setResType(String resType) {
		this.resType = resType;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**无参构造器*/
	public OssRequestVO() {}
	
}
