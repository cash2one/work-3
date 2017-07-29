package com.trust.privilege.model.complex.excel;

import java.util.List;

/***
 * @ClassName: ExcelDataClient 
 * @Description: 用户导入的Excel表格数据，非初始化表格数据
 * @author zyn161616@163.com
 * @version 创建时间：2016年12月28日 下午3:05:20
 */
public class ExcelDataClient {
	private String SYSTEM_USER_ID;		//用户ID
	private String User_Name;			//用户名
	private String ID_Card;				//身份证号
	private String User_Desc;			//用户身份的描述（普通用户，超级管理员）
	private String Create_User_Id;		//创建人ID
	private List<String> Role_Name; 	//角色名称,角色和组是多对多的关系
	private List<String> Group_Name;	//当前组名称，角色和组是多对多的关系
	
	//公有属性的取值赋值方法
	public String getUser_Name() {
		return User_Name;
	}
	public void setUser_Name(String user_Name) {
		User_Name = user_Name;
	}
	public String getID_Card() {
		return ID_Card;
	}
	public void setID_Card(String iD_Card) {
		ID_Card = iD_Card;
	}
	public String getUser_Desc() {
		return User_Desc;
	}
	public void setUser_Desc(String user_Desc) {
		User_Desc = user_Desc;
	}
	
	public List<String> getRole_Name() {
		return Role_Name;
	}
	public void setRole_Name(List<String> role_Name) {
		Role_Name = role_Name;
	}
	public List<String> getGroup_Name() {
		return Group_Name;
	}
	public void setGroup_Name(List<String> group_Name) {
		Group_Name = group_Name;
	}
	public String getSYSTEM_USER_ID() {
		return SYSTEM_USER_ID;
	}
	public void setSYSTEM_USER_ID(String sYSTEM_USER_ID) {
		SYSTEM_USER_ID = sYSTEM_USER_ID;
	}
	
	public String getCreate_User_Id() {
		return Create_User_Id;
	}
	public void setCreate_User_Id(String create_User_Id) {
		Create_User_Id = create_User_Id;
	}
	
	//重写toString方法
	@Override
	public String toString() {
		return "UserModel [SYSTEM_USER_ID=" + SYSTEM_USER_ID + ", User_Name=" + User_Name + ", ID_Card=" + ID_Card
				+ ", User_Desc=" + User_Desc + ", Create_User_Id=" + Create_User_Id + ", Role_Name=" + Role_Name
				+ ", Group_Name=" + Group_Name + "]";
	}
	
	//无参构造器
	public ExcelDataClient() {}
	
	//有参构造器
	public ExcelDataClient(String sYSTEM_USER_ID, String user_Name, String iD_Card, String user_Desc, List<String> role_Name,
			List<String> group_Name) {
		SYSTEM_USER_ID = sYSTEM_USER_ID;
		User_Name = user_Name;
		ID_Card = iD_Card;
		User_Desc = user_Desc;
		Role_Name = role_Name;
		Group_Name = group_Name;
	}
	
}
