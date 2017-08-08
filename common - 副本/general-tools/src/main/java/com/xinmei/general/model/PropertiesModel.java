package com.xinmei.general.model;

public class PropertiesModel {


	//流水号扩展编码 6位	默认值"0000000"
	private  String extraCd="0000000";
	//流水号编码版本 1位  默认值"0"
	 private String version="0";  
	//流水号应用服务编码	3位	默认值"000"
	 private String appSysId="000"; 
	//流水号应用部署序号	3位	默认值"000"
	 private String appDisId="000"; 
	//流水号应用ECS编号	2位	默认值"00"
	 private String ecsId="00";	
	//流水号分表编号	2位	默认值"00"
	 private String tblId="00";


	public String getExtraCd() {
		return extraCd;
	}
	public void setExtraCd(String extraCd) {
		this.extraCd = extraCd;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getAppSysId() {
		return appSysId;
	}
	public void setAppSysId(String appSysId) {
		this.appSysId = appSysId;
	}
	public String getAppDisId() {
		return appDisId;
	}
	public void setAppDisId(String appDisId) {
		this.appDisId = appDisId;
	}
	public String getEcsId() {
		return ecsId;
	}
	public void setEcsId(String ecsId) {
		this.ecsId = ecsId;
	}
	public String getTblId() {
		return tblId;
	}
	public void setTblId(String tblId) {
		this.tblId = tblId;
	}	
	 
}
